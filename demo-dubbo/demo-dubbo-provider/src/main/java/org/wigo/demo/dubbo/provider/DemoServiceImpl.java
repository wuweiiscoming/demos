package org.wigo.demo.dubbo.provider;

import org.apache.dubbo.config.annotation.Service;
import org.wigo.demo.dubbo.interfaces.DemoService;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}
