package com.luckk.lizzie.factory;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:23
 * @PackageName: com.luckk.lizzie.factory
 * @ClassName: BeanFactory
 * @Version 1.0
 */
public interface BeanFactory {

    public Object getBean(String name);

    Object getBean(String name, Object... args);
}
