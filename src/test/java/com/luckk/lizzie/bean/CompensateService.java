package com.luckk.lizzie.bean;

/**
 * @Author liukun.inspire
 * @Date 2023/12/21 22:19
 * @PackageName: com.luckk.lizzie.bean
 * @ClassName: CompesateService
 * @Version 1.0
 */
public interface CompensateService {

    void compensate(String uid, String money);


    void queryCompensate(String uid);
}
