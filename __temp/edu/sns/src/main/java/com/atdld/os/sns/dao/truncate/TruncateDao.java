package com.atdld.os.sns.dao.truncate;

/**
 * 
 * @author Administrator
 *
 */
public interface TruncateDao{

	/**
	 * 清空表
	 * 
	 * @param name
	 */
	public void truncateTableByName(String tableName);

   
}
