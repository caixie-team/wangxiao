package io.wangxiao.commons.service.cache;

import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;


public interface RedisService {


    /**
     * 获取
     *
     * @param key
     * @return Object
     */
    Object get(String key) ;

    /**
     * 设置对象默认时间是1天
     *
     * @param key
     * @param value
     * @return
     */
    void set(String key, Object value);

    /**
     * 设置超时时间
     *
     * @param key     键
     * @param value   值
     * @param seconds 时间（秒） 60*60为一小时
     * @return
     */
    void set(String key, Object value, int seconds);
    /**
     * 设置超时时间
     *
     * @param key     键
     * @param seconds 时间（秒） 60*60为一小时
     * @param value   值
     * @return
     */
    void setObject(String key, int seconds, Object value);

    /**
     * 根据key删除
     *
     * @param key
     * @return
     */
    void remove(String key);

    /**
     * 更新过期时间
     *
     * @param key
     * @param seconds
     * @return
     */
    Object exprie(String key, int seconds);
    /**
     * 获取缓存原生客户端
     *
     * @return
     */
    RedisTemplate getCache();

    /**
     * 获取缓存原Jedis
     *
     * @return
     *
     *
     */
    Jedis getJedis();


}
