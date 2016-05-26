package com.atdld.os.edu.dao.member;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.member.MemberRecord;
import com.atdld.os.edu.entity.member.MemberRecordDTO;

/**
 * MemberRecord管理接口 User:  Date: 2014-09-26
 */
public interface MemberRecordDao {

	/**
	 * 添加MemberRecord
	 * 
	 * @param memberRecord
	 *            要添加的MemberRecord
	 * @return id
	 */
	public java.lang.Long addMemberRecord(MemberRecord memberRecord);

	/**
	 * 修改MemberRecord
	 * 
	 * @param memberRecord
	 *            要修改的MemberRecord
	 */
	public void updateMemberRecord(MemberRecord memberRecord);

	/**
	 * 根据id获取单个MemberRecord对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return MemberRecord
	 */
	public MemberRecord getMemberRecordById(Long id);

	/**
	 * 根据条件获取MemberRecord列表
	 * 
	 * @param memberRecord
	 *            查询条件
	 * @return List<MemberRecord>
	 */
	public List<MemberRecordDTO> getMemberRecordPage(MemberRecordDTO memberRecordDTO, PageEntity page);

	/**
	 * 根据用户id、会员类型查询MemberRecord对象
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public MemberRecord getMemberRecordByCondition(Long userId, Long type);

	/**
	 * 查询用户的全部会员开通记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<MemberRecordDTO> getMemberRecordByUser(Long userId);

	/**
	 * 获得记录详情
	 * 
	 * @param id
	 * @return
	 */
	public MemberRecordDTO getMemberRecordInfo(Long id);

	/**
	 * 会员关闭 更新状态
	 * 
	 * @param memberRecord
	 */
	public void updateMemberStatus(MemberRecord memberRecord);
}