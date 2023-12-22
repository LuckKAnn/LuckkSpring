package com.luckk.lizzie.bean;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 22:19
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: CompensateServiceImpl
 * @Version 1.0
 */
@Slf4j
public class CompensateServiceImpl implements CompensateService {
    @Override
    public void compensate(String uid, String money) {
        log.info("compensate user:{} for money :{}", uid, money);
    }

    @Override
    public void queryCompensate(String uid) {
        // log.info("try to query user Compensate money");
        log.info("user :{}, compensate ", uid);
    }
}
