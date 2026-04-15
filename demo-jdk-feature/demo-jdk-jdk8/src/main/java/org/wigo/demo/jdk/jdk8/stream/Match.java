package org.wigo.demo.jdk.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author wuwei
 * @since 2020/12/26 3:36 下午
 */
public class Match {

    @Test
    public void allMatch() {
        List<String> list = Arrays.asList("apple", "banana", "orange");
        boolean a = list.stream().allMatch(e -> e.startsWith("a"));
        System.out.println(a);
    }

    @Test
    public void anyMatch() {
        List<String> list = Arrays.asList("apple", "banana", "orange");
        boolean a = list.stream().anyMatch(e -> e.startsWith("a"));
        System.out.println(a);
    }

}
