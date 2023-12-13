package com.luckk.lizzie.factory.factory;

/**
 * a bean definition do not store the real bean?
 *
 * @Author liukun.inspire
 * @Date 2023/12/13 13:46
 * @PackageName: com.luckk.lizzie.factory.factory
 * @ClassName: BeanDefinition
 * @Version 1.0
 */
public class BeanDefinition {

    private String beanType;

    private Class beanClass;


    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
