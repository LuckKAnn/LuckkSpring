package com.luckk.lizzie.aop.framework;

import com.luckk.lizzie.aop.AdvisedSupport;
import com.luckk.lizzie.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 21:43
 * @PackageName: com.luckk.lizzie.aop.framework
 * @ClassName: JdkDynamicAopProxy
 * @Version 1.0
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }


    @Override
    public Object getProxy() {
        // what the interface
        Class<?>[] targetClass = advisedSupport.getTargetSource().getTargetClass();
        return Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), advisedSupport.getTargetSource().getTargetClass(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advisedSupport.getMethodMatcher().match(method, advisedSupport.getTargetSource().getTarget().getClass())) {
            // 什么地方去执行具体的封装的逻辑呢
            // 这里就是执行具体的逻辑了把
            // 具体执行的逻辑在MethodInterceptor里面？
            // 需要的是原来的对象？
            return advisedSupport.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }
        // return
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
        // 调用support的match判断方法是否正确
        // 如果是正确的话，怎么执行
        // 如果不是正确的话，怎么执行
    }
}
