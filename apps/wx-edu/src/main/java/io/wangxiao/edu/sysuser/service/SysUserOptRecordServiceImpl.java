package io.wangxiao.edu.sysuser.service;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.sysuser.dao.SysUserOptRecordDao;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysUserOptRecordService")
public class SysUserOptRecordServiceImpl implements SysUserOptRecordService {

    @Autowired
    private SysUserOptRecordDao sysUserOptRecordDao;

    @Override
    public Long addRecord(SysUserOptRecord sysUserOptRecord) {
        return this.sysUserOptRecordDao.addRecord(sysUserOptRecord);
    }

    @Override
    public SysUserOptRecord getRecordById(Long id) {
        return this.sysUserOptRecordDao.getRecordById(id);
    }

    @Override
    public List<SysUserOptRecord> getRecordList(SysUserOptRecord sysUserOptRecord, PageEntity page) {
        return this.sysUserOptRecordDao.getRecordList(sysUserOptRecord, page);
    }
}
