package io.daff.springboot.ioc.anno;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/23
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition teacherBeanDefinition = configurableListableBeanFactory.getBeanDefinition("teacher");
        MutablePropertyValues propertyValues = teacherBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("name", "daffupman");
    }
}
