package com.luckk.lizzie.beans.factory.factory;

import com.luckk.lizzie.beans.factory.HierarchicalBeanFactory;
import com.luckk.lizzie.beans.factory.supports.BeanDefinitionRegistry;
import com.luckk.lizzie.beans.factory.supports.DefaultSingletonBeanRegistry;
import com.sun.jndi.rmi.registry.RegistryContextFactory;

/**
 * 可获取 BeanPostProcessor、BeanClassLoader的一个配置化接口。
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link com.luckk.lizzie.beans.factory.BeanFactory}
 * interface.
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 16:40
 * @PackageName: com.luckk.lizzie.beans.factory.factory
 * @ClassName: ConfigurableBeanFactory
 * @Version 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    void destroySingletons() throws Exception;
}
