package com.luckk.lizzie.processor;

import com.luckk.lizzie.beans.factory.ConfigurableListableBeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanFactoryPostProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/17 22:40
 * @PackageName: com.luckk.lizzie.processor
 * @ClassName: HelloBeanFactoryProcessor
 * @Version 1.0
 */
@Slf4j
public class HelloBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableBeanFactory) {
        log.warn("HelloBeanFactoryProcessor postProcessBeanFactory");
    }
}
