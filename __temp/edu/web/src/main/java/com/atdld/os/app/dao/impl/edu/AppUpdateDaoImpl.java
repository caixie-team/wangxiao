package com.atdld.os.app.dao.impl.edu;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.app.dao.edu.AppUpdateDao;
import com.atdld.os.app.entity.app.AppUpdate;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

@Repository("appUpdateDao")
public class AppUpdateDaoImpl extends GenericDaoImpl implements AppUpdateDao{

	@Override
	public List<AppUpdate> queryAllList() {
		return this.selectList("AppUpdateMapper.queryAllList", null);
	}

	@Override
	public void updateApp(AppUpdate appUpdate) {
		this.update("AppUpdateMapper.updateApp", appUpdate);
	}

	@Override
	public AppUpdate queryAppUpdateById(long updateId) {
		return this.selectOne("AppUpdateMapper.queryAppUpdateById", updateId);
	}

}
