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

### 小节