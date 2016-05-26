package io.wangxiao.edu.home.dao.card;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.card.*;

import java.util.List;

/**
 * CardCode管理接口
 */
public interface CardCodeDao {

    /**
     * 添加CardCode
     *
     * @param cardCode 要添加的CardCode
     * @return id
     */
    java.lang.Long addCardCode(CardCode cardCode);


    /**
     * 批量添加课程卡信息
     *
     * @param cardCodeList
     */
    void addCardCodeList(List<CardCode> cardCodeList);

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


    List<CardCode> getHistoryByQueryCardCode(QueryCardCode QueryCardCode);

    /**
     * 获取最后id
     *
     * @return
     */
    Long getLastCardCodeId();


    List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode, PageEntity page);

    /**
     * 获取课程卡主卡信息
     *
     * @param queryMainCard
     * @param page
     * @return
     */
    List<MainCardDTO> getMainCardListCondition(QueryMainCard queryMainCard, PageEntity page);

    /**
     * 获取课程卡状态
     *
     * @return
     */
    List<CourseCardDTO> getCardCodeStatus();


    /**
     * 修改课程卡状态
     */
    void updateCardCodeStatus(String cardIds);


    /**
     * 关闭课程卡
     *
     * @param cardIds
     */
    void closeCardCodeStatus(List<CardCode> listCardCode);

    /**
     * 根据卡编码  修改   未使用  为  已使用  状态
     *
     * @param card_code
     */
    void updateCardStatusByCode(String cardCode);
}