package com.luckk.lizzie.context.support;

import com.luckk.lizzie.beans.factory.ConfigurableListableBeanFactory;
import com.luckk.lizzie.beans.factory.factory.BeanFactoryPostProcessor;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.beans.factory.supports.DefaultListableBeanFactory;

import java.util.List;

/**
 * 抽象可刷新的应用上下文
 * @Author liukun.inspire
 * @Date 2023/12/15 18:36
 * @PackageName: com.luckk.lizzie.context.support
 * @ClassName: AbstractRefreshableApplicationContext
 * @Version 1.0
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    /**
     * 档案馆
     * 可刷新意味着需要知道档案馆吗
     * 组合
     */
    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        this.beanFactory = createBeanFactory();
        // 加载bd，本质上来说不是这一层完成的事情，交由具体的方式来进行完成。
        loadBeanDefinitions(beanFactory);
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        DefaultListableBeanFactory beanFactory = this.beanFactory;
        if (beanFactory == null) {
            throw new IllegalStateException("BeanFactory not initialized or already closed - " +
                    "call 'refresh' before accessing beans via the ApplicationContext");
        }
        return (ConfigurableListableBeanFactory) beanFactory;
    }

    /**
     * 需要能够从档案馆再去加载bean定义吗
     * 但是不同的bean定义的reader方式不同？
     *
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
