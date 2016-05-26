package io.wangxiao.edu.home.controller.website;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetail;
import io.wangxiao.edu.home.service.website.WebsiteCourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WebsiteCourseDetailController extends EduBaseController {

    @Autowired
    private WebsiteCourseDetailService websiteCourseDetailService;


    /**
     * 修改WebsiteCourseDetail
     *
     * @param websiteCourseDetail 要修改的WebsiteCourseDetail
     */
    public void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail) {
        websiteCourseDetailService.updateWebsiteCourseDetail(websiteCourseDetail);
    }


}