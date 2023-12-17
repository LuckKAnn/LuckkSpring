# rewrite of spring framework
Spring通过一个配置文件描述Bean和Bean之间的依赖关系，利用Java反射功能实例化Bean，并建立Bean之间的依赖关系。
Spring的IOC容器在完成这些底层工作的基础上，还提供了Bean实例缓存、生命周期管理、Bean实例代理、时间发布、资源装载等高级服务。
BeanFactory是Spring框架最核心的接口，它提供了高级IOC的配置机制。
ApplicationContext建立在BeanFactory的基础上，提供了更多面向应用的功能， 它提供了国际化支持和框架事件体系。

- 分包和拆包，根据不同的功能拆分不同的包
- 核心包尽量不要改动
    - core.io 主要参照spring本身的资源加载方式，单独处理资源加载，不经常变动

## 实现IOC功能

1. 进行接口和抽象类的层级划分，不同的接口或者抽象类完成不同的任务和功能

- 单例模式
    - 提供单例池singtonObjects进行缓存

- 当前的代码通过组合的方式，实现对于singleton对象与beandefinition对象的融合的处理，包括查询和增加

### 实例化策略

- Spring本身自带了对于有参bean的实例构造的getBean方法
    - 对于这种构造的方式，其实就有两种方式
    - 基于JDK的代理构造方式
    - 基于CGLIB的代理构造方式


- 依赖注入
    - 现阶段采用的是通过手动在BeanDefinition内部注册PV，在启动获取bean的时候进行手动查询注入或者反射注入
    - 抽象BeanReference来作为依赖其他Bean的表示

### 资源加载

- 抽象出资源加载器 ResourceLoader， 本质上不完成复杂的工作，完全是暴露接口提供给外部使用
- 抽象资源，Resource，资源根据路径进行自己独特的加载。比如说classpath，主要采用类加载器来进行动态的加载，将资源加载为流数据
- 抽象出BeanDefinitionReader，根据特定的方式进行BeanDefinition的读取和注册，接口定义规范，抽象类定义属性(面向接口)
  和部分共有办法，具体的实现类实现自定义逻辑，可以很方便地拓展到注解扫描的注册上面

### 应用上下文的抽象

- applicationContext抽象的上下文，可以进行刷新，提供给用户进行实际的使用
- 拓展BeanPostProcessor 方面对Bean执行拓展
- 拓展BeanFactoryPostProcessor 实现对Bean工厂创建的拓展
- 为什么AbstractApplicaionContext 需要去实现ClassLoader？应用上下文就是一个BeanFactory
- ConfigurableApplicationContext扩展于ApplicationContext，主要新增了两个方法 refresh()和close(),让Application具有启动、刷新、关闭应用上下文的能力。


BeanFactory是Spring框架的基础设施，面向Spring本身
ApplicationContext面向使用Spring框架的开发者，几乎所有的应用场合都可以直接使用Application而非底层的BeanFactory.