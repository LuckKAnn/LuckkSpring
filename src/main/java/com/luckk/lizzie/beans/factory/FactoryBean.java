package com.luckk.lizzie.beans.factory;


/**
 * @Author liukun.inspire
 * @Date 2023/12/20 09:55
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: FactoryBean
 * @Version 1.0
 */
public interface FactoryBean<T> {

    /**
     * 创建具体到对象
     *
     * @return T类型对象
     * @throws BeansException
     */
    T getObject() throws BeansException;


    Class<T> getObjectType();


    /**
     * flag represents whether the getObject() return a singleton object
     *
     * @return flag
     */
    boolean isSingleton();
}
