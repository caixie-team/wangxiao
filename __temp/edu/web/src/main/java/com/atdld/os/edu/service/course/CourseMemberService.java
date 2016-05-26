package com.atdld.os.edu.service.course;


import java.util.List;

import com.atdld.os.edu.entity.course.CourseMember;


/**
 * CourseMember管理接口
 * User:
 * Date: 2014-09-26
 */
public interface CourseMemberService {

 

    
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
  
}