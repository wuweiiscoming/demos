package org.wigo.demo.spring.framework.ioc.autowired;

import org.springframework.stereotype.Component;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
@Component
public class OrderService {

    public void order(){
        System.out.println("order");
    }
}
