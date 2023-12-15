package com.luckk.lizzie.beans.factory.supports;

import cn.hutool.core.collection.CollectionUtil;
import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;

import java.util.Collections;
import java.util.List;
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
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

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
    public List<String> getBeanDefinitionNames() {
        return CollectionUtil.newArrayList(beanDefinitionMap.keySet());
    }


}
