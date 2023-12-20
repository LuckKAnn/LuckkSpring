package com.luckk.lizzie.context.event;

import com.luckk.lizzie.context.ApplicationContext;
import com.luckk.lizzie.context.ApplicationEvent;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 11:34
 * @PackageName: com.luckk.lizzie.context.event
 * @ClassName: ApplicationContextEvent
 * @Version 1.0
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }
    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
