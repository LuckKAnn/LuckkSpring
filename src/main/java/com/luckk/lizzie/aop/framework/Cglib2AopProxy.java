package com.luckk.lizzie.aop.framework;

import com.luckk.lizzie.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // why CglibMethodInvocation
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            if (advised.getMethodMatcher().match(method, advised.getTargetSource().getTarget().getClass())) {
                // aop aspect logic involve in MethodInterceptor
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            return methodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() {
            try {
                // 本身直接调用方法？为什么没有产生循环代理
                return this.method.invoke(this.target, this.arguments);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

    }

}
