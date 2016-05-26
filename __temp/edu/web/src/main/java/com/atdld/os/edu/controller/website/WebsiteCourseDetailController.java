package com.atdld.os.edu.controller.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.atdld.os.edu.service.website.WebsiteCourseDetailService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.website.WebsiteCourseDetail;
/**
 * 
 * @ClassName  com.atdld.os.edu.controller.website.WebsiteCourseDetailController
 * @description
 * @author :
 * @Create Date : 2014年6月11日 下午3:16:26
 */
@Controller
public class WebsiteCourseDetailController extends EduBaseController{

 	@Autowired
    private WebsiteCourseDetailService websiteCourseDetailService;
    
    
    
    /**
     * 修改WebsiteCourseDetail
     * @param websiteCourseDetail 要修改的WebsiteCourseDetail
     */
    public void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail){
     	websiteCourseDetailService.updateWebsiteCourseDetail(websiteCourseDetail);
    }

   
}