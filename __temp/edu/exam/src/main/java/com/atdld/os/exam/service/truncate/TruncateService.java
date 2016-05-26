package com.atdld.os.exam.service.truncate;

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
