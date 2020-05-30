package io.daff.springbootguide.annotation;

import io.daff.springbootguide.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author daffupman
 * @since 2020/5/30
 */
public class HelloWorldImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[] {HelloWorldConfiguration.class.getName()};
    }
}
