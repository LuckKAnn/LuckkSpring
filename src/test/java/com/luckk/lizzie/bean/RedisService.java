package com.luckk.lizzie.bean;

import com.luckk.lizzie.beans.factory.BeanFactory;
import com.luckk.lizzie.beans.factory.BeanFactoryAware;
import com.luckk.lizzie.beans.factory.BeanNameAware;
import com.luckk.lizzie.context.ApplicationContext;
import com.luckk.lizzie.context.ApplicationContextAware;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 09:13
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: RedisService
 * @Version 1.0
 */
@Slf4j
public class RedisService implements ApplicationContextAware, BeanNameAware, BeanFactoryAware {

    private String beanName;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void queryData() {
        log.info("get data from redis .....");
    }


    public String getBeanName() {
        return beanName;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
