package com.atdld.os.api.course.service.impl;


import com.atdld.os.api.course.dao.CourseMemberDao;
import com.atdld.os.api.course.entity.CourseMember;
import com.atdld.os.api.course.service.CourseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * CourseMember管理接口
 * User:
 * Date: 2014-09-26
 */
@Service("courseMemberService")
public class CourseMemberServiceImpl implements CourseMemberService {
	
	@Autowired
	private CourseMemberDao courseMemberDao;


    
    /**
     * 添加课程所属会员
     * @param CourseMember
     */
    public void addCourseMember(List<CourseMember> courseMembers){
    	courseMemberDao.addCourseMember(courseMembers);
    }
    /**
     * 删除课程所属会员
     * @param id
     */
    public void delCourseMember(Long courseId){
    	courseMemberDao.delCourseMember(courseId);
    }
  
}