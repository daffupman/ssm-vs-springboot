package io.daff.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义系统初始化器（方法三）:继承ApplicationContextInitializer接口
 * 然后在application.properties文件中配置context.initializer.classes为该类的全限定名称
 *
 * @author daffupman
 * @since 2020/5/21
 */
@Order(3)   // 指定加载顺序
public class ThirdInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        // 将自定义的kv对放入ConfigurableEnvironment对象中
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("key3", "value3 ");
        }};
        MapPropertySource mapPropertySource = new MapPropertySource("thirdInitializer", map);

        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("ThirdInitializer Running...");
    }
}
