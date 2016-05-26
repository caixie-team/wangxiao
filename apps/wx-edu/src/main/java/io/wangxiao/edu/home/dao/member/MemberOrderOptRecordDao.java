package io.wangxiao.edu.home.dao.member;

import io.wangxiao.edu.home.entity.member.MemberOrderOptRecord;

public interface MemberOrderOptRecordDao {
    /**
     * 添加会员操作记录
     *
     * @param memberOrderOptRecord
     * @return
     */
    Long addMemberOrderOptRecord(MemberOrderOptRecord memberOrderOptRecord);
}
