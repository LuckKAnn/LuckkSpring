package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.beans.factory.factory.SingletonBeanRegistry;

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

    public DefaultSingletonBeanRegistry() {
        singletonObjects = new ConcurrentHashMap<>();
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
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
}
