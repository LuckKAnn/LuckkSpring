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

    public static final String SINGLETON = ConfigurableBeanFactory.SINGLETON;
    public static final String PROTOTYPE = ConfigurableBeanFactory.PROTOTYPE;

    /**
     * 变量作用域
     */
    private String scope;

    private Class beanClass;

    private PropertyValues propertyValues = new PropertyValues();

    /**
     * 初始化方法
     */
    private String initMethod;

    /**
     * 销毁方法
     */
    private String destroyMethod;

    public BeanDefinition() {
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public BeanDefinition(String scope, Class beanClass, PropertyValues propertyValues) {
        this.scope = scope;
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public boolean isSingleton() {
        return SINGLETON.equals(scope);
    }
    public boolean isPrototype() {
        return PROTOTYPE.equals(scope);
    }

    public String getScope() {
        return scope;
    }

    /**
     * @param scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
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

    public String getInitMethod() {
        return initMethod;
    }

    /**
     * @param initMethod to set
     */
    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getDestroyMethod() {
        return destroyMethod;
    }

    /**
     * @param destroyMethod to set
     */
    public void setDestroyMethod(String destroyMethod) {
        this.destroyMethod = destroyMethod;
    }
}
