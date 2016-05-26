package io.wangxiao.edu.home.service.card;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.card.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * Card管理接口
 */
public interface CardService {

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


    /**
     * 填充课程卡信息
     *
     * @param card
     * @param sellIds
     */
    void saveCourseCardInfo(Card card, String sellIds);


    /**
     * 获取用户卡集合
     *
     * @param cardCode
     * @param page
     * @return
     */
    List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode, @ModelAttribute("page") PageEntity page);


    /**
     * 获取课程卡主卡信息
     *
     * @param queryMainCard
     * @param page
     * @return
     */
    List<MainCardDTO> getMianListByCondition(QueryMainCard queryMainCard, @ModelAttribute("page") PageEntity page);

    /**
     * 添加学员卡
     */
    void addCourseUserCard(Card card, String courseIds) throws Exception;

}