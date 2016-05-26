package com.atdld.os.sns.dao.statistics;
import java.util.Date;
import java.util.List;

import com.atdld.os.sns.entity.statistics.SnsStatisticsDay;




/**
 * StatisticsDay管理接口
 * User:
 * Date: 2014-11-05
 */
public interface StatisticsDayDao {

	/**
	 * 查询时间集合的社区统计
	 * @param dates
	 * @return
	 */
	public List<SnsStatisticsDay> getStatisticsDay(List<Date> dates); 
}