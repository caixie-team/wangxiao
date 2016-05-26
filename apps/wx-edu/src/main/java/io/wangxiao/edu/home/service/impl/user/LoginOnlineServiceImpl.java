package io.wangxiao.edu.home.service.impl.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.user.LoginOnlineDao;
import io.wangxiao.edu.home.entity.user.LoginOnline;
import io.wangxiao.edu.home.service.user.LoginOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * LoginOnline管理接口
 */
@Service("loginOnlineService")
public class LoginOnlineServiceImpl implements LoginOnlineService {

    @Autowired
    private LoginOnlineDao loginOnlineDao;

    /**
     * 添加LoginOnline
     *
     * @param loginOnline 要添加的LoginOnline
     * @return id
     */
    public java.lang.Long addLoginOnline(LoginOnline loginOnline) {
        Long l = loginOnlineDao.updateLoginOnline(loginOnline);
        if (ObjectUtils.isNull(l)) {
            return loginOnlineDao.addLoginOnline(loginOnline);
        } else {
            return 0L;
        }
    }

    /**
     * 根据id删除一个LoginOnline
     *
     * @param id 要删除的id
     */
    public void deleteLoginOnlineById(Long id) {
        loginOnlineDao.deleteLoginOnlineById(id);
    }

    /**
     * 修改LoginOnline
     *
     * @param loginOnline 要修改的LoginOnline
     */
    public void updateLoginOnline(LoginOnline loginOnline) {
        loginOnlineDao.updateLoginOnline(loginOnline);
    }

    /**
     * 根据id获取单个LoginOnline对象
     *
     * @param id 要查询的id
     * @return LoginOnline
     */
    public LoginOnline getLoginOnlineById(Long id) {
        return loginOnlineDao.getLoginOnlineById(id);
    }

    /**
     * 根据条件获取LoginOnline列表
     *
     * @param loginOnline 查询条件
     * @return List<LoginOnline>
     */
    public List<LoginOnline> getLoginOnlineList(LoginOnline loginOnline, PageEntity pageEntity) {
        return loginOnlineDao.getLoginOnlineList(loginOnline, pageEntity);
    }
}