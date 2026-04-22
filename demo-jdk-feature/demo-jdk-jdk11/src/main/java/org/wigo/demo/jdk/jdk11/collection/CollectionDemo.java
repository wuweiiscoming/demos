package org.wigo.demo.jdk.jdk11.collection;

import java.util.List;
import java.util.Set;
import java.util.Map;

/**
 * JDK 9 不可变集合工厂方法示例
 *
 * List.of(), Set.of(), Map.of() 创建不可变集合。
 *
 * 面试要点：
 * 1. of() 创建的集合是不可变的（不能 add/remove/modify）
 * 2. 不允许 null 元素（会抛出 NullPointerException）
 * 3. Set.of() 不允许重复元素
 * 4. Map.of() 最多支持 10 个键值对
 * 5. 比 Arrays.asList() 更简洁，Arrays.asList 可修改元素但不能增删
 */
public class CollectionDemo {

    public static void main(String[] args) {
        // List.of()
        System.out.println("=== List.of() ===");
        List<String> list = List.of("a", "b", "c");
        System.out.println("List: " + list);

        // 不可变性演示
        try {
            list.add("d");  // UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("add() 抛出 UnsupportedOperationException (不可变)");
        }

        try {
            list.set(0, "x");  // UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("set() 抛出 UnsupportedOperationException (不可变)");
        }

        // 不允许 null
        try {
            List.of("a", null, "b");  // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("null 元素抛出 NullPointerException");
        }

        // Set.of()
        System.out.println("\n=== Set.of() ===");
        Set<String> set = Set.of("a", "b", "c");
        System.out.println("Set: " + set);

        // 重复元素
        try {
            Set.of("a", "a", "b");  // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("重复元素抛出 IllegalArgumentException");
        }

        // Map.of()
        System.out.println("\n=== Map.of() ===");
        Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3);
        System.out.println("Map: " + map);

        // Map.of() 最多 10 个键值对
        Map<String, Integer> bigMap = Map.of(
            "k1", 1, "k2", 2, "k3", 3, "k4", 4, "k5", 5,
            "k6", 6, "k7", 7, "k8", 8, "k9", 9, "k10", 10
        );
        System.out.println("10元素Map: " + bigMap.size() + " 个");

        // 超过10个用 Map.ofEntries()
        Map<String, Integer> largerMap = Map.ofEntries(
            Map.entry("k1", 1),
            Map.entry("k2", 2),
            Map.entry("k11", 11),
            Map.entry("k12", 12)
        );
        System.out.println("ofEntries创建: " + largerMap.size() + " 个");

        // 对比 Arrays.asList()
        System.out.println("\n=== List.of vs Arrays.asList ===");
        List<String> arraysList = java.util.Arrays.asList("a", "b", "c");
        List<String> ofList = List.of("a", "b", "c");

        // Arrays.asList 可以修改元素
        arraysList.set(0, "x");
        System.out.println("Arrays.asList 可修改元素: " + arraysList);

        // Arrays.asList 不能增删
        try {
            arraysList.add("d");  // UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Arrays.asList 不能 add");
        }

        // Arrays.asList 允许 null
        List<String> withNull = java.util.Arrays.asList("a", null);
        System.out.println("Arrays.asList 允许 null: " + withNull);

        System.out.println("\n总结:");
        System.out.println("Arrays.asList: 可修改元素，不可增删，允许null");
        System.out.println("List.of(): 完全不可变，不允许null");
    }
}