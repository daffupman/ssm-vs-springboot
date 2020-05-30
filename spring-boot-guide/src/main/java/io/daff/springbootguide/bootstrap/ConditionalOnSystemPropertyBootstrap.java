package io.daff.springbootguide.bootstrap;

import io.daff.springbootguide.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author daffupman
 * @since 2020/5/30
 */
public class ConditionalOnSystemPropertyBootstrap {

    @ConditionalOnSystemProperty(name = "user.name", value = "daffupman")   // 满足@Condition注解的约定才能注入这个Bean
    // @ConditionalOnSystemProperty(name = "user.name", value = "!daffupman")
    @Bean
    public String hello() {
        return "hello world, man";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalOnSystemPropertyBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        System.out.println(context.getBean("hello", String.class));

        context.close();
    }
}
