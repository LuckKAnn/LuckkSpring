package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.DisposableBean;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.SingletonBeanRegistry;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:50
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: DefaultSingletoneBeanRegistry
 * @Version 1.0
 */
public abstract class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 单例池
     */
    private final Map<String/*beanName*/, Object/*bean Object*/> singletonObjects;

    /**
     * 空对象的标识
     */
    protected final Object NULL_OBJECT = new Object();

    private final Map<String, DisposableBeanAdapter> disposableBeanAdapterMap = new HashMap<>();

    public DefaultSingletonBeanRegistry() {
        singletonObjects = new ConcurrentHashMap<>();
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void destroySingletons() throws Exception {
        Collection<DisposableBeanAdapter> disposableBeanAdapters = disposableBeanAdapterMap.values();
        for (DisposableBeanAdapter disposableBeanAdapter : disposableBeanAdapters) {
            disposableBeanAdapter.close();
        }
    }


    /**
     * why protected
     * why not in interface
     *
     * @param beanName
     * @param s
     */
    protected void addSingleton(String beanName, Object s) {
        this.getSingletonObjects().put(beanName, s);
    }

    public Map<String, Object> getSingletonObjects() {
        return singletonObjects;
    }


    public void addDisposableBean(Object bean, String beanName, BeanDefinition beanDefinition) {
        disposableBeanAdapterMap.put(beanName, new DisposableBeanAdapter(bean, beanDefinition.getDestroyMethod(), beanName));
    }
}
