package com.luckk.lizzie.aop;

/**
 * @Author liukun.inspire
 * @Date 2023/12/24 21:36
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: PointcutAdvisor
 * @Version 1.0
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
