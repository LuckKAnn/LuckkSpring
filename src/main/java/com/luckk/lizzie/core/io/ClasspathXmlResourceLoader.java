package com.luckk.lizzie.core.io;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 14:47
 * @PackageName: com.luckk.lizzie.factory.io
 * @ClassName: ClasspathXmlResourceLoader
 * @Version 1.0
 */
public class ClasspathXmlResourceLoader implements ResourceLoader {
    private String resourceUrl;

    public ClasspathXmlResourceLoader(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }


    @Override
    public Resource loadResource() {
        Document document = XmlUtil.readXML(resourceUrl);

        return new XmlResource(document);

    }
}
