package com.atdld.os.user.dao.impl;

import com.atdld.os.dao.impl.common.GenericDaoImpl;
import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.dao.UserMobileMsgDao;
import com.atdld.os.user.entity.UserMobileMsg;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 * 
 * @ClassName  com.atdld.os.edu.dao.impl.user.UserMobileMsgDaoImpl
 * @description
 * @author :
 * @Create Date : 2014年9月21日 下午9:38:15
 */
@Repository("userMobileDao")
public class UserMobileMsgDaoImpl extends GenericDaoImpl implements UserMobileMsgDao {

    /**
     * 添加发送用户短信记录
     * 
     * @param userMobileMsg
     * @return
     */
    public Long addUserMobileMsg(List<UserMobileMsg> userMobileMsgList) {
        return this.insert("UserMobileMsgMapper.addUserMobileMsg", userMobileMsgList);
    }

    /**
     * 查询记录
     * 
     * @param userMobileMsg
     * @param page
     * @return
     */
    public List<UserMobileMsg> queryUserMobileMsgList(UserMobileMsg userMobileMsg,
            PageEntity page) {
        return this.queryForListPage("UserMobileMsgMapper.queryUserMobileMsgList", userMobileMsg, page);
    }

    /**
     * 获得单个记录
     * 
     * @param id
     * @return
     */
    public UserMobileMsg queryUserMobileMsgById(Long id) {
        return this.selectOne("UserMobileMsgMapper.queryUserMobileMsgById", id);
    }
    
    /**
     * 查询和当前时间相等的短信记录 发送
     * @param userMobileMsg
     * @return
     */
    public List<UserMobileMsg> queryNowMobileMsgList(Date nowDate){
    	return this.selectList("UserMobileMsgMapper.queryNowMobileMsgList", nowDate);
    }

    /**
     * 修改短信发送状态
     */
    public void updateMsgStatus(Long id){
    	this.update("UserMobileMsgMapper.updateMsgStatus", id);
    }
    
    /**
     * 修改短信
     * @param userMobileMsg
     */
    public void updateUserMobileMsg(UserMobileMsg userMobileMsg) {
		this.update("UserMobileMsgMapper.updateUserMobileMsg", userMobileMsg);
	}
    
    /**
     * 删除短信
     */
    public void delUserMobileMsg(Long id){
    	this.delete("UserMobileMsgMapper.delUserMobileMsg", id);
    }
}
