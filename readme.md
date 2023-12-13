# rewrite of spring framework


## 实现IOC功能
1. 进行接口和抽象类的层级划分，不同的接口或者抽象类完成不同的任务和功能
- 单例模式
  - 提供单例池singtonObjects进行缓存

- 当前的代码通过组合的方式，实现对于singleton对象与beandefinition对象的融合的处理，包括查询和增加