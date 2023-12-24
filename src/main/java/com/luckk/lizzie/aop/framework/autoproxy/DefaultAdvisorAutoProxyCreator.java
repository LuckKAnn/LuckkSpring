package com.luckk.lizzie.aop.framework.autoproxy;

import com.luckk.lizzie.aop.AdvisedSupport;
import com.luckk.lizzie.aop.Advisor;
import com.luckk.lizzie.aop.ClassFilter;
import com.luckk.lizzie.aop.Pointcut;
import com.luckk.lizzie.aop.TargetSource;
import com.luckk.lizzie.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.luckk.lizzie.aop.framework.ProxyFactory;
import com.luckk.lizzie.beans.factory.BeanFactory;
import com.luckk.lizzie.beans.factory.BeanFactoryAware;
import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.beans.factory.factory.InstantiationAwareBeanPostProcessor;
import com.luckk.lizzie.beans.factory.supports.DefaultListableBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author liukun.inspire
 * @Date 2023/12/24 21:36
 * @PackageName: com.luckk.lizzie.aop.framework.autoproxy
 * @ClassName: DefaultAdvisorAutoProxyCreator
 * @Version 1.0
 */
@Slf4j
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        // filter infrastructure class
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        // 实例化之前，如果需要进行AOP代理，那么就进行代理吗？
        Map<String, AspectJExpressionPointcutAdvisor> aspectJExpressionPointcutAdvisorMap =
                beanFactory.getBeansByType(AspectJExpressionPointcutAdvisor.class);
        // 对所有的这个advisor进行对应的匹配过滤
        // 一旦符合需求，就进行对应的代理吗？意思在创建之前就代理？
        for (AspectJExpressionPointcutAdvisor advisor : aspectJExpressionPointcutAdvisorMap.values()) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.match(beanClass)) {
                continue;
            }
            // 创建advisedSupport
            AdvisedSupport advisedSupport = new AdvisedSupport(true);
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            TargetSource targetSource;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                log.error("can not make newInstance for proxy class");
                throw new BeansException();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            // get this target aop proxy
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        // Advice 切面
        // Pointcut 切点
        // Advisor 切面和切点吗
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
