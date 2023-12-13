package com.luckk.lizzie.bean;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/13 14:50
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: UserService02
 * @Version 1.0
 */

@Slf4j
public class UserService02 {
    private String name;

    public UserService02() {
        this.name = "LuckkKunSpring";

    }
    public UserService02(String name) {
        this.name = name;

    }

    public void doSaveHello() {
        log.info("hello:" + name);
    }
}
