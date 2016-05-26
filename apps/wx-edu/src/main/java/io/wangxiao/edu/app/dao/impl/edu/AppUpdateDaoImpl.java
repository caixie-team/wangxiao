package io.wangxiao.edu.app.dao.impl.edu;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.app.dao.edu.AppUpdateDao;
import io.wangxiao.edu.app.entity.app.AppUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("appUpdateDao")
public class AppUpdateDaoImpl extends GenericDaoImpl implements AppUpdateDao {

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
