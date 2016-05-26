package com.atdld.os.app.dao.website;

import com.atdld.os.app.entity.website.AppIosCheck;

/**
 * AppIosCheck 管理类
 * 
 * @author WangKaiping
 * 
 * @date2015-06-15 15:20:12
 */
public interface AppIosCheckDao {

	/**
	 * 添加
	 * @param appIosCheck
	 */
	public void saveIOSReceipt(AppIosCheck appIosCheck);
	
	/**
	 * 根据密文查询单个AppIosCheck对象
	 * 
	 * @param md5_receipt 
	 * 					查询条件
	 * @return
	 */
	public AppIosCheck isExistsIOSReceipt(String md5Receipt);
	
}
