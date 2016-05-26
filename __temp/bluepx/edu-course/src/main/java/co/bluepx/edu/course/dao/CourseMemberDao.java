package co.bluepx.edu.course.dao;


import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.course.entity.CourseMember;

import java.util.List;


/**
 * CourseMember管理接口
 */
public interface CourseMemberDao extends BaseDao<CourseMember> {


    /**
     * 添加课程所属会员
     *
     * @param courseMembers
     */
    void addCourseMember(List<CourseMember> courseMembers);

    /**
     * 删除课程所属会员
     *
     * @param id
     */
    void delCourseMember(Long courseId);

    /**
     * 根据课程获得课程的会员list
     *
     * @param List <Long> courses 课程集合
     * @return List<CourseMemberType>
     */
//    Map<Long, List<MemberType>> getCourseMemberTypeListByCourse(List<Long> courses);
}