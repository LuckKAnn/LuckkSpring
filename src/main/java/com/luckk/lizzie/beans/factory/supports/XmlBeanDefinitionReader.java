package com.luckk.lizzie.beans.factory.supports;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.PropertyValue;
import com.luckk.lizzie.beans.factory.PropertyValues;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanReference;
import com.luckk.lizzie.beans.factory.factory.ConfigurableBeanFactory;
import com.luckk.lizzie.context.annotation.ClassPathBeanDefinitionScanner;
import com.luckk.lizzie.context.annotation.ClassPathScanningCandidateComponentProvider;
import com.luckk.lizzie.core.io.Resource;
import com.luckk.lizzie.core.io.ResourceLoader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 15:38
 * @PackageName: com.luckk.lizzie.beans.factory.supports
 * @ClassName: XmlBeanDefinitionReader
 * @Version 1.0
 */
@Slf4j
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    /**
     * 扫描路径
     */
    private String baseScanPackage;


    private ClassPathBeanDefinitionScanner provider = new ClassPathBeanDefinitionScanner(getRegistry());

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinition(Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            // inputStream 转换为对应字符和string
            // 转换为document
            String xmlString = convertInputStreamToString(inputStream);
            Document document = XmlUtil.readXML(xmlString);
            parseAndRegisterBeanDefinition(document);
        } catch (Exception e) {
            log.error("parse and register bean definition failed", e);
            throw new BeansException();
        }
    }

    private String convertInputStreamToString(InputStream stream) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("read input stream to string failed ");
            throw new BeansException("read input stream to string failed ");
        }

        return sb.toString();
    }

    private void parseAndRegisterBeanDefinition(Document document) throws ClassNotFoundException {
        Element rootElement = XmlUtil.getRootElement(document);
        parseAndSetScanPackage(rootElement);
        // 所有的bean定义
        List<Element> beans = XmlUtil.getElements(rootElement, "bean");

        for (Element beanElement : beans) {
            String beanClazz = beanElement.getAttribute("class");
            Class<?> clazz = Class.forName(beanClazz);
            String beanName = StringUtils.isEmpty(beanElement.getAttribute("id")) ? StrUtil.lowerFirst(clazz.getSimpleName()) : beanElement.getAttribute("id");
            String initMethod = beanElement.getAttribute("init-method");
            String destroyMethod = beanElement.getAttribute("destroy-method");
            String type = beanElement.getAttribute("scope");

            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClass(clazz);
            if (StringUtils.isNotEmpty(initMethod)) {
                beanDefinition.setInitMethod(initMethod);
            }
            if (StringUtils.isNotEmpty(destroyMethod)) {
                beanDefinition.setDestroyMethod(destroyMethod);
            }
            if (StringUtils.isNotEmpty(type)) {
                beanDefinition.setScope(type);
            }
            if (StringUtils.isEmpty(type)) {
                beanDefinition.setScope(BeanDefinition.SINGLETON);
            }
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

            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void parseAndSetScanPackage(Element rootElement) {
        Element element = XmlUtil.getElement(rootElement, "context:component-scan");
        if (element == null) {
            return;
        }
        String value = element.getAttribute("base-package");
        this.baseScanPackage = value;
        provider.doScan(baseScanPackage);
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {

    }

    @Override
    public void loadBeanDefinitions(String location) {
        Resource resource = getResourceLoader().loadResource(location);
        loadBeanDefinition(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    public String getBaseScanPackage() {
        return baseScanPackage;
    }
}
