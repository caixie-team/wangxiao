package io.wangxiao.commons.dao.impl;

import io.wangxiao.commons.dao.GenericDao;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.entity.PageOL;
import io.wangxiao.commons.util.ObjectUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName GenericDaoImpl
 * @description 数据库基类 供其他dao继承
 *
 */
@Repository("genericDao")
public abstract class GenericDaoImpl implements GenericDao {

    public SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    @Resource(name = "sqlSessionMain")
    public void setSqlSession1(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Long insert(String sqlKey, Object object) {
        return Long.valueOf(this.getSqlSession().insert(sqlKey, object));
    }

    @Override
    public Long delete(String sqlKey, Object object) {
        return Long.valueOf(this.getSqlSession().delete(sqlKey, object));
    }

    @Override
    public Long update(String key, Object object) {
        return Long.valueOf(getSqlSession().update(key, object));
    }

    @Override
    public <T> T selectOne(String sqlKey, Object params) {
        T selectOne = null;
        List<T> list = selectList(sqlKey, params);
        if (!ObjectUtils.isNull(list)) {
            selectOne = list.get(0);
        }
        return selectOne;
    }

    @Override
    public <T> List<T> selectList(String sqlKey, Object params) {
        return this.getSqlSession().selectList(sqlKey, params);
    }

    /**
     * 分页查询时使用
     * 
     * @return
     */
    @Override
    public <T> List<T> queryForListPage(String sqlKey, Object params, PageEntity page) {

        /**
         * 分页时需要2个sql。在正常sql后面加pageCount为计算count的sql
         * 如：customre.getcustomreByTime必须命名为customre.getcustomreByTimeCount
         */

        // 查询总行数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("e", params);
        PageOL pageOL = new PageOL();
        pageOL.setOffsetPara((page.getCurrentPage() - 1) * page.getPageSize());
        pageOL.setLimitPara(page.getPageSize());
        map.put("page", pageOL);

        Integer objectscount = this.selectOne(sqlKey + "Count", map);

        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = 0;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return this.selectList(sqlKey, map);
        }

    }

}
