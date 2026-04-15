package org.wigo.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @author wuwei31
 * @since 2021/5/18
 */
public class Test {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("119.45.186.4",6379);
        String test = jedis.get("test");
        System.out.println(test);
    }
}
