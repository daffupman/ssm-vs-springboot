package io.daff.springbootguide.annotation;

import io.daff.springbootguide.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Selector方式会更加灵活
 *
 * @author daffupman
 * @since 2020/5/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
// @Import(HelloWorldConfiguration.class)   // 只导入HelloWorldConfiguration.class
@Import(HelloWorldImportSelector.class) // 导入HelloWorldImportSelector.class，可以导入多个Configuration
public @interface EnableHelloWorld {
}
