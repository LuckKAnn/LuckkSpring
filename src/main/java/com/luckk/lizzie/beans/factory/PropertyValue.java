package com.luckk.lizzie.beans.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/14 12:21
 * @PackageName: com.luckk.lizzie.factory
 * @ClassName: PropertyValue
 * @Version 1.0
 */
public class PropertyValue {

    public String propertyName;

    public Object propertyValue;

    public PropertyValue() {
    }

    public PropertyValue(String propertyName, Object propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @param propertyName to set
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    /**
     * @param propertyValue to set
     */
    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }
}
