package com.atdld.os.sns.service.visitstat;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.visitstat.VisitStatService
 * @description
 * @Create Date : 2014-1-24 下午11:37:09
 */
public interface VisitStatService {

    /**
     * 记录访问统计
     *
     * @param type
     * @param obj_id
     */
    void record(byte type, long obj_id);

    /**
     * 执行写入数据库操作
     */
    void run();

}
