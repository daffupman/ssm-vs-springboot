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

## 监听器解析

### 监听器模式介绍

![监听器](https://raw.githubusercontent.com/daffupman/markdown-img/master/%E6%97%A0%E6%A0%87%E9%A2%98.jpg)

### 系统监听器介绍

SpringBoot中的监听器：

- 系统监听器：`ApplicationListener`
- 系统广播器：`ApplicationEventMulticaster`

系统事件：

- `EventObject`
- `ApplicationEvent`
- `SpringApplicationEvent`
- `ApplicationContextInitializedEvent` | `ApplicationEnvironmentPreparedEvent` | `ApplicationFailedEvent` | `ApplicationPreparedEvent` | `ApplicationReadyEvent` | `ApplicatitonStartedEvent` | `ApplicationStartingEvent`

事件发送顺序：

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200522210244.png)

监听器可以通过在spring.factories文件中配置来注册。

### 监听事件触发机制

- 注册监听器实现
- 获取感兴趣的监听器列表
- 事件触发条件

### 自定义监听器

- 方式一：
    - 自定义监听器实现ApplicationListener
    - 在spring.factories中配置
- 方式二：
    - 自定义监听器实现ApplicationListener
    - 在SpringBoot启动类中，把自定义的监听器添加到SpringApplication实例中，再启动
- 方式三：
    - 自定义监听器实现ApplicationListener
    - 在application.properties文件中配置context.listener.classes
- 方式四：
    - 自定义监听器实现SmartApplicationListener
    - 重写supportsEventType和onApplicationEvent方法，supportsEventType指定感兴趣的事件，而onApplicationEvent则是在事件触发时的响应内容
    - 注入方式可以同前三种
    
    > 三者的执行顺序：application.properties > spring.factories > SpringApplication，前三种是监听一种事件，第四种可监听多种事件

### 小节

1. 介绍监听器模式？
2. SpringBoot关于监听器相关的实现类有哪些？
3. SpringBoot有哪些框架事件，它们的顺序如何？
4. 监听事件的触发机制？
5. 如果自定义实现系统监听器，注意事项？
6. 实现ApplicationListener和SmartApplicationListener的区别？