package org.wigo.demo.dubbo.consumer;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.wigo.demo.dubbo.interfaces.DemoService;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
@EnableAutoConfiguration
public class Consumer {

    @Reference
    private DemoService demoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Consumer.class, args);

        DemoService bean = context.getBean(DemoService.class);

        System.out.println(bean.sayHello("hehe"));
    }
}
