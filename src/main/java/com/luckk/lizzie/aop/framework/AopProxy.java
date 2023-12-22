package com.luckk.lizzie.aop.framework;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 21:28
 * @PackageName: com.luckk.lizzie.aop.framework
 * @ClassName: AopProxy
 * @Version 1.0
 */
public interface AopProxy {


    /**
     * 获取代理对象的方式
     * @return 代理对象
     */
    Object getProxy();
}
