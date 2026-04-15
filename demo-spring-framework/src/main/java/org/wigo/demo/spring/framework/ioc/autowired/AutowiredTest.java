package org.wigo.demo.spring.framework.ioc.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.wigo.demo.spring.framework.ioc.factorybean.Config;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
@ComponentScan
public class AutowiredTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredTest.class);

        UserService userService = context.getBean("userService",UserService.class);

        userService.order();
    }
}
