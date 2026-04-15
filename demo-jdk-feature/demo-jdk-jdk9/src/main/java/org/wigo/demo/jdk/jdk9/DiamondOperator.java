package org.wigo.demo.jdk.jdk9;

import org.junit.Test;

import java.util.HashMap;

/**
 * 钻石操作符的升级
 *
 * @author wuwei
 * @since 2020/12/27 上午11:45
 */
public class DiamondOperator {

    @Test
    public void anonymousInnerClass(){
        // java9 添加了匿名内部类的功能 后面添加了大括号 {}  可以做一些细节的操作
        HashMap<String,String> map  = new HashMap<>(){
            @Override
            public boolean isEmpty(){
                System.out.println("isEmpty:"+super.isEmpty());
                return super.isEmpty();
            }
        };

        System.out.println(map.isEmpty());
    }
}
