package com.luckk.lizzie.context.support;

import com.luckk.lizzie.beans.factory.factory.BeanFactoryPostProcessor;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;

import java.util.List;
import java.util.Map;

/**
 * 专门针对类路径下XML加载的应用上下文
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 18:01
 * @PackageName: com.luckk.lizzie.context.support
 * @ClassName: ClassPathXmlApplicationContext
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    String[] configLocations;

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredBeanType) {
        return getBeanFactory().getBean(name, requiredBeanType);
    }

    @Override
    public <T> Map<String, T> getBeansByType(Class<T> beanType) {
        return getBeanFactory().getBeansByType(beanType);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

}
