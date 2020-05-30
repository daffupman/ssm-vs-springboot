package io.daff.springbootguide.bootstrap;

import io.daff.springbootguide.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link CalculateService} 引导类
 *
 * @author daffupman
 * @since 2020/5/30
 */
@SpringBootApplication(scanBasePackages = "io.daff.springbootguide.service")
public class CalculateServiceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("Java8")  // 指定profile
                .run(args);

        CalculateService calculateService = context.getBean(CalculateService.class);
        System.out.println(calculateService.sum(1, 2, 3, 4, 5));

        context.close();
    }
}
