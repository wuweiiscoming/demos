package org.wigo.demo.jdk.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 归约（简化）
 * 允许通过指定的函数将多个元素归约为一个元素，结果通过Optional接口表示
 * 类型：最终操作
 * @author wuwei
 * @since 2020/12/26 3:30 下午
 */
public class Reduce {

    @Test
    public void sum(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Optional<Integer> reduce = list.stream().reduce(Integer::sum);
        System.out.println(reduce.get());
    }

    @Test
    public void join(){
        List<String> list = Arrays.asList("a","b","c","d");
        Optional<String> reduce = list.stream().reduce((s1, s2) -> s1 + "," + s2);
        System.out.println(reduce.get());
    }

}
