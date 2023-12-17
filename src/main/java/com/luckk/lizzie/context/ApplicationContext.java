package com.luckk.lizzie.context;

import com.luckk.lizzie.beans.factory.ListableBeanFactory;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 18:01
 * @PackageName: com.luckk.lizzie.content
 * @ClassName: ApplicationContext
 * @Version 1.0
 */

// 继承ListableBeanFactory只是希望获取BeanFactory获取Bean的能力吗
public interface ApplicationContext extends ListableBeanFactory {
}
