package com.atdld.os.app.service.edu;

import java.util.List;

import com.atdld.os.app.entity.app.AppUpdate;

public interface AppUpdateService {
	
	public List<AppUpdate> queryAllList();
	
	public void updateApp(AppUpdate appUpdate);
	
	public AppUpdate queryAppUpdateById(long updateId);
}
