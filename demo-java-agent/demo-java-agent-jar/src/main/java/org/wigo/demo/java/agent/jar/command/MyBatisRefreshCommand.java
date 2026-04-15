package org.wigo.demo.java.agent.jar.command;

import lombok.SneakyThrows;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @author wuwei
 * @since 2021/7/11
 */
public class MyBatisRefreshCommand extends AbstractCommand {

//    @Option(longName = "resource", shortName = "r", required = false)
//    private String resource;
//
//    @Option(longName = "packagePath", shortName = "pp", required = false)
//    private String packagePath;

    @SneakyThrows
    @Override
    public String process() {
        // 检查mybatis配置类
        Class.forName("org.apache.ibatis.session.Configuration");

        Configuration configuration = vmTool.getSingleInstance(Configuration.class);
        // 清空所有mapper相关数据
        clearAll(configuration);

        Resource[] resources = vmTool.getSingleInstance(MybatisProperties.class).resolveMapperLocations();
        // 重新解析所有xml
        reloadAll(resources,configuration);
        return "ok";
    }

    @SneakyThrows
    public void reloadAll(Resource[] resources,Configuration configuration) {
        for (Resource mapperLocation : resources) {
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(),
                    configuration, mapperLocation.toString(), configuration.getSqlFragments());
            xmlMapperBuilder.parse();
        }
    }

    public void clearAll(Configuration configuration) {
        Class<?> classConfig = configuration.getClass();
        clearMap(classConfig, configuration, "mappedStatements");
        clearMap(classConfig, configuration, "caches");
        clearMap(classConfig, configuration, "resultMaps");
        clearMap(classConfig, configuration, "parameterMaps");
        clearMap(classConfig, configuration, "keyGenerators");
        clearMap(classConfig, configuration, "sqlFragments");

        clearSet(classConfig, configuration, "loadedResources");
    }

    @SneakyThrows
    private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) {
        Field field = classConfig.getDeclaredField(fieldName);
        field.setAccessible(true);
        Map mapConfig = (Map) field.get(configuration);
        mapConfig.clear();
    }

    @SneakyThrows
    private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) {
        Field field = classConfig.getDeclaredField(fieldName);
        field.setAccessible(true);
        Set setConfig = (Set) field.get(configuration);
        setConfig.clear();
    }


}
