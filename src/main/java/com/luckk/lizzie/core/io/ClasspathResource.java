package com.luckk.lizzie.core.io;

import cn.hutool.core.util.ClassUtil;

import java.io.InputStream;

/**
 * 为什么使用classLoader来进行资源加载呢
 * 相对路径加载： 使用ClassLoader加载资源时，可以使用相对路径，而不必关心资源文件在文件系统中的具体位置。这使得应用程序更加灵活，不受具体文件结构的限制。
 * 跨平台性： 由于ClassLoader加载资源时是通过类路径来查找的，因此在不同的操作系统上，只要类路径配置正确，资源文件就能被正确加载。这增强了应用程序的跨平台性。
 * 打包和分发： 当你将应用程序打包成JAR文件或者其他形式的分发包时，资源文件可以被包含在这些文件中，而不必担心路径问题。ClassLoader能够从JAR文件中正确加载资源。
 * 模块化开发： 使用ClassLoader加载资源可以更好地支持模块化开发。不同的模块可以有各自的资源文件，ClassLoader能够根据需要加载这些资源文件。
 * 动态加载： 通过ClassLoader，你可以在运行时动态加载类和资源。这对于插件系统、动态扩展和热部署等场景非常有用。
 *
 * @Author liukun.inspire
 * @Date 2023/12/15 15:40
 * @PackageName: com.luckk.lizzie.core.io
 * @ClassName: ClasspathResource
 * @Version 1.0
 */
public class ClasspathResource implements Resource {

    private final String path;
    private final ClassLoader classLoader;

    public ClasspathResource(String path) {
        this.path = path;
        this.classLoader = ClassUtil.getClassLoader();
    }

    @Override
    public InputStream getInputStream() {
        return classLoader.getResourceAsStream(path);
    }
}
