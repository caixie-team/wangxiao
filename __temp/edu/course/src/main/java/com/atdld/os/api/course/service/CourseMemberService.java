package com.atdld.os.api.course.service;


import com.atdld.os.api.course.entity.CourseMember;

import java.util.List;



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