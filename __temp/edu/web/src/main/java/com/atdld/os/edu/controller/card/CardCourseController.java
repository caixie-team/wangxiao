package com.atdld.os.edu.controller.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.card.CardCourse;
import com.atdld.os.edu.service.card.CardCourseService;
/**
 * CardCourse管理接口
 * User:
 * Date: 2014-09-25
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