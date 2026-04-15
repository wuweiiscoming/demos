package org.wigo.demo.jdk.concurrency.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> atomicReference = AtomicIntegerFieldUpdater.newUpdater(User.class,"old");
        User user = new User("conan",10);
        atomicReference.getAndIncrement(user);
        System.out.println(user);

    }

    @Data
    @AllArgsConstructor
    static class User{
        private String name;
        public volatile int old;
    }
}
