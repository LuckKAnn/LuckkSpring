package com.luckk.lizzie.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * provide all aop ableity
 *
 * @Author liukun.inspire
 * @Date 2023/12/21 21:29
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: AdvisedSupport
 * @Version 1.0
 */
public class AdvisedSupport {

    private TargetSource targetSource;
    /**
     *
     */
    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    private boolean proxyTargetClass;

    public AdvisedSupport(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    /**
     * @param targetSource to set
     */
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    /**
     * @param methodInterceptor to set
     */
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    /**
     * @param methodMatcher to set
     */
    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }
}
