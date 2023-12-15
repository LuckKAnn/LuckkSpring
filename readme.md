# rewrite of spring framework


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

