package io.wangxiao.edu.app.dao.edu;

import io.wangxiao.edu.app.entity.app.AppUpdate;

import java.util.List;

public interface AppUpdateDao {
	
	List<AppUpdate> queryAllList();
	
	void updateApp(AppUpdate appUpdate);
	
	AppUpdate queryAppUpdateById(long updateId);
}
