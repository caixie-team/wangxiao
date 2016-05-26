package io.wangxiao.commons.service.cache;

import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

/**
 */
public class CacheKit {

    public static CacheKit cacheKit = new CacheKit();

    public static CacheKit getInstance() {
        return cacheKit;
    }

    public RedisService redisService = new RedisServiceImpl();

    static int defalutTime = 60 * 60 * 24;//默认存储时间1天（秒）

    public CacheKit() {

    }

    /**
     * 获取
     *
     * @param key
     * @return Object
     */
    public Object get(String key) {
        return redisService.get(key);
    }

    /**
     * 设置对象默认时间是1天
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, Object value) {
        set(key, value, defalutTime);
    }

    /**
     * 设置超时时间
     *
     * @param key     键
     * @param value   值
     * @param seconds 时间（秒） 60*60为一小时
     * @return
     */
    public void set(String key, Object value, int seconds) {
        setObject(key, seconds, value);
    }

    /**
     * 设置超时时间
     *
     * @param key     键
     * @param seconds 时间（秒） 60*60为一小时
     * @param value   值
     * @return
     */
    public void setObject(String key, int seconds, Object value) {
        redisService.setObject(key, seconds, value);
    }


    /**
     * 根据key删除
     *
     * @param key
     * @return
     */
    public void remove(String key) {
        redisService.remove(key);
    }

    /**
     * 更新过期时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public Object exprie(String key, int seconds) {
        return redisService.exprie(key, seconds);
    }

    /**
     * 获取缓存原生客户端
     *
     * @return
     */
    public RedisTemplate getCache() {
        return redisService.getCache();
    }

    public Jedis getJedis() {
        return redisService.getJedis();

    }

}
