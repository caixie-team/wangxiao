package com.atdld.os.edu.controller.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.atdld.os.edu.service.course.CourseProfileService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.course.CourseProfile;
/**
 * CourseProfile管理接口
 * User:
 * Date: 2014-05-27
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