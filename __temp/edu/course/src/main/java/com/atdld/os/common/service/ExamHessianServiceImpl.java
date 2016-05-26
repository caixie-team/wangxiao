
package com.atdld.os.common.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.util.web.HttpUtil;

/**
 * Created by Administrator on 2014/12/25.
 */
@Service("examHessianService")
public class ExamHessianServiceImpl implements ExamHessianService {
	private static final Logger logger = LoggerFactory.getLogger(ExamHessianServiceImpl.class);
	private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	
	/**
	 * 清空社区指定表
	 * @param name
	 */
	public void truncateTable(String name){
		try {
			Map<String,String> map=new HashMap<String, String>();
			map.put("tableName",name);
			String resultJson=HttpUtil.doPost(CommonConstants.examPath+"/api/yzl/truncate/table", map);
			Map<String,Object> result=new Gson().fromJson(resultJson, new TypeToken<Map<String,Object>>(){}.getType());
			if(!(boolean) result.get("success")){
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("ExamHessianServiceImpl.truncateTable------error",e);	
		}
	}
}

