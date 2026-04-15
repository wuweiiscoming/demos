package org.wigo.demo.jdk.jdk10;

import org.junit.Test;

import java.util.HashMap;

/**
 * 局部变量类型推断
 * 有助于减少样板式的代码
 *
 * @author wuwei
 * @since 2020/12/27 下午3:37
 */
public class LocalVariableTypeInference {

    @Test
    public void case1() {
        // var不是关键字，确保了向后兼容性
        var var = "hello java 10";
        System.out.println(var);
    }

    @Test
    public void case2(){
        var map = new HashMap<String,String>();
        map.put("xxx","yyyy");
        String xxx = map.get("xxx");
    }
}
