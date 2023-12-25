package com.luckk.lizzie.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.luckk.lizzie.beans.factory.BeanFactory;
import com.luckk.lizzie.beans.factory.BeanFactoryAware;
import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.ListableBeanFactory;
import com.luckk.lizzie.beans.factory.PropertyValue;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.beans.factory.factory.InstantiationAwareBeanPostProcessor;
import com.luckk.lizzie.beans.factory.supports.AbstractBeanFactory;
import com.luckk.lizzie.util.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 12:16
 * @PackageName: com.luckk.lizzie.beans.factory.annotation
 * @ClassName: AutowiredAnnotationBeanPostProcessor
 * @Version 1.0
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessPropertyValues(Object bean, String beanName) {
        // 重在初始化前？
        // 因为实例化的方式不一样，所以获取对应的类的方式也不一样
        // 1. 处理注解 @Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] declaredFields = clazz.getDeclaredFields();
        BeanDefinition beanDefinition = ((AbstractBeanFactory) beanFactory).getBeanDefinition(beanName);

        for (Field field : declaredFields) {
            // 处理Annotation
            if (field.isAnnotationPresent(Autowired.class)) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    String name = qualifier.name();
                    Object targetInjection = beanFactory.getBean(name);
                    BeanUtil.setFieldValue(bean, field.getName(), targetInjection);
                } else {
                    // 随机选择
                    Map<String, ?> beansByType = beanFactory.getBeansByType(field.getType());
                    // 设置为PV
                    for (Object target : beansByType.values()) {
                        beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(field.getName(), target));
                        // BeanUtil.setFieldValue(bean, field.getName(), target);
                        break;
                    }
                }
            }
            // 处理@Value
            if (field.isAnnotationPresent(Value.class)) {
                Value valueAnnotation = field.getAnnotation(Value.class);
                String value = valueAnnotation.value();
                // 解析？找Resolver？
                AbstractBeanFactory abstractBeanFactory = (AbstractBeanFactory) beanFactory;
                String res = abstractBeanFactory.stringResolve(value);
                if (StringUtils.isNotEmpty(res)) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(field.getName(), res));
                }
            }
        }
        return bean;
    }
}
