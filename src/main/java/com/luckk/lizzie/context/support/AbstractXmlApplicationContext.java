package com.luckk.lizzie.context.support;

import com.luckk.lizzie.beans.factory.factory.BeanFactoryPostProcessor;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.beans.factory.supports.DefaultListableBeanFactory;
import com.luckk.lizzie.beans.factory.supports.XmlBeanDefinitionReader;
import com.luckk.lizzie.core.io.Resource;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 18:37
 * @PackageName: com.luckk.lizzie.context.support
 * @ClassName: AbstractXmlApplicationContext
 * @Version 1.0
 */

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    private XmlBeanDefinitionReader xmlBeanDefinitionReader;

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);

        String[] configLocations = getConfigLocations();
        if (configLocations != null){
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
