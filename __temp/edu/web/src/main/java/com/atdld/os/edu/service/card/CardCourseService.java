package com.atdld.os.edu.service.card;

import java.util.List;
import com.atdld.os.edu.entity.card.CardCourse;

/**
 * CardCourse管理接口
 * User:
 * Date: 2014-09-25
 */
public interface CardCourseService {

    /**
     * 添加CardCourse
     * @param cardCourse 要添加的CardCourse
     * @return id
     */
    public java.lang.Long addCardCourse(CardCourse cardCourse);

    /**
     * 根据id删除一个CardCourse
     * @param id 要删除的id
     */
    public void deleteCardCourseById(Long id);

    /**
     * 修改CardCourse
     * @param cardCourse 要修改的CardCourse
     */
    public void updateCardCourse(CardCourse cardCourse);

    /**
     * 根据id获取单个CardCourse对象
     * @param id 要查询的id
     * @return CardCourse
     */
    public CardCourse getCardCourseById(Long id);

    /**
     * 根据条件获取CardCourse列表
     * @param cardCourse 查询条件
     * @return List<CardCourse>
     */
    public List<CardCourse> getCardCourseList(CardCourse cardCourse);
}