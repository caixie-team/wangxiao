package io.wangxiao.edu.home.service.member;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.member.MemberOrder;
import io.wangxiao.edu.home.entity.member.MemberRecord;
import io.wangxiao.edu.home.entity.member.MemberRecordDTO;
import io.wangxiao.edu.home.entity.user.User;

import java.util.List;

/**
 * MemberRecord管理接口
 */
public interface MemberRecordService {


    /**
     * 添加MemberRecord
     *
     * @param memberRecord 要添加的MemberRecord
     * @return id
     */
    void addMemberRecord(MemberOrder memberOrder);

    /**
     * 修改MemberRecord
     *
     * @param memberRecord 要修改的MemberRecord
     */
    void updateMemberRecord(MemberRecord memberRecord);

    /**
     * 根据id获取单个MemberRecord对象
     *
     * @param id 要查询的id
     * @return MemberRecord
     */
    MemberRecord getMemberRecordById(Long id);

    /**
     * 根据条件获取MemberRecord列表
     *
     * @param memberRecord 查询条件
     * @return List<MemberRecord>
     */
    List<MemberRecordDTO> getMemberRecordPage(MemberRecordDTO memberRecordDTO, PageEntity page);

    /**
     * 判断用户会员课程权限
     *
     * @param type
     * @return
     */
    boolean checkUserMember(Long userId, Long courseId);

    /**
     * 查询用户的全部会员开通记录
     *
     * @param userId
     * @return
     */
    List<MemberRecordDTO> getMemberRecordByUser(Long userId);

    /**
     * 获得记录详情
     *
     * @param id
     * @return
     */
    MemberRecordDTO getMemberRecordInfo(Long id);

    /**
     * 延期操作
     *
     * @param memberRecord
     * @param User
     */
    void updateMemberRecordEndDate(MemberRecord memberRecord, User user);

    /**
     * 会员关闭 更新状态
     *
     * @param memberRecord
     */
    void updateMemberStatus(MemberRecord memberRecord);
}