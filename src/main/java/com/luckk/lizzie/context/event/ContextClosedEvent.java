package com.luckk.lizzie.context.event;

import com.luckk.lizzie.context.ApplicationEvent;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 11:35
 * @PackageName: com.luckk.lizzie.context.event
 * @ClassName: ContextClosedEvent
 * @Version 1.0
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
