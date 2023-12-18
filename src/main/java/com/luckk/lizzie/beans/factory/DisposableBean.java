package com.luckk.lizzie.beans.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/18 11:56
 * @PackageName: com.luckk.lizzie.beans.factory
 * @ClassName: DisposableBean
 * @Version 1.0
 */
public interface DisposableBean {


    void close() throws Exception;
}
