package com.luckk.lizzie.context;

/**
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 18:03
 * @PackageName: com.luckk.lizzie.context
 * @ClassName: ConfigurableApplicationContext
 * @Version 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh();
}
