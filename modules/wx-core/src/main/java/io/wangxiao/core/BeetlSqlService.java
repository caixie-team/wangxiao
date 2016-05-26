package io.wangxiao.core;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by bison on 1/20/16.
 */

public abstract class BeetlSqlService<T> {

    private Class<T> entityClass;

    private Class<T> getEntityClass() {
        if (this.entityClass == null) {
            Type type = getClass().getGenericSuperclass();
            Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
            this.entityClass = (Class<T>) trueType;
        }
        return this.entityClass;
    }


    protected SQLManager dao;


    @Autowired
    public void setSqlManager(SpringBeetlSql beetlsql) {
        dao = beetlsql.getSQLMananger();

    }

    /**
     * 根据主键 id 查询
     *
     * @param pk
     * @return
     */
    public T findById(Object pk) {
        return dao.unique(getEntityClass(), pk);
    }

    /**
     * 查询全部
     *
     * @return
     */
    public List<T> findAll() {
        return dao.all(getEntityClass());
    }

    /**
     * 分页查询
     *
     * @param start
     * @param size
     * @return
     */
    public PageInfo<T> findAllByPage(int start, int size) {

        return new PageInfo<>(

                dao.all(getEntityClass(), start, size),
                size);

    }


    /**
     * 根据 sqlId 返回查询数据
     *
     * @param sqlId
     * @param dtoClass
     * @param paras
     * @param start
     * @param size
     * @return
     */
    public PageInfo findDtosAllByPage(String sqlId, Class dtoClass, Object paras, int start, int size) {

        return new PageInfo(
                dao.select(sqlId, dtoClass, paras, start, size),
                size);

    }

    /**
     * 根据条件分页查询
     *
     * @param t
     * @param start
     * @param size
     * @return
     */
    public PageInfo<T> findByTemplatePage(T t, int start, int size) {

        return new PageInfo<>(dao.template(t, start, size), size);
    }
}
