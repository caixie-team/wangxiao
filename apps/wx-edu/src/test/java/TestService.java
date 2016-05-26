import io.wangxiao.commons.service.cache.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml"})
public class TestService {
    @Autowired
    RedisTemplate<String, Long> redisTemplate;

    @Test
    public void test() {
        try {
            /*ValueOperations<String, Long> opsForValue = redisTemplate.opsForValue();
        	Long returnNum = opsForValue.increment("test", 1);
        	System.out.println(returnNum);
        	//redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Long.class));
        	//Long long1 = opsForValue.get("test");
        	//System.out.println(long1);
        	RedisOperations<String, Long> operations = opsForValue.getOperations();
        	BoundValueOperations<String, Long> boundValueOps = operations.boundValueOps("test");
        	Long long1 = boundValueOps.get();
        	System.out.println(long1);
        	Jedis jedis = RedisUtil.getJedis();
        	String string = jedis.get("test");
        	System.out.println(string);*/
            HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
            Long longa = opsForHash.increment("aaa", "a", 1);
            Long longb = opsForHash.increment("aaa", "b", 1);
            Long longc = opsForHash.increment("aaa", "c", 1);
            Collection<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            List<String> multiGet = opsForHash.multiGet("aaa", list);
            for (String string : multiGet) {
                System.out.println(string + "========");
            }
            System.out.println(longa);
            System.out.println(longb);
            System.out.println(longc);
            Jedis jedis = RedisUtil.getJedis();
            jedis.flushDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
