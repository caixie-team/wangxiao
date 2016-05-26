package com.atdld.os.edu.controller.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.atdld.os.edu.service.course.CourseTagService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.course.CourseTag;
/**
 * CourseTag管理接口
 * User:
 * Date: 2014-05-27
 */
@Controller
public class CourseTagController extends EduBaseController{

 	@Autowired
    private CourseTagService courseTagService;
    
    
    
    /**
     * 修改CourseTag
     * @param courseTag 要修改的CourseTag
     */
    public void updateCourseTag(CourseTag courseTag){
     	courseTagService.updateCourseTag(courseTag);
    }

   
}