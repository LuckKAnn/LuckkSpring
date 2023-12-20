package com.luckk.lizzie.context;

/**
 * 事件发布器
 *
 * @Author liukun.inspire
 * @Date 2023/12/20 11:32
 * @PackageName: com.luckk.lizzie.context
 * @ClassName: ApplicationEventPublisher
 * @Version 1.0
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent applicationEvent);
}
