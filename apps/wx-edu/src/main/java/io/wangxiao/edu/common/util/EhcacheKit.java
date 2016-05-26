package io.wangxiao.edu.common.util;

import io.wangxiao.commons.util.ObjectUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheKit {

    public static EhcacheKit cache = new EhcacheKit();

    CacheManager cacheManager = CacheManager.create(this.getClass().getClassLoader().getResourceAsStream("ehcache.xml"));
    Cache sample = cacheManager.getCache("SimplePageCachingFilter");

    public static EhcacheKit getInstance() {
        return cache;
    }

    public Object get(String key) {
        Element element = sample.get(key);
        if (ObjectUtils.isNotNull(element)) {
            return element.getObjectValue();
        }
        return null;
    }

    public void put(String key, Object value) {
        Element element = new Element(key, value);
        sample.put(element);

    }

    public void remove(String key) {
        sample.remove(key);
    }


}
