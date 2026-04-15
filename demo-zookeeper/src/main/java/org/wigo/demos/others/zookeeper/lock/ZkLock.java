package org.wigo.demos.others.zookeeper.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkLock {

    private ZooKeeper zk;

    private static final String LOCK_NAME = "/LOCK";

    private String currentNodeName;

    public void init() throws IOException {
        if (zk == null) {
            zk = new ZooKeeper("47.99.34.13:2181", 300, watchedEvent -> {
            });
        }
    }

    public void lock() throws KeeperException, InterruptedException, IOException {
        init();
        tryLock();
    }

    public boolean tryLock() throws KeeperException, InterruptedException {
        String nodeName = LOCK_NAME + "/zk_";
        // 创建节点  临时顺序节点
        currentNodeName = zk.create(nodeName, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // 判断当前节点是否为最小
        List<String> list = zk.getChildren(LOCK_NAME, false);
        Collections.sort(list);
        if (list.get(0).equals(currentNodeName)) {
            return true;
        } else {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zk.exists(LOCK_NAME + "/", watchedEvent -> {
                // 监听节点删除
                System.out.println("watchedEvent.getPath()" + watchedEvent.getPath());
                countDownLatch.countDown();
            });
            countDownLatch.await();
        }
        return false;
    }

    public void unlock() throws KeeperException, InterruptedException {
        zk.delete(currentNodeName, -1);
    }

}
