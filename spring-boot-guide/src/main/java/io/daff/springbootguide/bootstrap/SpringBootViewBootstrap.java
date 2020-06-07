package io.daff.springbootguide.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author daffupman
 * @since 2020/6/7
 */
@SpringBootApplication(scanBasePackages = {"io.daff.springbootguide"})
public class SpringBootViewBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootViewBootstrap.class, args);
    }
}
