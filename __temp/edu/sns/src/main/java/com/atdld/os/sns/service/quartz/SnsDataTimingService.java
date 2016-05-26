package com.atdld.os.sns.service.quartz;

/**
 * @author :
 * @ClassName com.supergenius..sns.service.quartz.SnsDataTimingService
 * @description
 * @Create Date : 2014-1-3 下午4:51:27
 */
public interface SnsDataTimingService {

    /**
     * 全站排行数据生成
     */
    public void queryHotData();

    /**
     * 定时生成索引数据
     */
    public void initLucenIndex();

    /**
     * 定时计数器入库
     */
    public void visitStat();
}
