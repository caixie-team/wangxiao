package com.atdld.os.edu.service.impl.course;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.edu.dao.course.CourseMemberDao;
import com.atdld.os.edu.entity.course.CourseMember;
import com.atdld.os.edu.service.course.CourseMemberService;


/**
 * CourseMember管理接口
 * User:
 * Date: 2014-09-26
 */
@Service("courseMemberService")
public class CourseMemberServiceImpl implements CourseMemberService{
	
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