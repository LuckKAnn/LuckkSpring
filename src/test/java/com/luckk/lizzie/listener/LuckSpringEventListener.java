package com.luckk.lizzie.listener;

import com.luckk.lizzie.context.ApplicationListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/20 16:29
 * @PackageName: com.luckk.lizzie.listener
 * @ClassName: LuckSpringEventListener
 * @Version 1.0
 */
@Slf4j
public class LuckSpringEventListener implements ApplicationListener<LuckSpringEvent> {
    @Override
    public Class<LuckSpringEvent> getListenEvent() {
        return LuckSpringEvent.class;
    }

    @Override
    public void onApplicationEvent(LuckSpringEvent event) {
        String source = (String) event.getSource();
        log.error("luckkSpring listener get info :{}", source);
    }
}
