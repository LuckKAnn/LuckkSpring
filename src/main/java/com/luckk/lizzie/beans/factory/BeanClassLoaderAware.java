package com.luckk.lizzie.beans.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 12:23
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: BeanClassLoaderAware
 * @Version 1.0
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
