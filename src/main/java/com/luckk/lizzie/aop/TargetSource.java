package com.luckk.lizzie.aop;

/**
 * 这个其实是模拟的需要包含原有的对象？
 *
 * @Author liukun.inspire
 * @Date 2023/12/21 21:37
 * @PackageName: com.luckk.lizzie.aop
 * @ClassName: TargetSource
 * @Version 1.0
 */
public class TargetSource {

    public Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public Class<?>[] getTargetClass() {
        return target.getClass().getInterfaces();
    }
}
