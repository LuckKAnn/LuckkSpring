package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.core.io.Resource;
import com.luckk.lizzie.core.io.ResourceLoader;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 15:36
 * @PackageName: com.luckk.lizzie.beans.factory.supports
 * @ClassName: BeanDefinitionReader
 * @Version 1.0
 */
public interface BeanDefinitionReader {


    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String... location);

}
