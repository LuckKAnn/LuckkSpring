package com.luckk.lizzie.context;

import java.util.EventListener;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 11:32
 * @PackageName: com.luckk.lizzie.context
 * @ClassName: ApplicationListener
 * @Version 1.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    // 监听的事件

    Class<E> getListenEvent();


    void onApplicationEvent(E event);

}
