package co.bluepx.edu.common.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.atdld.os.edu.entity.statistics.StatisticsDay;

/**
 * Created by Administrator on 2014/12/25.
 */
public interface SnsHessianService {
	/**
	 * 社区添加动态
	 * @param map
	 */
	public void addDynamic(Map<String, String> map);
	/**
	 * 社区删除动态
	 * @param map
	 */
	public void delDynamic(Long id, int type);
	/**
	 * 社区统计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<StatisticsDay> getSnsStatistics(List<Date> dates);
	/**
	 * 清空社区指定表
	 * @param name
	 */
	public void truncateTable(String name);
}
