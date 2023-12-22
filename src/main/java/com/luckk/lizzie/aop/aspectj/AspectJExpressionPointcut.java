package com.luckk.lizzie.aop.aspectj;

import com.luckk.lizzie.aop.ClassFilter;
import com.luckk.lizzie.aop.MethodMatcher;
import com.luckk.lizzie.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 主要是作为一个切面，以及作为一个方法匹配器？
 *
 * @Author liukun.inspire
 * @Date 2023/12/21 21:40
 * @PackageName: com.luckk.lizzie.aop.aspectj
 * @ClassName: AspectJExpressionPointcut
 * @Version 1.0
 */
public class AspectJExpressionPointcut implements ClassFilter, Pointcut, MethodMatcher {

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    // 这里完全就是AOP或者说AspectJ本身的能力了
    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution
                        (SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public boolean match(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean match(Method method, Class<?> targetClass) {
        // K1
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }
}
