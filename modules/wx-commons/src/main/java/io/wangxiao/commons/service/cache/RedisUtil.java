package io.wangxiao.commons.service.cache;

import io.wangxiao.commons.util.PropertyUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private static JedisPool pool;
    static {
        PropertyUtil redisProperty = PropertyUtil.getInstance("redis");
        // 创建jedis池配置实例
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置池配置项值
        config.setMaxTotal(Integer.valueOf(redisProperty.getProperty("redis.pool.maxActive")));
        config.setMaxIdle(Integer.valueOf(redisProperty.getProperty("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Long.valueOf(redisProperty.getProperty("redis.pool.maxWait")));
        config.setTestOnReturn(true);
        config.setTestOnBorrow(true);
        //need password
        pool = new JedisPool(config, redisProperty.getProperty("redis.ip"), Integer.valueOf(redisProperty.getProperty("redis.port")), 2000,redisProperty.getProperty("redis.password"));
    }

    /**
     * 单个jedis*
     * 
     * @return
     */
    public static Jedis getJedis() {
        Jedis Jedis = getPool().getResource();
        return Jedis;
    }

    public static JedisPool getPool() {
        return pool;
    }



}
