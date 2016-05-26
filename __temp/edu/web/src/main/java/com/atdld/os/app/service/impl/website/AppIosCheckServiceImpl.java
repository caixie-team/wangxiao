package com.atdld.os.app.service.impl.website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.app.dao.website.AppIosCheckDao;
import com.atdld.os.app.entity.website.AppIosCheck;
import com.atdld.os.app.service.website.AppIosCheckService;
import com.atdld.os.core.util.ObjectUtils;
/**
 * AppIosCheck 管理类
 * 
 * @author WangKaiping
 * 
 * @date2015-06-15 15:26:12
 */
@Service("appIosCheckService")
public class AppIosCheckServiceImpl implements AppIosCheckService {

	@Autowired
	private AppIosCheckDao appIosCheckDao;
	
	/**
	 * 添加AppIosCheck
	 */
	public void saveIOSReceipt(AppIosCheck appIosCheck) {
		appIosCheckDao.saveIOSReceipt(appIosCheck);
	}

	/**
	 * 根据密文查询单个AppIosCheck对象
	 * 
	 * @param md5_receipt 
	 * 					查询条件
	 * @return
	 */
	public boolean isExistsIOSReceipt(String md5Receipt) {
		AppIosCheck appIosCheck = appIosCheckDao.isExistsIOSReceipt(md5Receipt);
		if (ObjectUtils.isNotNull(appIosCheck)) {
			return true;
		}
		return false;
	}

}
