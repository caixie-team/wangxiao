package io.wangxiao.commons.service.cache;

import io.wangxiao.commons.util.PropertyUtil;

/**
 */
public class CacheKeyUtil {

    static PropertyUtil redisProperty = PropertyUtil.getInstance("redis");
    static String keynamespace=null;
    static {
        keynamespace=redisProperty.getProperty("redis.namespace");
    }

    public static String getKeyName(String key){

       /* if(StringUtils.isNotEmpty(key)){
            key=  keynamespace+key;
        }*/
        return key;
    }

}
