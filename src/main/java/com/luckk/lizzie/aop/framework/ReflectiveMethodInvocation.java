package com.luckk.lizzie.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 为什么还要把这个去包装一层呢
 *
 * @Author liukun.inspire
 * @Date 2023/12/21 21:43
 * @PackageName: com.luckk.lizzie.aop.framework
 * @ClassName: ReflectiveMethodInvocation
 * @Version 1.0
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    private Object target;

    private Method method;
    private Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    // proceed?
    // how? 直接调用？

    public Object proceed() {
        Object invoke;
        try {
            invoke = method.invoke(target, arguments);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return invoke;
    }

    public Object getThis() {
        // 这是什么意思呢？
        return null;
    }

    public AccessibleObject getStaticPart() {
        // 什么叫静态方法的部分？
        return null;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }
}
