
package com.atdld.os.common.service;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.util.web.HttpUtil;
import com.atdld.os.edu.entity.statistics.StatisticsDay;

/**
 * Created by Administrator on 2014/12/25.
 */
@Service("snsHessianService")
public class SnsHessianServiceImpl implements SnsHessianService {
	private static final Logger logger = LoggerFactory.getLogger(SnsHessianServiceImpl.class);
	private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	/**
	 * 社区添加动态
	 * @param map
	 */
	public void addDynamic(Map<String,String> map){
		try {
			String resultJson=HttpUtil.doPost(CommonConstants.snsPath+"/api/yzl/addlearn", map);
			Map<String,Object> result=new Gson().fromJson(resultJson, new TypeToken<Map<String,Object>>(){}.getType());
			if(!(boolean) result.get("success")){
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("SnsHessianServiceImpl.addDynamic------error",e);	
		}
	}
	
	/**
	 * 社区删除动态
	 * @param map
	 */
	public void delDynamic(Long id,int type){
		try {
			Map<String,String> map=new HashMap<String, String>();
			map.put("id", id+"");
			map.put("type", type+"");
			String resultJson=HttpUtil.doPost(CommonConstants.snsPath+"/api/yzl/dellearn", map);
			Map<String,Object> result=new Gson().fromJson(resultJson, new TypeToken<Map<String,Object>>(){}.getType());
			if(!(boolean) result.get("success")){
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("SnsHessianServiceImpl.delDynamic------error",e);	
		}
	}
	
	/**
	 * 社区统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<StatisticsDay> getSnsStatistics(List<Date> dates){
		try {
			Map<String,String> map=new HashMap<String, String>();
			map.put("datesJson",gson.toJson(dates) );//时间集合
			String resultJson=HttpUtil.doPost(CommonConstants.snsPath+"/api/yzl/statistics", map);
			Map<String,Object> result=new Gson().fromJson(resultJson, new TypeToken<Map<String,Object>>(){}.getType());
			if(!(boolean) result.get("success")){
				throw new Exception();
			}
			String statisticsJson=(String) result.get("entity");
			//社区统计集合
			return gson.fromJson(statisticsJson, new TypeToken<List<StatisticsDay>>(){}.getType());
		} catch (Exception e) {
			logger.error("SnsHessianServiceImpl.delDynamic------error",e);	
		}
		return null;
	}
	/**
	 * 清空社区指定表
	 * @param name
	 */
	public void truncateTable(String name){
		try {
			Map<String,String> map=new HashMap<String, String>();
			map.put("tableName",name);
			String resultJson=HttpUtil.doPost(CommonConstants.snsPath+"/api/yzl/truncate/table", map);
			Map<String,Object> result=new Gson().fromJson(resultJson, new TypeToken<Map<String,Object>>(){}.getType());
			if(!(boolean) result.get("success")){
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("SnsHessianServiceImpl.truncateTable------error",e);	
		}
	}
}

