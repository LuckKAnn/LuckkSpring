package com.luckk.lizzie;

import com.luckk.lizzie.bean.UserService03;
import com.luckk.lizzie.beans.factory.factory.BeanPostProcessor;
import com.luckk.lizzie.context.support.ClassPathXmlApplicationContext;
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
}
