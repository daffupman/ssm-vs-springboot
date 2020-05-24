package io.daff.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@SpringBootApplication
@MapperScan("io.daff.springboot.mapper")
@PropertySource({"classpath:prop.properties"})
public class SpringbootApplicationWithProperties {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringbootApplicationWithProperties.class);
        Properties properties = new Properties();
        properties.setProperty("name1", "value1");
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }

}
