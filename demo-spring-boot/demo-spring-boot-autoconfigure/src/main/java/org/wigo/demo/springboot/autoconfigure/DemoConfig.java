package org.wigo.demo.springboot.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author wuwei31
 * @since 2021/4/9
 */
@EnableConfigurationProperties(DemoProperties.class)
@ConditionalOnProperty(prefix = "demo",name = "enable",havingValue = "true")
public class DemoConfig {

    @Autowired
    private DemoProperties demoProperties;

    @Bean
    public DemoService get(){
        System.out.println(demoProperties.getProperty1());
        return null;
    }

}
