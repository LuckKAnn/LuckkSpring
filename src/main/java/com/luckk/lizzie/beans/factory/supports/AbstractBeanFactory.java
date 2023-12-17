package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.beans.factory.BeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.beans.factory.factory.ConfigurableBeanFactory;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过继承获得单例bean的注册能力
 * 不具备获取beandefinition和创建bean的能力，将其交给下游来具体实现
 *
 * @Author liukun.inspire
 * @Date 2023/12/13 13:48
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: AbstractBeanFactory
 * @Version 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {


    private List<BeanPostProcessor> beanPostProcessorChain = new ArrayList<>();

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
        return null;
    }

    protected <T> T doGetBean(String name, Object... args) {
        Object singleton = getSingleton(name);
        if (null != singleton) {
            return (T) singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
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
}
