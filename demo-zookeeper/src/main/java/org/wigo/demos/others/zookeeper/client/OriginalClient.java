package org.wigo.demos.others.zookeeper.client;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 样板代码
 * Zookeeper监听节点
 *
 * @author wuwei31
 * @since 2020/12/8
 */
public class OriginalClient {

    private static ZooKeeper zooKeeper;

    @Before
    public void init() throws IOException, InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        String connectionString = "119.45.186.4:2181";
        int sessionTimeout = 5000;

        zooKeeper = new ZooKeeper(connectionString, sessionTimeout, (event) -> {
            if (Watcher.Event.EventType.None.equals(event.getType())
                    && Watcher.Event.KeeperState.SyncConnected.equals(event.getState())) {
                System.out.println("连接建立成功");
                countDownLatch.countDown();
            }
        });

//        Watcher watcher = new Watcher() {
//            public void process(WatchedEvent watchedEvent) {
//                if (watchedEvent.getType().equals(Event.EventType.None)) {
//                    System.out.println("连接建立成功");
//                    countDownLatch.countDown();
//                }
//            }
//        };

        System.out.println("连接中");
        countDownLatch.await();
    }

    @Test
    public void syncCreate() throws KeeperException, InterruptedException {
        String s = zooKeeper.create("/root/java/original", "11111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s + "同步创建成功");
    }

    @Test
    public void asyncCreate() throws InterruptedException {
        AsyncCallback.StringCallback callback = (rc, path, ctx, name) -> {
            if (KeeperException.Code.OK.intValue() == rc) {
                System.out.println(name + "异步创建成功");
            }
        };
        Object ctx = "context";
        zooKeeper.create("/root/java/original", "11111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT_SEQUENTIAL, callback, ctx);
        System.out.println("主线程结束");
        Thread.sleep(10000);
    }

}
