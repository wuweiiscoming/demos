package org.wigo.demo.dubbo.spi.dubbospi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * AOP：自动实例化wrapper类，并将扩展实现传入wrapper实例
 * IOC：扫描扩展实现类中的set方法，从spring容器和dubbo spi中查找符合的实例并注入
 * 从dubbo spi中找到的是，目标类的Adaptive对象（代理对象），Adaptive代理类由@Adaptive注解或者由spi生成动态代理类
 * <p>
 * 动态代理中的代理逻辑：
 * 1.获取方法参数中的URL参数，或者方法参数存在getUrl方法
 * 2.从URL参数中查找属性为目标接口（car）的值
 * 3.根据这个值查找dubbo spi的扩展实现
 *
 * @author wuwei31
 * @since 2021/6/26
 */
public class DubboSpiTest {

    @Test
    public void simple(){
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);

        Car car = extensionLoader.getExtension("red");

        car.getCarName(null);
    }

    @Test
    public void ioc(){
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);

        Person person = extensionLoader.getExtension("black");

        System.out.println(person.getCar());

        URL url = URL.valueOf("http://www.xx?car=red");
        person.getCar().getCarName(url);
    }
}
