package com.luckk.lizzie;

import cn.hutool.core.collection.CollectionUtil;
import com.luckk.lizzie.bean.OrderDao;
import com.luckk.lizzie.bean.UserService;
import com.luckk.lizzie.bean.UserService02;
import com.luckk.lizzie.bean.UserService03;
import com.luckk.lizzie.beans.factory.PropertyValue;
import com.luckk.lizzie.beans.factory.PropertyValues;
import com.luckk.lizzie.beans.factory.factory.BeanDefinition;
import com.luckk.lizzie.beans.factory.factory.BeanReference;
import com.luckk.lizzie.beans.factory.supports.DefaultListableBeanFactory;
import com.luckk.lizzie.beans.factory.supports.XmlBeanDefinitionReader;
import com.luckk.lizzie.core.io.DefaultResourceLoader;
import com.luckk.lizzie.core.io.Resource;
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
        UserService02 userService = (UserService02) defaultListableBeanFactory.getBean("userService", "JJJ");
        userService.doSaveHello();
        // 4.第二次获取 bean from Singleton
        UserService02 userService_singleton = (UserService02) defaultListableBeanFactory.getBean("userService", "LLLL");
        userService_singleton.doSaveHello();

        // get the same object
        log.info(userService.toString());
        log.info(userService_singleton.toString());
    }

    @Test
    public void testGetBeanWithPvSet() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // init userBean
        BeanDefinition userBeanDefinition = new BeanDefinition();
        userBeanDefinition.setBeanClass(UserService03.class);

        PropertyValues propertyValues = new PropertyValues();
        userBeanDefinition.setPropertyValues(propertyValues);

        PropertyValue nameValue = new PropertyValue();
        nameValue.setPropertyName("name");
        nameValue.setPropertyValue("LuckkKun-Test");

        PropertyValue orderValue = new PropertyValue();
        orderValue.setPropertyName("orderDao");
        orderValue.setPropertyValue(new BeanReference("orderDao"));
        propertyValues.setPropertyValues(CollectionUtil.newArrayList(nameValue, orderValue));


        // init OrderBean
        BeanDefinition orderBd = new BeanDefinition();
        orderBd.setBeanClass(OrderDao.class);
        factory.registerBeanDefinition("userService", userBeanDefinition);
        factory.registerBeanDefinition("orderDao", orderBd);

        UserService03 userService = (UserService03) factory.getBean("userService");
        userService.findMyOrder();
    }


    @Test
    public void testLoadSpringXml() throws ClassNotFoundException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.loadResource("classpath:spring.xml");
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory, defaultResourceLoader);
        xmlBeanDefinitionReader.loadBeanDefinition(resource);

        UserService03 userService = (UserService03) defaultListableBeanFactory.getBean("userService");
        userService.findMyOrder();
    }
}
