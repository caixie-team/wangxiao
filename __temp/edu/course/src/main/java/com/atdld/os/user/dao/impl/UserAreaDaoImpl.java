package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.user.dao.UserAreaDao;
import com.atdld.os.user.entity.UserArea;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * UserArea
 * User:
 * Date: 2014-05-27
 */
@Repository("userAreaDao")
public class UserAreaDaoImpl extends GenericDaoImpl implements UserAreaDao {

    public Long addUserArea(UserArea userArea) {
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
