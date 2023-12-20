package com.luckk.lizzie.context.event;

import com.luckk.lizzie.context.ApplicationEvent;
import com.luckk.lizzie.context.ApplicationListener;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 11:35
 * @PackageName: com.luckk.lizzie.context.event
 * @ClassName: SimpleApplicationEventMulticaster
 * @Version 1.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
