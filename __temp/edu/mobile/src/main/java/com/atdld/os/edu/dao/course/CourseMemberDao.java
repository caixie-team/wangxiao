package com.atdld.os.edu.dao.course;


import java.util.List;
import java.util.Map;

import com.atdld.os.edu.entity.course.CourseMember;
import com.atdld.os.edu.entity.member.MemberType;


/**
 * CourseMember管理接口
 * User:
 * Date: 2014-09-26
 */
public interface CourseMemberDao {


   
    
    /**
     * 添加课程所属会员
     * @param CourseMember
     */
    public void addCourseMember(List<CourseMember> courseMembers);
    /**
     * 删除课程所属会员
     * @param id
     */
    public void delCourseMember(Long courseId);
    /**
     * 根据课程获得课程的会员list
     * 
     * @param List
     *            <Long> courses 课程集合
     * @return List<CourseMemberType>
     */
    public Map<Long, List<MemberType>> getCourseMemberTypeListByCourse(List<Long> courses);
}