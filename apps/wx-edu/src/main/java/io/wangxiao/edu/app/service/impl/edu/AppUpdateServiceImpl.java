package io.wangxiao.edu.app.service.impl.edu;

import io.wangxiao.edu.app.dao.edu.AppUpdateDao;
import io.wangxiao.edu.app.entity.app.AppUpdate;
import io.wangxiao.edu.app.service.edu.AppUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
