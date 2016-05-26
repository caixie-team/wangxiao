package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.CourseFavoritesDao;
import io.wangxiao.edu.home.entity.course.CourseFavorites;
import io.wangxiao.edu.home.entity.course.FavouriteCourseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("courseFavoritesDao")
public class CourseFavoritesDaoImpl extends GenericDaoImpl implements CourseFavoritesDao {

    public java.lang.Long addCourseFavorites(CourseFavorites courseFavorites) {
        return this.insert("CourseFavoritesMapper.createCourseFavorites", courseFavorites);
    }

    public void deleteCourseFavoritesById(String ids) {
        this.delete("CourseFavoritesMapper.deleteCourseFavoritesById", ids);
    }

    public void deleteCourseFavoritesByCourseId(Long courseId) {
        this.delete("CourseFavoritesMapper.deleteCourseFavoritesByCourseId", courseId);
    }

    /**
     * 清空收藏
     *
     * @param userId
     */
    public void cleanCourseFavorites(Long userId) {
        this.delete("CourseFavoritesMapper.cleanCourseFavorites", userId);
    }

    public void updateCourseFavorites(CourseFavorites courseFavorites) {
        this.update("CourseFavoritesMapper.updateCourseFavorites", courseFavorites);
    }

    public CourseFavorites getCourseFavoritesById(Long id) {
        return this.selectOne("CourseFavoritesMapper.getCourseFavoritesById", id);
    }

    public List<CourseFavorites> getCourseFavoritesList(CourseFavorites courseFavorites) {
        return this.selectList("CourseFavoritesMapper.getCourseFavoritesList", courseFavorites);
    }

    /**
     * 通过用户Id和课程Id检查是否收藏过
     *
     * @param courseFavorites 查询条件
     * @return List<CourseFavorites>
     */
    public List<CourseFavorites> checkCourseFavoritesByUserIdAndCourseId(CourseFavorites courseFavorites) {
        return this.selectList("CourseFavoritesMapper.checkCourseFavoritesByUserIdAndCourseId", courseFavorites);
    }


    public List<FavouriteCourseDTO> getFavouriteCourseDTO(Long userId, PageEntity page) {
        return this.queryForListPage("CourseFavoritesMapper.getCourseFavoritesByUserId", userId, page);
    }

    public List<FavouriteCourseDTO> getMoreFavouriteCourse() {
        return this.selectList("CourseFavoritesMapper.getMoreFavouriteCourse", null);
    }

    @Override
    public void deleteCourseFavoritesByCourseIds(String ids) {
        // TODO Auto-generated method stub
        this.delete("CourseFavoritesMapper.cleanCourseFavoritess", ids.replaceAll(" ", "").split(","));
    }

    @Override
    public int getCourseIfFavorite(Map<String, Long> map) {
        return this.selectOne("CourseFavoritesMapper.getCourseIfFavorite", map);
    }

}
