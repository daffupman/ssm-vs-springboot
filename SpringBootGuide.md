# Spring Boot实战

## Spring Boot特点

- 组件自动装配：约定大于配置，专注核心业务；（模式注解、@Enable模块、条件装配、加载机制）；
- 外部化配置：一次构建、按需调配，到处运行；(Environment抽象，生命周期，破坏性变更)
- 嵌入式容器：内置容器，无需部署，独立运行；（Servlet、Reactive）
- Spring Boot Starter：简化依赖，按需装配，自我包含；（依赖管理，装配条件，装配顺序）
- Production-Ready：一站式运维，生态无缝整合。（健康检查、数据指标、@EndPoint管控）

## Spring Boot和Java EE规范

- Web:Servlet(JSR 315.JSR 340)
- SQL:JDBC(JSR 221)
- 数据校验：Bean Validation(JSR 303, JSR 349)
- 缓存：Java Caching API(JSR 107)
- WebSockets:Java API for WebSocket(JSR 356)
- Web Services:JAX-WS(JSR-224)
- Java管理：JMX(JSR 3)
- 消息：JMS(JSR-914)

## 核心特性

### Spring Boot的三大特性

- 组件自动装配：Web MVC、Web Flux、JDBC等：
    - 激活：@EnableAutoConfiguration
    - 配置：/META-INFO/spring.factories
    - 实现：XxxAutoConfiguration

- 嵌入式Web容器：Tomcat、Jetty以及Undertow：
    - Servlet：Tomcat、Jetty
    - Reactive：Netty

- 生产准备特性：指标、健康检查、外部化配置等：
    - 指标：/actuator/metrics
    - 健康检查：/actuator/health
    - 外部化配置：/actuator/configprops

### Web应用

- 传统Servlet应用：
    - Servlet组件：Servlet、Filter、Listener。
    - Servlet注册：@WebServlet注解、Spring Bean、RegistrationBean
    - 异步非阻塞：异步Servlet、非阻塞Servlet

- Spring Web MVC应用：
    - Web MVC视图：模板引擎、内容协商、异常处理等；
        - ViewResolver、View
        - Thymeleaf、Freemarker、JSP
        - ContentNegotiationConfigurer、ContentNegotiationStrategy、ContentNegotiatingViewResolver
        - @ExceptionHandler、HandlerExceptionResolver（ExceptionHandlerExceptionResolver）、BasicErrorController
    - Web MVC REST：资源服务、资源跨域、服务发现等；
        - @RequestMapping、@RequestBody、@ResponseBody
        - CrossOrigin、WebMvcConfigurer#addCorsMappings
        - HATEOS
    - Web MVC核心：核心架构、处理流程、核心组件。
        - DispatcherServlet、HandleMapping、HandlerAdapter、ViewResolver

- Spring Web Flux应用：
    - Reactor基础：lambda、Mono、Flux
    - Web Flux核心：Web MVC注解、函数式声明、异步非阻塞
    - Web Flux的优劣
    
- Web Server应用
    - 切换Web Server：jetty,netty
    - 自定义Servlet Web Server
        - WebServerFactoryCustomizer
    - 自定义Reactive Web Server
        - ReactiveWebServerFactoryCustomizer

### 数据相关

- 关系型数据
    - JDBC：数据源、JdbcTemplate、自动装配
        - `javax.sql.DataSource`、`JdbcTemplate`、`DataSourceAutoConfiguration`
    - JPA：实体映射关系、实体操作、自动装配
        - `javax.persistence.*`、`HibernateJpaAutoConfiguration`
    - 事务：Spring事务抽象、JDBC事务处理、自动装配
        - `PlatformTransactionManager`、`DataSourceTransactionManager`、`TransactionAutoConfiguration`

### 功能拓展

- Spring Boot应用
    - SpringApplication：失败分析、应用特性、事件监听等
        - FailureAnalysisReporter、Fluent API、ConfigurationProperty、@Profile、PropertySource
    - Spring Boot配置：外部化配置、Profile、配置属性
    - Spring Boot Starter：Starter开发、最佳实践

### 运维管理

- Spring Boot Actuator
    - 端点：各类Web和JMX EndPoints
    - 健康检查：Health、HealthIndicator
    - 指标：内建Metrics、自定义Metrics
    
## 自动装配

### Spring Framework的手动装配

一种用于声明在应用中扮演组件角色的注解，如@Component、@Service、@Configuration等，可通过`<context:component-scan>`或@ComponentScan来装配。这些注解时可派生和具有层次性的。

@Enable模块装配：具备相同领域的功能组件集合，组合形成一个独立的单元，如@EnableWebMvc、@EnableAutoConfiguration等。可通过注解和编程两种方式来实现。

- Spring Framework
    - @EnableWebMvc：激活Web Mvc模块；
    - @EnableTransactionManagement：事务管理模块；
    - @EnableCaching：Caching模块；
    - @EnableMBeanExport：JMX模块；
    - @EnableAsync：异步处理模块；
    - @EnableWebFlux：Web Flux模块；
    - @EnableAspectJAutoProxy：AspectJ代理模块；
- Spring Boot
    - @EnableAutoConfiguration：自动装配模块
    - @EnableManagementContext：Actuator管理模块
    - @EnableConfigurationProperties：配置属性绑定模块
    - @EnableOAuth2Sso：OAuth2单点登录模块
- Spring Cloud
    - @EnableEureka：Eureka服务模块
    - @EnableConfigServer：配置服务模块
    - @EnableFeignClients：Feign客户端模块
    - @EnableZuulProxy：服务网关Zuul模块
    - @EnableCircuitBreaker：服务熔断模块
    
Spring条件装配：Bean装配前的前置判断，配置化条件装配@Profile、编程条件装配@Conditional

### Spring Boot自动装配

基于约定大于配置的原则，实现Spring组件自动装配。可以通过以下方式来自动装配：
- 模式注解
- @Enable模块
- 条件装配
- 工厂加载机制

实现步骤：
- 激活自动装配：@EnableAutoConfiguration
- 实现自动装配：XxxAUtoConfiguration
- 配置自动装配实现：META-INF/spring.factories

## 理解SpringApplication

### SpringApplication准备阶段

#### 基础技术

Spring Framework：
- Spring模式注解
- Spring应用上下文
- Spring工厂加载机制
- Spring应用上下文初始器
- Spring Environment抽象
- Spring应用事件/监听器

Spring Boot：
- SpringApplication
- SpringApplication Builder API
- SpringApplication运行监听器
- SpringApplication参数
- SpringApplication故障分析
- SpringApplication应用事件/监听器

SpringApplication：Spring应用引导类，提供便利的自定义行为方法。可运用在嵌入式Web应用和非Web应用。

- 配置Spring Bean的来源（xml和配置类）
- Web应用类型和主引导类
- 加载应用上下文初始化器和应用事件监听器

### SpringApplication运行阶段

- 加载SpringApplication运行监听器
    - 启动成功：创建应用上下文初始化器、Environment等
        - 根据准备阶段的推断Web应用类型创建对应的Configurable。
            - Web Reactive：AnnotationCOnfigReactiveWebServerApplicationContext
            - Web Servlet：AnnotationConfigServletWebServerApplicationContext
            - 非Web：AnnotationConfigApplicationContext
        - 根据准备阶段的推断Web应用类型创建对应的ConfigurableEnvironment实例
            - Web Reactive/非Web：StandardEnvironment
            - Web Servlet：StandardServletEnvironment
    - 启动失败：故障分析报告
    - 回调CommandLineRunner、ApplicationRunner
- 运行SpringApplication运行监听器：利用Spring工厂加载机制，读取SpringApplicationRunListener对象集合，并且封装到组合类SpringApplicationRunListeners。SpringApplicationRunListeners监听多个运行状态方法：
    - starting()：Spring应用刚启动
    - environmentPrepared(ConfigurableEnvironment)：ConfigurableEnvironment准备妥当，允许将其调整
    - contextPrepared(ConfigurableApplicationContext)：ConfigurableApplicationContext，允许将其调整
    - contextLoaded(ConfigurableApplicationContext)：ConfigurableApplicationContext已装载，但仍未启动
    - started(ConfigurableApplicationContext)：ConfigurableApplicationContext已启动，此时Spring Bean已初始化完成
    - running(ConfigurableApplicationContext)：Spring应用正在运行
    - failed(ConfigurableApplicationContext, Throwable)：Spring应用运行失败
- 监听SpringBoot事件、Spring事件：Spring Boot通过SpringApplicationRunListener的实现类EventPublishingRunListener利用Spring Framework事件API，广播Spring Boot事件。

## Web MVC核心

### Spring MVC的架构
- Servlet的基础架构

    ![Servlet的基础架构](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200606192916.png)

    Servlet特点：
    - 请求/响应式（Request/Response）
    - 屏蔽网络通信的细节
    - 完整的生命周期
    Servlet的职责：
    - 处理请求：对请求进行处理，并返回响应。主要接受请求中的请求头（header）和请求体（body）中的数据来做处理；
    - 资源管理：Servlet在装配时，可能需要连接jdbc数据库，加载缓存或redis等；
    - 视图渲染：以html或json响应出去。

- Spring Web MVC架构

    FrontController前端控制器
    
    ![前端控制器架构](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200606194007.png)

    客户端发送请求给前端控制器，前端控制器将请求派发给ApplicationController。ApplicationController会调用相应的服务来处理，此时会产生数据。可以把这些数据和视图结合，响应回去。

### Spring MVC的认识和简化

- SpringFramework时代的认识

    使用的步骤：
    
    - 需要实现Controller
        ```java
        @Controller
        public class HelloController {
          
            @RequestMapping
            public String index(Model model) {
              return "index";
            }   
        }
        ```
    - 配置Web MVC组件
        ```xml
        <!-- 指定组件扫描的包 -->
        <context:component-scan base-package="com.io.daff"/>
        
        <!-- 配置RequestMappingHandlerMapping -->
        <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>
        
        <!-- 配置RequestMappingHandlerAdapter -->  
        <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>
      
        <!-- 配置视图解析器 -->  
        <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
            <property name="prefix" value="/WEB-INF/views"/>
            <property name="suffix" value=".jsp"/>
        </bean>
        ```
    - 部署DispatcherServlet
    
        在web.xml文件中配置servlet：
        ```xml
        <servlet>
            <servlet-name>app</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>/WEB-INF/application-context.xml</param-value>
            </init-param>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>app</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
        ```
    
    Web MVC核心组件：
    
    处理器管理（Handler）；映射HandlerMapping，适配HandlerAdapter；执行HandlerExecutionChain；
    
    页面渲染：视图解析（ViewResolver），国际化（LocaleResolver，LocaleContextResolver），个性化（ThemeResolver）
    
    异常处理：异常解析（HandlerExceptionResolver）
    
    ![交互流程](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200606211113.png)
    
    Web MVC注解驱动：
    
    注解配置：@Configuration（Spring范式注解）
    
    组件激活：@EnableWebMvc（Spring模块装配），自动导入DelegatingWebMvcConfiguration
    
    自定义组件：WebMvcConfigurer（Spring Bean）
    
    模型属性：@ModelAttribute
    
    请求头：@RequestHeader
    
    Cookie：@CookieValue
    
    检验参数：@Valid、@Validated
    
    注解处理：@ExceptionHandler
    
    切面通知：@ControllerAdvice执行通用的逻辑
    
    Web MVC自动装配：
    
    需要 Servlet3.0+ 的依赖；
    
    Servlet SPI（ServletContainerInitializer）：在容器启动的时候会回调SPI
    
    Spring 适配：SpringServletContainerInitializer
    
    Spring SPI（WebApplicationInitializer）
    
    编程驱动：AbstractDispatcherServletInitializer
    
    注解驱动：AbstractAnnotationConfigDispatcherServletInitializer
    
- Spring Boot时代的简化

    完全自动装配：DispatcherServletAutoConfiguration     
    
    替换@EnableWebMvc：WebMvcAutoConfiguration
    
    Servlet容器：ServletWebServerFactoryAutoConfiguration
    
    自动装配的顺序：绝对顺序@AutoConfigureOrder，相对顺序@AutoConfigureAfter
    
    装配条件：
    
    Web类型判断（@ConditionalOnWebApplication）：Servlet类型为WebApplicationType.SERVLET
    
    API依赖（@ConditionalOnClass）：Servlet（Servlet）、Spring Web MVC（DispatcherServlet、WebMvcConfigurer）
    
    Bean判断（@ConditionalOnMissingBean、@ConditionalOnBean）：WebMvcConfigurationSupport
    
    外部化配置：
    
    Web MVC配置：WebMvcProperties
    
    资源配置：ResourceProperties
    
## Web MVC视图应用

### 模板引擎

thymeleaf是新一代服务端的、html友好的模板引擎，也是Spring官方推荐的模板引擎。但是thymeleaf的性能并不理想，可以将thymeleaf与jsp混合使用。

thymeleaf依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

核心要素：
- 资源定位：模板来源
    - 通用资源抽象：文件资源（File）、ClassPath资源（ClassLoader）、统一资源（URL）、Web资源（ServletContext）
    - Spring资源：Resource
- 渲染上下文：变量来源
    - thymeleaf上下文（Context）、Spring Web MVC模型（Model）、Servlet上下文（Attribute）
- 模板引擎：模板渲染
    - `ITemplateEngine`实现：
        - TemplateEngine：thymeleaf原生实现
        - SpringTemplateEngine：Spring实现
        - SpringWebFluxTemplateEngine：Spring WebFlux实现

### 视图处理

Spring Web MVC视图组件
    - 视图解析器：ViewResolver
    - 视图：View
    - 总控：DispatcherServlet

Thymeleaf整合Spring Web MVC
- 视图解析器：ThymeleafViewResolver
- 视图：ThymeleafView
- 渲染：SpringTemplateEngine

![视图解析器运行原理](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200607103149.png)

多视图解析器并存
- 视图解析器：
    - ThymeleafViewResolver
    - InternalResourceViewResolver
- 目的：
    - 理解ViewResolver Order
    - 理解ViewResolver 模板资源查找
    - 自定义ViewResolver Order

### 视图内容协商

Web根据不同的请求策略，实现服务端响应对应视图内容输出。这些策略实现包括：

- Accept请求头：Accept:text/*，HeaderContentNegotiationStrategy
- 请求query参数：/path?format=pdf，ParameterContentNegotiationStrategy
- 路径拓展名：/abc.pdf,PathExtensionContentNegotiationStrategy
- 固定MediaType：FixedContentNegotiationStrategy

内容协商的核心组件：

- 视图解析器：ContentNegotiatingViewResolver

    - 关联ViewResolver Bean列表
    
        - InternalResourceViewResolver
        - BeanNameViewResolver
        - ThymeleafViewResolver
    
    - 关联ContentNegotiationManager Bean
    - 解析最佳匹配View

- 内容协商管理器：ContentNegotiatingManager

    - 由ContentNegotiatingConfigurer配置
    - 由ContentNegotiatingManagerFactoryBean创建
    - 关联ContentNegotiationStrategy集合

- 内容协商策略：ContentNegotiatingStrategy

![内容协商时序图](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200607161206.png)

### 视图组件的自动装配

自动装配：

- Web MVC：WebMvcAutoConfiguration
- Thymeleaf Web MVC：ThymeleafWebMvcConfiguration
- Thymeleaf Web Flux：ThymeleafWebFluxConfiguration

外部化配置：

- 内容协商：WebMvcProperties.ContentNegotiation
- Thymeleaf：ThymeleafProperties