package io.wangxiao.edu.home.controller.course;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.course.CourseFavorites;
import io.wangxiao.edu.home.service.course.CourseFavoritesService;

@Controller
public class CourseFavoritesController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseFavoritesController.class);
    @Autowired
    private CourseFavoritesService courseFavoritesService;
    
    /**
     * 收藏课程列表循环
     */
    public static final String favouriteList = getViewPath("");
    

    /**
     * 课程收藏
     * 
     * @return
     */
    @RequestMapping("/front/addfavorites")
    @ResponseBody
    public Map<String, Object> addFavorites(HttpServletRequest request, @RequestParam("courseId") Long courseId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CourseFavorites courseFavorites = new CourseFavorites();
            courseFavorites.setCourseId(courseId);
            courseFavorites.setUserId(getLoginUserId(request));
            courseFavorites.setAddTime(new Date());
            String falg = courseFavoritesService.addCourseFavorites(courseFavorites);
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("CourseFavoritesController.addFavorites", e);
            map.put("message", "false");
            return map;
        }
        return map;
    }
}