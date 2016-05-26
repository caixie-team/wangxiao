package io.wangxiao.edu.home.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.course.CourseProfile;
import io.wangxiao.edu.home.service.course.CourseProfileService;
/**
 * CourseProfile管理接口
 */
@Controller
public class CourseProfileController extends EduBaseController{

 	@Autowired
    private CourseProfileService courseProfileService;
    
    
    
    /**
     * 修改CourseProfile
     * @param courseProfile 要修改的CourseProfile
     */
    public void updateCourseProfile(CourseProfile courseProfile){
     	courseProfileService.updateCourseProfile(courseProfile);
    }

   
}