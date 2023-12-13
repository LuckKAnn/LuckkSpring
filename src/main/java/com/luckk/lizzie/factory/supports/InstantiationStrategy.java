package com.luckk.lizzie.factory.supports;

import com.luckk.lizzie.factory.factory.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 14:53
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: InstantiationStrategy
 * @Version 1.0
 */
public interface InstantiationStrategy {

    /**
     * 实例化
     *
     * @param beanDefinition
     * @param beanName
     * @param ctor
     * @param args
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
