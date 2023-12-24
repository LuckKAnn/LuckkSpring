package com.luckk.lizzie.aop;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @Author liukun.inspire
 * @Date 2023/12/24 22:20
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: CompensateMethodBeforeAdvice
 * @Version 1.0
 */
@Slf4j
public class CompensateMethodBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.error("CompensateMethodBeforeAdvice info");
    }
}
