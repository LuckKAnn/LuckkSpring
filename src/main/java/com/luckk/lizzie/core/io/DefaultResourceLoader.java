package com.luckk.lizzie.core.io;

import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 15:42
 * @PackageName: com.luckk.lizzie.core.io
 * @ClassName: DefaultResourceLoader
 * @Version 1.0
 */
@Slf4j
public class DefaultResourceLoader implements ResourceLoader {

    /**
     * classpath prefix template
     */
    private final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource loadResource(String location) {
        // default 就直接使用classPath来进行加载吧
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // classLoader 默认不需要classpath作为开始
            return new ClasspathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            // try url and then try file system
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                log.debug("DefaultClassLoader can not load resource from URL, try file disk");
                return new FileSystemResource(location);
            }
        }
    }
}
