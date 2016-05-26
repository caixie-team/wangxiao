package io.wangxiao.edu.home.controller.website;

import io.wangxiao.edu.common.util.DateEditor;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.website.WebsiteImageMange;
import io.wangxiao.edu.home.service.website.WebsiteImageMangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminWebsiteImageMangeController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteImageMangeController.class);

    @Autowired
    private WebsiteImageMangeService websiteImageMangeService;

    private static final String addImagesMange = getViewPath("/admin/website/images/imagesMange_add");// 图片类型添加
    private static final String updateImagesMange = getViewPath("/admin/website/images/imagesMange_update");// 图片类型修改
    private static final String imagesMangeList = getViewPath("/admin/website/images/imagesMange_list");// 图片类型列表

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("websiteImageMange")
    public void initBinderWebsiteImageMange(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("websiteImageMange.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 更新单个管理图片信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/website/toAddImagesMange")
    public ModelAndView toAddImagesMange(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(addImagesMange);
        try {
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.toAddImagesMange", e);
            modelAndView.setViewName(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 执行图片的添加
     *
     * @param request
     * @param websiteImageMange
     * @return
     */
    @RequestMapping("/website/addImagesMange")
    @ResponseBody
    public Map<String, Object> addImagesMange(HttpServletRequest request,
                                              @ModelAttribute("websiteImageMange") WebsiteImageMange websiteImageMange) {
        websiteImageMange.setCreateTime(new Date());
        Map<String, Object> json = null;
        try {
            websiteImageMangeService.addimagesMange(websiteImageMange);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.addImagesMange", e);
            json = this.getJsonMap(false, "fail", null);
        }
        return json;
    }

    /**
     * 检查图片key 是否重复
     *
     * @return
     */
    @RequestMapping("/website/checkImagesMange")
    @ResponseBody
    public Map<String, Object> checkImagesMange(HttpServletRequest request) {
        String key = request.getParameter("imageKey");
        Map<String, Object> json = null;
        try {
            int count = websiteImageMangeService.checkImagesMange(key);
            if (count == 0) {
                json = this.getJsonMap(true, "success", null);
            } else {
                json = this.getJsonMap(false, "fail", null);
            }
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.addImagesMange", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 删除图片管理
     *
     * @param id
     * @return
     */
    @RequestMapping("/website/deleteImagesMange/{id}")
    public String deleteImagesMange(@PathVariable("id") long id) {
        try {
            websiteImageMangeService.deleteImagesMangeById(id);
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.deleteImagesMange", e);
        }
        return "redirect:/admin/website/imagesMangeList";
    }

    /**
     * 得到单个管理图片的信息并跳转更新页面
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/website/toUpdateImagesMange/{key}")
    public String toUpdateImagesMange(@PathVariable("key") String key, Model model) {
        try {
            WebsiteImageMange websiteImageMange = websiteImageMangeService.getImagesMangeByKey(key);
            model.addAttribute("websiteImageMange", websiteImageMange);
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.toUpdateImagesMange", e);
        }
        return updateImagesMange;
    }

    /**
     * 更新单个管理图片信息
     *
     * @param websiteImageMange
     * @return
     */
    @RequestMapping("/website/updateImagesMange")
    public String updateImagesMange(@ModelAttribute("websiteImageMange") WebsiteImageMange websiteImageMange) {
        try {
            websiteImageMangeService.updateImagesMange(websiteImageMange);
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.toUpdateImagesMange", e);
        }
        return "redirect:/admin/website/imagesMangeList";
    }

    /**
     * 展现全部的图片管理列表
     *
     * @return
     */
    @RequestMapping("/website/imagesMangeList")
    public String imagesMangeList(Model model) {
        try {
            List<WebsiteImageMange> websiteImageMangeList = websiteImageMangeService.queryWebsiteImageMangeList();
            model.addAttribute("websiteImageMangeList", websiteImageMangeList);
            //System.out.println(websiteImageMangeList);
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.imagesMangeList", e);
        }
        return imagesMangeList;
    }

    /**
     * 批量删除图片类型
     *
     * @param request
     * @return
     */
    @RequestMapping("/website/delImageMangebatch")
    @ResponseBody
    public Map<String, Object> delImageMangebatch(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ids = request.getParameter("ids");
            websiteImageMangeService.delImageMangebatch(ids);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminWebsiteImageMangeController.delImageMangebatch", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }
}
