package io.wangxiao.edu.home.service.plan;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.PlanRecord;

import java.util.List;

public interface PlanRecordService {

    /**
     * 批量添加用户计划记录
     *
     * @param planRecordList
     * @return
     */
    void addPlanRecordBatch(List<PlanRecord> planRecordList);

    /**
     * 更新进度
     *
     * @param planRecord
     */
    void updatePlanRecord(PlanRecord planRecord);

    /**
     * 获取用户计划记录
     *
     * @param planRecord
     * @return
     */
    PlanRecord getPlanRecord(PlanRecord planRecord);

    /**
     * 查询用户计划记录
     *
     * @param planRecord
     * @return
     */
    List<PlanRecord> getPlanRecordList(PlanRecord planRecord);

    /**
     * 查询用户计划记录(分页)
     *
     * @param planRecord
     * @param page
     * @return
     */
    List<PlanRecord> getPlanRecordListPage(PlanRecord planRecord, PageEntity page);
}