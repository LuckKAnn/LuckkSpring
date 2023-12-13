package com.luckk.lizzie;

import com.luckk.lizzie.bean.UserService;
import com.luckk.lizzie.bean.UserService02;
import com.luckk.lizzie.factory.BeanFactory;
import com.luckk.lizzie.factory.factory.BeanDefinition;
import com.luckk.lizzie.factory.supports.DefaultListableBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:26
 * @PackageName: com.luckk.lizzie
 * @ClassName: ApiTest
 * @Version 1.0
 */
@Slf4j
public class ApiTest {

    @Test
    public void test() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(UserService.class);

        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");

        userService.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) defaultListableBeanFactory.getBean("userService");
        userService_singleton.queryUserInfo();

        // get the same object
        log.info(userService.toString());
        log.info(userService_singleton.toString());
    }

    @Test
    public void hasParameterClassInstantiateTest() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(UserService02.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService02 userService = (UserService02) defaultListableBeanFactory.getBean("userService");

        userService.doSaveHello();
        // 4.第二次获取 bean from Singleton
        UserService02 userService_singleton = (UserService02) defaultListableBeanFactory.getBean("userService");
        userService_singleton.doSaveHello();

        // get the same object
        log.info(userService.toString());
        log.info(userService_singleton.toString());

    }

    @Test
    public void hasParameterInstantiateTest() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(UserService02.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService02 userService = (UserService02) defaultListableBeanFactory.getBean("userService","JJJ");
        userService.doSaveHello();
        // 4.第二次获取 bean from Singleton
        UserService02 userService_singleton = (UserService02) defaultListableBeanFactory.getBean("userService","LLLL");
        userService_singleton.doSaveHello();

        // get the same object
        log.info(userService.toString());
        log.info(userService_singleton.toString());
    }
}
