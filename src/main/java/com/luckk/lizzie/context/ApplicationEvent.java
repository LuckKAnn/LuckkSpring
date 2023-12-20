package com.luckk.lizzie.context;

import com.luckk.lizzie.context.event.ApplicationContextEvent;

import java.util.EventObject;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 11:32
 * @PackageName: com.luckk.lizzie.context
 * @ClassName: ApplicationEvent
 * @Version 1.0
 */
public abstract class ApplicationEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
