package com.atdld.os.edu.dao.card;
import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.entity.card.CourseCardDTO;
import com.atdld.os.edu.entity.card.MainCardDTO;
import com.atdld.os.edu.entity.card.QueryCardCode;
import com.atdld.os.edu.entity.card.QueryMainCard;

/**
 * CardCode管理接口
 * User:
 * Date: 2014-09-25
 */
public interface CardCodeDao {

    /**
     * 添加CardCode
     * @param cardCode 要添加的CardCode
     * @return id
     */
    public java.lang.Long addCardCode(CardCode cardCode);
    
    
    
    /**
     * 批量添加课程卡信息
     * @param cardCodeList
     */
    public void addCardCodeList(List<CardCode> cardCodeList);

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
    
    
    public List<CardCode> getHistoryByQueryCardCode(QueryCardCode QueryCardCode);
     
    /**
     * 获取最后id
     * @return
     */
    public Long getLastCardCodeId();
    
    
    public List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode, PageEntity page);
    
    /**
     * 获取课程卡主卡信息
     * @param queryMainCard
     * @param page
     * @return
     */
    public List<MainCardDTO> getMainCardListCondition(QueryMainCard queryMainCard,PageEntity page);
    
    /**
     * 获取课程卡状态
     * @return
     */
    public List<CourseCardDTO> getCardCodeStatus();
   

    
    
    /**
     * 修改课程卡状态
     */
    public void updateCardCodeStatus(String cardIds);
    
    
    /**
     * 关闭课程卡
     * @param cardIds
     */
    public void closeCardCodeStatus(List<CardCode> listCardCode);
    
    /**
     * 根据卡编码  修改   未使用  为  已使用  状态
     * @param card_code 
     */
    public void updateCardStatusByCode(String cardCode);
}