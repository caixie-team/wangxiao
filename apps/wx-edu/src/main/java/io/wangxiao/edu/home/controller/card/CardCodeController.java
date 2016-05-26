package io.wangxiao.edu.home.controller.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.card.CardCode;
import io.wangxiao.edu.home.service.card.CardCodeService;
/**
 * CardCode管理接口
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