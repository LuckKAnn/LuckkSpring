package com.luckk.lizzie.beans.factory.supports;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.PropertyValue;
import com.luckk.lizzie.beans.factory.PropertyValues;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanReference;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
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
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    InstantiationStrategy instantiationStrategy;

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
            o = createBeanInstance(beanName, beanDefinition, args);
            // set pv
            applyPropertyValue(beanName, o, beanDefinition);
        } catch (Exception e) {
            log.error("create bean instance by beanDefinition fail", e);
            throw new BeansException();
        }
        addSingleton(beanName, o);
        return o;
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
}
