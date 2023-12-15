package com.luckk.lizzie.beans.factory.factory;

import com.luckk.lizzie.beans.factory.PropertyValues;

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

    private PropertyValues propertyValues;

    public BeanDefinition() {
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public BeanDefinition(String beanType, Class beanClass, PropertyValues propertyValues) {
        this.beanType = beanType;
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

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

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}