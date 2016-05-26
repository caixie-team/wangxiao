package io.wangxiao.edu.home.controller.customer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.util.DateEditor;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.customer.CustomerCourse;
import io.wangxiao.edu.home.entity.customer.QueryCustomerCourse;
import io.wangxiao.edu.home.service.customer.CustomerCourseService;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台customerCourse接口管理
 */
@Controller
@RequestMapping("/admin")
public class AdminCustomerCourseController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCustomerCourseController.class);
    private static final String customerCourseList = getViewPath("/admin/course/customerCourse_list");
    private String deleteRedirect = "redirect:/admin/customerCourse/CourseList";
    /**
     * customerCourseService服务
     */
    @Autowired
    private CustomerCourseService customerCourseService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;

    /**
     * 绑定CustomerCourse属性
     */
    @InitBinder("customerCourse")
    public void initBinderCustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("customerCourse.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @InitBinder("queryCustomerCourse")
    public void initBinderqueryCustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCustomerCourse.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 后台自定义课程列表
     *
     * @param queryCustomerCourse
     */
    @RequestMapping("/customerCourse/CourseList")
    public ModelAndView queryCustomerCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCustomerCourse") QueryCustomerCourse queryCustomerCourse) {
        ModelAndView model = new ModelAndView();
        try {

            model.setViewName(customerCourseList);
            page.setPageSize(10);
            List<QueryCustomerCourse> courseList = new ArrayList<QueryCustomerCourse>();
            courseList = customerCourseService.getCustomerCourseList(queryCustomerCourse, page);
            model.addObject("courseList", courseList);
            model.addObject("page", page);
            model.addObject("queryCustomerCourse", queryCustomerCourse);
        } catch (Exception e) {
            logger.error("queryCustomerCourseList-----", e);
        }

        return model;
    }

    /**
     * 删除自定义课程
     *
     * @param customerCourseId
     */
    @RequestMapping("/customerCourse/deleteCourse")
    public ModelAndView deleteCustomerCourse(HttpServletRequest request, @ModelAttribute("customerCourseId") long customerCourseId) {
        ModelAndView model = new ModelAndView(deleteRedirect);
        try {
            if (ObjectUtils.isNotNull(customerCourseId)) {
                CustomerCourse beforeRecord = customerCourseService.getCustomerCourseById(customerCourseId);
                customerCourseService.deleteCustomerCourseById(customerCourseId);
                CustomerCourse afterRecord = customerCourseService.getCustomerCourseById(customerCourseId);
                SysUserOptRecord record = new SysUserOptRecord(request, "删除自定义课程", "自定义课程记录-" + customerCourseId, beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }
            }
        } catch (Exception e) {
            logger.error("deleteCustomerCourse.AdminCustomerCourseController", e);
        }
        return model;
    }

    /**
     * 更新自定义课程状态 默认显示
     *
     * @param customerCourse
     */
    @RequestMapping("/customerCourse/updateCustomerCourse")
    @ResponseBody
    public Map<String, Object> updateCustomerCourse(HttpServletRequest request, HttpServletResponse response, @ModelAttribute() CustomerCourse customerCourse) {
        Map<String, Object> json = null;
        try {
            // CustomerCourse cusCourse=new CustomerCourse();
            if (customerCourse != null) {
                CustomerCourse beforeRecord = customerCourseService.getCustomerCourseById(customerCourse.getId());
                CustomerCourse course = customerCourseService.getCustomerCourseById(customerCourse.getId());
                course.setStatus(customerCourse.getStatus());
                customerCourseService.updateCustomerCourse(course);
                CustomerCourse afterRecord = customerCourseService.getCustomerCourseById(customerCourse.getId());
                String statusStr = "";
                if (customerCourse.getStatus() == 0) {
                    statusStr = "显示";
                } else {
                    statusStr = "隐藏";
                }
                SysUserOptRecord record = new SysUserOptRecord(request, statusStr + "自定义课程", "自定义课程记录-" + customerCourse.getId(), beforeRecord, afterRecord);
                if (record != null) {
                    sysUserOptRecordService.addRecord(record);
                }
                json = this.getJsonMap(true, "updatesuccess", course);
            } else {
                json = this.getJsonMap(false, "参数为空", null);
            }
        } catch (Exception e) {
            logger.error("updateCustomerCourse----------", e);
            json = this.getJsonMap(false, "请求错误", null);
        }

        return json;
    }

}
