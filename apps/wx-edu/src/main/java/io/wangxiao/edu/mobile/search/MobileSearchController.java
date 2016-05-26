package io.wangxiao.edu.mobile.search;

import io.wangxiao.edu.home.common.MobileBaseController;
import io.wangxiao.edu.home.entity.article.Article;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.service.article.ArticleService;
import io.wangxiao.edu.home.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MobileSearchController extends MobileBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MobileSearchController.class);

    private static String searchpage = getViewPath("/search/search-page");
    private static String resultpage = getViewPath("/search/search-result");


    @Autowired
    private CourseService courseService;
    @Autowired
    private ArticleService articleService;

    /**
     * 网站搜索页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/mobile/search")
    public String getSearchpage(HttpServletRequest request, Model model) {
        try {

            // 获得所有推荐课程
            Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            model.addAttribute("mapCourseList", mapCourseList);// 推荐课程

        } catch (Exception e) {
            logger.error("MobileSearchController.getSearchpage", e);
            return setExceptionRequest(request, e);
        }
        return searchpage;
    }

    /**
     * 网站搜索课程、直播、资讯
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/mobile/search/result")
    public String getSearchResult(HttpServletRequest request, Model model) {
        try {
            String content = request.getParameter("content");
            //查询相关课程
            Course course = new Course();
            course.setName(content);
            course.setSellType("NOLIVE");
            List<Course> courses = courseService.getCourseList(course);
            //查询相关直播
            course.setSellType("LIVE");
            List<Course> lives = courseService.getCourseList(course);
            //查询相关资讯
            Article article = new Article();
            article.setTitle(content);
            List<Article> articles = articleService.getArticleList(article);

            request.setAttribute("courses", courses);
            request.setAttribute("lives", lives);
            request.setAttribute("articles", articles);
        } catch (Exception e) {
            logger.error("MobileSearchController.getSearchResult", e);
            return setExceptionRequest(request, e);
        }
        return resultpage;
    }


}
