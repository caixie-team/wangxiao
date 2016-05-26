package io.wangxiao.edu.home.dao.letter;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.letter.MsgSystem;

import java.util.Date;
import java.util.List;


/**
 * @description 站内信发件箱的Dao
 */
public interface MsgSystemDao {
    /**
     * 添加系统消息
     *
     * @param msgSender
     * @return
     * @throws Exception
     */
    Long addMsgSystem(MsgSystem msgSystem) throws Exception;

    /**
     * 批量添加系统消息
     *
     * @param msgSystemList 消息的list
     */
    void addMsgSystemBatch(List<MsgSystem> msgSystemList);

    /**
     * 查询系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    List<MsgSystem> queryMsgSystemList(MsgSystem msgSystem, PageEntity page) throws Exception;

    /**
     * 通过id删除系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    Long delMsgSystemById(Long id) throws Exception;

    /**
     * 查询大于传入的时间的系统系统消息
     *
     * @param msgSystem
     * @return
     * @throws Exception
     */
    List<MsgSystem> queryMSListByLT(Date lastTime) throws Exception;

    /**
     * 更新过期的系统消息的字段为过期
     */
    void updateMsgSystemPastTime(Date lastTime) throws Exception;
}
