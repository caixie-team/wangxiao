package com.atdld.os.edu.dao.impl.user;

import java.util.List;
import java.util.Map;

import com.atdld.os.edu.entity.user.UserArea;
import com.atdld.os.edu.dao.user.UserAreaDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * UserArea
 * User:
 * Date: 2014-05-27
 */
 @Repository("userAreaDao")
public class UserAreaDaoImpl extends GenericDaoImpl implements UserAreaDao{

    public java.lang.Long addUserArea(UserArea userArea) {
        return this.insert("UserAreaMapper.createUserArea",userArea);
    }

    public void deleteUserAreaById(Long id){
        this.delete("UserAreaMapper.deleteUserAreaById",id);
    }

    public void updateUserArea(UserArea userArea) {
        this.update("UserAreaMapper.updateUserArea",userArea);
    }

    public UserArea getUserAreaById(Long id) {
        return this.selectOne("UserAreaMapper.getUserAreaById",id);
    }

    public List<UserArea> getUserAreaList(UserArea userArea) {
    	return this.selectList("UserAreaMapper.getUserAreaList",userArea);
    }
}
