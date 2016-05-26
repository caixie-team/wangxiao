package io.wangxiao.edu.home.dao.impl.card;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.card.CardCourseDao;
import io.wangxiao.edu.home.entity.card.CardCourse;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("cardCourseDao")
public class CardCourseDaoImpl extends GenericDaoImpl implements CardCourseDao {

    public java.lang.Long addCardCourse(CardCourse cardCourse) {
        return this.insert("CardCourseMapper.createCardCourse", cardCourse);
    }

    public void deleteCardCourseById(Long id) {
        this.delete("CardCourseMapper.deleteCardCourseById", id);
    }

    public void updateCardCourse(CardCourse cardCourse) {
        this.update("CardCourseMapper.updateCardCourse", cardCourse);
    }

    public CardCourse getCardCourseById(Long id) {
        return this.selectOne("CardCourseMapper.getCardCourseById", id);
    }

    public List<CardCourse> getCardCourseList(CardCourse cardCourse) {
        return this.selectList("CardCourseMapper.getCardCourseList", cardCourse);
    }

    @Override
    public Long addCardCourse(List<CardCourse> cardCourseList) {
        this.insert("CardCourseMapper.createCardCourse", cardCourseList);
        return null;
    }
}
