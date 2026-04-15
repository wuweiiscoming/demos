package org.wigo.demos.others.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author wuwei
 * @since 2021/7/25
 */
public class HashMapTest {

    @Test
    public void concurrentModificationException() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");

        // 内部使用了map的iterator迭代器
        // map.remove(key)会将modCount+1，造成modCount与expectedModCount不一致，抛出异常
        for (String key : map.keySet()) {
            if (key.equals("a")) {
                map.remove(key);
            }
        }
    }

    @Test
    public void concurrentModificationExceptionSolved() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("a")) {
                iterator.remove();
            }
        }
        System.out.println(map);
    }
}
