package com.luckk.lizzie.anno;

import com.luckk.lizzie.context.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author liukun.inspire
 * @Date 2023/12/25 12:26
 * @PackageName: com.luckk.lizzie.anno
 * @ClassName: TransferDao
 * @Version 1.0
 */
@Slf4j
@Component
public class TransferDao {

    public void doRealTransfer() {
        log.error("transfer transaction commit");
    }

}
