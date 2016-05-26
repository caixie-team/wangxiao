package io.wangxiao.edu.home.dao.arrange;

import io.wangxiao.edu.home.entity.arrange.ArrangeMiddleUser;

import java.util.List;

/**
 * ArrangeMiddleUser管理接口
 */
public interface ArrangeMiddleUserDao {

    /**
     * 添加ArrangeMiddleUser
     *
     * @param arrangeMiddleUser 要添加的ArrangeMiddleUser
     * @return id
     */
    java.lang.Long addArrangeMiddleUser(ArrangeMiddleUser arrangeMiddleUser);

    /**
     * 根据id删除一个ArrangeMiddleUser
     *
     * @param id 要删除的id
     */
    void deleteArrangeMiddleUserById(Long id);

    /**
     * 修改ArrangeMiddleUser
     *
     * @param arrangeMiddleUser 要修改的ArrangeMiddleUser
     */
    void updateArrangeMiddleUser(ArrangeMiddleUser arrangeMiddleUser);

    /**
     * 根据id获取单个ArrangeMiddleUser对象
     *
     * @param id 要查询的id
     * @return ArrangeMiddleUser
     */
    ArrangeMiddleUser getArrangeMiddleUserById(Long id);

    /**
     * 根据条件获取ArrangeMiddleUser列表
     *
     * @param arrangeMiddleUser 查询条件
     * @return List<ArrangeMiddleUser>
     */
    List<ArrangeMiddleUser> getArrangeMiddleUserList(ArrangeMiddleUser arrangeMiddleUser);

    /**
     * 批量添加员工
     */
    void batchAddArrangeMiddleUser(List<ArrangeMiddleUser> arrangeMiddleUsers);

    /**
     * 任务下的员工
     *
     * @param arrangeMiddleUser
     * @return
     */
    List<ArrangeMiddleUser> getTaakUserById(ArrangeMiddleUser arrangeMiddleUser);
}