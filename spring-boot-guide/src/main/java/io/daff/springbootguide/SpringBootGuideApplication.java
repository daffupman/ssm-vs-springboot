package io.daff.springbootguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "io.daff.springbootguide.web.servlet")
public class SpringBootGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGuideApplication.class, args);
    }

}
