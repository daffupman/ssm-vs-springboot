package io.daff.springboot.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/24
 */
@Component
@Order(2)
public class SecondCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\u001B[32m >>> startup second runner <<<");
    }
}
