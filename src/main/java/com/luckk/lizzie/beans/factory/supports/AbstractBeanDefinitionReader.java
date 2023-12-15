package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.core.io.ResourceLoader;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 15:37
 * @PackageName: com.luckk.lizzie.beans.factory.supports
 * @ClassName: AbstractBeanDefinitionReader
 * @Version 1.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private BeanDefinitionRegistry beanDefinitionRegistry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
