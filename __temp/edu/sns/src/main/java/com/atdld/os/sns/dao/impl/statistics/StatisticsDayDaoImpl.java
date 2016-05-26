package com.atdld.os.sns.dao.impl.statistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.sns.dao.statistics.StatisticsDayDao;
import com.atdld.os.sns.entity.statistics.SnsStatisticsDay;

/**
 *
 * StatisticsDay
 * User:
 * Date: 2014-11-05
 */
 @Repository("statisticsDayDao")
public class StatisticsDayDaoImpl extends GenericDaoImpl implements StatisticsDayDao{

	/**
	 * 查询时间集合的社区统计
	 * @param dates
	 * @return
	 */
	public List<SnsStatisticsDay> getStatisticsDay(List<Date> dates){
		List<SnsStatisticsDay> statisticsDays=new ArrayList<SnsStatisticsDay>();
		for(Date date:dates){
			SnsStatisticsDay statisticsDay=this.selectOne("StatisticsDayMapper.querySnsStatisticsDay", date);
			String countStr=statisticsDay.getCountStr();//社区统计字符串
			statisticsDay.setWeiboNum(Long.parseLong(countStr.split(",")[0]));//微博数
			statisticsDay.setBlogNum(Long.parseLong(countStr.split(",")[1]));//博文数
			statisticsDay.setQuesNum(Long.parseLong(countStr.split(",")[2]));//问答数
			statisticsDays.add(statisticsDay);
		}
		return statisticsDays;
	}
 }
