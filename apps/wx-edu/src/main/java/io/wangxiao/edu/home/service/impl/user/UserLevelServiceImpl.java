package io.wangxiao.edu.home.service.impl.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wangxiao.edu.home.entity.user.UserLevel;
import io.wangxiao.edu.home.dao.user.UserLevelDao;
import io.wangxiao.edu.home.service.user.UserLevelService;

@Service("userLevelService")
public class UserLevelServiceImpl implements UserLevelService {

	@Autowired
	private UserLevelDao userLevelDao;

	/**
	 * 添加UserLevel
	 * 
	 * @param userLevel
	 *            要添加的UserLevel
	 * @return id
	 */
	public java.lang.Long addUserLevel(UserLevel userLevel) {
		return userLevelDao.addUserLevel(userLevel);
	}

	/**
	 * 根据id删除一个UserLevel
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserLevelById(Long id) {
		userLevelDao.deleteUserLevelById(id);
	}

	/**
	 * 修改UserLevel
	 * 
	 * @param userLevel
	 *            要修改的UserLevel
	 */
	public void updateUserLevel(UserLevel userLevel) {
		userLevelDao.updateUserLevel(userLevel);
	}

	/**
	 * 根据id获取单个UserLevel对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserLevel
	 */
	public UserLevel getUserLevelById(Long id) {
		return userLevelDao.getUserLevelById(id);
	}

	/**
	 * 获取UserLevel列表
	 * 
	 * @return
	 */
	public List<UserLevel> getUserLevelList() {
		return userLevelDao.getUserLevelList();
	}
}