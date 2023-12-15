package com.luckk.lizzie.factory.io;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.XmlUtil;
import com.luckk.lizzie.factory.PropertyValue;
import com.luckk.lizzie.factory.PropertyValues;
import com.luckk.lizzie.factory.factory.BeanDefinition;
import com.luckk.lizzie.factory.factory.BeanReference;
import com.luckk.lizzie.factory.supports.DefaultListableBeanFactory;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 14:47
 * @PackageName: com.luckk.lizzie.factory.io
 * @ClassName: BeanDefinitionLoader
 * @Version 1.0
 */
// 读取Bean定义
public class BeanDefinitionLoader {
    // 在这里面做具体的XMl的读取的工作吗

    ResourceLoader resourceLoader;

    DefaultListableBeanFactory factory;

    public BeanDefinitionLoader(ResourceLoader resourceLoader, DefaultListableBeanFactory factory) {
        this.resourceLoader = resourceLoader;
        this.factory = factory;
    }

    public BeanDefinitionLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void loadBeanDefinition() throws ClassNotFoundException {
        XmlResource resource = (XmlResource) resourceLoader.loadResource();
        Document document = resource.getDocument();

        Element rootElement = XmlUtil.getRootElement(document);
        // 所有的bean定义
        List<Element> beans = XmlUtil.getElements(rootElement, "bean");

        for (Element beanElement : beans) {
            String beanClazz = beanElement.getAttribute("class");
            String beanName = beanElement.getAttribute("id");
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClass(Class.forName(beanClazz));
            PropertyValues propertyValues = new PropertyValues();

            beanDefinition.setPropertyValues(propertyValues);
            List<Element> propertys = XmlUtil.getElements(beanElement, "property");
            if (CollectionUtil.isNotEmpty(propertys)) {
                for (Element element : propertys) {
                    String name = element.getAttribute("name");
                    String value = element.getAttribute("value");
                    if (StringUtils.isNotEmpty(value)) {
                        PropertyValue propertyValue = new PropertyValue();
                        propertyValue.setPropertyName(name);
                        propertyValue.setPropertyValue(value);
                        propertyValues.addPropertyValue(propertyValue);
                        continue;
                    }
                    String ref = element.getAttribute("ref");
                    PropertyValue propertyValue = new PropertyValue();
                    propertyValue.setPropertyName(name);
                    propertyValue.setPropertyValue(new BeanReference(ref));
                    propertyValues.addPropertyValue(propertyValue);
                }
            }

            factory.registerBeanDefinition(beanName, beanDefinition);
        }
    }
}
