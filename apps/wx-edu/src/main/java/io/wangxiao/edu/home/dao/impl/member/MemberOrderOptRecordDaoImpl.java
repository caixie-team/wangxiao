package io.wangxiao.edu.home.dao.impl.member;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.member.MemberOrderOptRecordDao;
import io.wangxiao.edu.home.entity.member.MemberOrderOptRecord;
import org.springframework.stereotype.Repository;

@Repository("memberOrderOptRecordDao")
public class MemberOrderOptRecordDaoImpl extends GenericDaoImpl implements MemberOrderOptRecordDao {

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
