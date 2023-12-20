package com.luckk.lizzie.beans.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 12:24
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: BeanFactoryAware
 * @Version 1.0
 */
public interface BeanFactoryAware extends Aware {


    void setBeanFactory(BeanFactory beanFactory);


}
