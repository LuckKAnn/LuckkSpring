package com.luckk.lizzie.factory;

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

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void addPropertyValue(PropertyValue pv) {
        propertyValues.add(pv);
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
