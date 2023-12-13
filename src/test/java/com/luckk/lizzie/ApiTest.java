package com.luckk.lizzie;

import com.luckk.lizzie.bean.UserService;
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
}
