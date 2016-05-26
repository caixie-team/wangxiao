package io.wangxiao.edu.home.controller.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.card.CardCourse;
import io.wangxiao.edu.home.service.card.CardCourseService;
/**
 * CardCourse管理接口
 */
@Controller
public class CardCourseController extends EduBaseController{

 	@Autowired
    private CardCourseService cardCourseService;
    
    
    
    /**
     * 修改CardCourse
     * @param cardCourse 要修改的CardCourse
     */
    public void updateCardCourse(CardCourse cardCourse){
     	cardCourseService.updateCardCourse(cardCourse);
    }

   
}