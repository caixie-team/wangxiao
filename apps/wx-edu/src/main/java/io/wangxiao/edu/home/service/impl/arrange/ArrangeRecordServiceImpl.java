
package io.wangxiao.edu.home.service.impl.arrange;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.edu.home.dao.arrange.ArrangeRecordDao;
import io.wangxiao.edu.home.entity.arrange.ArrangeRecord;
import io.wangxiao.edu.home.service.arrange.ArrangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("arrangeRecordService")
public class ArrangeRecordServiceImpl implements ArrangeRecordService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private ArrangeRecordDao arrangeRecordDao;

    /**
     * 添加ArrangeRecord
     *
     * @param arrangeRecord 要添加的ArrangeRecord
     * @return id
     */
    @Override
    public Long addArrangeRecord(ArrangeRecord arrangeRecord) {
        // TODO Auto-generated method stub
        return arrangeRecordDao.addArrangeRecord(arrangeRecord);
    }

    @Override
    public ArrangeRecord getArrangeRecordByArrangeRecord(ArrangeRecord arrangeRecord) {
        List<ArrangeRecord> arrangeRecordList = arrangeRecordDao.getArrangeRecordList(arrangeRecord, null);
        if (arrangeRecordList.size() > 0) {
            return arrangeRecordList.get(0);
        }
        return null;
    }
    /*
     * public java.lang.Long addArrangeRecord(ArrangeRecord arrangeRecord){ return
     * arrangeRecordDao.addArrangeRecord(arrangeRecord); }
     */

    /**
     * 批量添加ArrangeRecord
     */
    public void batchAddArrangeTecord(List<ArrangeRecord> arrangeRecords) {
        this.arrangeRecordDao.batchAddArrangeTecord(arrangeRecords);
    }


    /**
     * 根据部门和任务ID查出分数
     */
    public List<ArrangeRecord> getGroudArrange(ArrangeRecord arrangeRecord) {

        return arrangeRecordDao.getGroudArrange(arrangeRecord);

    }

    ;

    /**
     * 根据id删除一个ArrangeRecord
     *
     * @param id 要删除的id
     */
    public void deleteArrangeRecordById(Long id) {
        arrangeRecordDao.deleteArrangeRecordById(id);
    }

    /**
     * 修改ArrangeRecord
     *
     * @param arrangeRecord 要修改的ArrangeRecord
     */
    public void updateArrangeRecord(ArrangeRecord arrangeRecord) {
        arrangeRecordDao.updateArrangeRecord(arrangeRecord);
    }

    /**
     * 根据id获取单个ArrangeRecord对象
     *
     * @param id 要查询的id
     * @return ArrangeRecord
     */
    public ArrangeRecord getArrangeRecordById(Long id) {
        return arrangeRecordDao.getArrangeRecordById(id);
    }

    /**
     * 根据条件获取ArrangeRecord列表
     *
     * @param arrangeRecord 查询条件
     * @return List<ArrangeRecord>
     */
    public List<ArrangeRecord> getArrangeRecordList(ArrangeRecord arrangeRecord, PageEntity page) {
        return arrangeRecordDao.getArrangeRecordList(arrangeRecord, page);
    }

    @Override
    public List<ArrangeRecord> getArrangeRecorduserId(Long userid) {
        return arrangeRecordDao.getArrangeRecorduserId(userid);
    }

    /**
     * 删除任务下的记录
     */
    public void deleteArrangeRecoredId(Long taskId) {
        this.arrangeRecordDao.deleteArrangeRecoredId(taskId);
    }

    @Override
    public List<ArrangeRecord> getArrangeRecordList(ArrangeRecord arrangeRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ArrangeRecord> getArrangeRecordGroudUsers(ArrangeRecord arrangeRecord) {
        // TODO Auto-generated method stub
        return arrangeRecordDao.getArrangeRecordGroudUsers(arrangeRecord);
    }

    @Override
    public ArrangeRecord getuserArrangeid(ArrangeRecord ArrangeRecord) {
        // TODO Auto-generated method stub
        return arrangeRecordDao.getuserArrangeid(ArrangeRecord);
    }

    @Override
    public ArrangeRecord getGrouduserid(Long userid) {
        // TODO Auto-generated method stub
        return arrangeRecordDao.getGrouduserid(userid);
    }

    @Override
    public Long getgruopArrange(Long groupid) {
        // TODO Auto-generated method stub
        return arrangeRecordDao.getgruopArrange(groupid);
    }

    @Override
    public Long getcountgruopArrange(Long groupid) {
        // TODO Auto-generated method stub
        return arrangeRecordDao.getcountgruopArrange(groupid);
    }

    @Override
    public Long getIscompleteArrange(Long groupid) {
        // TODO Auto-generated method stub
        return arrangeRecordDao.getIscompleteArrange(groupid);
    }

    /**
     * 提交考试安排修改考试记录信息
     *
     * @param arrangeRecord
     */
    public void updateArrangeRecordBySubmit(ArrangeRecord arrangeRecord) {
        arrangeRecordDao.updateArrangeRecordBySubmit(arrangeRecord);
    }

    /**
     * 获取ArrangeRecordAndArrange
     *
     * @param arrangeRecordId
     * @return
     */
    public ArrangeRecord getArrangeRecordAndArrangeById(Long arrangeRecordId) {
        return arrangeRecordDao.getArrangeRecordAndArrangeById(arrangeRecordId);
    }


}
