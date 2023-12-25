package com.luckk.lizzie.anno;

import com.luckk.lizzie.context.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 11:27
 * @PackageName: com.luckk.lizzie.anno
 * @ClassName: TransferServiceImpl
 * @Version 1.0
 */
@Slf4j
@Component
public class TransferServiceImpl implements TransferService {

    private String token;


    @Override
    public void doTransfer(String from, String target) {
        log.error("Do Transfer task from {} account to {} account with token :{}", from, target, token);
    }
}
