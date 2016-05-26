package com.atdld.os.edu.controller.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.service.card.CardCodeService;
/**
 * CardCode管理接口
 * User:
 * Date: 2014-09-25
 */
@Controller
public class CardCodeController extends EduBaseController{

 	@Autowired
    private CardCodeService cardCodeService;
    
    
    
    /**
     * 修改CardCode
     * @param cardCode 要修改的CardCode
     */
    public void updateCardCode(CardCode cardCode){
     	cardCodeService.updateCardCode(cardCode);
    }
    

   
}