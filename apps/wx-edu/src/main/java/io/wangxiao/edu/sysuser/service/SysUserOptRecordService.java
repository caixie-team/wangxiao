package io.wangxiao.edu.sysuser.service;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;

import java.util.List;

public interface SysUserOptRecordService {

    /**
     * 添加操作记录
     *
     * @param sysUserOptRecord
     * @return
     */
    Long addRecord(SysUserOptRecord sysUserOptRecord);

    /**
     * 根据id获取操作记录
     *
     * @param id
     * @return
     */
    SysUserOptRecord getRecordById(Long id);

    /**
     * 获取操作记录
     *
     * @param sysUserOptRecord
     * @param page
     * @return
     */
    List<SysUserOptRecord> getRecordList(SysUserOptRecord sysUserOptRecord, PageEntity page);
}
