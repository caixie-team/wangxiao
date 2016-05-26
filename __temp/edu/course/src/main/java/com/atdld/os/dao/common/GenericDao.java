package com.atdld.os.dao.common;


import com.atdld.os.entity.PageEntity;

import java.util.List;

/**
 * 
 * @ClassName GenericDao
 * @package com.atdld.os.core.common.dao
 * @description
 * @author
 * @Create Date: 2013-5-25 下午5:37:47
 * 
 */
public interface GenericDao {

    Long insert(String sqlKey, Object object);

    Long delete(String sqlKey, Object object);

    Long update(String key, Object object);

    <T> T selectOne(String sqlKey, Object params);

    <T> List<T> selectList(String sqlKey, Object params);

    // 分页代码
    <T> List<T> queryForListPage(String sqlKey, Object params, PageEntity page);

}
