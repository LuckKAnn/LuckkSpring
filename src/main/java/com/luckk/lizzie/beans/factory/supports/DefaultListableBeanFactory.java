package com.luckk.lizzie.beans.factory.supports;

import com.google.common.collect.Maps;
import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.ConfigurableListableBeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * this is the storage of beandefinition map
 *
 * @Author liukun.inspire
 * @Date 2023/12/13 13:56
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: DefaultListableBeanFactoryt
 * @Version 1.0
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap;

    public DefaultListableBeanFactory() {
        beanDefinitionMap = new ConcurrentHashMap<>();
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansByType(Class<T> beanType) {

        HashMap<String, T> resultMap = Maps.newHashMap();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            if (beanType.isAssignableFrom(entry.getValue().getBeanClass())) {
                resultMap.put(entry.getKey(), getBean(entry.getKey(), beanType));
            }
        }
        return resultMap;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }


}
