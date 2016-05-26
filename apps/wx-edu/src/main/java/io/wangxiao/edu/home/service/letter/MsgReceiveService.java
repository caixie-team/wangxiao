package io.wangxiao.edu.home.service.letter;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.letter.MsgReceive;
import io.wangxiao.edu.home.entity.letter.QueryMsgReceive;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description 站内信的service
 */
public interface MsgReceiveService {

    /**
     * 查询站内信收件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QuerymsgReceive> 站内信的list
     * @throws Exception
     */
    List<QueryMsgReceive> queryMsgReceiveByInbox(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 传来的receivingCusId的用户id 给我发送的站内信的历史记录
     *
     * @param msgReceive 站内信实体 传入receivingCusId
     * @param page       分页参数
     * @return List<QuerymsgReceive> 站内信的list
     * @throws Exception
     */
    List<QueryMsgReceive> queryMsgReceiveHistory(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 查询站内信发件箱
     *
     * @param msgReceive 站内信实体
     * @param page       分页参数
     * @return List<QuerymsgReceive> 站内信的list
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
     * 更新发件箱所有信为已读
     *
     * @param letter 站内信实体
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
     * @return List<QuerymsgReceive> 站内信的list
     * @throws Exception
     */
    List<QueryMsgReceive> querysystemInform(MsgReceive msgReceive, PageEntity page) throws Exception;

    /**
     * 通过站内信的id更新status
     *
     * @param status       msgReceive 的状态id
     * @param msgReceiveId msgReceive的id
     * @throws Exception
     */
    void updateStatusReadMsgReceiveById(int status, Long msgReceiveId, Long receivingCusId) throws Exception;

    /**
     * 根据cusId和receivingCusId 更新站内信状态
     *
     * @param status 要更新的状态
     * @param letter 传入cusId和receivingCusId
     * @throws Exception
     */
    void updateStatusReadMsgReceiveByCusIdAndReceivingCusId(int status, MsgReceive msgReceive) throws Exception;


    /**
     * 发送消息
     *
     * @param content
     * @param cusId
     * @param type
     * @return
     * @throws Exception
     */
    String addMessageByCusId(String content, Long cusId, int type) throws Exception;

    /**
     * 发送系统消息
     *
     * @param content 要发送的内容
     * @param cusId   用户id
     * @throws Exception
     */
    String addSystemMessageByCusId(String content, Long cusId) throws Exception;

    /**
     * 查询该用户未读消息数量
     *
     * @param content 要发送的内容
     * @return 返回该用户四种类型每个的未读消息的数量和总的未读数量
     * @throws Exception
     */
    Map<String, String> queryUnReadMsgReceiveNumByCusId(Long cusId) throws Exception;

    /**
     * 更新某种类型的站内信状态为已读
     *
     * @param msgReceive 传入type 传入type和站内信收信人id
     * @throws Exception
     */
    void updateAllMsgReceiveReadByType(MsgReceive msgReceive) throws Exception;

    /**
     * 批量添加消息
     *
     * @param msgReceiveList 消息的list
     */
    Long addMsgReceiveBatch(List<MsgReceive> msgReceiveList);

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
