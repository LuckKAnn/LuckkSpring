package com.luckk.lizzie.beans.factory;

import com.luckk.lizzie.beans.factory.factory.AutowireCapableBeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean以及预先实例化的操作接口，
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 16:39
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: ConfigurableListableBeanFactory
 * @Version 1.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
