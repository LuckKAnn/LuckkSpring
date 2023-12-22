package com.luckk.lizzie.aop;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 21:37
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: ClassFilter
 * @Version 1.0
 */
public interface ClassFilter {

    /**
     *
     * @param clazz
     * @return
     */
    boolean match(Class<?> clazz);
}
