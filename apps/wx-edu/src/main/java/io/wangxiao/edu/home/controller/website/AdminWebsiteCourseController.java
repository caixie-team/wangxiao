package io.wangxiao.edu.home.controller.website;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.GroupMiddleCourse;
import io.wangxiao.edu.home.entity.website.WebsiteCourse;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetail;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetailDTO;
import io.wangxiao.edu.home.service.website.WebsiteCourseDetailService;
import io.wangxiao.edu.home.service.website.WebsiteCourseService;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 推荐模块课程分类
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteCourseController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteCourseController.class);

    @Autowired
    private WebsiteCourseService websiteCourseService;
    @Autowired
    private WebsiteCourseDetailService websiteCourseDetailService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;

    private static final String getWebsiteCourseList = getViewPath("/admin/website/course/websiteCourse_list");
    private static final String updateWebsiteCourse = getViewPath("/admin/website/course/websiteCourse_update");
    private static final String addWebsiteCourse = getViewPath("/admin/website/course/websiteCourse_add");
    private static final String getWebsiteCourseDetailList = getViewPath("/admin/website/course/websiteCourseDetail_list");
    private static final String updateWebsiteCourseDetail = getViewPath("/admin/website/course/websiteCourseDetail_update");

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("websiteCourse")
    public void initBinderWebsiteCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("websiteCourse.");
    }

    @InitBinder("websiteCourseDetail")
    public void initBinderWebsiteCourseDetail(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("websiteCourseDetail.");
    }

    @InitBinder("websiteCourseDetailDTO")
    public void initBinderWebsiteCourseDetailDTO(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("websiteCourseDetailDTO.");
    }

    /**
     * 添加课程课程分类
     *
     * @param request
     * @param websiteCourse
     * @return
     */
    @RequestMapping("/website/addCourse")
    public String addWebsiteCourse(HttpServletRequest request, WebsiteCourse websiteCourse) {
        try {
            if (ObjectUtils.isNotNull(websiteCourse)) {
                websiteCourseService.addWebsiteCourse(websiteCourse);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.addWebsiteNavigates--添加推荐课程分类出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/website/websiteCoursePage";
    }

    /**
     * 查询课程分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/website/websiteCoursePage")
    public ModelAndView getWebsiteCourseList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(getWebsiteCourseList);
        try {
            // 查询模块课程分类
            List<WebsiteCourse> websiteCourseList = websiteCourseService.queryWebsiteCourseList();
            // 把websiteCourseList放到modelAndView
            modelAndView.addObject("websiteCourseList", websiteCourseList);
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.getWebsiteCourseList--查询课程分类列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 删除课程分类
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/website/delWebsiteCourseById/{id}")
    @ResponseBody
    public Map<String, Object> delWebsiteCourseById(HttpServletRequest request, @PathVariable Long id) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(id.toString())) {
                // 删除课程分类
                websiteCourseService.deleteWebsiteCourseDetailById(id);
                json = this.getJsonMap(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.deleteWebsiteCourseById--删除课程分类出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 添加课程分类跳转
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/website/doAddWebsiteCourse")
    public ModelAndView getWebsiteCourse() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(addWebsiteCourse);
        return modelAndView;
    }

    /**
     * 更新课程分类跳转
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/website/doUpdateWebsiteCourse/{id}")
    public ModelAndView doUpdateWebsiteCourse(HttpServletRequest request, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(updateWebsiteCourse);
        try {
            // 获得websiteCourse
            WebsiteCourse websiteCourse = websiteCourseService.queryWebsiteCourseById(id);
            modelAndView.addObject("websiteCourse", websiteCourse);
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.doUpdateWebsiteCourse--更新课程分类跳转出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 修改课程分类
     *
     * @param request
     * @param websiteCourse
     * @return
     */
    @RequestMapping("/website/updateWebsiteCourse")
    public String updateWebsiteCourse(HttpServletRequest request, WebsiteCourse websiteCourse) {
        try {
            if (ObjectUtils.isNotNull(websiteCourse)) {
                websiteCourseService.updateWebsiteCourseById(websiteCourse);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.updateWebsiteCourse--修改课程分类分类出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/website/websiteCoursePage";
    }

    /**
     * 添加推荐课程
     *
     * @param request
     * @param WebsiteCourseDetail
     * @return
     */
    @RequestMapping("/website/addCourseDetail")
    @ResponseBody
    public Map<String, Object> addWebsiteCourse(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ids = request.getParameter("ids");
            Long id = Long.parseLong(request.getParameter("recommendId"));
            int exitSize = websiteCourseDetailService.getWebsiteCourseDetails(id).size();
            WebsiteCourse websiteCourse = websiteCourseService.queryWebsiteCourseById(id);
            if (ObjectUtils.isNotNull(ids)) {//添加推荐课程
                String[] idsArry = ids.split(",");
                if (websiteCourse.getCourseNum() >= (idsArry.length + exitSize)) {//未超过该分类课程上限
                    List<WebsiteCourseDetail> websiteCourseDetails = new ArrayList<WebsiteCourseDetail>();
                    for (int i = 0; i < idsArry.length; i++) {
                        WebsiteCourseDetail websiteCourseDetail = new WebsiteCourseDetail();
                        websiteCourseDetail.setCourseId(Long.parseLong(idsArry[i]));//课程id
                        websiteCourseDetail.setOrderNum(0);//排序
                        websiteCourseDetail.setRecommendId(id);//分类id
                        websiteCourseDetails.add(websiteCourseDetail);
                    }
                    websiteCourseDetailService.addWebsiteCourseDetail(websiteCourseDetails);
                    json = this.getJsonMap(true, "true", null);
                } else {
                    json = this.getJsonMap(false, "than", websiteCourse);
                }
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.addWebsiteNavigates--添加推荐课程分类出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }


    /**
     * 添加推荐课程
     *
     * @param request
     * @param WebsiteCourseDetail
     * @return
     */
    @RequestMapping("/usergroup/addcourse")
    @ResponseBody
    public Map<String, Object> addGroupCourse(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ids = request.getParameter("ids");
            Long id = Long.parseLong(request.getParameter("groupId"));
            if (ObjectUtils.isNotNull(ids)) {//添加推荐课程
                String[] idsArry = ids.split(",");
                List<GroupMiddleCourse> groupMiddleCourseList = new ArrayList<GroupMiddleCourse>();
                for (int i = 0; i < idsArry.length; i++) {
                    GroupMiddleCourse groupMiddleCourse = new GroupMiddleCourse();
                    groupMiddleCourse.setCourseId(Long.parseLong(idsArry[i]));//课程id
                    groupMiddleCourse.setGroupId(id);
                    groupMiddleCourseList.add(groupMiddleCourse);
                }
                websiteCourseDetailService.addGroupCourseList(groupMiddleCourseList);
                json = this.getJsonMap(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.addWebsiteNavigates--添加推荐课程分类出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 查询推荐课程列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/website/websiteCourseDetailPage")
    public ModelAndView getWebsiteCourseDetailList(HttpServletRequest request, WebsiteCourseDetailDTO websiteCourseDetailDTO, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(getWebsiteCourseDetailList);
        try {
            // 查询推荐课程

            page.setPageSize(10);
            List<WebsiteCourseDetailDTO> websiteCourseDetailDTOList = websiteCourseDetailService.queryWebsiteCourseDetailList(websiteCourseDetailDTO, page);
            modelAndView.addObject("websiteCourseDetailDTOList", websiteCourseDetailDTOList);
            List<WebsiteCourse> websiteCourses = websiteCourseService.queryWebsiteCourseList();
            request.setAttribute("websiteCourses", websiteCourses);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.getWebsiteCourseDetailList--查询推荐课程列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 删除推荐课程
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/website/delWebsiteCourseDetailById/{id}")
    @ResponseBody
    public Map<String, Object> deleteWebsiteCourseDetailById(HttpServletRequest request, @PathVariable Long id) {
        Map<String, Object> json = null;
        try {
            if (StringUtils.isNotEmpty(id.toString())) {
                // 删除课程分类
                WebsiteCourseDetailDTO beforeRecord = websiteCourseDetailService.queryWebsiteCourseDetailById(id);
                websiteCourseDetailService.deleteWebsiteCourseDetail(id);
                WebsiteCourseDetailDTO afterRecord = websiteCourseDetailService.queryWebsiteCourseDetailById(id);
                SysUserOptRecord record = new SysUserOptRecord(request, "删除推荐课程记录", "推荐课程记录-" + id, beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }
                json = this.getJsonMap(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.delWebsiteCourseDetailById--删除课程分类出错", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 更新推荐课程跳转
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/website/doUpdateWebsiteCourseDetail/{id}")
    public ModelAndView doUpdateWebsiteCourseDetail(HttpServletRequest request, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(updateWebsiteCourseDetail);
        try {
            // 获得websiteCourse
            WebsiteCourseDetailDTO websiteCourseDetailDTO = websiteCourseDetailService.queryWebsiteCourseDetailById(id);
            modelAndView.addObject("websiteCourseDetailDTO", websiteCourseDetailDTO);
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.doUpdateWebsiteCourseDetail--更新推荐课程跳转出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 修改推荐课程
     *
     * @param request
     * @param websiteCourse
     * @return
     */
    @RequestMapping("/website/updateWebsiteCourseDetail")
    public String updateWebsiteCourseDetail(HttpServletRequest request, WebsiteCourseDetail websiteCourseDetail) {
        try {
            if (ObjectUtils.isNotNull(websiteCourseDetail)) {

                WebsiteCourseDetailDTO beforeRecord = websiteCourseDetailService.queryWebsiteCourseDetailById(websiteCourseDetail.getId());
                websiteCourseDetailService.updateWebsiteCourseDetail(websiteCourseDetail);
                WebsiteCourseDetailDTO afterRecord = websiteCourseDetailService.queryWebsiteCourseDetailById(websiteCourseDetail.getId());
                SysUserOptRecord record = new SysUserOptRecord(request, "修改推荐课程排序", "推荐课程记录-" + websiteCourseDetail.getId(), beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.updateWebsiteCourse--修改推荐课程出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/website/websiteCourseDetailPage";
    }


    /**
     * 添加推荐课程
     *
     * @param request
     * @param WebsiteCourseDetail
     * @return
     */
    @RequestMapping("/usergroup/delcourse")
    @ResponseBody
    public Map<String, Object> delGroupCourse(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ids = request.getParameter("ids");
            Long id = Long.parseLong(request.getParameter("groupId"));
            if (ObjectUtils.isNotNull(ids)) {//添加推荐课程
                String[] idsArry = ids.split(",");
                //List<GroupMiddleCourse> groupMiddleCourseList=new ArrayList<GroupMiddleCourse>();
                for (int i = 0; i < idsArry.length; i++) {
                    GroupMiddleCourse groupMiddleCourse = new GroupMiddleCourse();
                    groupMiddleCourse.setCourseId(Long.parseLong(idsArry[i]));//课程id
                    groupMiddleCourse.setGroupId(id);
                    websiteCourseDetailService.deleteGroupCourse(groupMiddleCourse);
                }
                json = this.getJsonMap(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteCourseController.addWebsiteNavigates--添加推荐课程分类出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }
}
