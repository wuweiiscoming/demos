package org.wigo.demo.jdk.jdk8.lambda;

import org.junit.Test;

import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * @author wuwei
 * @since 2020/12/26 2:47 下午
 */
public class Suppliers {

    @Test
    public void supplier(){
        Supplier<Person> supplier = Person::new;
        supplier.get();
    }

    @Test
    public void intSupplier(){
        IntSupplier intSupplier = () -> {
            Random random = new Random();
            return random.nextInt();
        };
        System.out.println(intSupplier.getAsInt());
    }

}
