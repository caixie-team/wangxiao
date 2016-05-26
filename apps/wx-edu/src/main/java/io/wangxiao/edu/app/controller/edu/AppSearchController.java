package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.service.article.ArticleService;
import io.wangxiao.edu.home.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppSearchController extends AppBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AppSearchController.class);


    @Autowired
    private CourseService courseService;
    @Autowired
    private ArticleService articleService;


    /**
     * 搜索课程、资讯
     *
     * @param content
     * @return
     */
    @RequestMapping("/search/result")
    @ResponseBody
    public Map<String, Object> getSearchResult(@RequestParam("content") String content) {
        Map<String, Object> json = null;
        try {
            //查询相关课程
            Course course = new Course();
            course.setName(content);
            course.setSellType("NOLIVE");
            List<Course> courses = courseService.getCourseList(course);
            List<Course> courseList = new ArrayList<>();
            if (course != null) {
                for (Course cou : courses) {
                    Course c = new Course();
                    c.setName(cou.getName());
                    c.setId(cou.getId());
                    courseList.add(c);
                }
            }
            //查询相关资讯
            Article article = new Article();
            article.setTitle(content);
            List<Article> articles = articleService.getArticleList(article);
            List<Article> articleList = new ArrayList<>();
            if (articles != null) {
                for (Article art : articles) {
                    Article a = new Article();
                    a.setTitle(art.getTitle());
                    a.setId(art.getId());
                    articleList.add(a);
                }
            }

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("articleList", articleList);
            dataMap.put("courseList", courseList);
            dataMap.put("content", content);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            logger.error("AppSearchController.getSearchResult", e);
            json = this.getJsonMap(false, "系统繁忙", null);
        }
        return json;
    }


}
