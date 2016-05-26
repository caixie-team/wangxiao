import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TestJedis {

    public static RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    public void setRedisTemplate111(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Test
    public void test() {
        System.out.println("+++++++ start +++++++");
        String testkey = "aaa_001";
        ValueOperations valueOperations = redisTemplate.opsForValue();
        System.out.println(valueOperations.increment(testkey, 1));
        //System.out.println(valueOperations.get(testkey));
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        byte[] by = redisTemplate.getConnectionFactory().getConnection().get(testkey.getBytes());
        System.out.println(stringRedisSerializer.deserialize(by));
        System.out.println("+++++++ end ++++++++ ");
    }

    @Test
    public void test2() {
        System.out.println("+++++++ start +++++++");
        JedisConnection jedisConnection = (JedisConnection) redisTemplate.getConnectionFactory().getConnection();
        Jedis jedis = jedisConnection.getNativeConnection();
        jedis.incrBy("login:2015-01-01", 1);
        jedis.incrBy("login:2015-01-02", 1);
        jedis.incrBy("login:2015-01-03", 1);
        jedis.incrBy("login:2015-01-04", 1);
        jedis.incrBy("login:2015-01-05", 1);
        jedis.incrBy("login:2015-01-06", 1);
        System.out.println(jedis.keys("login:2015-01-*"));
        System.out.println("+++++++ end ++++++++ ");
    }

}
