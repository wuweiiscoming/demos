package org.wigo.demo.spring.framework.ioc.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
@Component
public class UserService {

    @Autowired
    private OrderService orderService;

    public void order(){
        orderService.order();
    }
}
