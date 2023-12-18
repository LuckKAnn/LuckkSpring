package com.luckk.lizzie.beans.factory.supports;

import com.luckk.lizzie.beans.factory.DisposableBean;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * @Author liukun.inspire
 * @Date 2023/12/18 21:03
 * @PackageName: com.luckk.lizzie.beans.factory.supports
 * @ClassName: DisposableBeanAdapter
 * @Version 1.0
 */
public class DisposableBeanAdapter implements DisposableBean {


    private Object bean;

    private String destroyMethod;

    public DisposableBeanAdapter(Object bean, String destroyMethod) {
        this.bean = bean;
        this.destroyMethod = destroyMethod;
    }


    @Override
    public void close() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).close();
        }

        if (StringUtils.isNotEmpty(destroyMethod) && !(bean instanceof DisposableBean && "close".equals(destroyMethod))) {
            Method method = bean.getClass().getMethod(destroyMethod);
            method.invoke(bean);
        }
    }
}
