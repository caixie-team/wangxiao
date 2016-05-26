package io.wangxiao.edu.home.dao.impl.card;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.card.CardCodeDao;
import io.wangxiao.edu.home.entity.card.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cardCodeDao")
public class CardCodeDaoImpl extends GenericDaoImpl implements CardCodeDao {

    public java.lang.Long addCardCode(CardCode cardCode) {
        return this.insert("CardCodeMapper.createCardCode", cardCode);
    }

    public void deleteCardCodeById(Long id) {
        this.delete("CardCodeMapper.deleteCardCodeById", id);
    }

    public void updateCardCode(CardCode cardCode) {
        this.update("CardCodeMapper.updateCardCode", cardCode);
    }

    public CardCode getCardCodeById(Long id) {
        return this.selectOne("CardCodeMapper.getCardCodeById", id);
    }

    public List<CardCode> getCardCodeList(CardCode cardCode) {
        return this.selectList("CardCodeMapper.getCardCodeList", cardCode);
    }

    public void addCardCodeList(List<CardCode> cardCodeList) {
        // TODO Auto-generated method stub
        this.insert("CardCodeMapper.addCardCodeList", cardCodeList);
    }

    @Override
    public Long getLastCardCodeId() {
        // TODO Auto-generated method stub
        return this.selectOne("CardCodeMapper.getCardCodeLastId", "");
    }

    @Override
    public List<CardCode> getHistoryByQueryCardCode(QueryCardCode QueryCardCode) {
        // TODO Auto-generated method stub
        return this.selectList("CardCodeMapper.getCardHistory", QueryCardCode);
    }

    @Override
    public List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode, PageEntity page) {
        return this.queryForListPage("CardCodeMapper.getCardHistoryCondition", queryCardCode, page);
    }

    @Override
    public List<MainCardDTO> getMainCardListCondition(QueryMainCard queryMainCard, PageEntity page) {
        // TODO Auto-generated method stub
        return this.queryForListPage("CardCodeMapper.getMainCardCondition", queryMainCard, page);
    }

    @Override
    public void closeCardCodeStatus(List<CardCode> listCardCode) {
        this.update("CardCodeMapper.closeCardCodeStatus", listCardCode);
    }

    @Override
    public List<CourseCardDTO> getCardCodeStatus() {
        // TODO Auto-generated method stub
        return this.selectList("CardCodeMapper.getCardCodeStatus", null);
    }

    @Override
    public void updateCardCodeStatus(String cardIds) {
        // TODO Auto-generated method stub
        this.update("CardCodeMapper.updateCardCodeStatus", cardIds);
    }

    @Override
    public void updateCardStatusByCode(String cardCode) {
        this.update("CardCodeMapper.updateCardStatusByCode", cardCode);
    }

}
