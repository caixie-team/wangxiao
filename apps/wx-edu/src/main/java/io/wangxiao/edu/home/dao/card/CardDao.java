package io.wangxiao.edu.home.dao.card;

import io.wangxiao.edu.home.entity.card.Card;

import java.util.List;

/**
 * Card管理接口
 */
public interface CardDao {

    /**
     * 添加Card
     *
     * @param card 要添加的Card
     * @return id
     */
    java.lang.Long addCard(Card card);

    /**
     * 根据id删除一个Card
     *
     * @param id 要删除的id
     */
    void deleteCardById(Long id);

    /**
     * 修改Card
     *
     * @param card 要修改的Card
     */
    void updateCard(Card card);

    /**
     * 根据id获取单个Card对象
     *
     * @param id 要查询的id
     * @return Card
     */
    Card getCardById(Long id);

    /**
     * 根据条件获取Card列表
     *
     * @param card 查询条件
     * @return List<Card>
     */
    List<Card> getCardList(Card card);
}