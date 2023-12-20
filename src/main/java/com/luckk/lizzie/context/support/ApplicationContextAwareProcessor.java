package com.luckk.lizzie.context.support;

import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.context.ApplicationContext;
import com.luckk.lizzie.context.ApplicationContextAware;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 12:25
 * @PackageName: com.luckk.lizzie.context.support
 * @ClassName: ApplicationContextAwareProcessor
 * @Version 1.0
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (!(bean instanceof ApplicationContextAware)) {
            return bean;
        }
        ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
