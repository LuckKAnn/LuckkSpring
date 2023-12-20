package com.luckk.lizzie.bean;

import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.FactoryBean;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 10:48
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: ProxyBeanFactory
 * @Version 1.0
 */
@Slf4j
public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws BeansException {
        // 直接根据接口就获取到了代理类

        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "Luckkun");
            hashMap.put("10002", "JLJ");
            hashMap.put("10003", "MMMM");
            return "代理:" + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<IUserDao> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
