package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 14:53
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: SimpleInstantiationStrategy
 * @Version 1.0
 */
@Slf4j
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("Failed to instantiate [" + clazz.getName() + "]", e);
            throw new BeansException();
        }
    }
}
