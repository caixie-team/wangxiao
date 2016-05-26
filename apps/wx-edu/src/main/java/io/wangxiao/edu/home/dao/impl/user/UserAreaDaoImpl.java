package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.user.UserAreaDao;
import io.wangxiao.edu.home.entity.user.UserArea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userAreaDao")
public class UserAreaDaoImpl extends GenericDaoImpl implements UserAreaDao {

    public java.lang.Long addUserArea(UserArea userArea) {
        return this.insert("UserAreaMapper.createUserArea", userArea);
    }

    public void deleteUserAreaById(Long id) {
        this.delete("UserAreaMapper.deleteUserAreaById", id);
    }

    public void updateUserArea(UserArea userArea) {
        this.update("UserAreaMapper.updateUserArea", userArea);
    }

    public UserArea getUserAreaById(Long id) {
        return this.selectOne("UserAreaMapper.getUserAreaById", id);
    }

    public List<UserArea> getUserAreaList(UserArea userArea) {
        return this.selectList("UserAreaMapper.getUserAreaList", userArea);
    }
}
