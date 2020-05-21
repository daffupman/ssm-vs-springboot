package io.daff.springboot.service;

import org.springframework.context.ApplicationContextAware;

/**
 * @author daffupman
 * @since 2020/5/21
 */
public interface TestService extends ApplicationContextAware {

    String test1();

    String test2();

    String test3();
}
