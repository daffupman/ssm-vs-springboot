package io.daff.springboot.service.impl;

import io.daff.springboot.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author daffupman
 * @since 2020/5/21
 */
@Service
public class TestServiceImpl implements TestService {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String test1() {
        return applicationContext.getEnvironment().getProperty("key1");
    }

    @Override
    public String test2() {
        return applicationContext.getEnvironment().getProperty("key2");
    }

    @Override
    public String test3() {
        return applicationContext.getEnvironment().getProperty("key3");
    }
}
