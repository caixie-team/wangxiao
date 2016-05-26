package io.wangxiao.course.dao;


import io.wangxiao.course.model.Course;
import io.wangxiao.course.vo.CourseVo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;


public interface CourseDao extends BaseMapper<Course> {

    // 使用"user.getCount"语句,无参数
    int getCount();


    //使用"user.findById", 传入参数id
    Course findById(@Param("id") Long id);

    //or 使用params，一一对应
    @SqlStatement(params = "id,status")
    Course findByIdAndStatus(Integer id, Integer status);

    //翻页查询，使用"user.queryNewUser"
    void queryNewCourse(PageQuery query);

    // 使用_st,_sz 翻页
    @SqlStatement(params = "name,age,_st,_sz")
    List<Course> queryCoruse(String name, Integer age, int start, int size);

    //使用sqlready
    @Sql(value = " update user set age = ? where id = ? ")
    void updateAge(int age, int id);

    @Sql(value = " select name from user", returnType = String.class)
    List<String> allNames();

    /**
     * 查找首页推荐课程
     *
     * @param recommendId
     * @return
     */
    List<CourseVo> findRecommendCoursesById(@Param("recommendId") Long recommendId);


}