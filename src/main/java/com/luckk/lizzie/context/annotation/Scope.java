package com.luckk.lizzie.context.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 11:00
 * @PackageName: com.luckk.lizzie.context.annotation
 * @ClassName: Scope
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {
    String value() default "singleton";
}
