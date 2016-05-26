package com.atdld.open.common.service;

import com.atdld.open.core.service.cache.MemCache;

/**
 * @ClassName com.atdld.open.common.service.BaseService
 * @description
 */
public class BaseService {
    protected MemCache memCache = MemCache.getInstance();
}
