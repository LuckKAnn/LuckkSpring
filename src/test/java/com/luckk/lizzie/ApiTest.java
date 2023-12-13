package com.luckk.lizzie;

import com.luckk.lizzie.bean.BeanDefinition;
import com.luckk.lizzie.bean.BeanType;
import com.luckk.lizzie.bean.UserService;
import com.luckk.lizzie.factory.BeanFactory;
import org.junit.Test;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 13:26
 * @PackageName: com.luckk.lizzie
 * @ClassName: ApiTest
 * @Version 1.0
 */
public class ApiTest {
    @Test
    public void testBeanFactory(){
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanType(BeanType.SCOPE_SINGLETON);

        UserService userService = new UserService();
        beanDefinition.setBean(userService);


        beanFactory.registerBeanDefinition("userService", beanDefinition);


        UserService userServiceBean = (UserService) beanFactory.getBean("userService");

        userServiceBean.queryUserInfo();


    }
}
