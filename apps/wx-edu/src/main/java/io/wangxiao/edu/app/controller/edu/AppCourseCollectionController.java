package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.constants.web.WebContants;
import io.wangxiao.edu.home.entity.course.CourseFavorites;
import io.wangxiao.edu.home.entity.course.FavouriteCourseDTO;
import io.wangxiao.edu.home.service.course.CourseFavoritesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppCourseCollectionController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppCourseCollectionController.class);
    @Autowired
    private CourseFavoritesService courseFavoritesService;
    @Autowired
    GuidGeneratorService guidGeneratorService;

    /**
     * app查询用户收藏列表
     *
     * @param page
     * @param userId
     * @return
     */
    @RequestMapping("/collection/list")
    @ResponseBody
    public Map<String, Object> queryCollectionCourseList(@ModelAttribute("page") PageEntity page,
                                                         @RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            page.setPageSize(10);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            // 用户收藏课程
            List<FavouriteCourseDTO> favouriteCourses = courseFavoritesService.getFavouriteCourseDTO(userId, page);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("favouriteCourses", favouriteCourses);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryCollectionCourseList()--error", e);
        }
        return json;
    }

    /***
     * app 添加收藏课程接口
     *
     * @param userId
     * @param courseId
     * @return
     */
    @RequestMapping("/collection/add")
    @ResponseBody
    public Map<String, Object> collectionCourse(@RequestParam("userId") Long userId,
                                                @RequestParam("courseId") Long courseId) {
        Map<String, Object> json = null;
        try {
            CourseFavorites courseFavorites = new CourseFavorites();
            courseFavorites.setCourseId(courseId);
            courseFavorites.setUserId(userId);
            courseFavorites.setAddTime(new Date());
            String falg = courseFavoritesService.addCourseFavorites(courseFavorites);
            if (falg.equals(WebContants.OWNED)) {
                json = this.getJsonMap(false, "您已经收藏过该课程了", null);
            } else if (falg.equals(WebContants.SUCCESS)) {
                json = this.getJsonMap(true, "收藏成功", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("collectionAdd()---error", e);
        }
        return json;
    }

    /**
     * 删除收藏 传入收藏ID（ids），可多个 ，用“,”隔开
     *
     * @param ids
     * @return
     */
    @RequestMapping("/collection/del")
    @ResponseBody
    public Map<String, Object> deleteCollection(@RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (ids != null && ids.trim().length() > 0) {
                if (ids.trim().endsWith(",")) {
                    ids = ids.trim().substring(0, ids.trim().length() - 1);
                }
                courseFavoritesService.deleteCourseFavoritesById(ids);
                json = this.getJsonMap(true, "删除成功", null);
            } else {
                json = this.getJsonMap(false, "请选择要删除的收藏课程！", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙！", null);
            logger.error("deleteCollection()--error", e);
        }
        return json;
    }

    /**
     * 清空收藏
     *
     * @param userId
     * @return
     */
    @RequestMapping("/collection/clean")
    @ResponseBody
    public Map<String, Object> cleanCollection(@RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            courseFavoritesService.cleanCourseFavorites(userId);
            json = this.getJsonMap(true, "清空成功", null);

        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙！", null);
            logger.error("cleanCollection()--error", e);
        }
        return json;
    }

}
