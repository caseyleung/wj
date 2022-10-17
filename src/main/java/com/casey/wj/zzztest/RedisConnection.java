package com.casey.wj.zzztest;
/*
 * @author CaseyL
 * @date 2022/10/14 16:30
 * */

import redis.clients.jedis.Jedis;

public class RedisConnection {
    public static void main(String[] args) {
        // 192.168.16.64(信宜)   192.168.27.251(南京)
        Jedis jedis = new Jedis("192.168.16.64", 6379);
        String ping = jedis.ping();
        System.out.println(ping);



    }
}
