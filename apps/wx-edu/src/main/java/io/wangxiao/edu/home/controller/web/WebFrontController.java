package io.wangxiao.edu.home.controller.web;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.PropertiesReader;
import io.wangxiao.commons.util.PropertyUtil;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.help.HelpMenu;
import io.wangxiao.edu.home.entity.plan.QueryPlan;
import io.wangxiao.edu.home.service.article.ArticleService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.help.HelpMenuService;
import io.wangxiao.edu.home.service.plan.PlanService;
import io.wangxiao.edu.home.service.website.WebsiteImagesService;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebFrontController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(WebFrontController.class);

    private static String getIndexpage = getViewPath("/front/index");

    private static String getHelpCenter = getViewPath("/front/helpCenter");

    private static String guide_one = getViewPath("/guideOne/guide_one");
    private static String guide_two = getViewPath("/guideTwo/guide_two");

    @Autowired
    private CourseService courseService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private HelpMenuService helpMenuService;
    @Autowired
    private PlanService planService;
    @Autowired
    private WebsiteImagesService websiteImagesService;

    /**
     * 首页获取网站首页数据
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String getIndexpage(HttpServletRequest request, Model model) {
        try {
            // 获得banner图
            Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
            model.addAttribute("websiteImages", websiteImages);

            // 首页显示专业
            List<Subject> subjectShowIndex = subjectService.getSubjectByShowIndex();
            model.addAttribute("subjectShowIndex", subjectShowIndex);

            // 查询公告
            List<Article> noticeList = articleService.queryArticleIndex();
            model.addAttribute("noticeList", noticeList);

            // 企业动态
            List<Article> newsList = articleService.queryArticleNewsIndex();
            model.addAttribute("newsList", newsList);

            // 获取计划参与人数最多的计划
            PageEntity page = new PageEntity();
            page.setPageSize(2);
            QueryPlan queryPlan = new QueryPlan();
            queryPlan.setStatus(1L);
            queryPlan.setOrderNum(1);
            List<QueryPlan> planList = planService.getPlanList(queryPlan, page);
            model.addAttribute("planList", planList);
            // 获得所有推荐课程
            Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            model.addAttribute("mapCourseList", mapCourseList);// 推荐课程

        } catch (Exception e) {
            logger.error("WebFrontController.getIndexpage", e);
            return setExceptionRequest(request, e);
        }
        return getIndexpage;
    }

    /**
     * 获取热门的课程
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/getHotCourse")
    @ResponseBody
    public Object getHotCourse(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            // 热门课程搜索
            List<Course> courseList = courseService.getCourseListByBro();
            json = this.getJsonMap(true, "", courseList);
        } catch (Exception e) {
            logger.error(".getHotCourse", e);
            return setExceptionRequest(request, e);
        }
        return json;
    }

    @RequestMapping("/front/mdfiy")
    @ResponseBody
    public Object mdfiy(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            String k = request.getParameter("k");
            String v = request.getParameter("v");
            PropertyUtil prosecurity = PropertyUtil.getInstance("prosecurity");
            String domiankey = PurseSecurityUtils.decryption(prosecurity.getProperty("domiankey"),
                    CommonConstants.SecurityKey);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(domiankey).getAsJsonObject();
            if (k.equals("l")) {
                CommonConstants.l = v;
                jsonObject.addProperty("l", v);
            } else if (k.equals("w")) {
                CommonConstants.w = v;
                jsonObject.addProperty("w", v);
            }
            domiankey = PurseSecurityUtils.secrect(jsonObject.toString(), CommonConstants.SecurityKey);
            PropertiesReader.setValue("prosecurity", "domiankey", domiankey);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            json = this.getJsonMap(true, "failure", null);
        }
        return json;
    }

    /**
     * 帮助中心
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/help")
    public String getHelpCenter(HttpServletRequest request, Model model) {
        try {
            // 右侧显示内容的二级菜单id
            String id = request.getParameter("id");
            // 帮助中心菜单集合，不含内容
            List<List<HelpMenu>> helpMenus = helpMenuService.getHelpMenuAll();
            model.addAttribute("helpMenus", helpMenus);

            // 右侧显示内容
            HelpMenu helpMenuContent = null;
            if (id != null && !id.equals("")) {
                helpMenuContent = helpMenuService.getHelpMenuById(Long.parseLong(id));
            } else if (helpMenus.size() > 0 && helpMenus.get(0).get(1) != null) {
                helpMenuContent = helpMenuService.getHelpMenuById(helpMenus.get(0).get(1).getId());
            }
            model.addAttribute("helpMenuContent", helpMenuContent);// 推荐课程

        } catch (Exception e) {
            logger.error("WebFrontController.getHelpCenter", e);
            return setExceptionRequest(request, e);
        }
        return getHelpCenter;
    }

    /**
     * 导航one
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/guideOne")
    public String guideOne(HttpServletRequest request) {
        return guide_one;
    }

    /**
     * 导航two
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/guideTwo")
    public String guideTwo(HttpServletRequest request) {
        return guide_two;
    }

    @RequestMapping("/front/paomadeng")
    @ResponseBody
    public Map<String, Object> paomadeng(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JsonObject jsonObject = SingletonLoginUtils.getLoginUser(request);
            if (ObjectUtils.isNotNull(jsonObject)) {
                map.put("username", jsonObject.get("email"));
            } else {
                map.put("username", "");
            }
            map.put("status", 1);
            map.put("msg", "");
            map.put("fontSize", 18);
            map.put("fontColor", "0xffffff");
            map.put("speed", 200);
            map.put("filter", "on");
            map.put("setting", 3);
            map.put("alpha", 0.5);
            map.put("filterAlpha", 1);
            map.put("filterColor", "0x000000");
            map.put("blurX", 2);
            map.put("blurY", 2);
            map.put("interval", 5);
            map.put("lifeTime", 3);
            map.put("tweenTime", 1);
            map.put("strength", 4);
        } catch (Exception e) {
            logger.error("paomadeng---error", e);
        }
        return map;
    }
}