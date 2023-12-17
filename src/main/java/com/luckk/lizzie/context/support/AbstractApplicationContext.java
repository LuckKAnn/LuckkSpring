package com.luckk.lizzie.context.support;

import cn.hutool.core.collection.CollectionUtil;
import com.luckk.lizzie.beans.factory.ConfigurableListableBeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanFactoryPostProcessor;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.context.ConfigurableApplicationContext;
import com.luckk.lizzie.core.io.DefaultResourceLoader;

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
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


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
        // beanFacotryPostprocessor 在refresh的时候就被加载成为了bd
        // 优先通过getBean的方式来加载bfp
        invokeBeanFactoryPostProcessor(beanFactory);

        // 对于某个容器的实例化？
        // 仓库注册地址
        // 实例化的容器
        // BeanFactoryPostProcessor什么时候注册到容器的呢
        // 为什么还需要手动去注册呢
        // 创建bp的方式同bfp，只是需要把bfp注册到列表当中去，方便后续其他bean生命周期调用
        registerBeanPostProcessor(beanFactory);

        // 初始化所有的单例非懒加载的bean
        // 当前的逻辑看起来就是完全getBean创建、初始化
        beanFactory.preInstantiateSingletons();
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


    abstract protected void refreshBeanFactory();

    abstract protected ConfigurableListableBeanFactory getBeanFactory();
}
