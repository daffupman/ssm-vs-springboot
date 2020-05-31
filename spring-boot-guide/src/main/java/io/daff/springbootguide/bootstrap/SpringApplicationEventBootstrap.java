package io.daff.springbootguide.bootstrap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author daffupman
 * @since 2020/5/31
 */
public class SpringApplicationEventBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.addApplicationListener(event -> {
            System.out.println("监听到的事件：" + event);
        });
        ac.refresh();
        ac.publishEvent("hi");
        ac.close();
    }
}
