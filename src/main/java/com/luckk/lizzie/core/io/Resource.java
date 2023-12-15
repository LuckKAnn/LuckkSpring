package com.luckk.lizzie.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 14:43
 * @PackageName: com.luckk.lizzie.factory.supports
 * @ClassName: Resource
 * @Version 1.0
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
