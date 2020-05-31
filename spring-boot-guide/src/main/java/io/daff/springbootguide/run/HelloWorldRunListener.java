package io.daff.springbootguide.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;

/**
 * @author daffupman
 * @since 2020/5/31
 */
public class HelloWorldRunListener implements SpringApplicationRunListener {

    public HelloWorldRunListener(SpringApplication application, String[] args) {}

    @Override
    public void starting() {
        System.out.println("HelloWorldRunListener#starting running...");
    }
}
