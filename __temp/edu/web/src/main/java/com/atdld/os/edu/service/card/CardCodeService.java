package com.atdld.os.edu.service.card;

import java.util.List;

import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.entity.card.QueryCardCode;

/**
 * CardCode管理接口
 * User:
 * Date: 2014-09-25
 */
public interface CardCodeService {

    /**
     * 添加CardCode
     * @param cardCode 要添加的CardCode
     * @return id
     */
    public java.lang.Long addCardCode(CardCode cardCode);

    /**
     * 根据id删除一个CardCode
     * @param id 要删除的id
     */
    public void deleteCardCodeById(Long id);

    /**
     * 修改CardCode
     * @param cardCode 要修改的CardCode
     */
    public void updateCardCode(CardCode cardCode);

    /**
     * 根据id获取单个CardCode对象
     * @param id 要查询的id
     * @return CardCode
     */
    public CardCode getCardCodeById(Long id);

    /**
     * 根据条件获取CardCode列表
     * @param cardCode 查询条件
     * @return List<CardCode>
     */
    public List<CardCode> getCardCodeList(CardCode cardCode);
    
    
    public List<CardCode> getHistoryByQueryCardCode(QueryCardCode queryCardCode);
    
    
    /**
     * 激活课程卡/充值卡
     * @param cardCode
     * @param userId
     * @return
     */
    public String activationCard(CardCode cardCode,Long  userId,int type) throws Exception;
    
    
    
    /**
     * 修改课程卡信息
     * @param cardId
     */
    public void closeMainCard(long cardId);
    
    
    /**
     * 定时器
     * 课程卡过期操作
     */
    public void updateCardStatus();
    
    /**
     * 根据卡编码  修改   未使用  为  已使用  状态
     * @param card_code 
     */
    public void updateCardStatusByCode(String cardCode);
}