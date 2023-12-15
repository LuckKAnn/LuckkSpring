package com.luckk.lizzie.factory.factory;

/**
 *
 * bean 引用
 * @Author liukun.inspire
 * @Date 2023/12/15 14:16
 * @PackageName: com.luckk.lizzie.factory.factory
 * @ClassName: BeanReference
 * @Version 1.0
 */
public class BeanReference {

    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public BeanReference() {
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
