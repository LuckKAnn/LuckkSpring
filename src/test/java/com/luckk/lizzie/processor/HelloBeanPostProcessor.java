package com.luckk.lizzie.processor;

import com.luckk.lizzie.bean.UserService03;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/17 22:40
 * @PackageName: com.luckk.lizzie.processor
 * @ClassName: HelloBeanPostProcessor
 * @Version 1.0
 */
@Slf4j
public class HelloBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        log.info("HelloBeanPostProcessor postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        log.info("HelloBeanPostProcessor postProcessAfterInitialization");
        if (beanName.equals("userService") && bean instanceof UserService03) {
            ((UserService03) bean).setName("helloProcessorChange");
        }

        return bean;
    }
}
