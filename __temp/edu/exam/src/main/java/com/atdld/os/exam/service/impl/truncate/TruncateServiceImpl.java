package com.atdld.os.exam.service.impl.truncate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.exam.dao.truncate.TruncateDao;
import com.atdld.os.exam.service.truncate.TruncateService;

/**
 * @author
 * @ClassName FriendServiceImpl
 * @package com.atdld.open.sns.service.impl.friend
 * @description
 * @Create Date: 2013-12-10 下午4:14:22
 */

@Service("truncateService")
public class TruncateServiceImpl implements TruncateService {
    @Autowired
    private TruncateDao truncateDao;

    /**
	 * 清空表
	 * 
	 * @param name
	 */
	public void truncateTableByName(String tableName){
		truncateDao.truncateTableByName(tableName);
	}

   
}
