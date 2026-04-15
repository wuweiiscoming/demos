package org.wigo.demo.jdk.concurrency;

import java.util.concurrent.*;

/**
 * @author wuwei31
 * @since 2021/6/16
 */
public class FutureTest implements Callable<String> {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTest futureTest = new FutureTest();
        FutureTask<String> futureTask = new FutureTask<String>(futureTest);
        new Thread(futureTask).start();
        while (true){
            boolean done = futureTask.isDone();
            System.out.println(done);
            if(done){
                System.out.println(futureTask.get());
                break;
            }
        }
    }

    @Override
    public String call() throws Exception {
        int i = 0;
        for (int j = 0; j < 200; j++) {
            i+= j;
        }
        return i+"";
    }
}
