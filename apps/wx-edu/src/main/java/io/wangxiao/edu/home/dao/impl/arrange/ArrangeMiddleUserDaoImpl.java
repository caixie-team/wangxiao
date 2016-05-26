package io.wangxiao.edu.home.dao.impl.arrange;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.arrange.ArrangeMiddleUserDao;
import io.wangxiao.edu.home.entity.arrange.ArrangeMiddleUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("arrangeMiddleUserDao")
public class ArrangeMiddleUserDaoImpl extends GenericDaoImpl implements ArrangeMiddleUserDao {

    public Long addArrangeMiddleUser(ArrangeMiddleUser arrangeMiddleUser) {
        return this.insert("ArrangeMiddleUserMapper.createArrangeMiddleUser", arrangeMiddleUser);
    }

    /**
     * 批量添加员工
     */
    public void batchAddArrangeMiddleUser(List<ArrangeMiddleUser> arrangeMiddleUsers) {
        this.insert("ArrangeMiddleUserMapper.batchAddArrangeMiddleUser", arrangeMiddleUsers);
    }

    public void deleteArrangeMiddleUserById(Long id) {
        this.delete("ArrangeMiddleUserMapper.deleteArrangeMiddleUserById", id);
    }

    public void updateArrangeMiddleUser(ArrangeMiddleUser arrangeMiddleUser) {
        this.update("ArrangeMiddleUserMapper.updateArrangeMiddleUser", arrangeMiddleUser);
    }

    public ArrangeMiddleUser getArrangeMiddleUserById(Long id) {
        return this.selectOne("ArrangeMiddleUserMapper.getArrangeMiddleUserById", id);
    }

    public List<ArrangeMiddleUser> getArrangeMiddleUserList(ArrangeMiddleUser arrangeMiddleUser) {
        return this.selectList("ArrangeMiddleUserMapper.getArrangeMiddleUserList", arrangeMiddleUser);
    }

    /**
     * 任务下的员工
     */
    public List<ArrangeMiddleUser> getTaakUserById(ArrangeMiddleUser arrangeMiddleUser) {
        return this.selectList("ArrangeMiddleUserMapper.getTaakUserById", arrangeMiddleUser);
    }
}
