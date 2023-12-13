package com.luckk.lizzie.bean;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:13
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: BeanDefinition
 * @Version 1.0
 */
public class BeanDefinition {

    private String beanName;

    private Object bean;

    private String beanType;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }
}
