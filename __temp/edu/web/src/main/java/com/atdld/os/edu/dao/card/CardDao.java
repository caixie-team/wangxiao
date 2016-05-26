package com.atdld.os.edu.dao.card;
import java.util.List;
import com.atdld.os.edu.entity.card.Card;

/**
 * Card管理接口
 * User:
 * Date: 2014-09-25
 */
public interface CardDao {

    /**
     * 添加Card
     * @param card 要添加的Card
     * @return id
     */
    public java.lang.Long addCard(Card card);

    /**
     * 根据id删除一个Card
     * @param id 要删除的id
     */
    public void deleteCardById(Long id);

    /**
     * 修改Card
     * @param card 要修改的Card
     */
    public void updateCard(Card card);

    /**
     * 根据id获取单个Card对象
     * @param id 要查询的id
     * @return Card
     */
    public Card getCardById(Long id);

    /**
     * 根据条件获取Card列表
     * @param card 查询条件
     * @return List<Card>
     */
    public List<Card> getCardList(Card card);
}