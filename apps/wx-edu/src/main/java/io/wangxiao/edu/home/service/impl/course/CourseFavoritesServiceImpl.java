package io.wangxiao.edu.home.service.impl.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.constants.web.WebContants;
import io.wangxiao.edu.home.dao.course.CourseFavoritesDao;
import io.wangxiao.edu.home.entity.course.CourseFavorites;
import io.wangxiao.edu.home.entity.course.FavouriteCourseDTO;
import io.wangxiao.edu.home.service.course.CourseFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseFavoritesService")
public class CourseFavoritesServiceImpl implements CourseFavoritesService {

	@Autowired
	private CourseFavoritesDao courseFavoritesDao;

	/**
	 * 添加CourseFavorites
	 * 
	 * @param courseFavorites
	 *            要添加的CourseFavorites
	 * @return id
	 */
	public String addCourseFavorites(CourseFavorites courseFavorites) {
		// 检查是否收藏过
		List<CourseFavorites> courseFavoritesList = courseFavoritesDao
				.checkCourseFavoritesByUserIdAndCourseId(courseFavorites);
		if (ObjectUtils.isNotNull(courseFavoritesList)) {
			// 收藏过
			return WebContants.OWNED;
		}
		courseFavoritesDao.addCourseFavorites(courseFavorites);
		return WebContants.SUCCESS;
	}

	/**
	 * 根据id删除一个CourseFavorites
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCourseFavoritesById(String ids) {
		
		courseFavoritesDao.deleteCourseFavoritesById(ids);
	}
	/**
	 * 清空收藏
	 * @param userId
	 */
	public void cleanCourseFavorites(Long userId){
		courseFavoritesDao.cleanCourseFavorites(userId);
	}

	/**
	 * 修改CourseFavorites
	 * 
	 * @param courseFavorites
	 *            要修改的CourseFavorites
	 */
	public void updateCourseFavorites(CourseFavorites courseFavorites) {
		courseFavoritesDao.updateCourseFavorites(courseFavorites);
	}

	/**
	 * 根据id获取单个CourseFavorites对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CourseFavorites
	 */
	public CourseFavorites getCourseFavoritesById(Long id) {
		return courseFavoritesDao.getCourseFavoritesById(id);
	}

	/**
	 * 根据条件获取CourseFavorites列表
	 * 
	 * @param courseFavorites
	 *            查询条件
	 * @return List<CourseFavorites>
	 */
	public List<CourseFavorites> getCourseFavoritesList(
			CourseFavorites courseFavorites) {
		return courseFavoritesDao.getCourseFavoritesList(courseFavorites);
	}

	/**
	 * 通过用户Id和课程Id检查是否收藏过
	 * 
	 * @param courseFavorites
	 *            查询条件
	 * @return List<CourseFavorites>
	 */
	public List<CourseFavorites> checkCourseFavoritesByUserIdAndCourseId(
			CourseFavorites courseFavorites) {
		return courseFavoritesDao
				.checkCourseFavoritesByUserIdAndCourseId(courseFavorites);
	}

	@Override
	public List<FavouriteCourseDTO> getFavouriteCourseDTO(Long userId,
			PageEntity page) {
		return courseFavoritesDao.getFavouriteCourseDTO(userId, page);
	}

	@Override
	public List<FavouriteCourseDTO> getMoreFavouriteCourse() {
		// TODO Auto-generated method stub
		return courseFavoritesDao.getMoreFavouriteCourse();
	}
	 /**
     * 判断是否收藏课程
     * @param id
     * @param userId
     * @return
     */
	public Boolean getCourseIfFavorite(Long id, Long userId){
		Map<String,Long> map = new HashMap<String, Long>();
		map.put("courseId", id);
		map.put("userId", userId);
		int count = courseFavoritesDao.getCourseIfFavorite(map);
		if(count>0){
			return true;
		}
		return false;
	}
	

}