package org.wigo.demos.others;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * @author wuwei31
 * @since 2021/6/30
 */
public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();
    }
}
