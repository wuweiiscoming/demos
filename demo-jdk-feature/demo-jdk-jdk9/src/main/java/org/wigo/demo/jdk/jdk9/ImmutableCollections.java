package org.wigo.demo.jdk.jdk9;

import org.junit.Test;

import java.util.List;

/**
 * 增加了生成不可变集合的快捷方法List.of
 *
 * @author wuwei
 * @since 2020/12/27 上午10:48
 */
public class ImmutableCollections {

    @Test
    public void immutableList(){
        List<Integer> immutableList = List.of(1,2,3);
        immutableList.add(4);
    }

}
