package com.luckk.lizzie.context.event;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 11:35
 * @PackageName: com.luckk.lizzie.context.event
 * @ClassName: ContextRefreshedEvent
 * @Version 1.0
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
