package org.wigo.demo.jdk.concurrency.connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {

    private final LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initSize) {
        for (int i = 0; i < initSize; i++) {
            pool.add(ConnectionDriver.createConnection());
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (pool) {
            pool.addLast(connection);
            pool.notifyAll();
        }
    }

    public Connection fetchConnection(long millis) throws InterruptedException {
        synchronized (pool) {
            if (millis <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.pop();
            }
            long future = System.currentTimeMillis() + millis;
            long remain = millis;
            while (pool.isEmpty() && remain > 0) {
                pool.wait();
                remain = future - System.currentTimeMillis();
            }
            Connection connection = null;
            if (!pool.isEmpty()) {
                connection = pool.pop();
            }
            return connection;
        }

    }


    public static void main(String[] args) {
        Connection connection = ConnectionDriver.createConnection();
    }
}
