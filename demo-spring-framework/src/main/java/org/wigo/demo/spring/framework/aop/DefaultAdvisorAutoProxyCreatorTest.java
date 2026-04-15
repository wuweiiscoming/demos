package org.wigo.demo.spring.framework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
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
public class DefaultAdvisorAutoProxyCreatorTest {

    @Bean
    public DefaultAdvisorAutoProxyCreator creator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        return creator;
    }

    @Bean
    public NameMatchMethodPointcutAdvisor advisor(){
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.addMethodName("test");
        advisor.setAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("123");
                Object result = invocation.proceed();
                System.out.println("345");
                return result;
            }
        });
        return advisor;
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DefaultAdvisorAutoProxyCreatorTest.class);
        UserInterface userInterface = (UserInterface) context.getBean("userService");
        userInterface.test();
    }
}
