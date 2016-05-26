package com.atdld.os.sns.service.truncate;

/**
 * 
 * @author Administrator
 *
 */
public interface TruncateService{

	/**
	 * 清空表
	 * 
	 * @param name
	 */
	public void truncateTableByName(String tableName);

   
}
