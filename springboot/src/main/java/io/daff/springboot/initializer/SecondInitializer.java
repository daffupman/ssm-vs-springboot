package io.daff.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义系统初始化器（方法二）:继承ApplicationContextInitializer接口
 * new一个SpringApplication对象，把该系统初始化器放入SpringApplication对象中，然后启动
 *
 * @author daffupman
 * @since 2020/5/21
 */
@Order(2)   // 指定加载顺序
public class SecondInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        // 将自定义的kv对放入ConfigurableEnvironment对象中
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("key2", "value2 ");
        }};
        MapPropertySource mapPropertySource = new MapPropertySource("secondInitializer", map);

        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("SecondInitializer Running...");
    }
}
