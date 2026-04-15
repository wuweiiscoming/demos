package org.wigo.demo.jdk.concurrency.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {


    public static void main(String[] args) {
        User user = new User("xx",12);
        AtomicReference<User> atomicReference = new AtomicReference<>(user);
        user.setName("qq");
        atomicReference.compareAndSet(user,new User("zz",14));
        System.out.println(atomicReference.get());
    }

    @Data
    @AllArgsConstructor
    static class User{
        private String name;
        private int old;
    }
}
