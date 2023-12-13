package com.luckk.lizzie.factory.supports;

import com.luckk.lizzie.factory.BeansException;
import com.luckk.lizzie.factory.factory.BeanDefinition;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * 本质上也就是具备了创建Bean的能力的beanFactory
 *
 * @Author liukun.inspire
 * @Date 2023/12/13 13:53
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: AbstractAutowireCapableBeanFactory
 * @Version 1.0
 */
@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    InstantiationStrategy instantiationStrategy;

    public AbstractAutowireCapableBeanFactory() {
        this.instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    }

    public AbstractAutowireCapableBeanFactory(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * 日志记录Logger
     */
    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        // 这里完成的是创建bean的工作吗
        // 创建bean需要什么呢？
        Object o = null;
        try {
            o = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            log.error("create bean instance by beanDefinition fail", e);
            throw new BeansException();
        }
        addSingleton(beanName, o);
        return o;
    }

    public Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {

        Constructor<?>[] declaredConstructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Constructor chooseConstructors = null;
        for (Constructor cotr : declaredConstructors) {
            if (args != null && cotr.getParameterTypes().length == args.length) {
                chooseConstructors = cotr;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, chooseConstructors, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
