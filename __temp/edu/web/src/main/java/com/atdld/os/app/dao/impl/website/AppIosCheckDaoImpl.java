package com.atdld.os.app.dao.impl.website;

import org.springframework.stereotype.Repository;

import com.atdld.os.app.dao.website.AppIosCheckDao;
import com.atdld.os.app.entity.website.AppIosCheck;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
/**
 * AppIosCheck 管理类
 * 
 * @author WangKaiping
 * 
 * @date2015-06-15 15:23:12
 */
@Repository("appIosCheckDao")
public class AppIosCheckDaoImpl extends GenericDaoImpl implements AppIosCheckDao {

	/**
	 * 添加AppIosCheck
	 */
	public void saveIOSReceipt(AppIosCheck appIosCheck) {
		this.insert("AppIosCheckMapper.createAppIosCheck", appIosCheck);
	}

	/**
	 * 根据密文查询单个AppIosCheck对象
	 * 
	 * @param md5_receipt 
	 * 					查询条件
	 * @return
	 */
	public AppIosCheck isExistsIOSReceipt(String md5Receipt) {
		return this.selectOne("AppIosCheckMapper.getAppCourseByMd5Receipt", md5Receipt);
	}

}
