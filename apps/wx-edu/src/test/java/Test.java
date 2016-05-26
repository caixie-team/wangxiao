import io.wangxiao.commons.service.cache.RedisUtil;
import io.wangxiao.commons.util.ObjectUtils;
import redis.clients.jedis.Jedis;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = RedisUtil.getJedis();

        jedis.flushAll();
        System.out.println("aaa.swf".endsWith(".swf"));
       /*Jedis jedis =RedisUtil.getJedis();
        String key="urlcount";
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer=new JdkSerializationRedisSerializer();

        System.out.println(jedis.type(key));
        Set<Tuple> vs=jedis.zrevrangeWithScores(key.getBytes(), 0, -1);
        for(Tuple v:vs){
            System.out.print(jdkSerializationRedisSerializer.deserialize(v.getBinaryElement()));
            System.out.print(":");
            System.out.println(v.getScore());
        }
*/

    }

    public static String checkString(Object str) {
        if (ObjectUtils.isNotNull(str)
                && !"null".equals(str.toString())) {
            return str.toString();
        } else {
            return "";
        }
    }

    public static byte[] jdkconvert(Object source) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);

        try {
            serialize(source, byteStream);
            return byteStream.toByteArray();
        } catch (Throwable var4) {
        }
        return null;
    }

    public static void serialize(Object object, OutputStream outputStream) throws IOException {
        if (!(object instanceof Serializable)) {
        } else {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        }
    }

}
