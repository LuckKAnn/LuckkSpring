package com.luckk.lizzie.aop;

import java.lang.reflect.Method;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 21:37
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: MethodMatcher
 * @Version 1.0
 */
public interface MethodMatcher {


    /**
     * 方法匹配
     * @param method 待匹配方法
     * @param targetClass 目标类型
     * @return
     */
    boolean match(Method method, Class<?> targetClass);
}
