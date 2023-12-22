package com.luckk.lizzie.aop;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 21:37
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: Pointcut
 * @Version 1.0
 */
public interface Pointcut {


    /**
     * 获取类过滤器
     * @return 类过滤器
     */
    ClassFilter getClassFilter();

    /**
     * 获取方法匹配
     * @return 方法匹配器
     */
    MethodMatcher getMethodMatcher();
}
