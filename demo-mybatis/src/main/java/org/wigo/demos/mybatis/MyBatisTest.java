package org.wigo.demos.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.wigo.demos.mybatis.plugin.MyPlugin;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wuwei
 * @since 2021/4/12
 */
public class MyBatisTest {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis/mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        sqlSessionFactory.getConfiguration().addInterceptor(new MyPlugin());
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.selectById2(1L);
    }

}
