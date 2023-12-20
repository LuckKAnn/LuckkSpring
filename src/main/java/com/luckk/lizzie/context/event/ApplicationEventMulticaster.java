package com.luckk.lizzie.context.event;

import com.luckk.lizzie.context.ApplicationEvent;
import com.luckk.lizzie.context.ApplicationListener;
import org.checkerframework.checker.units.qual.A;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 11:34
 * @PackageName: com.luckk.lizzie.context.event
 * @ClassName: ApplicationEventMulticaster
 * @Version 1.0
 */
public interface ApplicationEventMulticaster {

    // 注册监听器
    void registerListener(ApplicationListener<?> applicationListener);


    void removeListener(ApplicationListener<?> listener);


    void multicastEvent(ApplicationEvent event);
}
