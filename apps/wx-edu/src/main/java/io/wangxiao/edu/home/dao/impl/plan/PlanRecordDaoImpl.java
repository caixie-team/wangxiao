package io.wangxiao.edu.home.dao.impl.plan;


import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PlanRecordDao;
import io.wangxiao.edu.home.entity.plan.PlanRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("planRecordDao")
public class PlanRecordDaoImpl extends GenericDaoImpl implements PlanRecordDao {

    @Override
    public void addPlanRecordBatch(List<PlanRecord> planRecordList) {
        this.insert("PlanRecordMapper.addPlanRecordBatch", planRecordList);
    }

    @Override
    public void updatePlanRecord(PlanRecord planRecord) {
        this.update("PlanRecordMapper.updatePlanRecord", planRecord);
    }

    @Override
    public PlanRecord getPlanRecord(PlanRecord planRecord) {
        return this.selectOne("PlanRecordMapper.getPlanRecord", planRecord);
    }

    @Override
    public List<PlanRecord> getPlanRecordList(PlanRecord planRecord) {
        return this.selectList("PlanRecordMapper.getPlanRecordList", planRecord);
    }

    @Override
    public List<PlanRecord> getPlanRecordListPage(PlanRecord planRecord, PageEntity page) {
        return this.queryForListPage("PlanRecordMapper.getPlanRecordListPage", planRecord, page);
    }

}