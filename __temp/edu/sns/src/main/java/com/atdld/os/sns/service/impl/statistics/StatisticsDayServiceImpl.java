package com.atdld.os.sns.service.impl.statistics;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.service.SnsHessianService;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.sns.dao.statistics.StatisticsDayDao;
import com.atdld.os.sns.entity.statistics.SnsStatisticsDay;
import com.atdld.os.sns.service.statistics.StatisticsDayService;
/**
 * StatisticsDayDay管理接口
 * User:
 * Date: 2014-11-05
 */
@Service("statisticsDayService")
public class StatisticsDayServiceImpl implements StatisticsDayService{
	MemCache memCache = MemCache.getInstance();
 	@Autowired
    private StatisticsDayDao statisticsDayDao;
 	@Autowired
    private SnsHessianService snsHessianService;
 	@Getter@Setter
	private Map<String,Object> userMsg= new HashMap<String,Object>();
  
 	/**
	 * 查询时间集合的社区统计
	 * @param dates
	 * @return
	 */
	public List<SnsStatisticsDay> getStatisticsDay(List<Date> dates){
		return statisticsDayDao.getStatisticsDay(dates);
	}
}