package com.luckk.lizzie.aop;

import java.lang.reflect.Method;

/**
 * @Author liukun.inspire
 * @Date 2023/12/24 21:36
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: MethodBeforeAdvice
 * @Version 1.0
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
    // 可以返回对应的对象?

    /**
     * Callback before a given method is invoked.
     *
     * @param method method being invoked
     * @param args   arguments to the method
     * @param target target of the method invocation. May be <code>null</code>.
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}