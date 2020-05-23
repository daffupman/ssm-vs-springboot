package io.daff.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.daff.springboot.mapper")
public class SpringbootApplicationWithBanner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplicationWithBanner.class, args);
    }

}
