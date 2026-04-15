package org.wigo.demo.jdk.concurrency;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    static Exchanger<String> exchanger = new Exchanger<>();


    public static void main(String[] args) {
        new Thread(() -> {
            String a = "银行流水A";
            try {
                String b = exchanger.exchange(a);
                System.out.println("b:" + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            String b = "银行流水B";
            try {
                String a = exchanger.exchange(b);
                System.out.println("a:" + a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
