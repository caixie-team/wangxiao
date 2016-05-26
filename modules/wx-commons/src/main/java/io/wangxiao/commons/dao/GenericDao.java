package io.wangxiao.commons.dao;

import io.wangxiao.commons.entity.PageEntity;

import java.util.List;

/**
 * 
 * @ClassName GenericDao
 *
 */
public interface GenericDao {

    public Long insert(String sqlKey, Object object);

    public Long delete(String sqlKey, Object object);

    public Long update(String key, Object object);

    public <T> T selectOne(String sqlKey, Object params);

    public <T> List<T> selectList(String sqlKey, Object params);

    // 分页代码
    public <T> List<T> queryForListPage(String sqlKey, Object params, PageEntity page);

}
