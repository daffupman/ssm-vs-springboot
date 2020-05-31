package io.daff.springbootguide.configuration;

import io.daff.springbootguide.annotation.EnableHelloWorld;
import io.daff.springbootguide.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author daffupman
 * @since 2020/5/30
 */
@Configuration  // 模式注解装配
@EnableHelloWorld   // @Enable模块装配
@ConditionalOnSystemProperty(name = "user.name", value = "daffupman")   // 条件装配
public class HelloWorldAutoConfiguration {
}
