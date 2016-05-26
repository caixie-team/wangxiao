package io.wangxiao.edu.home.dao.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseFavorites;
import io.wangxiao.edu.home.entity.course.FavouriteCourseDTO;

import java.util.List;
import java.util.Map;

/**
 * CourseFavorites管理接口
 */
public interface CourseFavoritesDao {

    /**
     * 添加CourseFavorites
     *
     * @param courseFavorites 要添加的CourseFavorites
     * @return id
     */
    java.lang.Long addCourseFavorites(CourseFavorites courseFavorites);

    /**
     * 根据id删除一个CourseFavorites
     *
     * @param id 要删除的id
     */
    void deleteCourseFavoritesById(String ids);

    /**
     * 根据courseId删除一个CourseFavorites
     *
     * @param courseId 要删除的courseId
     */
    void deleteCourseFavoritesByCourseId(Long courseId);


    void deleteCourseFavoritesByCourseIds(String ids);


    /**
     * 清空收藏
     *
     * @param userId
     */
    void cleanCourseFavorites(Long userId);

    /**
     * 修改CourseFavorites
     *
     * @param courseFavorites 要修改的CourseFavorites
     */
    void updateCourseFavorites(CourseFavorites courseFavorites);

    /**
     * 根据id获取单个CourseFavorites对象
     *
     * @param id 要查询的id
     * @return CourseFavorites
     */
    CourseFavorites getCourseFavoritesById(Long id);

    /**
     * 根据条件获取CourseFavorites列表
     *
     * @param courseFavorites 查询条件
     * @return List<CourseFavorites>
     */
    List<CourseFavorites> getCourseFavoritesList(CourseFavorites courseFavorites);

    /**
     * 通过用户Id和课程Id检查是否收藏过
     *
     * @param courseFavorites 查询条件
     * @return List<CourseFavorites>
     */
    List<CourseFavorites> checkCourseFavoritesByUserIdAndCourseId(CourseFavorites courseFavorites);


    /**
     * 通过用户获取收藏的课程
     *
     * @param userId
     * @param page
     * @return
     */
    List<FavouriteCourseDTO> getFavouriteCourseDTO(Long userId, PageEntity page);

    /**
     * 获取收藏最多的课程
     *
     * @return
     */
    List<FavouriteCourseDTO> getMoreFavouriteCourse();

    int getCourseIfFavorite(Map<String, Long> map);


}