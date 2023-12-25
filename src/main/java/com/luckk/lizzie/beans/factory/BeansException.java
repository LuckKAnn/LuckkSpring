package com.luckk.lizzie.beans.factory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 14:04
 * @PackageName: com.luckk.lizzie.factory
 * @ClassName: BeansException
 * @Version 1.0
 */
public class BeansException extends RuntimeException {

    public BeansException() {
    }

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable e) {
        super(message, e);
    }
}
