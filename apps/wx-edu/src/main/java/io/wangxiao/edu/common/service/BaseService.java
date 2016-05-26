package io.wangxiao.edu.common.service;

import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.edu.common.util.EhcacheKit;

public class BaseService {
    protected CacheKit cacheKit = CacheKit.getInstance();

    protected EhcacheKit ehcache = EhcacheKit.getInstance();
}
