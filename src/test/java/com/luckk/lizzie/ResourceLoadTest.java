package com.luckk.lizzie;

import cn.hutool.core.io.IoUtil;
import com.luckk.lizzie.core.io.DefaultResourceLoader;
import com.luckk.lizzie.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 16:14
 * @PackageName: com.luckk.lizzie
 * @ClassName: ResourceLoadTest
 * @Version 1.0
 */
public class ResourceLoadTest {


    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.loadResource("classpath:spring.xml");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.loadResource("src/test/resources/spring.xml");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.loadResource("https://github.com/LuckKAnn/LuckkSpring/blob/master/src/test/resources/spring.xml");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

}
