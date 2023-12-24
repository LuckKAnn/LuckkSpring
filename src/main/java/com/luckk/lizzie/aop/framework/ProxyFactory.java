package com.luckk.lizzie.aop.framework;

import com.luckk.lizzie.aop.AdvisedSupport;
import com.luckk.lizzie.aop.TargetSource;

/**
 * 代理工厂，意思是从这里获取对应的代理
 *
 * @Author liukun.inspire
 * @Date 2023/12/24 21:36
 * @PackageName: com.luckk.lizzie.aop.framework
 * @ClassName: ProxyFactory
 * @Version 1.0
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    public AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
