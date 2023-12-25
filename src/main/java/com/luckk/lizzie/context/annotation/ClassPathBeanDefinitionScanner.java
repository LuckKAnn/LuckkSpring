package com.luckk.lizzie.context.annotation;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.supports.BeanDefinitionRegistry;
import com.luckk.lizzie.context.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 11:00
 * @PackageName: com.luckk.lizzie.context.annotation
 * @ClassName: ClassPathBeanDefinitionScanner
 * @Version 1.0
 */

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
    // xmlReader 提供路径
    // registry提供注册beandefinition的方法
    //
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        // 返回类
        for (String basePackage : basePackages) {
            List<Class<?>> candidateComponents = findCandidateComponents(basePackage);
            for (Class<?> clazz : candidateComponents) {
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanClass(clazz);
                beanDefinition.setScope(resolveBeanScope(beanDefinition));
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        // 需要从注解上面去获取对应的信息吧
        if (!beanDefinition.getBeanClass().isAnnotationPresent(Scope.class)) {
            return BeanDefinition.SINGLETON;
        }
        Scope scope = (Scope) beanDefinition.getBeanClass().getAnnotation(Scope.class);
        return scope.value();
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        if (!beanDefinition.getBeanClass().isAnnotationPresent(Component.class)) {
            return StrUtil.lowerFirst(beanDefinition.getBeanClass().getSimpleName());
        }
        Component annotation = (Component) beanDefinition.getBeanClass().getAnnotation(Component.class);
        if (StringUtils.isNotEmpty(annotation.value())) {
            return annotation.value();
        }
        return StrUtil.lowerFirst(beanDefinition.getBeanClass().getSimpleName());
    }

}
