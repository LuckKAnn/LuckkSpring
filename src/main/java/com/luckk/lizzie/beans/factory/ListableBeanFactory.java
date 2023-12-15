package com.luckk.lizzie.beans.factory;

import java.util.Map;

/**
 * 是一个扩展 Bean 工厂接口的接口
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 16:39
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: ListableBeanFactory
 * @Version 1.0
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照Bean类型返回bean实例
     *
     * @param beanType
     * @param <T>
     * @return
     */
    <T> Map<String, T> getBeansByType(Class<T> beanType);


    /**
     * get all bean definition names
     *
     * @return arr
     */
    String[] getBeanDefinitionNames();
}
