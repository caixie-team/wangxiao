package io.wangxiao.edu.home.service.impl.plan;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PlanRecordDao;
import io.wangxiao.edu.home.entity.plan.PlanRecord;
import io.wangxiao.edu.home.service.plan.PlanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("planServiceDao")
public class PlanRecordServiceImpl implements PlanRecordService {

    @Autowired
    private PlanRecordDao planRecordDao;

    @Override
    public void addPlanRecordBatch(List<PlanRecord> planRecordList) {
        this.planRecordDao.addPlanRecordBatch(planRecordList);
    }

    @Override
    public void updatePlanRecord(PlanRecord planRecord) {
        this.planRecordDao.updatePlanRecord(planRecord);
    }

    @Override
    public PlanRecord getPlanRecord(PlanRecord planRecord) {
        return this.planRecordDao.getPlanRecord(planRecord);
    }

    @Override
    public List<PlanRecord> getPlanRecordList(PlanRecord planRecord) {
        return this.planRecordDao.getPlanRecordList(planRecord);
    }

    @Override
    public List<PlanRecord> getPlanRecordListPage(PlanRecord planRecord, PageEntity page) {
        return this.planRecordDao.getPlanRecordListPage(planRecord, page);
    }

}