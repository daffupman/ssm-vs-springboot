# Spring Boot深入♂学习笔记

## Spring Boot 框架启动

 - 计时器开始计时
 - Headless模式赋值
 - 发送ApplicationStartingEvent
 - 配置环境模块
 - 发送ApplicationEnvironmentPreparedEvent
 - 打印banner
 - 创建应用上下文对象
 - 初始化失败分析器 
 - 关联springboot组件与应用上下文对象 
 - 发送ApplicationContextInitializedEvent 
 - 加载sources到context 
 - 发送ApplicationPreparedEvent 
 - 刷新上下文 
 - 计时器停止计时 
 - 发送ApplicationStartedEvent 
 - 调用框架启动扩展类 
 - 发送ApplicationReadyEvent

## 框架自动化装配步骤

 - 收集配置文件中的配置工厂类
 - 加载组件工厂
 - 注册组件内定义bean
 
## Spring Boot系统初始化器解析

### 系统初始化器介绍

ApplicationContextInitializer(系统初始化器)是Spring容器刷新之前执行的一个回调函数，可以向SpringBoot容器中注册属性。一般可以继承接口自定义实现。

- 方式一：

    - 实现ApplicationContextInitializer接口，
    - spring.factories内填写接口实现
    - key为org.springframework.context.ApplicationContextInitializer
    > 定义在spring.factories文件中被SpringFactoriesLoader发现注册
    
- 方式二：

    - 实现ApplicationContextInitializer接口，
    - SpringApplication类初始化后设置进去
    > SpringApplication初始化完毕之后手动添加
    
 - 方式三：
    
    - 实现ApplicationContextInitializer接口
    - application.properties内填写接口实现
    - key为context.initializer.classes
    > 定义成环境变量被DelegatingApplicationContextInitializer发现注册；
      Order值越小越先执行，但application.properties内定义的会优先于另外两种方式执行；

### SpringFactoriesLoader介绍

springboot通过SpringFactoriesLoader识别并注入进容器。

- SpringFactoriesLoader是框架内部使用的通用工厂加载机制；
- 从classpath下多个jar包特定的位置读取文件并初始化类；
- 文件内容必须是kv形式，即properties类型；
- key是全限定名（抽象类、接口），value是其实现，多个实现使用逗号分隔；

loadFactories流程：

- 使用key去缓存中查找，如果存在的话直接返回，否则下走；
- 读取指定的资源文件名（spring.factories）;
- 构造Properties对象；
- 获取指定key对应的value值；
- 逗号分隔value值，保存结果到缓存中；
- 依次实例化结果对象；
- 对结果对象排序，并返回结果；

在上下文刷新（refresh）方法之前调用；用来编码设置一些属性变量通常在web环境中；可以通过order接口进行排序；

### 系统初始化器原理解析

系统初始化器的调用流程：

- run()：框架启动
- prepareContext()：上下文准备
- applyInitializers()：调用系统初始化器
- 遍历调用系统初始化器

### 总结
    
