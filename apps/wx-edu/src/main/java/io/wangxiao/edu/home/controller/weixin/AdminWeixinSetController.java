package io.wangxiao.edu.home.controller.weixin;

import com.google.gson.Gson;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.website.WebsiteProfile;
import io.wangxiao.edu.home.entity.weixin.WeixinReply;
import io.wangxiao.edu.home.entity.weixin.WeixinSetReply;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.home.service.weixin.WeixinReplyService;
import io.wangxiao.edu.home.service.weixin.WeixinSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信常规回复、微信开发者配置
 */
@Controller
@RequestMapping("/admin")
public class AdminWeixinSetController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminWeixinSetController.class);

    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private WeixinSetService weixinSetService;
    @Autowired
    private WeixinReplyService weixinReplyService;

    private static final String getWeixinSite = getViewPath("/admin/weixin/set/weixin_site");
    private static final String getWebSiteList = getViewPath("/admin/website/profile/website_profile_list");// 网站配置管理页面
    private static final String getWeixinDefault = getViewPath("/admin/weixin/set/default_reply");

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("weixinSetReply")
    public void initBinderWeixinSetReply(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("weixinSetReply.");
    }

    /**
     * 查询微信配置 根据Type
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/weixinsite/find/{type}")
    public ModelAndView getWebSiteWeixin(HttpServletRequest request, Model model, @PathVariable("type") String type) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(type + "type值");
        if (type.equals("weixins")) {
            modelAndView.setViewName(getWeixinSite);
            type = "weixin";
        } else if (type.equals("weixin")) {
            modelAndView.setViewName(getWebSiteList);
        }

        try {
            // 查询微信配置 --存memcache缓存
            Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(type);
            modelAndView.addObject("weixinMap", map);
            model.addAttribute("type", type);
        } catch (Exception e) {
            logger.error("AdminWeixinSetController.getWebSiteWeixin", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 更新微信配置
     *
     * @return
     */
    @RequestMapping("/weixinsite/update")
    @ResponseBody
    public Map<String, Object> updateWebSiteWeixin(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Gson gson = new Gson();
            Map<String, String> map = new HashMap<String, String>();
            map.put("wxToken", request.getParameter("wxToken"));// token
            map.put("wxAppID", request.getParameter("wxAppID"));// appID
            map.put("wxAppSecret", request.getParameter("wxAppSecret"));// appKEY
            map.put("wxPayKey", request.getParameter("wxPayKey"));// 支付密钥
            map.put("wxMchId", request.getParameter("wxMchId"));// 商户ids
            map.put("encodingAESKey", request.getParameter("encodingAESKey"));// encodingAESKey

            map.put("mobileAppId", request.getParameter("mobileAppId"));// mobileAppId
            map.put("mobileMchId", request.getParameter("mobileMchId"));// mobileMchId
            map.put("mobilePayKey", request.getParameter("mobilePayKey"));// mobilePayKey支付密钥
            // 将map转化json串
            String mapJson = gson.toJson(map);
            if (ObjectUtils.isNotNull(mapJson) && StringUtils.isNotEmpty(mapJson)) {// 如果不为空进行更新
                WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
                websiteProfile.setType("weixin");
                websiteProfile.setDesciption(mapJson);
                websiteProfileService.updateWebsiteProfile(websiteProfile);
            }
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminWeixinSetController.updateWebSiteWeixin", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 获取微信常规回复
     *
     * @return
     */
    @RequestMapping("/default/reply/{type}")
    public ModelAndView setDefaultWeixinReply(@PathVariable String type, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getWeixinDefault);
        try {
            /*
             * 查询列表和更新查询同用此方法 区别type返回结果页面不同，根据相应类型返回
			 */
            WeixinSetReply weixinSetReply = weixinSetService.getWeixinSetReply(type);
            modelAndView.addObject("weixinSetReply", weixinSetReply);
            modelAndView.addObject("type", type);
            if (weixinSetReply != null && weixinSetReply.getReplyId() > 0) {
                WeixinReply weixinReply = weixinReplyService.getWeixinById(weixinSetReply.getReplyId());
                modelAndView.addObject("weixinReply", weixinReply);
            }
        } catch (Exception e) {
            logger.error("AdminWeixinSetController.setDefaultWeixinReply", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 更新常规回复
     *
     * @return
     */
    @RequestMapping("/default/update")
    @ResponseBody
    public Map<String, Object> setReplyAddOrUpdate(WeixinSetReply weixinSetReply, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            weixinSetService.updateSetWeixinReply(weixinSetReply);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminWeixinSetController.setReplyAddOrUpdate", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }
}
