package com.luckk.lizzie.bean;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.sql.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * with pv depend
 * @Author liukun.inspire
 * @Date 2023/12/13 14:50
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: UserService02
 * @Version 1.0
 */

@Slf4j
public class UserService03 {
    private String name;

    private OrderDao orderDao;
    public UserService03() {
    }
    public void doSaveHello() {
        log.info("hello:" + name);
    }

    public void findMyOrder(){
        log.info("hello:" + name);
        long orderId = RandomUtil.getRandom().nextLong();
        orderDao.findOrderById(orderId);
    }

    /**
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
