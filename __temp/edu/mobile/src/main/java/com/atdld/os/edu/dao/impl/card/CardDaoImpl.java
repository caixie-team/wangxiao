package com.atdld.os.edu.dao.impl.card;

import java.util.List;
import com.atdld.os.edu.entity.card.Card;
import com.atdld.os.edu.dao.card.CardDao;
import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * Card
 * User:
 * Date: 2014-09-25
 */
 @Repository("cardDao")
public class CardDaoImpl extends GenericDaoImpl implements CardDao{

    public java.lang.Long addCard(Card card) {
        return this.insert("CardMapper.createCard",card);
    }

    public void deleteCardById(Long id){
        this.delete("CardMapper.deleteCardById",id);
    }

    public void updateCard(Card card) {
        this.update("CardMapper.updateCard",card);
    }

    public Card getCardById(Long id) {
        return this.selectOne("CardMapper.getCardById",id);
    }

    public List<Card> getCardList(Card card) {
        return this.selectList("CardMapper.getCardList",card);
    }
}
