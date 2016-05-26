package com.atdld.os.api.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.edu.entity.course.CourseProfile;
import com.atdld.os.edu.dao.course.CourseProfileDao;
import com.atdld.os.edu.service.course.CourseProfileService;
/**
 * CourseProfile管理接口
 * User:
 * Date: 2014-05-27
 */
@Service("courseProfileService")
public class CourseProfileServiceImpl implements CourseProfileService{

 	@Autowired
    private CourseProfileDao courseProfileDao;
    
    /**
     * 添加CourseProfile
     * @param courseProfile 要添加的CourseProfile
     * @return id
     */
    public Long addCourseProfile(CourseProfile courseProfile){
    	return courseProfileDao.addCourseProfile(courseProfile);
    }

    /**
     * 根据id删除一个CourseProfile
     * @param id 要删除的id
     */
    public void deleteCourseProfileById(Long id){
    	 courseProfileDao.deleteCourseProfileById(id);
    }

    /**
     * 修改CourseProfile
     * @param courseProfile 要修改的CourseProfile
     */
    public void updateCourseProfile(CourseProfile courseProfile){
     	courseProfileDao.updateCourseProfile(courseProfile);
    }


    /**
     * 根据条件获取CourseProfile列表
     * @param courseProfile 查询条件
     * @return List<CourseProfile>
     */
    public List<CourseProfile> getCourseProfileList(CourseProfile courseProfile){
    	return courseProfileDao.getCourseProfileList(courseProfile);
    }
    /**
     * 要更新的数量类型
     * 修改CourseProfile浏览次数
     * @param courseProfile 
     */
    public void updateCourseProfile(String type,Long courseId,Long count,String operation){
        courseProfileDao.updateCourseProfile(type, courseId, count,operation);
    }
    
}