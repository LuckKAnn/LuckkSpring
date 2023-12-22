package com.luckk.lizzie.context.event;

import com.luckk.lizzie.beans.factory.BeansException;
import com.luckk.lizzie.context.ApplicationEvent;
import com.luckk.lizzie.context.ApplicationListener;
import com.luckk.lizzie.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 广播器
 *
 * @Author liukun.inspire
 * @Date 2023/12/20 11:34
 * @PackageName: com.luckk.lizzie.context.event
 * @ClassName: AbstractApplicationEventMulticaster
 * @Version 1.0
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {

    private final Set<ApplicationListener<ApplicationEvent>> listeners = new HashSet<>();


    @Override
    public void registerListener(ApplicationListener<?> applicationListener) {
        // 什么时候调用进行注册呢
        listeners.add((ApplicationListener<ApplicationEvent>) applicationListener);
    }

    @Override
    public void removeListener(ApplicationListener<?> listener) {
        listeners.remove((ApplicationListener<ApplicationEvent>) listener);
    }

    protected List<ApplicationListener<ApplicationEvent>> getApplicationListeners(ApplicationEvent event) {
        // 如何去获取到对应的可支持的listener的呢
        LinkedList<ApplicationListener<ApplicationEvent>> suitableListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : listeners) {
            if (supportsEvent(listener, event)) {
                suitableListeners.add(listener);
            }
        }
        return suitableListeners;
    }

    private boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        // TODO: Read， which can get real genericClass of a interface
        // 如何判断是否相同呢，理想情况下其实就是获取一个的泛型具体类
        // 另外
        Class<? extends ApplicationListener> listenerClass = listener.getClass();
        // 按照 CglibSubclassingInstantiationStrategy、
        // SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class

        // JDK 通过接口，cglib通过继承
        // TODO: read how this get the target real class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // 实现的接口？
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        // 实现的泛型的具体类型
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        // 类名
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。
        // 如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        // 前者是后者的超类？
        // 该监听器监听的事件能否有后者转换而来
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
