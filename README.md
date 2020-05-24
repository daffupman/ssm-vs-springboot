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

## 属性配置解析

### 属性配置

Spring提供属性配置的17种方式（有顺序）：
- Devtools全局配置
- 测试环境@TestPropertySource注解
- 测试环境properties属性
- 命令行参数
- SPRING_APPLICATION_JSON属性：在启动时，指定Program arguments,值为json字符串
- ServletConfig初始化参数
- ServletContext初始化参数
- JNDI属性
- Java系统属性
- 操作系统环境变量
- RandomValuePropertySource随机值属性
- jar包外的application-{profile}.properties
- jar包内的application-{profile}.properties
- jar包外的application.properties
- jar包内的application.properties
- @PropertySource绑定配置：需要在resource目录下新建properties文件，填写kv属性对。在启动类上加上@PropertySource注解并指定属性文件。
- 默认属性

### Spring Aware

Spring中的Bean本身是感受不到容器的存在的,但是可以通过Aware接口感知到Spring容器。

常用的Aware：
- BeanNameAware：获取容器中bean名称
- BeanClassLoaderAware：获取类加载器
- BeanFactoryAware：获取的beanFactory
- EnvironmentAware：获取环境变量
- EmbeddedValueResolverAware：获取spring容器加载的properties文件属性值
- ResourceLoaderAware：获取资源加载器
- ApplicationEventPublisherAware：获取应用事件发布器
- MessageSourceAware：获取文本信息
- ApplicationContextAware：获取当前应用上下文

原理：

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200524140219.png)

自定义Aware：

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200524140339.png)

### Environment

获取属性的过程：
- AbstractEnvironment#getProperty
- PropertySourcesPropertyResolver#getProperty
- 遍历propertySources集合获取属性

Environment对象填充propertySource集合的过程：
- ConfigurableEnvironment#prepareEnvironment
    - getOrCreateEnvironment：根据当前环境返回相应的Environment对象。调用子类的customizePropertySource方法：
        - 添加servletConfigInitParams属性集
        - 添加servletContextInitParams属性集
        - 添加Jndi属性集
        - 添加systemProperties属性集
        - 添加systemEnvironment属性集
- configEnvironment：调用configPropertySource方法
    - 添加defaultProperties属性集
    - 添加commandLineArgs属性集
- listeners#environmentPrepared
    - 添加SPRING_APPLICATION_JSON属性集
    - 如果当前环境是springcloud，添加vcap属性集
    - 添加random属性集
    - 添加application-profile.(properties|yml)属性集
- ConfigurationPropertySource#attach
    - 添加configurationProperties属性集
- ConfigurationClassParser：@PropertySource注解的解析器
    - 将@PropertySource配置的属性文件中的所有属性加载进来

### Spring Profile

将不同的配置参数绑定在不同的环境中。默认使用application.properties文件，该文件默认激活application-default.properties。
- 可以使用启动参数 `spring.profiles.default=...` 来切换环境。
- 可以使用启动参数 `spring.profiles.active=...` 来集合profile文件
- 使用 `spring.profiles.include` 可以引入多个属性文件
- spring.profiles.active与default互拆
- 使用 `spring.config.name=..` 可以将profile前缀修改为自定义的（默认为application）

原理：
- 处理入口：
    - ConfigFileApplicationListener#onApplicationEvent
    - postProcessEnvironment
    - addPropertySource
    - Loader#load
    
- initializeProfiles逻辑

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200524195414.png)

profile处理逻辑：
- 遍历getSearchLocations:
    - 读取spring.config.location
    - spring.config.additional-location
    - classpath:/ , classpath:/config/, file:./ , file:./config/
- 遍历getSearchNames：
    - 检查是否指定spring.config.name
    - 默认为application
- 遍历propertySourceLoaders：
    - propertiesPropertySourceLoader：properties或xml文件的属性
    - yamlPropertySourceLoader：yml|yaml文件的属性
- loadForFileExtension
- load
    - 尝试读取application-profile.xx文件；
    - 如果资源存在，则loadDocuments读取文件属性；
    - 将文件内激活的profile添加到profiles集合中；
    - 将文件内定义的属性放入loaded集合中
- addLoadedPropertySources
    - 获取Environment的propertySource集合对象destination
    - 遍历loaded集合
    - 依次将集合内属性集添加到destination中
    
### 小节

1. SpringBoot属性配置的方式？
2. Spring Aware的作用和常见的有哪些？
3. Spring Aware注入原理？
4. 动手写一个Spring Aware？
5. Environment对象是如何加载属性集的？
6. Spring profile是什么，常用配置方式？
7. Spring profile配置方式有哪些注意事项？
8. Spring profile处理逻辑？