package org.wigo.demo.spring.framework.ioc.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuwei31
 * @since 2021/6/20
 */
@Configuration
@ComponentScan
public class Config {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Object factoryBeanTest = context.getBean("myFactoryBean");

        System.out.println(factoryBeanTest);

        Object factoryBeanTest1 = context.getBean("myFactoryBean");

        System.out.println(factoryBeanTest1);
    }
}
