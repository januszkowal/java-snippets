package org.blacksmith.jsnip.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
        InitXyz.class,
        XyzConfiguration.class
})
@Configuration
public @interface EnableXyz {
    String prop1() default "";

    String prop2() default "false";
}
