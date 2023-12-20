package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FactoryBean 的处理
 *
 * @Author liukun.inspire
 * @Date 2023/12/20 10:09
 * @PackageName: com.luckk.lizzie.beans.factory.supports
 * @ClassName: FactoryBeanRegistrySupport
 * @Version 1.0
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * FactoryBean 生成对象的缓存池
     * 因为当业务手动获取Bean的时候，可能会造成并发的访问读写吗
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    public Object getCachedObjectForFactoryBean(String beanName) {
        Object target = factoryBeanObjectCache.get(beanName);
        return target == NULL_OBJECT ? null : target;
    }

    public Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object target = getCachedObjectForFactoryBean(beanName);
            if (target == null) {
                target = doGetObjectFromFactoryBena(factoryBean, beanName);

                factoryBeanObjectCache.put(beanName, target);
            }
            return target != NULL_OBJECT ? target : null;
        } else {
            return doGetObjectFromFactoryBena(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBena(final FactoryBean factoryBean, final String beanName) {
        // 通过factoryBean来进行创建
        return factoryBean.getObject();
    }


}
