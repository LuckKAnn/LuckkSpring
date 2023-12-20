package com.luckk.lizzie.listener;

import com.luckk.lizzie.context.ApplicationListener;
import com.luckk.lizzie.context.event.ContextRefreshedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 16:28
 * @PackageName: com.luckk.lizzie.listener
 * @ClassName: ContextRefreshedListener
 * @Version 1.0
 */
@Slf4j
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public Class<ContextRefreshedEvent> getListenEvent() {
        return ContextRefreshedEvent.class;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.error("happen context refreshed event");
    }
}
