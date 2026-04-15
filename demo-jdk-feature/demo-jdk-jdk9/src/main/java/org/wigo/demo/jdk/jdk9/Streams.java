package org.wigo.demo.jdk.jdk9;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream API改进
 *
 * @author wuwei
 * @since 2020/12/27 下午1:59
 */
public class Streams {

    /**
     * 增加takeWhile方法
     * 返回第一段连续符合条件的元素
     */
    @Test
    public void takeWhile() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        stream.takeWhile(e -> e < 4).forEach(System.out::println);
    }

    /**
     * 增加dropWhile方法
     * 跳过第一段连续符合条件的元素
     */
    @Test
    public void dropWhile() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        stream.dropWhile(e -> e < 4).forEach(System.out::println);
    }

    /**
     * 增加iterate方法
     * iterate的三个参数分别为：起始值，终止条件，循环体
     */
    @Test
    public void iterate() {
        IntStream.iterate(2, x -> x < 20, x -> x * x).forEach(System.out::println);
    }

    /**
     * 增加ofNullable方法
     */
    @Test
    public void ofNullable() {
        Stream.ofNullable(1).forEach(System.out::println);
        Stream.ofNullable(null).forEach(System.out::println);
    }

}
