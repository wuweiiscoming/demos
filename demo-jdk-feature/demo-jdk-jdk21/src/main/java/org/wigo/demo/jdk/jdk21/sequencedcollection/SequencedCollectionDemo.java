package org.wigo.demo.jdk.jdk21.sequencedcollection;

import java.util.*;

/**
 * JDK 21 Sequenced Collection（有序集合）示例
 *
 * SequencedCollection 是新的接口，提供了统一的顺序访问方法。
 * List、Deque、LinkedHashSet 等都实现了此接口。
 *
 * 面试要点：
 * 1. addFirst/addLast - 在头部/尾部添加元素
 * 2. getFirst/getLast - 获取头部/尾部元素
 * 3. removeFirst/removeLast - 移除头部/尾部元素
 * 4. reversed() - 返回反转视图
 * 5. 解决了之前各集合 API 不一致的问题
 */
public class SequencedCollectionDemo {

    public static void main(String[] args) {
        // ArrayList
        System.out.println("=== ArrayList ===");
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        list.addFirst("Start");
        list.addLast("End");
        System.out.println("List: " + list);
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        list.removeFirst();
        list.removeLast();
        System.out.println("After removal: " + list);

        // reversed() 视图
        System.out.println("Reversed: " + list.reversed());

        // LinkedHashSet
        System.out.println("\n=== LinkedHashSet ===");
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set.addFirst("First");  // JDK 21 新方法
        set.addLast("Last");
        System.out.println("Set: " + set);
        System.out.println("First: " + set.getFirst());
        System.out.println("Last: " + set.getLast());

        // Deque (LinkedList)
        System.out.println("\n=== LinkedList (Deque) ===");
        LinkedList<Integer> deque = new LinkedList<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        System.out.println("Deque: " + deque);
        deque.addFirst(0);
        deque.addLast(4);
        System.out.println("After addFirst/addLast: " + deque);

        // 传统方式 vs SequencedCollection
        System.out.println("\n=== 传统方式 vs 新方式 ===");
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        // 传统方式获取最后一个元素
        int oldWay = numbers.get(numbers.size() - 1);

        // 新方式
        int newWay = numbers.getLast();

        System.out.println("传统方式获取最后一个: " + oldWay);
        System.out.println("新方式获取最后一个: " + newWay);

        // 传统方式反转
        List<Integer> oldReversed = new ArrayList<>(numbers);
        Collections.reverse(oldReversed);
        System.out.println("传统反转: " + oldReversed);

        // 新方式反转视图（不修改原列表）
        System.out.println("新反转视图: " + numbers.reversed());
        System.out.println("原列表未改变: " + numbers);
    }
}