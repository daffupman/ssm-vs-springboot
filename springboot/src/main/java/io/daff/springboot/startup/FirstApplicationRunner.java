package io.daff.springboot.startup;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/24
 */
@Component
@Order(1)
public class FirstApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\u001B[32m >>> startup first application <<<");
    }
}
