package io.daff.springboot.ioc.anno;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/23
 */
// @Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("worker".equalsIgnoreCase(beanName)) {
            return new Worker();
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("worker".equalsIgnoreCase(beanName)) {
            Worker worker = (Worker) bean;
            worker.setName("daffupman");
        }
        return false;
    }
}
