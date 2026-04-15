package org.wigo.demo.spring.framework.aop.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 *
 * @author Wu Wei
 * @since 2019-05-20 10:48
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        UserService targetClass = new UserService();
        UserInterface targetInterface = (UserInterface) Proxy.newProxyInstance(
                targetClass.getClass().getClassLoader(),
                targetClass.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理逻辑");
                        method.invoke(targetClass, args);
                        return null;
                    }
                });

        targetInterface.test();
    }
}
