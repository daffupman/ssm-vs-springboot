package io.daff.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.daff.springboot.mapper")
public class SpringbootApplicationWithShutdownHook {

    public static void main(String[] args) {
        System.out.println("Running . . .");
        // 添加shutdownhook钩子方法，在jvm关闭时回调
        Thread jvm_close = new Thread(() -> System.out.println("jvm close"));
        Runtime.getRuntime().addShutdownHook(jvm_close);
        SpringApplication.run(SpringbootApplicationWithShutdownHook.class, args);
        // 移除
        Runtime.getRuntime().removeShutdownHook(jvm_close);
    }

}
