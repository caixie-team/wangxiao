package io.wangxiao.edu.home.service.impl.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.home.dao.user.UserLoginLogDao;
import io.wangxiao.edu.home.entity.user.UserLoginLog;
import io.wangxiao.edu.home.service.user.UserLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userLoginLogService")
public class UserLoginLogServiceImpl implements UserLoginLogService {

    @Autowired
    private UserLoginLogDao userLoginLogDao;

    /**
     * 添加UserLoginLog
     *
     * @param userLoginLog 要添加的UserLoginLog
     * @return id
     */
    public java.lang.Long addUserLoginLog(UserLoginLog userLoginLog) {
        return userLoginLogDao.addUserLoginLog(userLoginLog);
    }

    /**
     * 修改UserLoginLog
     *
     * @param userLoginLog 要修改的UserLoginLog
     */
    public void updateUserLoginLog(UserLoginLog userLoginLog) {
        String address = WebUtils.getAddressByIP(userLoginLog.getLoginIp());
        if (address.equals("")) {
            address = "未知";
        }
        userLoginLog.setAddress(address);
        userLoginLogDao.updateUserLoginLog(userLoginLog);
    }

    /**
     * 分页查询用户的登录记录 Long userId,PageEntity page
     *
     * @param id 要查询的id
     * @return UserLoginLog
     */
    public UserLoginLog getUserLoginLogById(Long id) {
        return userLoginLogDao.getUserLoginLogById(id);
    }

    /**
     * address is null limit 10000
     *
     * @param userLoginLog 查询条件
     * @return List<UserLoginLog>
     */
    public List<UserLoginLog> getUserLoginLogList() {
        return userLoginLogDao.getUserLoginLogList();
    }

    /**
     * 查询登陆log前台
     *
     * @param userLoginLog
     * @param page
     * @return
     */
    public List<UserLoginLog> getUserLoginLogListPage(UserLoginLog userLoginLog, PageEntity page) {
        return userLoginLogDao.getUserLoginLogListPage(userLoginLog, page);
    }
}