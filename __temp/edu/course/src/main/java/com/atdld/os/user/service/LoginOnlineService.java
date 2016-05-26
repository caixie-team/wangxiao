package com.atdld.os.user.service;

import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.LoginOnline;

import java.util.List;


/**
 * LoginOnline管理接口
 * User:
 * Date: 2014-11-18
 */
public interface LoginOnlineService {

    /**
     * 添加LoginOnline
     *
     * @param loginOnline 要添加的LoginOnline
     * @return id
     */
    Long addLoginOnline(LoginOnline loginOnline);

    /**
     * 根据id删除一个LoginOnline
     *
     * @param id 要删除的id
     */
    void deleteLoginOnlineById(Long id);

    /**
     * 修改LoginOnline
     *
     * @param loginOnline 要修改的LoginOnline
     */
    void updateLoginOnline(LoginOnline loginOnline);

    /**
     * 根据id获取单个LoginOnline对象
     *
     * @param id 要查询的id
     * @return LoginOnline
     */
    LoginOnline getLoginOnlineById(Long id);

    /**
     * 根据条件获取LoginOnline列表
     *
     * @param loginOnline 查询条件
     * @return List<LoginOnline>
     */
    List<LoginOnline> getLoginOnlineList(LoginOnline loginOnline, PageEntity pageEntity);
}