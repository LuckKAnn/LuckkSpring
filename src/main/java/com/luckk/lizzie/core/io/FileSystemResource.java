package com.luckk.lizzie.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 15:40
 * @PackageName: com.luckk.lizzie.core.io
 * @ClassName: FileSystemResource
 * @Version 1.0
 */
public class FileSystemResource implements Resource {
    private File file;
    private String path;

    public FileSystemResource(String path) {
        this.path = path;
        // 在这里加载文件?
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() {
        try {
            return Files.newInputStream(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
