package com.luckk.lizzie.beans.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 12:25
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: BeanNameAware
 * @Version 1.0
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
