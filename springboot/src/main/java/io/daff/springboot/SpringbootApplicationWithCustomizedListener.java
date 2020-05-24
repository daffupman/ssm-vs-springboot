package io.daff.springboot;

import io.daff.springboot.listener.SecondListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// @MapperScan("io.daff.springboot.mapper")
public class SpringbootApplicationWithCustomizedListener {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootApplicationWithCustomizedListener.class);
        // 把自定义的监听器加入到SpringApplication对象中
        springApplication.addListeners(new SecondListener());
        // 启动
        springApplication.run(args);
    }

}
