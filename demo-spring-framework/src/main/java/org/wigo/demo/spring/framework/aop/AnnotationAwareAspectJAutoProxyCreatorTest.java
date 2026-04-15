package org.wigo.demo.spring.framework.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.wigo.demo.spring.framework.aop.dynamicproxy.jdk.UserInterface;
import org.wigo.demo.spring.framework.aop.dynamicproxy.jdk.UserService;

/**
 * @author wuwei
 * @since 2021/5/17
 */
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AnnotationAwareAspectJAutoProxyCreatorTest {

    @Before("execution(public void org.wigo.demo.spring.framework.aop.dynamicproxy.jdk.UserService.test())")
    public void before(){
        System.out.println("执行前");
    }

    @After("execution(public void org.wigo.demo.spring.framework.aop.dynamicproxy.jdk.UserService.test())")
    public void after(){
        System.out.println("执行后");
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationAwareAspectJAutoProxyCreatorTest.class);
        UserInterface userInterface = (UserInterface) context.getBean("userService");
        userInterface.test();
    }
}
