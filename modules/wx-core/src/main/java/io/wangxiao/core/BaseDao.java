package io.wangxiao.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BaseDao<T> extends SqlMapper {
/*
    */

    /**
     * 查询一批记录。
     * 如果当前实体是subjectEnable or CategoryEnable，在biz中传入了dataAuthority。
     * 从配置文件中把这两个参数抽取出来，主要是因为缓存的key需要根据参数来生成。
     * 如果这个参数不抽取出来，则当用户的数据权限变化时，会从缓存里取出错误的数据。
     *
     * @param page
     * @param conditions
     * @param dataAuthority
     * @return
     *//*

	public List<T> find(Pagination page,
                        @Param(value = "conditions") List<Condition> conditions, List<CategoryAuthorityVO> dataAuthority);
*/

    List<T> find(Pagination page,
                 @Param(value = "conditions") List<Condition> conditions);

    List<T> find(@Param(value = "conditions") List<Condition> conditions);

    List<T> findAll();

    T findById(Long id);

    void add(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(Object id);

    /**
     * 根据id的集合删除一批记录
     *
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 把ids对应的实体中的属性值更新成entity中所有非null的属性值
     *
     * @param entity
     * @param ids
     */
    void updateBatch(@Param("entity") T entity, @Param("ids") List<Long> ids);

    /**
     * 功能:修改相关实体的递增值，如问题的查看次数等
     *
     * @param entity
     */
    void updateIncrement(T entity);

}
