package com.atdld.os.exam.controller.yzlapi;


import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.service.truncate.TruncateService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.yzlapi.ApiAction
 * @description
 * @Create Date : 2014-2-26 下午1:02:15
 */
@Controller
@RequestMapping("/api")
public class ApiController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(ApiController.class);
    @Autowired
    private TruncateService truncateService;// 清除表service

    
    /**
   	 * 清空社区指定表
   	 * @param name
   	 */
       @RequestMapping("/yzl/truncate/table")
       @ResponseBody
   	public Map<String,Object> truncateTable(HttpServletRequest request){
   		try {
   			String tableName = request.getParameter("tableName");
   			if(tableName==null){
   				this.setJson(false, "参数不能为空", null);
   				return json;
   			}
   			truncateService.truncateTableByName(tableName);
   			this.setJson(true, "success", null);
   		} catch (Exception e) {
   			logger.error("ApiController.truncateTable------error",e);	
   			this.setJson(false, "error", null);
   		}
   		return json;
   	}

   
}
