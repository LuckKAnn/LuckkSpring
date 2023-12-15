package com.luckk.lizzie.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/14 12:21
 * @PackageName: com.luckk.lizzie.factory
 * @ClassName: PropertyValue
 * @Version 1.0
 */
public class PropertyValue {

    private String propertyName;

    private Object propertyValue;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }
}
