package com.luckk.lizzie.context;

import com.luckk.lizzie.beans.factory.Aware;

/**
 * @Author liukun.inspire
 * @Date 2023/12/19 12:25
 * @PackageName: com.luckk.lizzie.context
 * @ClassName: ApplicationContextAware
 * @Version 1.0
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext context);
}
