package com.atdld.os.edu.service.card;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.card.Card;
import com.atdld.os.edu.entity.card.CourseCardDTO;
import com.atdld.os.edu.entity.card.MainCardDTO;
import com.atdld.os.edu.entity.card.QueryCardCode;
import com.atdld.os.edu.entity.card.QueryMainCard;

/**
 * Card管理接口
 * User:
 * Date: 2014-09-25
 */
public interface CardService {

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
    
    
    /**
     * 填充课程卡信息
     * @param card
     * @param sellIds
     */
    public void saveCourseCardInfo(Card card,String sellIds);
    
    
    
    
    /**
     * 获取用户卡集合
     * @param cardCode
     * @param page
     * @return
     */
    public List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode ,@ModelAttribute("page") PageEntity page);
    
    
    /**
     * 获取课程卡主卡信息
     * @param queryMainCard
     * @param page
     * @return
     */
    public List<MainCardDTO> getMianListByCondition(QueryMainCard queryMainCard,@ModelAttribute("page") PageEntity page);

}