package org.wigo.demos.others.zookeeper.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wuwei
 * @since 2021/3/18
 */
public class CuratorClient {

    private CuratorFramework client;

    @Before
    public void init() {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        String connectionString = "119.45.186.4:2181";

        client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
        client.start();
    }

    @Test
    public void createWithParent() throws Exception {

        client.create().creatingParentsIfNeeded().withProtection().forPath("/root/curator/persistent");
    }

    @Test
    public void get() throws Exception {
        byte[] bytes = client.getData().forPath("/root/curator/persistent");
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void getInBackground() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        BackgroundCallback callback = (client, event) -> {
            System.out.println(new String(event.getData(),StandardCharsets.UTF_8));
        };

        client.getData().inBackground(callback, executorService).forPath("/root/curator/persistent");

        Thread.sleep(2000);
    }

}
