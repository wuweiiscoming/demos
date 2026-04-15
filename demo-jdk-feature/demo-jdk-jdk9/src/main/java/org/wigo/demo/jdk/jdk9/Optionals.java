package org.wigo.demo.jdk.jdk9;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 升级Optional
 *
 * @author wuwei
 * @since 2020/12/27 上午11:51
 */
public class Optionals {

    /**
     * 增加stream方法
     */
    @Test
    public void stream() {
        long count = Stream.of(Optional.empty(), Optional.of(1), Optional.of("2"))
                .flatMap(Optional::stream).count();
        System.out.println(count);
    }

    /**
     * 增加ifPresentOrElse方法
     */
    @Test
    public void ifPresentOrElse() {
        Consumer<Optional<Integer>> consumer
                = o -> o.ifPresentOrElse(e -> System.out.println("Result Found:" + e), () -> System.out.println("Not Found"));
        consumer.accept(Optional.empty());
        consumer.accept(Optional.of(1));
    }

    /**
     * 增加or方法
     */
    @Test
    public void or() {
        Optional<String> optional = Optional.empty();
        String str = optional.or(() -> Optional.of("no")).get();
        System.out.println(str);
    }
}
