package com.itsocoo.multidatasource.jdbc.starter;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author wanghaibo
 * @version V1.0
 * @Description 自定义的ConditionalOnProperty
 * @date 2018/3/4 17:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(MyOnPropertyCondition.class)
public @interface MyConditionalOnProperty {

    String name() default "";

    String havingValue() default "";

}
