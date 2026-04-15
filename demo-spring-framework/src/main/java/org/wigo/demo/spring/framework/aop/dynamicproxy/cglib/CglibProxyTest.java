package org.wigo.demo.spring.framework.aop.dynamicproxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Wu Wei
 * @since 2019-05-20 11:13
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("代理前");
                Object object = methodProxy.invokeSuper(o, objects);
                System.out.println("代理后");
                return object;
            }
        });
        TargetClass targetClass = (TargetClass) enhancer.create();
        targetClass.test();
    }
}
