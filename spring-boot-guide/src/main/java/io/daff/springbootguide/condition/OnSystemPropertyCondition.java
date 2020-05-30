package io.daff.springbootguide.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 系统属性条件判断
 *
 * @author daffupman
 * @since 2020/5/30
 */
public class OnSystemPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        assert attributes != null;
        String propertyName = attributes.get("name").toString();
        String propertyValue = attributes.get("value").toString();
        String javaProperty = System.getProperty(propertyName);
        return propertyValue.equalsIgnoreCase(javaProperty);
    }
}
