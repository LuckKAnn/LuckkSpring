package com.luckk.lizzie.factory;

import com.luckk.lizzie.bean.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:23
 * @PackageName: com.luckk.lizzie.factory
 * @ClassName: BeanFactory
 * @Version 1.0
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap;


    public BeanFactory() {
        beanDefinitionMap = new HashMap<>();
    }

    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (null == beanDefinition || null == beanDefinition.getBean()) {
            return null;
        }
        return beanDefinition.getBean();
    }


    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
