package com.luckk.lizzie.beans.factory.factory;

import com.luckk.lizzie.beans.factory.BeansException;

/**
 * @Author liukun.inspire
 * @Date 2023/12/24 21:41
 * @PackageName: com.luckk.lizzie.beans.factory.factory
 * @ClassName: InstantiationAwareBeanPostProcessor
 * @Version 1.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {


    /**
     * 实例化之前进行处理
     *
     * @param beanClass
     * @param beanName
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
