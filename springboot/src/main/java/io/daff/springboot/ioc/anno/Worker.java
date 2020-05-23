package io.daff.springboot.ioc.anno;

import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/23
 */
@Component
public class Worker {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
