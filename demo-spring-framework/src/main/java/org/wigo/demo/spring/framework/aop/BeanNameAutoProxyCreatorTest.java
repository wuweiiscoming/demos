package org.wigo.demo.spring.framework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wigo.demo.spring.framework.aop.dynamicproxy.jdk.UserInterface;
import org.wigo.demo.spring.framework.aop.dynamicproxy.jdk.UserService;

/**
 * @author wuwei
 * @since 2021/5/17
 */
@Configuration
public class BeanNameAutoProxyCreatorTest {

    @Bean
    public BeanNameAutoProxyCreator creator(){
        BeanNameAutoProxyCreator creator =new BeanNameAutoProxyCreator();
        creator.setBeanNames("userService");
        creator.setInterceptorNames("methodInterceptor");
        return creator;
    }

    @Bean
    public MethodInterceptor methodInterceptor(){
        return new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("xxx");
                Object result = invocation.proceed();
                System.out.println("yyy");
                return result;
            }
        };
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanNameAutoProxyCreatorTest.class);
        UserInterface userInterface = (UserInterface) context.getBean("userService");
        userInterface.test();
    }
}
