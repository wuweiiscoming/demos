package org.wigo.demo.spring.boot.test.controller;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wigo.demo.java.agent.jar.command.MyBatisRefreshCommand;
import org.wigo.demo.spring.boot.test.dao.UserMapper;
import org.wigo.demo.spring.boot.test.entity.User;

import java.io.IOException;

/**
 * @author wuwei31
 * @since 2021/5/18
 */
@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private ApplicationContext context;

    @RequestMapping("hello")
    public String hello() {
        User user = userMapper.selectById(1L);
        return "hello";
    }

    @RequestMapping("refresh")
    public String refresh() throws IOException {
        Configuration configuration = sqlSessionFactory.getConfiguration();

        Resource[] resources = context.getBean(MybatisProperties.class).resolveMapperLocations();

        MyBatisRefreshCommand command = new MyBatisRefreshCommand();

        command.clearAll(configuration);

        command.reloadAll(resources,configuration);

        userMapper.selectById(1L);

        return "refreshed";
    }

}
