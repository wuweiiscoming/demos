package org.wigo.demo.spring.framework.aop.dynamicproxy.jdk;

import org.springframework.stereotype.Component;

/**
 * @author Wu Wei
 * @since 2019-05-20 10:50
 */
@Component
public class UserService implements UserInterface {

    @Override
    public void test() {
        System.out.println("目标接口方法test");
    }

}
