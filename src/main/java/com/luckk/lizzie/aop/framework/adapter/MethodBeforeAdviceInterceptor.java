package com.luckk.lizzie.aop.framework.adapter;

import com.luckk.lizzie.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * this interceptor is just a Advice
 *
 * @Author liukun.inspire
 * @Date 2023/12/24 21:35
 * @PackageName: com.luckk.lizzie.aop.framework.adapter
 * @ClassName: MethodBeforeAdviceInterceptor
 * @Version 1.0
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 执行advice?
        // this involve advice
        // 对应的对象呢？
        advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        Object proceed = methodInvocation.proceed();
        return proceed;
    }
}
