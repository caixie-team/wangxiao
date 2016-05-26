package io.wangxiao.edu.home.dao.impl.card;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.card.CardDao;
import io.wangxiao.edu.home.entity.card.Card;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cardDao")
public class CardDaoImpl extends GenericDaoImpl implements CardDao {

    public java.lang.Long addCard(Card card) {
        return this.insert("CardMapper.createCard", card);
    }

    public void deleteCardById(Long id) {
        this.delete("CardMapper.deleteCardById", id);
    }

    public void updateCard(Card card) {
        this.update("CardMapper.updateCard", card);
    }

    public Card getCardById(Long id) {
        return this.selectOne("CardMapper.getCardById", id);
    }

    public List<Card> getCardList(Card card) {
        return this.selectList("CardMapper.getCardList", card);
    }
}
