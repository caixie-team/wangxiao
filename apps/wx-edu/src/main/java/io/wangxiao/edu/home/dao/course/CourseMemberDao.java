package io.wangxiao.edu.home.dao.course;


import io.wangxiao.edu.home.entity.course.CourseMember;
import io.wangxiao.edu.home.entity.member.MemberType;

import java.util.List;
import java.util.Map;


/**
 * CourseMember管理接口
 */
public interface CourseMemberDao {


    /**
     * 添加课程所属会员
     *
     * @param CourseMember
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
    Map<Long, List<MemberType>> getCourseMemberTypeListByCourse(List<Long> courses);

    /**
     * 根据课程id courseMember list
     *
     * @param courseId
     * @return
     */
    List<CourseMember> getCourseMemberListByCourseId(Long courseId);

}