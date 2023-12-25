package com.luckk.lizzie.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.luckk.lizzie.beans.factory.supports.XmlBeanDefinitionReader;
import com.luckk.lizzie.context.stereotype.Component;
import com.luckk.lizzie.core.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 11:00
 * @PackageName: com.luckk.lizzie.context.annotation
 * @ClassName: ClassPathScanningCandidateComponentProvider
 * @Version 1.0
 */
public class ClassPathScanningCandidateComponentProvider {

    public List<Class<?>> findCandidateComponents(String basePackage) {
        // 这个就是去找到合适的可能的component的
        // 仅仅完成对应的加载的工作，但是不做具体的bd解析和封装
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        // resourceLoader.loadResource()
        // 如果用这种字符流的方式，其实就不要进行类的加载了？
        // 如果用类加载的方式，不是完全需要加载对应的类了吗
        return new ArrayList<>(classes);
    }
}
