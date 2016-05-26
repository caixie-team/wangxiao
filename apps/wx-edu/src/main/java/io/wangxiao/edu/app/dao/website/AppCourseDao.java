package io.wangxiao.edu.app.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.AppCourse;
import io.wangxiao.edu.app.entity.website.AppCourseDto;
import io.wangxiao.edu.app.entity.website.QueryAppCourseCondition;

import java.util.List;


/**
 * app课程Dao
 */
public interface AppCourseDao {
    /**
     * 查询app课程
     *
     * @param query
     * @param page
     * @return
     */
    List<AppCourseDto> queryAppMainCourse(QueryAppCourseCondition query, PageEntity page);

    /**
     * 删除app课程
     *
     * @param id
     */
    void delAppCourseById(long id);

    /**
     * 创建app课程
     *
     * @param appCourse
     */
    void createAppMainCourse(AppCourse appCourse);

    /**
     * 查询课程是否已选取
     *
     * @param id
     * @return
     */
    int getAppCourseById(Long id);

    /**
     * 批量删除app课程
     *
     * @param ids id字符串集合
     */
    void delAppCourseBatch(String ids);
}
