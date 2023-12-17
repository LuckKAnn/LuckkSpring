package com.luckk.lizzie.beans.factory.factory;

import com.luckk.lizzie.beans.factory.HierarchicalBeanFactory;
import com.luckk.lizzie.beans.factory.supports.BeanDefinitionRegistry;
import com.luckk.lizzie.beans.factory.supports.DefaultSingletonBeanRegistry;
import com.sun.jndi.rmi.registry.RegistryContextFactory;

/**
 * 可获取 BeanPostProcessor、BeanClassLoader的一个配置化接口。
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 16:40
 * @PackageName: com.luckk.lizzie.beans.factory.factory
 * @ClassName: ConfigurableBeanFactory
 * @Version 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, BeanDefinitionRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
