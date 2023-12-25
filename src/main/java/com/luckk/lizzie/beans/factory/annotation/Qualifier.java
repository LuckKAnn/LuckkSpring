package com.luckk.lizzie.beans.factory.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 12:16
 * @PackageName: com.luckk.lizzie.beans.factory.annotation
 * @ClassName: Qualifier
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Qualifier {

    String name() default "";
}
