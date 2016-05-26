package com.atdld.os.app.dao.edu;

import java.util.List;

import com.atdld.os.app.entity.app.AppUpdate;

public interface AppUpdateDao {
	
	public List<AppUpdate> queryAllList();
	
	public void updateApp(AppUpdate appUpdate);
	
	public AppUpdate queryAppUpdateById(long updateId);
}
