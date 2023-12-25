package com.luckk.lizzie.context.stereotype;

import lombok.extern.slf4j.Slf4j;

import javax.management.DescriptorKey;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 11:01
 * @PackageName: com.luckk.lizzie.context.stereotype
 * @ClassName: Component
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {

    String value() default "";
}
