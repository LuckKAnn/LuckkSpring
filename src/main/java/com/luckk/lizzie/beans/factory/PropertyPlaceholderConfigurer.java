package com.luckk.lizzie.beans.factory;

import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanFactoryPostProcessor;
import com.luckk.lizzie.beans.factory.supports.AbstractBeanFactory;
import com.luckk.lizzie.core.io.DefaultResourceLoader;
import com.luckk.lizzie.core.io.Resource;
import com.luckk.lizzie.util.StringValueResolver;
import lombok.var;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 11:40
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: PropertyPlaceholderConfigurer
 * @Version 1.0
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor, StringValueResolver {
    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    private Properties properties = new Properties();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.loadResource(location);
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getPropertyValue();
                    if (!(value instanceof String)) continue;
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIdx, stopIdx + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getPropertyName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }

        AbstractBeanFactory abstractBeanFactory = (AbstractBeanFactory) beanFactory;
        abstractBeanFactory.addStringValueResolver(this);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String resolveStringValue(String strVal) {
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
            return buffer.toString();
        }
        return null;
    }
}
