package co.bluepx.edu.common.service;

import com.atdld.os.core.service.cache.MemCache;

/**
 * @author :
 * @ClassName com.atdld.os.common.service.BaseService
 * @description
 * @Create Date : 2014-6-13 上午8:59:59
 */
public class BaseService {
    protected MemCache memCache = MemCache.getInstance();
}
