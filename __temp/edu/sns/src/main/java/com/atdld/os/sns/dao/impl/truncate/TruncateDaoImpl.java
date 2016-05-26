package com.atdld.os.sns.dao.impl.truncate;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.sns.dao.truncate.TruncateDao;

/**
 * 
 * @author Administrator
 *
 */
@Repository("truncateDao")
public class TruncateDaoImpl extends GenericDaoImpl implements TruncateDao{

	/**
	 * 清空表
	 * 
	 * @param name
	 */
	public void truncateTableByName(String tableName){
		this.delete("TruncateMapper.truncateTable", tableName);
	}

   
}
