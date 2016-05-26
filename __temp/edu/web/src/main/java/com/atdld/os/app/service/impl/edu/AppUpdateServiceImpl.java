package com.atdld.os.app.service.impl.edu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.app.dao.edu.AppUpdateDao;
import com.atdld.os.app.entity.app.AppUpdate;
import com.atdld.os.app.service.edu.AppUpdateService;

@Service("appUpdateService")
public class AppUpdateServiceImpl implements AppUpdateService {

	@Autowired
	private AppUpdateDao appUpdateDao;
		
	@Override
	public List<AppUpdate> queryAllList() {
		return appUpdateDao.queryAllList();
	}

	@Override
	public void updateApp(AppUpdate appUpdate) {
		appUpdateDao.updateApp(appUpdate);
	}

	@Override
	public AppUpdate queryAppUpdateById(long updateId) {
		return appUpdateDao.queryAppUpdateById(updateId);
	}

}
