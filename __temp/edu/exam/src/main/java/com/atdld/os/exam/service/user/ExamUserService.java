package com.atdld.os.exam.service.user;




import java.util.Map;

import com.atdld.os.exam.entity.customer.ExamUserExpandDto;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.common.CommonServiceImpl
 * @description 博客serviceImpl
 * @Create Date : 2013-12-30 上午10:27:07
 */

public interface ExamUserService{

  
    /**
     * 用户详细信息
     * @param cusId
     * @return
     */
    public ExamUserExpandDto getUserExpandByCusId(Long cusId);
    /**
     * 批量用户详细信息
     * @param cusId
     * @return
     */
    public Map<String, ExamUserExpandDto> getUserExpandsByCusId(String cusIds);
	
}