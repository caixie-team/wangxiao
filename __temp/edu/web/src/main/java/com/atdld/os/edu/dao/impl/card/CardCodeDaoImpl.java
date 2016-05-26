package com.atdld.os.edu.dao.impl.card;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.card.CardCodeDao;
import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.entity.card.CourseCardDTO;
import com.atdld.os.edu.entity.card.MainCardDTO;
import com.atdld.os.edu.entity.card.QueryCardCode;
import com.atdld.os.edu.entity.card.QueryMainCard;

/**
 * 
 * CardCode User:   Date: 2014-09-25
 */
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
