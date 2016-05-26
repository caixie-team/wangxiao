package com.atdld.os.edu.dao.member;

import com.atdld.os.edu.entity.member.MemberOrderOptRecord;

/**
 * 
 * @ClassName com.atdld.os.edu.dao.member.MemberOrderOptRecordDao
 * @description
 * @author :
 * @Create Date : 2014年10月29日 下午4:44:58
 */
public interface MemberOrderOptRecordDao {
	/**
	 * 添加会员操作记录
	 * 
	 * @param memberOrderOptRecord
	 * @return
	 */
	public Long addMemberOrderOptRecord(MemberOrderOptRecord memberOrderOptRecord);
}
