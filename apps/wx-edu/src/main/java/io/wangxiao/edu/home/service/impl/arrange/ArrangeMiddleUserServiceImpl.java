package io.wangxiao.edu.home.service.impl.arrange;

import io.wangxiao.edu.home.dao.arrange.ArrangeMiddleUserDao;
import io.wangxiao.edu.home.entity.arrange.ArrangeMiddleUser;
import io.wangxiao.edu.home.service.arrange.ArrangeMiddleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("arrangeMiddleUserService")
public class ArrangeMiddleUserServiceImpl implements ArrangeMiddleUserService {

    @Autowired
    private ArrangeMiddleUserDao arrangeMiddleUserDao;

    /**
     * 添加ArrangeMiddleUser
     *
     * @param arrangeMiddleUser 要添加的ArrangeMiddleUser
     * @return id
     */
    public java.lang.Long addArrangeMiddleUser(ArrangeMiddleUser arrangeMiddleUser) {
        return arrangeMiddleUserDao.addArrangeMiddleUser(arrangeMiddleUser);
    }

    /**
     * 批量添加员工
     */
    public void batchAddArrangeMiddleUser(List<ArrangeMiddleUser> arrangeMiddleUsers) {
        this.arrangeMiddleUserDao.batchAddArrangeMiddleUser(arrangeMiddleUsers);
    }

    /**
     * 根据id删除一个ArrangeMiddleUser
     *
     * @param id 要删除的id
     */
    public void deleteArrangeMiddleUserById(Long id) {
        arrangeMiddleUserDao.deleteArrangeMiddleUserById(id);
    }

    /**
     * 修改ArrangeMiddleUser
     *
     * @param arrangeMiddleUser 要修改的ArrangeMiddleUser
     */
    public void updateArrangeMiddleUser(ArrangeMiddleUser arrangeMiddleUser) {
        arrangeMiddleUserDao.updateArrangeMiddleUser(arrangeMiddleUser);
    }

    /**
     * 根据id获取单个ArrangeMiddleUser对象
     *
     * @param id 要查询的id
     * @return ArrangeMiddleUser
     */
    public ArrangeMiddleUser getArrangeMiddleUserById(Long id) {
        return arrangeMiddleUserDao.getArrangeMiddleUserById(id);
    }

    /**
     * 根据条件获取ArrangeMiddleUser列表
     *
     * @param arrangeMiddleUser 查询条件
     * @return List<ArrangeMiddleUser>
     */
    public List<ArrangeMiddleUser> getArrangeMiddleUserList(ArrangeMiddleUser arrangeMiddleUser) {
        return arrangeMiddleUserDao.getArrangeMiddleUserList(arrangeMiddleUser);
    }

    /**
     * 任务下的员工
     */
    public List<ArrangeMiddleUser> getTaakUserById(ArrangeMiddleUser arrangeMiddleUser) {
        return this.arrangeMiddleUserDao.getTaakUserById(arrangeMiddleUser);
    }

}