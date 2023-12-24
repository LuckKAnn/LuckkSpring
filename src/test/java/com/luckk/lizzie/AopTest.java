package com.luckk.lizzie;

import com.luckk.lizzie.aop.AdvisedSupport;
import com.luckk.lizzie.aop.CompensateInterceptor;
import com.luckk.lizzie.aop.TargetSource;
import com.luckk.lizzie.aop.aspectj.AspectJExpressionPointcut;
import com.luckk.lizzie.aop.framework.Cglib2AopProxy;
import com.luckk.lizzie.aop.framework.JdkDynamicAopProxy;
import com.luckk.lizzie.bean.CompensateService;
import com.luckk.lizzie.bean.CompensateServiceImpl;
import com.luckk.lizzie.context.support.ClassPathXmlApplicationContext;
import com.luckk.lizzie.listener.LuckSpringEvent;
import org.junit.Test;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 22:18
 * @PackageName: com.luckk.lizzie
 * @ClassName: AopTest
 * @Version 1.0
 */
public class AopTest {

    @Test
    public void testAop() {

        AdvisedSupport advisedSupport = new AdvisedSupport(true);

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.luckk.lizzie.bean.CompensateService.queryCompensate(..))");
        CompensateService compensateService = new CompensateServiceImpl();
        advisedSupport.setMethodInterceptor(new CompensateInterceptor());
        advisedSupport.setTargetSource(new TargetSource(compensateService));
        advisedSupport.setMethodMatcher(pointcut);
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        CompensateService proxy = (CompensateService) jdkDynamicAopProxy.getProxy();
        proxy.queryCompensate("111");
        proxy.compensate("111", "22121");

        Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
        CompensateService proxy1 = (CompensateService) cglib2AopProxy.getProxy();
        proxy1.queryCompensate("111");
        proxy1.compensate("111", "22121");

        // Cglib 基于继承，父类可以设置为CompensateServiceImpl，可以转换
        CompensateServiceImpl proxy2 = (CompensateServiceImpl) cglib2AopProxy.getProxy();
        proxy2.queryCompensate("111");
        proxy2.compensate("111", "22121");

        // JDK 代理基于接口，代理出来的对象无法转换为实现类
        // CompensateService proxy4 = (CompensateServiceImpl) jdkDynamicAopProxy.getProxy();
        // proxy4.queryCompensate("111");
        // proxy4.compensate("111","22121");
    }


    @Test
    public void testSpringAop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-aop.xml"});
        CompensateService compensateService = applicationContext.getBean("compensateService", CompensateService.class);
        System.out.println(compensateService);
        compensateService.compensate("111", "222");
    }
}
