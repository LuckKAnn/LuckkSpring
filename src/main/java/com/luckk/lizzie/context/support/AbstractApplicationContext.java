package com.luckk.lizzie.context.support;

import cn.hutool.core.collection.CollectionUtil;
import com.luckk.lizzie.beans.factory.ConfigurableListableBeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanFactoryPostProcessor;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.beans.factory.supports.DefaultListableBeanFactory;
import com.luckk.lizzie.context.ApplicationContext;
import com.luckk.lizzie.context.ApplicationEvent;
import com.luckk.lizzie.context.ApplicationListener;
import com.luckk.lizzie.context.ConfigurableApplicationContext;
import com.luckk.lizzie.context.event.ApplicationEventMulticaster;
import com.luckk.lizzie.context.event.ContextClosedEvent;
import com.luckk.lizzie.context.event.ContextRefreshedEvent;
import com.luckk.lizzie.context.event.SimpleApplicationEventMulticaster;
import com.luckk.lizzie.core.io.DefaultResourceLoader;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

/**
 * 抽象AbstractContext本身也是默认的资源加载器吗
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 18:02
 * @PackageName: com.luckk.lizzie.context.support
 * @ClassName: AbstractApplicationContext
 * @Version 1.0
 */
@Slf4j
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";


    private ApplicationEventMulticaster eventMulticaster;

    /**
     * 为什么要这么抽象呢？
     * 应用上下文，负责管理和刷新吗
     * 难道不应该是一个BeanFactory吗，增加了额外的功能
     */
    @Override
    public void refresh() {
        // 刷新容器，并不是，那么这个完成的是什么任务呢？
        // 容器刷新，当前完成的任务是，创建容器，加载bd
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 手动添加Aware 接口的赋值
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // beanFacotryPostprocessor 在refresh的时候就被加载成为了bd
        // 优先通过getBean的方式来加载bfp
        invokeBeanFactoryPostProcessor(beanFactory);
        // 创建bp的方式同bfp，只是需要把bfp注册到列表当中去，方便后续其他bean生命周期调用
        registerBeanPostProcessor(beanFactory);

        // 初始化事件广播器
        initApplicationEventMulticaster();
        // 注册所有的Listener
        registerApplicationListeners(beanFactory);

        // 初始化所有的单例非懒加载的bean
        // 当前的逻辑看起来就是完全getBean创建、初始化
        beanFactory.preInstantiateSingletons();

        // 发布容器刷新完成的事件
        publishEvent(new ContextRefreshedEvent(this));
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        eventMulticaster = new SimpleApplicationEventMulticaster();
        // 注入到容器当中，是为了让其他人也能够获取到吗
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, eventMulticaster);
    }

    private void registerApplicationListeners(ConfigurableListableBeanFactory beanFactory) {
        Map<String, ApplicationListener> listenerMap = beanFactory.getBeansByType(ApplicationListener.class);
        for (ApplicationListener<?> listener : listenerMap.values()) {
            eventMulticaster.registerListener(listener);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) getBeanFactory();
        // 如果先销毁，再发布，似乎并不能获取到信息的样子
        publishEvent(new ContextClosedEvent(this));
        try {
            beanFactory.destroySingletons();
        } catch (Exception e) {
            log.error("ApplicationContext destroy bean when close failed", e);
        }
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> bpMap = getBeanFactory().getBeansByType(BeanPostProcessor.class);
        for (BeanPostProcessor bp : bpMap.values()) {
            beanFactory.addBeanPostProcessor(bp);
        }
    }

    protected void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Collection<BeanFactoryPostProcessor> beanFactoryPostProcessors = beanFactory.getBeansByType(BeanFactoryPostProcessor.class).values();
        if (CollectionUtil.isEmpty(beanFactoryPostProcessors)) {
            return;
        }

        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessors) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 刷新容器
     * 当前完成: 创建BF、重新加载BD
     */
    abstract protected void refreshBeanFactory();

    abstract protected ConfigurableListableBeanFactory getBeanFactory();

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
    public void publishEvent(ApplicationEvent applicationEvent) {
        eventMulticaster.multicastEvent(applicationEvent);
    }

}
