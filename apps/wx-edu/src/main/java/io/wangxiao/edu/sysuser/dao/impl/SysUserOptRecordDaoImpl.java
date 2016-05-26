package io.wangxiao.edu.sysuser.dao.impl;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.sysuser.dao.SysUserOptRecordDao;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysUserOptRecordDao")
public class SysUserOptRecordDaoImpl extends GenericDaoImpl implements SysUserOptRecordDao {

    @Override
    public Long addRecord(SysUserOptRecord sysUserOptRecord) {
        return this.insert("SysUserOptRecordMapper.addRecord", sysUserOptRecord);
    }

    @Override
    public SysUserOptRecord getRecordById(Long id) {
        return this.selectOne("SysUserOptRecordMapper.getRecordById", id);
    }

    @Override
    public List<SysUserOptRecord> getRecordList(SysUserOptRecord sysUserOptRecord, PageEntity page) {
        return this.queryForListPage("SysUserOptRecordMapper.getRecordList", sysUserOptRecord, page);
    }
}
