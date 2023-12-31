package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.beans.factory.BeanFactory;
import com.luckk.lizzie.beans.factory.FactoryBean;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.beans.factory.factory.ConfigurableBeanFactory;
import com.luckk.lizzie.util.StringValueResolver;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过继承获得单例bean的注册能力
 * 不具备获取beandefinition和创建bean的能力，将其交给下游来具体实现
 * <p>
 * 直接实现BeanDefinitionRegistry的接口，让下游具体来实现不是更好吗
 * 但是这个本身应该不能算是一一种档案馆
 *
 * @Author liukun.inspire
 * @Date 2023/12/13 13:48
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: AbstractBeanFactory
 * @Version 1.0
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements BeanDefinitionRegistry, ConfigurableBeanFactory {
    private List<BeanPostProcessor> beanPostProcessorChain = new ArrayList<>();

    private List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredBeanType) {
        return doGetBean(name, null);
    }

    protected <T> T doGetBean(String name, Object... args) {
        Object singleton = getSingleton(name);
        if (null != singleton) {
            return (T) getObjectForBeanInstance(singleton, name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        singleton = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(singleton, name);
    }

    protected Object getObjectForBeanInstance(Object bean, String beanName) {
        if (!(bean instanceof FactoryBean)) {
            return bean;
        }
        // 如果是FactoryBean？
        // if singleton
        FactoryBean<?> factoryBean = (FactoryBean<?>) bean;
        Object cachedObjectForFactoryBean = getCachedObjectForFactoryBean(beanName);
        if (cachedObjectForFactoryBean == null) {
            cachedObjectForFactoryBean = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return cachedObjectForFactoryBean;
    }

    /**
     * 这个创建bean为什么不放到BeanFactory的接口里面去呢
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    public abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    public List<BeanPostProcessor> getBeanPostProcessorChain() {
        return beanPostProcessorChain;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorChain.add(beanPostProcessor);
    }

    public void addStringValueResolver(StringValueResolver resolver) {
        this.embeddedValueResolvers.add(resolver);
    }

    public String stringResolve(String str) {
        for (StringValueResolver resolver : embeddedValueResolvers) {
            String resolveStringValue = resolver.resolveStringValue(str);
            if (StringUtils.isNotEmpty(resolveStringValue)) {
                return resolveStringValue;
            }
        }
        return null;
    }

}
