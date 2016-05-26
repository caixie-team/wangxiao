package com.atdld.os.exam.service.customer;

import com.atdld.os.exam.entity.customer.CusDateRecord;

/**
 * @author
 * @ClassName ICustomerService
 * @package com.atdld.os.exam.service.customer
 * @description 操作数据库demo
 * @Create Date: 2013-2-27 上午10:44:08
 */
public interface CusDateRecordService {
    /**
     * 通过cusId和subjectId查找CusDateRecord
     */
    public CusDateRecord queryCusDateRecordByCusIdAndSubjectId(CusDateRecord cusDateRecord);

    /**
     * 通过cusId和subjectId查找CusDateRecord的个人平均分排名
     */
    public int queryCusDateRecordAverageScoreRanking(CusDateRecord cusDateRecord);

}

