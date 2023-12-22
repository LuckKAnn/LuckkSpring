package com.luckk.lizzie.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 22:29
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: CompensateInterceptor
 * @Version 1.0
 */
@Slf4j
public class CompensateInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        log.error("this is methodInterceptor method log");
        return methodInvocation.proceed();
    }
}
