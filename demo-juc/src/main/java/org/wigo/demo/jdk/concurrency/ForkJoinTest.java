package org.wigo.demo.jdk.concurrency;

import lombok.AllArgsConstructor;

import java.util.concurrent.*;

@AllArgsConstructor
public class ForkJoinTest extends RecursiveTask<Integer> {

    private int start;
    private int end;

    private static final int MIN_THRESHOLD = 2;

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread());
        int sum = 0;
        if (end - start <= MIN_THRESHOLD) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        int middle = (start + end) / 2;
        ForkJoinTask<Integer> left = new ForkJoinTest(start, middle);
        ForkJoinTask<Integer> right = new ForkJoinTest(middle + 1, end);
        left.fork();
        right.fork();
        int leftSum = left.join();
        int rightSum = right.join();
        sum = leftSum + rightSum;
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinTest forkJoinTest = new ForkJoinTest(1, 100000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(forkJoinTest);
        System.out.println(result.get());
    }
}
