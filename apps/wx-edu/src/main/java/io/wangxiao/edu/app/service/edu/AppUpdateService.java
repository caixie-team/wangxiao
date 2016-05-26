package io.wangxiao.edu.app.service.edu;

import io.wangxiao.edu.app.entity.app.AppUpdate;

import java.util.List;

public interface AppUpdateService {
	
	List<AppUpdate> queryAllList();
	
	void updateApp(AppUpdate appUpdate);
	
	AppUpdate queryAppUpdateById(long updateId);
}
