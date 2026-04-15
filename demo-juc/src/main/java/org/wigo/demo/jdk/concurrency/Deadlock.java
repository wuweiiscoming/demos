package org.wigo.demo.jdk.concurrency;

import lombok.SneakyThrows;

public class Deadlock {

    public static void main(String[] args) {
        String A = "A";
        String B = "B";

        Thread t1 = new Thread(() -> {
            synchronized (A){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 A");
                synchronized (B){
                    System.out.println("t1 B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B){
                System.out.println("t2 A");
                synchronized (A){
                    System.out.println("t2 B");
                }
            }
        });

        t1.start();
        t2.start();

    }



}
