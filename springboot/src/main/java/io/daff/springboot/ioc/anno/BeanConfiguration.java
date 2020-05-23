package io.daff.springboot.ioc.anno;

import io.daff.springboot.ioc.xml.Animal;
import io.daff.springboot.ioc.xml.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author daffupman
 * @since 2020/5/23
 */
@Configuration
public class BeanConfiguration {

    @Bean("dog")
    Animal getDog() {
        return new Dog();
    }
}
