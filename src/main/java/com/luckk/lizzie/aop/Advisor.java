package com.luckk.lizzie.aop;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Author liukun.inspire
 * @Date 2023/12/24 21:35
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: Advisor
 * @Version 1.0
 */
public interface Advisor {

    Advice getAdvice();
}
