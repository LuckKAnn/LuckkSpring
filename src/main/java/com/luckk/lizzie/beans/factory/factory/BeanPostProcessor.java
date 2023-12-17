package com.luckk.lizzie.beans.factory.factory;

/**
 * Allows for custom modification of an application context's bean definitions,
 * adapting the bean property values of the context's underlying bean factory
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 18:00
 * @PackageName: com.luckk.lizzie.beans.factory.factory
 * @ClassName: BeanPostProcessor
 * @Version 1.0
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);


    Object postProcessAfterInitialization(Object bean, String beanName);
}
