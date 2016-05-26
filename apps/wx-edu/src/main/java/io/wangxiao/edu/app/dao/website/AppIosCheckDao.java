package io.wangxiao.edu.app.dao.website;

import io.wangxiao.edu.app.entity.website.AppIosCheck;

/**
 * AppIosCheck 管理类
 * 
 */
public interface AppIosCheckDao {

	/**
	 * 添加
	 * @param appIosCheck
	 */
	void saveIOSReceipt(AppIosCheck appIosCheck);
	
	/**
	 * 根据密文查询单个AppIosCheck对象
	 * 
	 * @param md5_receipt 
	 * 					查询条件
	 * @return
	 */
	AppIosCheck isExistsIOSReceipt(String md5Receipt);
	
}
