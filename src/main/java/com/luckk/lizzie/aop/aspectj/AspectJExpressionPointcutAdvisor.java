package com.luckk.lizzie.aop.aspectj;

import com.luckk.lizzie.aop.Pointcut;
import com.luckk.lizzie.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;

/**
 * This class support basic aop ablelity
 *
 * @Author liukun.inspire
 * @Date 2023/12/24 21:35
 * @PackageName: com.luckk.lizzie.aop.aspectj
 * @ClassName: AspectJExpressionPointcutAdvisor
 * @Version 1.0
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * point cut resolver
     */
    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public AspectJExpressionPointcutAdvisor() {
    }

    public AspectJExpressionPointcutAdvisor(Advice advice, String expression) {
        this.advice = advice;
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
