package io.daff.springboot;

import io.daff.springboot.initializer.SecondInitializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// @MapperScan("io.daff.springboot.mapper")
public class SpringbootApplicationWithCustomizedInitializer {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootApplicationWithCustomizedInitializer.class);
        // 塞入系统初始化器
        springApplication.addInitializers(new SecondInitializer());
        springApplication.run(args);
    }

}
