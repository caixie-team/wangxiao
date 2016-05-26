package io.wangxiao.edu.home.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.wangxiao.edu.home.entity.user.UserIntegralTemplate;
import io.wangxiao.edu.home.dao.user.UserIntegralTemplateDao;
import io.wangxiao.edu.home.service.user.UserIntegralTemplateService;

@Service("userIntegralTemplateService")
public class UserIntegralTemplateServiceImpl implements UserIntegralTemplateService {

	@Autowired
	private UserIntegralTemplateDao userIntegralTemplateDao;

	/**
	 * 添加UserIntegralTemplate
	 * 
	 * @param userIntegralTemplate
	 *            要添加的UserIntegralTemplate
	 * @return id
	 */
	public java.lang.Long addUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate) {
		return userIntegralTemplateDao.addUserIntegralTemplate(userIntegralTemplate);
	}

	/**
	 * 根据id删除一个UserIntegralTemplate
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralTemplateById(Long id) {
		userIntegralTemplateDao.deleteUserIntegralTemplateById(id);
	}

	/**
	 * 修改UserIntegralTemplate
	 * 
	 * @param userIntegralTemplate
	 *            要修改的UserIntegralTemplate
	 */
	public void updateUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate) {

		userIntegralTemplateDao.updateUserIntegralTemplate(userIntegralTemplate);
	}

	/**
	 * 根据id获取单个UserIntegralTemplate对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralTemplate
	 */
	public UserIntegralTemplate getUserIntegralTemplateById(Long id) {
		return userIntegralTemplateDao.getUserIntegralTemplateById(id);
	}

	/**
	 * 获取UserIntegralTemplate列表
	 * 
	 * @return
	 */
	public List<UserIntegralTemplate> getUserIntegralTemplateList() {
		return userIntegralTemplateDao.getUserIntegralTemplateList();
	}

	/**
	 * 更新状态 正常 停止
	 * 
	 * @param userIntegralTemplat
	 */
	public void updateUserIntegralTemplateStatus(UserIntegralTemplate userIntegralTemplate) {
		userIntegralTemplateDao.updateUserIntegralTemplateStatus(userIntegralTemplate);
	}
	/**
	 * 查询积分模板根据关键字
	 * 
	 * @param keyword
	 * @return
	 */
	public UserIntegralTemplate getUserIntegralTemplateByKeyword(String keyword) {
		return userIntegralTemplateDao.getUserIntegralTemplateByKeyword(keyword);
	}
}