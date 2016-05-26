package io.wangxiao.edu.home.service.card;

import io.wangxiao.edu.home.entity.card.CardCode;
import io.wangxiao.edu.home.entity.card.QueryCardCode;

import java.util.List;

/**
 * CardCode管理接口
 */
public interface CardCodeService {

    /**
     * 添加CardCode
     *
     * @param cardCode 要添加的CardCode
     * @return id
     */
    java.lang.Long addCardCode(CardCode cardCode);

    /**
     * 根据id删除一个CardCode
     *
     * @param id 要删除的id
     */
    void deleteCardCodeById(Long id);

    /**
     * 修改CardCode
     *
     * @param cardCode 要修改的CardCode
     */
    void updateCardCode(CardCode cardCode);

    /**
     * 根据id获取单个CardCode对象
     *
     * @param id 要查询的id
     * @return CardCode
     */
    CardCode getCardCodeById(Long id);

    /**
     * 根据条件获取CardCode列表
     *
     * @param cardCode 查询条件
     * @return List<CardCode>
     */
    List<CardCode> getCardCodeList(CardCode cardCode);


    List<CardCode> getHistoryByQueryCardCode(QueryCardCode queryCardCode);


    /**
     * 激活课程卡/充值卡
     *
     * @param cardCode
     * @param userId
     * @return
     */
    String activationCard(CardCode cardCode, Long userId, int type) throws Exception;


    /**
     * 修改课程卡信息
     *
     * @param cardId
     */
    void closeMainCard(long cardId);


    /**
     * 定时器
     * 课程卡过期操作
     */
    void updateCardStatus();

    /**
     * 根据卡编码  修改   未使用  为  已使用  状态
     *
     * @param cardCode
     */
    void updateCardStatusByCode(String cardCode);
}