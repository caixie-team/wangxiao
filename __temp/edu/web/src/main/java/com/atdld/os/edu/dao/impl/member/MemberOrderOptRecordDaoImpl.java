package com.atdld.os.edu.dao.impl.member;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.edu.dao.member.MemberOrderOptRecordDao;
import com.atdld.os.edu.entity.member.MemberOrderOptRecord;

/**
 * 
 * @ClassName  com.atdld.os.edu.dao.impl.member.MemberOrderOptRecordDaoImpl
 * @description
 * @author :
 * @Create Date : 2014年10月29日 下午4:46:36
 */
@Repository("memberOrderOptRecordDao")
public class MemberOrderOptRecordDaoImpl extends GenericDaoImpl implements MemberOrderOptRecordDao{

	/**
	 * 添加会员操作记录
	 * 
	 * @param memberOrderOptRecord
	 * @return
	 */
	public Long addMemberOrderOptRecord(MemberOrderOptRecord memberOrderOptRecord) {
		return this.insert("MemberOrderOptRecordMapper.addMemberOrderOptRecord", memberOrderOptRecord);
	}

}
