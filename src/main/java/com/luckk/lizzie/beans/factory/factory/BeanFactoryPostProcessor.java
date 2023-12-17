package com.luckk.lizzie.beans.factory.factory;

import com.luckk.lizzie.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 18:00
 * @PackageName: com.luckk.lizzie.beans.factory.factory
 * @ClassName: BeanFactoryPostProcessor
 * @Version 1.0
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory configurableBeanFactory);
}
