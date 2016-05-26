package io.wangxiao.edu.app.controller.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.app.entity.website.AppWebsiteImages;
import io.wangxiao.edu.app.service.website.AppCourseService;
import io.wangxiao.edu.app.service.website.AppWebsiteImagesService;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminAppWebsiteImagesController extends AppBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminAppWebsiteImagesController.class);

    @Autowired
    private AppWebsiteImagesService appWebsiteImagesService;
    @Autowired
    private AppCourseService appCourseService;

    private static final String getWebsiteImagesList = getViewPath("/admin/website/images/websiteImages_list");
    private static final String updateWebsiteImages = getViewPath("/admin/website/images/websiteImages_update");
    private static final String addWebsiteImages = getViewPath("/admin/website/images/websiteImages_add");
    private static final String showRecommendCourseList = getViewPath("/admin/website/images/course_recommend_list");// 课程列表(推荐课程)

    @Autowired
    private CourseService courseService;

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("appWebsiteImages")
    public void initBinderWebsiteImages(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("appWebsiteImages.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    /**
     * 添加广告图
     *
     * @return
     */
    @RequestMapping("/appWebsite/addImages")
    public String addWebsiteImages(AppWebsiteImages appWebsiteImages, HttpServletRequest request) {
        try {
            if (appWebsiteImages != null) {
                // 判断广告图路径和广告图关键是否不为空
                if (appWebsiteImages.getImagesUrl() != null && !appWebsiteImages.getImagesUrl().equals("") && appWebsiteImages.getKeyWord() != null && !appWebsiteImages.getKeyWord().equals("")) {
                    // 插入广告图
                    if (appWebsiteImages.getSeriesNumber() == null || appWebsiteImages.getSeriesNumber() == 0) {
                        appWebsiteImages.setSeriesNumber(0);
                    }

                    if (appWebsiteImages.getColor() == null || appWebsiteImages.getColor().trim().length() == 0) {
                        appWebsiteImages.setColor("");
                    }
                    appWebsiteImagesService.insertWebsiteImages(appWebsiteImages);
                }
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteImagesController.addWebsiteImages--添加广告图出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/appWebsite/imagesPage";
    }

    /**
     * 跳转添加广告图
     *
     * @return
     */
    @RequestMapping("/appWebsite/doAddImages")
    public String doAddWebsiteImages(Model model) {

        return addWebsiteImages;
    }

    /**
     * 广告图分页列表
     *
     * @return
     */
    @RequestMapping("/appWebsite/imagesPage")
    public ModelAndView getWebsiteImagesPage(AppWebsiteImages appWebsiteImages, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getWebsiteImagesList);
        try {

            page.setPageSize(10);
            List<AppWebsiteImages> websiteImagesList = appWebsiteImagesService.getImgPageList(appWebsiteImages, page);
            modelAndView.addObject("websiteImagesList", websiteImagesList);
            modelAndView.addObject("page", page);
            modelAndView.addObject("appWebsiteImages", appWebsiteImages);
        } catch (Exception e) {
            logger.error("AdminWebsiteImagesController.getWebsiteImagesPage--广告图分页列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 删除广告图
     *
     * @return
     */
    @RequestMapping("/appWebsite/delImages/{ids}")
    @ResponseBody
    public Map<String, Object> deleteWebsiteImages(@PathVariable String ids) {
        Map<String, Object> json = null;
        try {
            if (ids != null) {
                if (ids.trim().endsWith(",")) {
                    ids = ids.trim().substring(0, ids.trim().length() - 1);
                }
                appWebsiteImagesService.deleteImgByIds(ids);
                json = this.getJsonMap(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteImagesController.deleteWebsiteImages--删除广告图出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 跳转广告图修改
     *
     * @return
     */
    @RequestMapping("/appWebsite/doUpdateImages/{id}")
    public ModelAndView doUpdateImages(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(updateWebsiteImages);
        try {
            AppWebsiteImages appWebsiteImages = appWebsiteImagesService.getWebsiteImagesById(id);
            modelAndView.addObject("appWebsiteImages", appWebsiteImages);
            Course course = courseService.getCourseById(appWebsiteImages.getCourseId());
            modelAndView.addObject("course", course);
        } catch (Exception e) {
            logger.error("AdminWebsiteImagesController.doUpdateImages--跳转广告图修改", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 更新广告图
     *
     * @return
     */
    @RequestMapping("/appWebsite/updateImages")
    public String updatewebsiteImages(AppWebsiteImages appWebsiteImages, HttpServletRequest request) {
        try {
            if (appWebsiteImages != null) {

                if (appWebsiteImages.getColor() == null || appWebsiteImages.getColor().equals("")) {
                    appWebsiteImages.setColor("#FFFFFF");
                }
                appWebsiteImagesService.updateWebsiteImages(appWebsiteImages);
            }

        } catch (Exception e) {
            logger.error("AdminWebsiteImagesController.updateImages--更新广告图出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/appWebsite/imagesPage";
    }

    /**
     * 课程列表(banner选课程)
     *
     * @return
     */
    @RequestMapping("/appWebsite/CourseList")
    public String showCourseListByRecommend(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 添加时间倒叙
            queryCourse.setOrder(2);
            // 搜索课程列表
            List<CourseDto> courseList = appCourseService.getCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 添加时间倒叙
            queryCourse.setOrder(2);
            // 只查询上架的
            queryCourse.setIsavaliable(0L);
        } catch (Exception e) {
            logger.error("CourseController.showCourseListByRecommend", e);
            return setExceptionRequest(request, e);
        }
        return showRecommendCourseList;
    }

}
