package com.luckk.lizzie.bean;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/15 14:20
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: OrderDao
 * @Version 1.0
 */
@Slf4j
public class OrderDao {

    public void findOrderById(Long id) {
        log.info("get order info from db by {}", id);
    }
}
