package io.daff.springboot.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义系统初始化器（方法一）：继承ApplicationContextInitializer接口
 * 同时在resource目录下新建META-INF/spring.factories文件，写入该接口的实现
 *
 * @author daffupman
 * @since 2020/5/21
 */
@Order(1)   // 指定加载顺序，order值越小越先执行
public class FirstInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        // 将自定义的kv对放入ConfigurableEnvironment对象中
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("key1", "value1");
        }};
        MapPropertySource mapPropertySource = new MapPropertySource("firstInitializer", map);

        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        environment.getPropertySources().addLast(mapPropertySource);
        System.out.println("FirstInitializer Running...");
    }
}
