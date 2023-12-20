package com.luckk.lizzie;

import com.luckk.lizzie.bean.RedisService;
import com.luckk.lizzie.bean.UserService03;
import com.luckk.lizzie.bean.UserService04;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.context.ApplicationContext;
import com.luckk.lizzie.context.support.AbstractApplicationContext;
import com.luckk.lizzie.context.support.ClassPathXmlApplicationContext;
import com.luckk.lizzie.listener.LuckSpringEvent;
import org.junit.Test;

import java.util.Map;

/**
 * @Author liukun.inspire
 * @Date 2023/12/17 22:38
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: ApplicationContextTest
 * @Version 1.0
 */
public class ApplicationContextTest {
    @Test
    public void testInitApplicationContext() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});
        Map<String, BeanPostProcessor> beansByType = classPathXmlApplicationContext.getBeansByType(BeanPostProcessor.class);
        System.out.println(beansByType.toString());
        UserService03 userService = (UserService03) classPathXmlApplicationContext.getBean("userService");
        userService.findMyOrder();
    }

    @Test
    public void testAwareInterface() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});
        System.out.println(applicationContext);
        RedisService redisService = (RedisService) applicationContext.getBean("redisService");
        System.out.println(redisService.getApplicationContext());
        System.out.println(redisService.getBeanName());
        System.out.println(redisService.getBeanFactory());
        redisService.queryData();
    }

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-factoryBean.xml"});
        System.out.println(applicationContext);

        UserService04 userService = (UserService04) applicationContext.getBean("userService");

        userService.findUser();
    }

    @Test
    public void testPrototype() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-factoryBean.xml"});
        UserService04 userService = (UserService04) applicationContext.getBean("userService2");
        System.out.println(userService);
        userService.findUser();
        userService = (UserService04) applicationContext.getBean("userService2");
        System.out.println(userService);
        userService.findUser();
    }


    @Test
    public void testListenerAndEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-Listener.xml"});
        applicationContext.publishEvent(new LuckSpringEvent("this is a test"));
    }
}
