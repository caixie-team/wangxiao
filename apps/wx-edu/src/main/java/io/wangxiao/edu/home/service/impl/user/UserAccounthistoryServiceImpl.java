package io.wangxiao.edu.home.service.impl.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.user.UserAccounthistoryDao;
import io.wangxiao.edu.home.entity.user.QueryUserAccounthistory;
import io.wangxiao.edu.home.entity.user.UserAccounthistory;
import io.wangxiao.edu.home.service.user.UserAccounthistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserAccounthistory管理接口
 */
@Service("userAccounthistoryService")
public class UserAccounthistoryServiceImpl implements UserAccounthistoryService {

    @Autowired
    private UserAccounthistoryDao userAccounthistoryDao;

    /**
     * 添加UserAccounthistory
     *
     * @param userAccounthistory 要添加的UserAccounthistory
     * @return id
     */
    public java.lang.Long addUserAccounthistory(UserAccounthistory userAccounthistory) {
        return userAccounthistoryDao.addUserAccounthistory(userAccounthistory);
    }

    /**
     * 根据id获取单个UserAccounthistory对象
     *
     * @param id 要查询的id
     * @return UserAccounthistory
     */
    public UserAccounthistory getUserAccounthistoryById(Long id) {
        return userAccounthistoryDao.getUserAccounthistoryById(id);
    }

    /**
     * 根据条件获取UserAccounthistory列表
     *
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory) {
        return userAccounthistoryDao.getUserAccounthistoryList(userAccounthistory);
    }

    /**
     * 账户历史 不隐藏后台操作 后台查看
     * 根据条件获取UserAccounthistory分页列表
     *
     * @param userAccounthistory, 查询条件
     * @return List<UserAccounthistory>
     */
    @Override
    public List<UserAccounthistory> getUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
        return userAccounthistoryDao.getUserAccounthistoryListByCondition(queryUserAccounthistory, page);
    }

    /**
     * 账户历史 隐藏后台操作 前台个人中心查看
     * 根据条件获取UserAccounthistory分页列表
     *
     * @param userAccounthistory, 查询条件
     * @return List<UserAccounthistory>
     */
    @Override
    public List<UserAccounthistory> getWebUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
        return userAccounthistoryDao.getWebUserAccounthistoryListByCondition(queryUserAccounthistory, page);
    }
}
