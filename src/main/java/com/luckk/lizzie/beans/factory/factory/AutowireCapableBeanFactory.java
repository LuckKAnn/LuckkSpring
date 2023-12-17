package com.luckk.lizzie.beans.factory.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.luckk.lizzie.beans.factory.BeanFactory;

/**
 * 是一个自动化处理Bean工厂配置的接口
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 16:40
 * @PackageName: com.luckk.lizzie.beans.factory.factory
 * @ClassName: AutowireCapableBeanFactory
 * @Version 1.0
 */

public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName);

    Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName);
}
