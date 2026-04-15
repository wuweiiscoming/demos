package org.wigo.demo.dubbo.spi.javaspi;

import java.util.ServiceLoader;

/**
 * @author wuwei31
 * @since 2021/6/26
 */
public class JavaSpiTest {

    public static void main(String[] args) {
        ServiceLoader<Animal> load = ServiceLoader.load(Animal.class);
        for (Animal animal : load) {
            animal.bark();
        }
    }
}
