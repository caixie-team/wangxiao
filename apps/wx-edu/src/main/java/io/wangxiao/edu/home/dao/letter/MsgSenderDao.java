package io.wangxiao.edu.home.dao.letter;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.letter.MsgSender;

import java.util.Date;
import java.util.List;

/**
 * @description 站内信发件箱的Dao
 */
public interface MsgSenderDao {
    /**
     * 添加站内信发件箱
     *
     * @param msgSender 站内信实体
     * @throws Exception
     */
    Long addMsgSender(MsgSender msgSender) throws Exception;

    /**
     * 查询站内信发件箱
     *
     * @param MsgSender 站内信发件箱实体
     * @param page      分页参数
     * @return List<QueryLetter> 站内信的list
     * @throws Exception
     */
    List<MsgSender> queryMsgSenderByOutbox(MsgSender msgSender, PageEntity page) throws Exception;

    /**
     * 删除发件箱
     *
     * @param msgReceive 站内信实体 通过站内信的id
     * @throws Exception
     */
    Long delLetterOutbox(MsgSender msgSender) throws Exception;

    /**
     * 删除站内信过期消息
     */
    Long delMsgSenderPast(Date time) throws Exception;

    /**
     * 清空站内信发件箱
     */
    Long delAllOutbox(Long cusId) throws Exception;

    /**
     * 删除传入的ids
     */
    Long delMsgSenderByids(String ids) throws Exception;
}
