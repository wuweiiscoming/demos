package org.wigo.demo.jdk.concurrency.volatilee;

/**
 * @author wuwei
 * @since 2021/2/28 上午11:05
 */
public class VisibilityTest {

    private boolean flag = true;

    private void busyThread(){
        Thread thread = new Thread(() -> {
            System.out.println("start to run");
            while(flag){
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            System.out.println("stop");
        });
        thread.start();
    }

    private void changeThread() throws InterruptedException {
        Thread.sleep(1000);
        Thread thread = new Thread(() -> {
            flag =false;
            System.out.println("change flag to false");
        });
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest test =new VisibilityTest();
        test.busyThread();
        test.changeThread();
    }
}
