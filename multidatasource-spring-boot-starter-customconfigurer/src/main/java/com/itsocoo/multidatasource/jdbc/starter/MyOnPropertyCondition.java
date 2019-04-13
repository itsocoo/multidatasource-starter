package com.itsocoo.multidatasource.jdbc.starter;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

/**
 * @author wanghaibo
 * @version V1.0
 * @Description
 * @date 2018/3/4 17:41
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 40)
public class MyOnPropertyCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

        MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(MyConditionalOnProperty.class.getName());

        List<Object> havingValue = attributes.get("havingValue");

        String value = (String) havingValue.get(0);

        Object propertiesName = metadata.getAnnotationAttributes(MyConditionalOnProperty.class.getName()).get("name");

        if (propertiesName != null) {
            String names = context.getEnvironment().getProperty(propertiesName.toString());
            if (names != null) {
                boolean present = Arrays.stream(names.split(",")).anyMatch(s -> s.equalsIgnoreCase(value));

                if (present) {
                    return new ConditionOutcome(true, "none get properties");
                }
            }
        }
        return new ConditionOutcome(false, "none get properties");
    }

}
