package com.atdld.os.core.service.cache;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @ClassName com.supergenius.sns.service.common.MemCache
 * @description memcache操作类
 * @author :
 * @Create Date : 2013-12-25 上午11:55:52
 */
public class MemCache {

    private static MemCacheService memCacheService = null;
    private static MemCache memCache = new MemCache();

    public static MemCache getInstance() {
        return memCache;
    }

    private MemCache() {
        memCacheService = MemCacheServiceImpl.getInstance();
    }

    /**
     * 获取
     * 
     * @param key
     * @return Object
     */
    public Object get(String key) {
        try {
            if (memCacheService != null) {
                return memCacheService.get(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };

    /**
     * 设置。默认时间为1天
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            if (memCacheService != null) {
                return memCacheService.set(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量取
     * 
     * @param keys
     * @return
     */
    public Map<String, Object> getBulk(Set<String> keys) {
        try {
            if (memCacheService != null) {
                return memCacheService.getBulk(keys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据key删除
     * 
     * @param key
     * @return
     */
    public boolean remove(String key) {
        try {
            if (memCacheService != null) {
                return memCacheService.remove(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 存,设置超时时间
     * 
     * @param key
     *            键
     * @param value值
     * @param exp
     *            时间（秒） 60*60为一小时
     * @return
     */
    public boolean set(String key, Object value, int exp) {
        try {
            if (memCacheService != null) {
                return memCacheService.set(key, value, exp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
