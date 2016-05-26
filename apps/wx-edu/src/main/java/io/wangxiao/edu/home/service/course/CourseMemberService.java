package io.wangxiao.edu.home.service.course;


import java.util.List;

import io.wangxiao.edu.home.entity.course.CourseMember;


/**
 * CourseMember管理接口
 */
public interface CourseMemberService {


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
     * 根据课程id courseMember list
     *
     * @param courseId
     * @return
     */
    List<CourseMember> getCourseMemberListByCourseId(Long courseId);

}