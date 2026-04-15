package org.wigo.demo.java.agent.jar.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuwei31
 * @since 2021/7/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Option {

    String longName() default "";

    String shortName() default "";

    boolean required() default false;

}
