package com.luckk.lizzie.beans.factory.factory;

import com.luckk.lizzie.beans.factory.BeansException;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:47
 * @PackageName: com.luckk.lizzie.factory.factory
 * @ClassName: SingletonBeanRegistry
 * @Version 1.0
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
