package com.luckk.lizzie.beans.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/18 11:56
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: InitializingBean
 * @Version 1.0
 */
public interface InitializingBean {


    /**
     * 初始化方法
     * 对于单个bean的增强
     */
    void afterPropertiesSet();
}
