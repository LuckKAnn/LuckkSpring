package com.luckk.lizzie;

import com.luckk.lizzie.anno.TransferService;
import com.luckk.lizzie.anno.TransferServiceImpl;
import com.luckk.lizzie.bean.CompensateService;
import com.luckk.lizzie.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.util.Map;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 11:31
 * @PackageName: com.luckk.lizzie
 * @ClassName: AnnoTesst
 * @Version 1.0
 */
public class AnnoTest {

    @Test
    public void testSpringAnno() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-anno.xml"});
        Map<String, TransferService> beansByType = applicationContext.getBeansByType(TransferService.class);
        for (Map.Entry<String, TransferService> entry : beansByType.entrySet()) {
            System.out.println(entry.getKey());
        }

        TransferService transferServiceImpl = applicationContext.getBean("TransferServiceImpl", TransferService.class);
        transferServiceImpl.doTransfer("1111.", "@22222");
    }

    @Test
    public void testPlaceholder() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-placeholder.xml"});
        TransferService transferServiceImpl = applicationContext.getBean("transferService", TransferService.class);
        transferServiceImpl.doTransfer("1111.", "@22222");
    }
}
