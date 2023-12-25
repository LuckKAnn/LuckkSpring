package com.luckk.lizzie.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/14 12:22
 * @PackageName: com.luckk.lizzie.factory
 * @ClassName: PropertyValues
 * @Version 1.0
 */
public class PropertyValues {

    private List<PropertyValue> propertyValues;

    public PropertyValues() {
        propertyValues = new ArrayList<>();
    }

    public PropertyValue[] getPropertyValues() {
        // return propertyValues;
        // 这里相当于返回一个副本了
        // 可以避免潜在的修改BD导致的并发修改一场
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    public void addPropertyValue(PropertyValue pv) {
        propertyValues.add(pv);
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
