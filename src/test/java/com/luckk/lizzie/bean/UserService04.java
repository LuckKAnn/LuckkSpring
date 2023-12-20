package com.luckk.lizzie.bean;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * with pv depend
 *
 * @Author liukun.inspire
 * @Date 2023/12/13 14:50
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: UserService02
 * @Version 1.0
 */

@Slf4j
public class UserService04 {

    private IUserDao userDao;

    public UserService04() {
    }

    public void findUser() {
        log.error("use dao get result :{}", userDao.queryUserName("10001"));
        log.error("use dao get result :{}", userDao.queryUserName("10002"));
        log.error("use dao get result :{}", userDao.queryUserName("10003"));
    }
}
