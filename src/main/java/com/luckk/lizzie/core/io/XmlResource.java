package com.luckk.lizzie.core.io;

import org.w3c.dom.Document;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 15:04
 * @PackageName: com.luckk.lizzie.factory.io
 * @ClassName: XmlResource
 * @Version 1.0
 */
public class XmlResource implements Resource {

    private final Document document;

    public XmlResource(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }
}
