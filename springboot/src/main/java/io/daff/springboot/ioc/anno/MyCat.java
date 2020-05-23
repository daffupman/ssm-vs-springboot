package io.daff.springboot.ioc.anno;

import io.daff.springboot.ioc.xml.Animal;
import io.daff.springboot.ioc.xml.Cat;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author daffupman
 * @since 2020/5/23
 */
@Component
public class MyCat implements FactoryBean<Animal> {

    @Override
    public Animal getObject() throws Exception {
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }
}
