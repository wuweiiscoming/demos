package org.wigo.demo.spring.framework.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author wuwei31
 * @since 2021/6/20
 */
@Component
public class MyFactoryBean implements FactoryBean<String>{

    @Override
    public String getObject() throws Exception {
        return UUID.randomUUID().toString();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }



}
