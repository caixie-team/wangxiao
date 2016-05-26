package com.atdld.os.edu.service.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.entity.user.UserArea;
import com.atdld.os.edu.dao.user.UserAreaDao;
import com.atdld.os.edu.service.user.UserAreaService;
/**
 * UserArea管理接口
 * User:
 * Date: 2014-05-27
 */
@Service("userAreaService")
public class UserAreaServiceImpl implements UserAreaService{

 	@Autowired
    private UserAreaDao userAreaDao;
    
    /**
     * 添加UserArea
     * @param userArea 要添加的UserArea
     * @return id
     */
    public java.lang.Long addUserArea(UserArea userArea){
    	return userAreaDao.addUserArea(userArea);
    }

    /**
     * 根据id删除一个UserArea
     * @param id 要删除的id
     */
    public void deleteUserAreaById(Long id){
    	 userAreaDao.deleteUserAreaById(id);
    }

    /**
     * 修改UserArea
     * @param userArea 要修改的UserArea
     */
    public void updateUserArea(UserArea userArea){
     	userAreaDao.updateUserArea(userArea);
    }

    /**
     * 根据id获取单个UserArea对象
     * @param id 要查询的id
     * @return UserArea
     */
    public UserArea getUserAreaById(Long id){
    	return userAreaDao.getUserAreaById( id);
    }

    /**
     * 根据条件获取UserArea列表
     * @param userArea 查询条件
     * @return List<UserArea>
     */
    public List<UserArea> getUserAreaList(UserArea userArea){
    	return userAreaDao.getUserAreaList(userArea);
    }
    /**
     * 根据条件获取UserArea的map集合
     */
    public Map<Long,UserArea> getMapUserAreaList(UserArea userArea){
    	//把查询出的userArea转换成map
    	Map<Long,UserArea> map = new HashMap<Long,UserArea>();
    	List<UserArea> userAreaList = getUserAreaList(userArea);
    	//判断不为空
    	if(ObjectUtils.isNotNull(userAreaList)){
    		for(UserArea area:userAreaList){
        		map.put(area.getId(), area);
        	}
    	}
    	return map;
    }
}