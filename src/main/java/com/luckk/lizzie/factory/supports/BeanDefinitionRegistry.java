package com.luckk.lizzie.factory.supports;

import com.luckk.lizzie.factory.factory.BeanDefinition;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:55
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: BeanDefinitionRegistry
 * @Version 1.0
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);
}
