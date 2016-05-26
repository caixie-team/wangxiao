package io.wangxiao.edu.home.controller.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.website.WebsiteClassify;
import io.wangxiao.edu.home.service.website.WebsiteClassifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: 分类管理
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteClassifyController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteClassifyController.class);

    private static final String website_classify_list = getViewPath("/admin/website/classify/website_classify");//列表
    private static final String website_add = getViewPath("/admin/website/classify/website_add");//添加
    private static final String website_classify_update = getViewPath("/admin/website/classify/website_classify_update");

    @Autowired
    private WebsiteClassifyService websiteClassifyService;

    /*绑定变量参数*/
    @InitBinder("websiteClassify")
    public void initBinderWebsiteClassify(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("websiteClassify.");
    }

    /**
     * 查询分类列表
     *
     * @param request
     * @param websiteClassify
     * @param page
     * @return
     */
    @RequestMapping("/websiteClassify/list")
    public String getWebsiteClassifyList(HttpServletRequest request, Model model, @ModelAttribute WebsiteClassify websiteClassify, @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(10);
            List<WebsiteClassify> webClassifies = websiteClassifyService.getWebsiteClassifyList(websiteClassify, page);
            model.addAttribute("webClassifies", webClassifies);
            model.addAttribute("websiteClassify", websiteClassify);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("AdminControllerWebsiteClassify.getWebsiteClassifyList", e);
        }
        return website_classify_list;
    }

    /**
     * 跳转添加
     *
     * @param request
     * @return
     */
    @RequestMapping("/websiteClassify/toadd")
    public String toaddWebsiteClassify(HttpServletRequest request) {
        return website_add;
    }

    /**
     * 添加分类
     *
     * @param request
     * @param websiteClassify
     * @return
     */
    @RequestMapping("/websiteClassify/add")
    @ResponseBody
    public Map<String, Object> addWebsiteClassify(HttpServletRequest request, @ModelAttribute("websiteClassify") WebsiteClassify websiteClassify) {
        Map<String, Object> jsonMap = null;
        try {
            List<WebsiteClassify> classifies = websiteClassifyService.getWebsiteClassifyByCondition(websiteClassify);
            if (ObjectUtils.isNotNull(classifies)) {//查询该关键词是否存在
                jsonMap = this.getJsonMap(false, "添加关键词已存在", null);
                return jsonMap;
            }
            websiteClassify.setLevel(1L);
            websiteClassifyService.addWebsiteClassify(websiteClassify);
            jsonMap = this.getJsonMap(true, "添加成功", null);
        } catch (Exception e) {
            logger.error("AdminControllerWebsiteClassify.addWebsiteClassify", e);
        }
        return jsonMap;
    }

    /**
     * 删除此条信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/websiteClassify/del")
    @ResponseBody
    public Map<String, Object> deleteWebsiteClassify(@RequestParam("id") Long id) {
        Map<String, Object> json = null;
        try {
            // 删除此条信息
            websiteClassifyService.deleteWebsiteMemcacheById(id);
            json = this.getJsonMap(true, "", null);
        } catch (Exception e) {
            logger.error("AdminWebsiteMemcacheController.deleteWebsiteMemcache", e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }

    /**
     * 跳转更新页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/websiteClassify/toupdate/{id}")
    public String getWebsiteClassify(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
        try {
            WebsiteClassify websiteClassify = websiteClassifyService.getWebsiteClassifyById(id);
            model.addAttribute("websiteClassify", websiteClassify);
        } catch (Exception e) {
            logger.error("getWebsite", e);
        }
        return website_classify_update;
    }

    /**
     * 更新
     *
     * @param request
     * @param websiteClassify
     * @return
     */
    @RequestMapping("/websiteClassify/update")
    @ResponseBody
    public Map<String, Object> updateWebsiteClassify(HttpServletRequest request, @ModelAttribute("websiteClassify") WebsiteClassify websiteClassify) {
        Map<String, Object> jsonMap = null;
        try {
            websiteClassifyService.updateWebsiteClassify(websiteClassify);
            jsonMap = this.getJsonMap(true, "修改成功", null);
        } catch (Exception e) {
            logger.error("updateWebsiteClassify", e);
            jsonMap = this.getJsonMap(false, "系统繁忙，请稍后重试", null);
        }
        return jsonMap;
    }
}
