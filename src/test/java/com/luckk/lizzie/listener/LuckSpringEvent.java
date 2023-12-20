package com.luckk.lizzie.listener;

import com.luckk.lizzie.context.ApplicationEvent;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 16:29
 * @PackageName: com.luckk.lizzie.listener
 * @ClassName: LuckSpringEvent
 * @Version 1.0
 */
public class LuckSpringEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public LuckSpringEvent(Object source) {
        super(source);
    }
}
