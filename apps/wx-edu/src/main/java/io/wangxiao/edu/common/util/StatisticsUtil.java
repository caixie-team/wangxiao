package io.wangxiao.edu.common.util;

import io.wangxiao.commons.service.cache.CacheKit;
import org.springframework.data.redis.core.HashOperations;
import redis.clients.jedis.Jedis;

import java.util.BitSet;
import java.util.Set;

public class StatisticsUtil {
    public static CacheKit cacheKit = CacheKit.getInstance();
    public static Jedis jedis = cacheKit.getJedis();

    /**
     * 获取hash操作类
     *
     * @return
     */
    public static HashOperations<String, String, String> getOpsForHash() {
        @SuppressWarnings("unchecked")
        HashOperations<String, String, String> opsForHash = cacheKit.getCache().opsForHash();
        return opsForHash;
    }

    /**
     * 获取某个用户操作在某天的活跃用户
     *
     * @param action
     * @param date
     * @return
     */
    public static int uniqueCount(String action, String date) {
        String key = action + ":" + date;
        byte[] bs = jedis.get(key.getBytes());
        if (bs != null && bs.length > 0) {
            BitSet users = BitSet.valueOf(bs);
            if (users != null) {
                return users.cardinality();
            }
        }
        return 0;

    }

    /**
     * 返回键set集合
     *
     * @param key
     * @return
     */
    public static Set<String> getKeys(String key) {
        return jedis.keys(key);
    }

    /**
     * 设置某个用户操作在某天的活跃用户
     *
     * @param action
     * @param dateOrAction
     * @return
     */
    public static void setUniqueCount(String action, String date, Long userId) {
        String key = action + ":" + date;
        jedis.setbit(key, userId, true);
    }

    /**
     * 统计某个用户操作在一个指定多个日期的活跃用户
     *
     * @param action
     * @param dates
     * @return
     */
    public static int uniqueCount(String action, String... dates) {
        BitSet all = new BitSet();
        for (String date : dates) {
            String key = action + ":" + date;
            byte[] bs = key.getBytes();
            if (bs != null && bs.length > 0) {
                BitSet users = BitSet.valueOf(bs);
                if (users != null) {
                    all.or(users);
                }
            }
        }
        if (all != null && all.length() > 0) {
            return all.cardinality();
        }
        return 0;
    }

    /**
     * 获取统计事件的所有时间的数据
     *
     * @param action
     * @return
     */
    public static Long getActionAllCount(String action) {
        Long returnNum = 0L;
        Set<String> keys = jedis.keys(action + ":*");
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                String numStr = jedis.get(key);
                if (numStr != null) {
                    returnNum += Long.parseLong(numStr);
                }
            }
        }
        return returnNum;
    }

    /**
     * 获取统计事件的所有时间或分事件的数据
     *
     * @param action
     * @param dateOrAction 时间或事件
     * @return
     */
    public static Long getActionOneDayOrActionCount(String action, String dateOrAction) {
        Long returnNum = 0L;
        String numStr = jedis.get(action + ":" + dateOrAction);
        if (numStr != null) {
            returnNum += Long.parseLong(numStr);
        }
        return returnNum;
    }

    /**
     * 增加1统计事件的数据
     *
     * @param action       事件
     * @param dateOrAction 时间或事件
     * @return
     */
    public static Long incrActionOneDayOrActionCount(String action, String dateOrAction) {
        Long incr = jedis.incr(action + ":" + dateOrAction);
        return incr;
    }

    /**
     * 设置统计事件的传入时间或事件的数据
     *
     * @param action       事件
     * @param dateOrAction 时间或事件
     * @param count        数量
     */
    public static void setActionOneDayOrActionCount(String action, String dateOrAction, Long count) {
        jedis.set(action + ":" + dateOrAction, count.toString());
    }
}
