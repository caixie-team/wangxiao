package io.bb;

import io.wangxiao.commons.service.cache.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisTest {
    public static void main(String[] args) {

        Jedis jedis = RedisUtil.getJedis();
        jedis.flushAll();
       Set<String> strigs =jedis.keys("*");
        for(String k:strigs){
            System.out.println(k);
        }
      /*  System.out.println(jedis.get("aa".getBytes()));
        System.out.println(jedis.get("aa".getBytes()));
        System.out.println(jedis.get("aa".getBytes()));*/



    }
}
