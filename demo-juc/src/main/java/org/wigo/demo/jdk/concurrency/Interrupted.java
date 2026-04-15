package org.wigo.demo.jdk.concurrency;

public class Interrupted {


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runner());
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }

    static class Runner implements  Runnable{
        @Override
        public void run() {
            long i = 0;
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println(i);
                    return;
                }
                i ++ ;
            }
        }
    }
}
