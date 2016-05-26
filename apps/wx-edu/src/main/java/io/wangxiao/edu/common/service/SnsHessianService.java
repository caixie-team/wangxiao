package io.wangxiao.edu.common.service;

import io.wangxiao.edu.home.entity.statistics.StatisticsDay;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SnsHessianService {
	/**
	 * 社区添加动态
	 * @param map
	 */
	void addDynamic(Map<String, String> map);
	/**
	 * 社区删除动态
	 * @param map
	 */
	void delDynamic(Long id, int type);
	/**
	 * 社区统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<StatisticsDay> getSnsStatistics(List<Date> dates);
	/**
	 * 清空社区指定表
	 * @param name
	 */
	void truncateTable(String name);
}
