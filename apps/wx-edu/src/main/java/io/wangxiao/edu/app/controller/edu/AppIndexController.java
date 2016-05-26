package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.app.entity.website.AppWebsiteImages;
import io.wangxiao.edu.app.service.edu.EduAppService;
import io.wangxiao.edu.app.service.website.AppWebsiteImagesService;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.service.article.ArticleService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppIndexController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppIndexController.class);

    @Autowired
    private EduAppService eduAppService;
    @Setter
    @Getter
    private QueryCourse queryCourse;
    @Autowired
    private AppWebsiteImagesService appWebsiteImagesService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    GuidGeneratorService guidGeneratorService;

    /***
     * app查询首页推荐课程
     *
     * @return
     */
    @RequestMapping("/index/course")
    @ResponseBody
    public Map<String, Object> queryWebsiteCourseDetails() {
        Map<String, Object> json = null;
        try {
            Map<String, List<Map<String, Object>>> recommendMap = eduAppService.getWebWebsiteCourseDetails();
            json = this.getJsonMap(true, "查询成功", recommendMap);
        } catch (Exception e) {
            logger.error("queryWebsiteCourseDetails", e);
            json = this.getJsonMap(false, "系统繁忙", null);
        }
        return json;
    }

    /***
     * app查询首页banner图片
     *
     * @return
     */
    @RequestMapping("/index/banner")
    @ResponseBody
    public Map<String, Object> queryIndexBanner() {
        Map<String, Object> json = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("count", 4);
            paramMap.put("keyWord", "indexCenterBanner");
            Map<String, List<AppWebsiteImages>> map = appWebsiteImagesService.getIndexPageBanner(paramMap);
            json = this.getJsonMap(true, "查询成功", map);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryIndexBanner()---error", e);
        }
        return json;
    }

    /***
     * app查询首页公告 最新三条
     *
     * @return
     */
    @RequestMapping("/index/article")
    @ResponseBody
    public Map<String, Object> queryIndexArticle() {
        Map<String, Object> json = null;
        try {
            List<Article> articles = articleService.getAppArticleList(3);
            json = this.getJsonMap(true, "查询成功", articles);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryIndexBanner()---error", e);
        }
        return json;
    }
}
