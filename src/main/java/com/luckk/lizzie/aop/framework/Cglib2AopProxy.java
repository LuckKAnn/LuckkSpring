package com.luckk.lizzie.aop.framework;

import com.luckk.lizzie.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 21:43
 * @PackageName: com.luckk.lizzie.aop.framework
 * @ClassName: Cglib2AopProxy
 * @Version 1.0
 */
public class Cglib2AopProxy implements AopProxy {
    private AdvisedSupport advisedSupport;

    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        // 为什么一定要创建呢？
        // 只有需要的才进行代理的创建吗
        Enhancer enhancer = new Enhancer();
        Class<?> aClass = advisedSupport.getTargetSource().getTarget().getClass();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            if (advisedSupport.getMethodMatcher().match(method, advisedSupport.getTargetSource().getTarget().getClass())) {
                // return methodProxy.invoke();
                return advisedSupport.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, objects));
            }
            // CGLIB本身的代理的逻辑
            return methodProxy.invoke(advisedSupport.getTargetSource().getTarget(), objects);
        });
        return enhancer.create();
    }
}
