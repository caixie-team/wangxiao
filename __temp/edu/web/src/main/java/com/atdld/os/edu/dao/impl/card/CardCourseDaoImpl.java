package com.atdld.os.edu.dao.impl.card;

import java.util.List;

import com.atdld.os.edu.entity.card.CardCourse;
import com.atdld.os.edu.dao.card.CardCourseDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * CardCourse
 * User:
 * Date: 2014-09-25
 */
 @Repository("cardCourseDao")
public class CardCourseDaoImpl extends GenericDaoImpl implements CardCourseDao{

    public java.lang.Long addCardCourse(CardCourse cardCourse) {
        return this.insert("CardCourseMapper.createCardCourse",cardCourse);
    }
    
    public void deleteCardCourseById(Long id){
        this.delete("CardCourseMapper.deleteCardCourseById",id);
    }

    public void updateCardCourse(CardCourse cardCourse) {
        this.update("CardCourseMapper.updateCardCourse",cardCourse);
    }

    public CardCourse getCardCourseById(Long id) {
        return this.selectOne("CardCourseMapper.getCardCourseById",id);
    }

    public List<CardCourse> getCardCourseList(CardCourse cardCourse) {
        return this.selectList("CardCourseMapper.getCardCourseList",cardCourse);
    }

	@Override
	public Long addCardCourse(List<CardCourse> cardCourseList) {
		this.insert("CardCourseMapper.createCardCourse", cardCourseList);
		return null;
	}
}
