package io.daff.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@MapperScan("io.daff.springboot.mapper")
public class SpringbootApplicationWithBanner {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();
        springApplication.setBanner(new ResourceBanner(new ClassPathResource("banner_bak.txt")));
        springApplication.run(args);
    }

}
