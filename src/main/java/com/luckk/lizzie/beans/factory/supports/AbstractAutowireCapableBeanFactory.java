package com.luckk.lizzie.beans.factory.supports;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.luckk.lizzie.beans.factory.BeanClassLoaderAware;
import com.luckk.lizzie.beans.factory.BeanFactoryAware;
import com.luckk.lizzie.beans.factory.BeanNameAware;
import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.DisposableBean;
import com.luckk.lizzie.beans.factory.FactoryBean;
import com.luckk.lizzie.beans.factory.InitializingBean;
import com.luckk.lizzie.beans.factory.PropertyValue;
import com.luckk.lizzie.beans.factory.PropertyValues;
import com.luckk.lizzie.beans.factory.factory.AutowireCapableBeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.beans.factory.factory.BeanReference;
import com.luckk.lizzie.beans.factory.factory.InstantiationAwareBeanPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 本质上也就是具备了创建Bean的能力的beanFactory
 *
 * @Author liukun.inspire
 * @Date 2023/12/13 13:53
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: AbstractAutowireCapableBeanFactory
 * @Version 1.0
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy;

    public AbstractAutowireCapableBeanFactory() {
        this.instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    }

    public AbstractAutowireCapableBeanFactory(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 日志记录Logger
     */
    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        // 这里完成的是创建bean的工作吗
        // 创建bean需要什么呢？
        Object o = null;
        try {
            // 执行对应的处理
            Object proxyBean = applyBeanPostProcessorBeforeInstantiation(beanName, null, beanDefinition);
            if (proxyBean != null) {
                return proxyBean;
            }
            o = createBeanInstance(beanName, beanDefinition, args);
            // set pv
            applyPropertyValue(beanName, o, beanDefinition);

            o = initializeBean(beanName, o, beanDefinition);
        } catch (Exception e) {
            log.error("create bean instance by beanDefinition fail, beanName:{}", beanName, e);
            throw new BeansException();
        }

        registerDisposableBeanIfNecessary(o, beanName, beanDefinition);
        // 只有单例类型才被假如到单例池
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, o);
        }
        return o;
    }

    protected void registerDisposableBeanIfNecessary(Object bean, String beanName, BeanDefinition beanDefinition) {
        // 非单例方法没有必要
        if (beanDefinition.isSingleton()) {
            return;
        }
        // 如果有必要，添加注册Disposable
        if (StringUtils.isNotEmpty(beanDefinition.getDestroyMethod()) || bean instanceof DisposableBean) {
            addDisposableBean(bean, beanName, beanDefinition);
        }
    }

    private Object applyBeanPostProcessorBeforeInstantiation(String beanName, Object o, BeanDefinition beanDefinition) {
        if (CollectionUtil.isEmpty(getBeanPostProcessorChain())) {
            return null;
        }
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorChain()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                return instantiationAwareBeanPostProcessor.postProcessBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
            }
        }
        return null;
    }

    private Object initializeBean(String beanName, Object o, BeanDefinition beanDefinition) {
        // 这一步其实是执行初始化的步骤啊
        Object wrapperBean = applyBeanPostProcessorBeforeInitialization(o, beanName);

        // 执行初始化的方法和内容
        // 可能包括InitializingBean 的方法和@PostConstruct之类的方法
        invokeInitMethods(beanName, wrapperBean, beanDefinition);

        wrapperBean = applyBeanPostProcessorAfterInitialization(o, beanName);
        return wrapperBean;
    }

    private void invokeInitMethods(String beanName, Object wrapperBean, BeanDefinition beanDefinition) {

        invokeAwareMethods(beanName, wrapperBean, beanDefinition);
        // 执行接口方法
        if (wrapperBean instanceof InitializingBean) {
            ((InitializingBean) wrapperBean).afterPropertiesSet();
        }
        if (StringUtils.isNotEmpty(beanDefinition.getInitMethod())) {
            // 反射调用initMethod
            Class<?> clazz = beanDefinition.getBeanClass();
            try {
                Method declaredMethod = clazz.getDeclaredMethod(beanDefinition.getInitMethod(), (Class<?>[]) null);
                declaredMethod.invoke(wrapperBean, (Object[]) null);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error("can not find init method for bean :{}", beanName);
                throw new BeansException();
            }
        }

    }

    /**
     * set aware
     *
     * @param beanName
     * @param wrapperBean
     * @param beanDefinition
     */
    private void invokeAwareMethods(String beanName, Object wrapperBean, BeanDefinition beanDefinition) {

        if (wrapperBean instanceof BeanNameAware) {
            ((BeanNameAware) wrapperBean).setBeanName(beanName);
        }
        if (wrapperBean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) wrapperBean).setBeanFactory(this);
        }
        if (wrapperBean instanceof BeanClassLoaderAware) {
            ((BeanClassLoaderAware) wrapperBean).setBeanClassLoader(this.getClass().getClassLoader());
        }
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {

        Constructor<?>[] declaredConstructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Constructor chooseConstructors = null;
        for (Constructor cotr : declaredConstructors) {
            if (args != null && cotr.getParameterTypes().length == args.length) {
                chooseConstructors = cotr;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, chooseConstructors, args);

    }

    protected void applyPropertyValue(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (null == propertyValues || CollectionUtil.isEmpty(propertyValues.getPropertyValues())) {
            return;
        }
        List<PropertyValue> pvList = propertyValues.getPropertyValues();
        for (PropertyValue pv : pvList) {

            String propertyName = pv.getPropertyName();
            Object propertyValue = pv.getPropertyValue();

            if (propertyValue instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) propertyValue;
                propertyValue = getBean(beanReference.getBeanName());
            }
            // set the property name
            BeanUtil.setFieldValue(bean, propertyName, propertyValue);

            // under is a way by refelect

            // Class<?> beanClazz = bean.getClass();
            // try {
            //     Field declaredField = beanClazz.getDeclaredField(pv.getPropertyName());
            //     declaredField.setAccessible(true);
            //     declaredField.set(bean, pv.getPropertyValue());
            // } catch (NoSuchFieldException e) {
            //     throw new BeansException(String.format("Target Bean has no property name for : %s", pv.getPropertyName()));
            // } catch (IllegalAccessException e) {
            //     throw new BeansException("Target Bean set property value failed");
            // }
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName) {
        if (CollectionUtil.isEmpty(getBeanPostProcessorChain())) {
            return bean;
        }
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorChain()) {
            Object processedBean = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            if (processedBean == null) {
                return result;
            }
            result = processedBean;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName) {
        if (CollectionUtil.isEmpty(getBeanPostProcessorChain())) {
            return bean;
        }
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorChain()) {
            Object processedBean = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            if (processedBean == null) {
                return result;
            }
            result = processedBean;
        }
        return result;
    }
}
