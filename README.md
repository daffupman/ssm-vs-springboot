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

## Bean解析

### IOC思想

把对象的创建权转交给Spring框架。

### Bean的配置方式

#### xml

- 无参
- 有参
- 静态工厂
- 工厂方法

#### 注解

- @Component/@Repository/@Service/@Controller
- 配置类中的@Bean 与 @Configuration
- 实现FactoryBean
- 实现BeanDefinitionRegistryPostProcessor
- 实现ImportBeanDefinitionRegistrar

### refresh方法

refresh方法是bean配置读取加载入口。具体流程如下：

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200523154800.png)

- `prepareRefresh`: 清除scanner（元数据工厂）缓存；设置容器状态；初始化属性设置；检查容器的必备属性是否存在。
    > 容器的必备属性可以通过ConfigurationEnvironment#setRequiredProperties方法来设置，此时需要在项目的配置文件中配置这个必须的属性，否则在项目启动时报错。
- `obtainFreshBeanFactory`: 设置BeanFactory的序列化id；获取BeanFactory；
- `prepareBeanFactory`: 设置BeanFactory一些属性；添加后置处理器；设置忽略的自动装配接口；注册一些组件；
- `postProcessorBeanFactory`：子类会重写该方法，以在BeanFactory完成创建后做进一步设置；
    > 如在Web环境中：添加BeanPostProcessor，忽略装配接口，注册web应用的作用域和环境配置信息。
- `invokeBeanFactoryPostProcessors`：
    - 遍历 `BeanFactoryPostProcessors集合` ，如果集合中的BeanFactoryPostProcessor实现了BeanDefinitionRegistryPostProcessor，那么调用它的 `postProcessorBeanDefinitionRegistry` 方法，同时添加到registryProcessor集合中；否则直接添加到regularPostProcessor中。
    - 遍历 `BeanFactory` 中 `BeanDefinitionRegistryPostProcessor`实现，如果它实现了 `PriorityOrdered` 接口，就把它添加到currentRegistryProcessors集合中，再添加到processedBeans集合中。然后对集合currentRegistryProcessors排序，将上述结果添加到registryProcessors集合中。currentRegistryProcessors集合内遍历调用postProcessorBeanDefinitionRegistry方法，清空集合currentRegistryProcessors。
    - 遍历 `BeanFactory` 中 `BeanDefinitionRegistryPostProcessor`实现，如果处理过且实现了 `Ordered` 接口，就把它添加到currentRegistryProcessors集合中，再添加到processedBeans集合中。然后对集合currentRegistryProcessors排序，将上述结果添加到registryProcessors集合中。currentRegistryProcessors集合内遍历调用postProcessorBeanDefinitionRegistry方法，清空集合currentRegistryProcessors。
    - 循环上一步，直到BeanFactory中不存在未处理的BeanDefinitionRegistryPostProcessor实现，registryProcessors集合内对象依次调用postProcessorBeanFactory方法，regularPostProcessors集合内对象依次调用postProcessBeanFactory方法。
    
    该方法主要作用是：1）调用BeanDefinitionRegistryPostProcessor实现向容器内添加bean定义；2）调用BeanFactoryPostProcessor实现向容器内bean的定义添加属性；
- `registerBeanPostProcessors`：
    - 找到BeanPostProcessor实现
    - 排序后注册进容器内
- `initMessageSource`：初始化国际化相关属性
- `initApplicationEventMulticaster`：初始化事件广播器
- `onRefresh`：创建Web容器；
- `registerListeners`：
    - 添加容器内事件监听器至事件广播器中；
    - 派发早期事件；
- `finishBeanFactoryInitialization`：初始化剩下的单实例Bean；
- `finishRefresh`：
    - 初始化生命周期处理器；
    - 调用生命周期处理器的onRefresh方法；
    - 发布ContextRefreshedEvent事件；
    - JMX相关处理；
    
### bean实例化解析

getBean -> doGetBean -> getSingleton -> CreateBean -> resolveBeforeInstantiation -> doCreateBean -> createBeanInstance -> instantiateBean -> instantiate -> populateBean -> initializeBean

### 小节

1. ioc思想？
2. springboot中bean的配置方式？
3. refresh方法的流程？
4. bean实例化流程？

## SpringBoot banner解析

### banner设置

在resource目录下新建banner.txt，将图案或文字放入banner.txt文件中；可以指定banner.txt为自定义的文件名，然后在配置文件中配置（key为spring.banner.location/spring.banner.image.location）。可以在txt文本中使用${}。spring.banner.charset指定字符集。
- 文字：banner.txt
- 图案：banner.jpg|png|gif

通过SpringApplication#setBanner设置兜底banner。
> spring.main.banner-mode=off # 禁用banner
> 可以通过设置spring.banner.* 来设置更多的属性

### banner设置原理

`Banner printedBanner = printBanner(environment);`

输出banner逻辑：
- 获取banner：
- 打印banner：getImageBanner/getTextBanner

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200523233857.png)

### 小节

1. banner的配置方式？
2. banner的打印流程？
3. 获取banner的原理？
4. 输出banner的原理？
5. banner的一些常见属性？

## 启动加载器解析

### 计时器

`StopWatch` 用来统计任务耗时。

### 案例

- 方式一：
    - 实现CommandLineRunner接口
    - 重写run方法
- 方式二：
    - 实现ApplicationRunner接口
    - 重写run方法
    - 通过@Order排序
> 排序规则：order值可以指定顺序，order值相同ApplicationRunner实现优先。

### 原理

callRunner实现：
- 添加ApplicationRunner实现至Runners集合；
- 添加CommandLineRunner实现至Runners集合；
- 对Runners集合排序；
- 遍历Runners集合调用实现类的run方法；

同Order值下，ApplicationRunner实现优先于CommandLineRunner；两者的入参也不一致。

### 小节

1. 如何设计一个计时器？
2. 怎么实现在SpringBoot启动后执行程序？
3. 启动加载器如何实现？
4. 启动加载器实现的异同？
5. 启动加载器的调用时机？