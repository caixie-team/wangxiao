package io.wangxiao.commons.service.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    public static RedisTemplate redisTemplate;

    @Resource(name="redisTemplate")
    public  void setRedisTemplate111(RedisTemplate redisTemplate) {
        logger.info("++++++++++++redisTemplate create ok++++++++++++");
        this.redisTemplate = redisTemplate;
    }

    RedisServiceImpl(){

    }

    /**
     * 获取
     *
     * @param key
     * @return Object
     */
    public Object get(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 设置对象默认时间是1天
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, Object value) {
        set(key, value, 60 * 60 * 24);
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
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, seconds, TimeUnit.SECONDS);
        //  return jedis.setex(key.getBytes(), seconds, SerializationUtils.serialize(value));
    }


    /**
     * 根据key删除
     *
     * @param key
     * @return
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 更新过期时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public Object exprie(String key, int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存原生客户端
     *
     * @return
     */
    public RedisTemplate getCache() {
        return redisTemplate;
    }

    public Jedis getJedis(){
        JedisConnection jedisConnection = (JedisConnection)this.redisTemplate.getConnectionFactory().getConnection();
        Jedis jedis = jedisConnection.getNativeConnection();
        return jedis;

    }

}
