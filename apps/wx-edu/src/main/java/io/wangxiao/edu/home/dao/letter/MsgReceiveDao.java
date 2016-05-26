package io.wangxiao.edu.home.dao.letter;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.letter.MsgReceive;
import io.wangxiao.edu.home.entity.letter.QueryMsgReceive;

import java.util.Date;
import java.util.List;

/**
 * @description 站内信Dao的接口
 */

public interface MsgReceiveDao {
    /**
     * 添加站内信
     *
     * @param letter 站内信实体
     */
    Long addMsgReceive(MsgReceive msgReceive);

    /**
     * 通过站内信的id 添加站内信
     *
     * @param msgReceiveList 站内信实体
     */
    MsgReceive queryMsgReceiveListById(int id);

    /**
     * 查询站内信收件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信收件箱List
     * @throws Exception
     */
    List<QueryMsgReceive> queryMsgReceiveByInbox(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 传来的用户id 给我发送的站内信的历史记录
     *
     * @param msgReceive 站内信实体 传入receivingCusId
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信的list
     * @throws Exception
     */
    List<QueryMsgReceive> queryMsgReceiveHistory(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 查询站内信发件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 站内信发件箱List
     * @throws Exception
     */
    List<QueryMsgReceive> queryMsgReceiveByOutbox(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 删除站内信
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    Long delMsgReceive(MsgReceive msgReceive) throws Exception;

    /**
     * 删除站内信过期消息
     */
    Long delMsgReceivePast(Date time) throws Exception;

    /**
     * 删除收件箱
     *
     * @param msgReceive 站内信实体 通过站内信的id
     * @throws Exception
     */
    Long delMsgReceiveInbox(MsgReceive msgReceive) throws Exception;

    /**
     * 更新收件箱所有信为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    void updateAllReadMsgReceiveInbox(MsgReceive msgReceive) throws Exception;

    /**
     * 更新某种类型的站内信状态为已读
     *
     * @param msgReceive 传入type 传入type和站内信收信人id
     * @throws Exception
     */
    void updateAllMsgReceiveReadByType(MsgReceive msgReceive) throws Exception;

    /**
     * 更新发件箱所有信为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    void updateAllReadMsgReceiveOutbox(MsgReceive msgReceive) throws Exception;

    /**
     * 通过站内信的id更新为已读
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    void updateReadMsgReceiveById(MsgReceive msgReceive) throws Exception;

    /**
     * 查询系统消息
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QueryMsgReceive> 系统消息 list
     * @throws Exception
     */
    List<QueryMsgReceive> querysystemInform(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 通过站内信的id更新status
     *
     * @param msgReceive 站内信实体
     * @throws Exception
     */
    void updateStatusReadLetterById(MsgReceive msgReceive) throws Exception;

    /**
     * 根据cusId和receivingCusId 更新
     *
     * @param status       要更新的状态
     * @param msgReceiveId 站内信id
     * @throws Exception
     */
    void updateStatusReadLetterByCusIdAndReceivingCusId(int status, MsgReceive msgReceive) throws Exception;

    /**
     * 批量添加消息
     *
     * @param msgReceiveList 消息的list
     */
    Long addMsgReceiveBatch(List<MsgReceive> msgReceiveList);

    /**
     * 查询该用户未读消息数量
     *
     * @param content 要发送的内容
     * @return 返回该用户四种类型每个的未读消息的数量和总的未读数量
     * @throws Exception
     */
    QueryMsgReceive queryUnReadMsgReceiveNumByCusId(Long cusId) throws Exception;

    /**
     * 清空站内信收件箱
     */
    Long delAllOutbox(Long cusId) throws Exception;

    /**
     * 清空用户系统消息
     */
    Long delAllMsgSys(Long cusId) throws Exception;

    /**
     * 删除传入的ids
     */
    Long delMsgReceiveByids(String ids) throws Exception;
}
