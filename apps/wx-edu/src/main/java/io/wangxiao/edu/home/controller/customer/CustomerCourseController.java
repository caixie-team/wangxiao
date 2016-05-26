package io.wangxiao.edu.home.controller.customer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.common.util.DateEditor;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.customer.CustomerCourse;
import io.wangxiao.edu.home.entity.customer.CustomerCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCusCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCustomerCourse;
import io.wangxiao.edu.home.service.customer.CustomerCourseService;
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
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * CustomerCourse管理接口
 */
@Controller
public class CustomerCourseController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerCourseController.class);

    private static final String frontCustomerCourseList = getViewPath("/front/customerCourse_frontlist");

    @Autowired
    private CustomerCourseService customerCourseService;

    /**
     * 绑定属性参数
     *
     * @param binder
     */
    @InitBinder("queryCustomerCourse")
    public void initBinderqueryCustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCustomerCourse.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 绑定 customerCourse
     */
    @InitBinder("customerCourse")
    public void initBindercustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("customerCourse.");
    }

    /**
     * 绑定queryCusCourseRecord 封装参数
     *
     * @param queryCusCourseRecord
     */
    @InitBinder("queryCusCourseRecord")
    public void initBinderqueryCusCourseRecord(HttpServletRequest request, ServletRequestDataBinder binder) {

        binder.setFieldDefaultPrefix("queryCusCourseRecord.");
    }

    /**
     * 修改CustomerCourse
     *
     * @param customerCourse 要修改的CustomerCourse
     */
    public void updateCustomerCourse(CustomerCourse customerCourse) {
        customerCourseService.updateCustomerCourse(customerCourse);
    }

    /**
     * 前台分页查询所有自定义课程列表
     *
     * @param queryCustomerCourse
     * @return model
     */

    @RequestMapping("/front/customerCourse")
    @ResponseBody
    public ModelAndView getFrontCustomerCourseList(HttpServletRequest request, @ModelAttribute("queryCustomerCourse") QueryCustomerCourse queryCustomerCourse, @ModelAttribute("page") PageEntity page) {
        ModelAndView model = new ModelAndView();
        try {
            model.setViewName(frontCustomerCourseList);

            page.setPageSize(6);
            String title = URLDecoder.decode(queryCustomerCourse.getTitle() != null ? queryCustomerCourse.getTitle() : "", "utf-8").replace(" ", "");
            if (title != null) {
                queryCustomerCourse.setTitle(title);
            }
            queryCustomerCourse.setStatus(0L);
            List<QueryCustomerCourse> courseList = new ArrayList<QueryCustomerCourse>();
            courseList = customerCourseService.getCustomerCourseList(queryCustomerCourse, page);
            //查询加入自定义课程的人数
            int cusCourseJoinNum = customerCourseService.queryJoinNum();
            model.addObject("cusCourseJoinNum", cusCourseJoinNum);
            model.addObject("courseList", courseList);
            model.addObject("page", page);
            model.addObject("queryCustomerCourse", queryCustomerCourse);
        } catch (Exception e) {
            logger.error("getFrontCustomerCourseList-----------", e);
        }
        return model;
    }

    /**
     * 前台学员创建自定义课程
     *
     * @param CustomerCourse
     * @return json
     */
    @RequestMapping("/front/tocommitCusCourse")
    @ResponseBody
    public Map<String, Object> customerCommitCourse(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("customerCourse") CustomerCourse customerCourse) {
        Map<String, Object> json = null;
        try {
            if (customerCourse != null) {
                customerCourse.setCreateTime(new Date());
                customerCourse.setStatus(1L);
                customerCourse.setCreateuserId(getLoginUserId(request));
                customerCourse.setJoinNum(0L);
                customerCourseService.addCustomerCourse(customerCourse);
                json = this.getJsonMap(true, "提交成功", customerCourse);
            } else {
                json = this.getJsonMap(false, "参数为空", null);
            }

        } catch (Exception e) {
            logger.error("customerCommitCourse.error------", e);
            json = this.getJsonMap(false, "请求错误", null);
        }
        return json;
    }

    /**
     * 增加加入自定义课程的人数 投票记录
     *
     * @param
     */
    @RequestMapping("/front/userjoinCourse")
    @ResponseBody
    public Map<String, Object> addUserforCusCourse(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("queryCusCourseRecord") QueryCusCourseRecord queryCusCourseRecord) {
        Map<String, Object> json = null;
        try {
            if (queryCusCourseRecord != null) {
                queryCusCourseRecord.setUserId(getLoginUserId(request));
                boolean joinStatus = customerCourseService.queryuserIsjoinCourse(queryCusCourseRecord);
                if (joinStatus) {
                    json = this.getJsonMap(false, "alreadyJoin", null);
                } else {
                    CustomerCourseRecord customerCourseRecord = new CustomerCourseRecord();
                    customerCourseRecord.setCustomerCourseId(queryCusCourseRecord.getCusCourseId());
                    customerCourseRecord.setUserId(queryCusCourseRecord.getUserId());
                    customerCourseRecord.setJoinTime(new Date());
                    customerCourseService.createCustomerCourseRecord(customerCourseRecord);
                    CustomerCourse course = customerCourseService.getCustomerCourseById(queryCusCourseRecord.getCusCourseId());
                    if (course.getJoinNum() == null) {
                        course.setJoinNum(1L);
                    } else {
                        course.setJoinNum(course.getJoinNum() + 1L);
                    }
                    customerCourseService.updateCustomerCourse(course);
                    json = this.getJsonMap(true, "success", null);
                }
            }
        } catch (Exception e) {
            logger.error("addUserforCusCourse.error-------", e);
            json = this.getJsonMap(false, "请求出错", null);
        }
        return json;
    }


}