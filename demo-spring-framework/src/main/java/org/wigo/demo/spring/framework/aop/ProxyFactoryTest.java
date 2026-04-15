package org.wigo.demo.spring.framework.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.wigo.demo.spring.framework.aop.dynamicproxy.jdk.UserService;

import java.lang.reflect.Method;

/**
 * @author wuwei
 * @since 2021/5/16
 */
public class ProxyFactoryTest {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(new UserService());
        factory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("方法执行前");
            }
        });
        UserService proxy = (UserService) factory.getProxy();
        proxy.test();
    }

}
