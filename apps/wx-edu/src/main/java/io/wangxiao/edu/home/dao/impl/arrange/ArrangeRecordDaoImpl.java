
package io.wangxiao.edu.home.dao.impl.arrange;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.arrange.ArrangeRecordDao;
import io.wangxiao.edu.home.entity.arrange.Arrange;
import io.wangxiao.edu.home.entity.arrange.ArrangeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("arrangeRecordDao")
public class ArrangeRecordDaoImpl extends GenericDaoImpl implements ArrangeRecordDao {

    public Long addArrangeRecord(ArrangeRecord arrangeRecord) {

        return this.insert("ArrangeRecordMapper.addArrangeRecord", arrangeRecord);
    }

    /*public java.lang.Long addArrangeRecord(ArrangeRecord arrangeRecord) {
        return this.insert("ArrangeRecordMapper.createArrangeRecord",arrangeRecord);
    }
*/

    /**
     * 批量添加ArrangeRecord
     */
    public void batchAddArrangeTecord(List<ArrangeRecord> arrangeRecords) {
        this.insert("ArrangeRecordMapper.batchAddArrangeTecord", arrangeRecords);
    }

    public void deleteArrangeRecordById(Long id) {
        this.delete("ArrangeRecordMapper.deleteArrangeRecordById", id);
    }

    public void updateArrangeRecord(ArrangeRecord arrangeRecord) {
        this.update("ArrangeRecordMapper.updateArrangeRecord", arrangeRecord);
    }

    public List<ArrangeRecord> getArrangeRecorduserId(Long userid) {
        return this.selectList("ArrangeRecordMapper.getArrangeRecorduserId", userid);
    }

    /**
     * 我的任务
     */
    public List<ArrangeRecord> getArrangeRecordList(ArrangeRecord arrangeRecord, PageEntity page) {
        return this.queryForListPage("ArrangeRecordMapper.getArrangeRecordList", arrangeRecord, page);
    }


    public List<ArrangeRecord> getGroudArrange(ArrangeRecord arrangeRecord) {
        // TODO Auto-generated method stub
        return this.selectList("ArrangeRecordMapper.getGroudArrange", arrangeRecord);
    }


    @Override
    public ArrangeRecord getArrangeRecordById(Long id) {
        return this.selectOne("ArrangeRecordMapper.getArrangeRecordById", id);
    }

    /**
     * 删除任务下的记录
     */
    public void deleteArrangeRecoredId(Long taskId) {
        this.delete("ArrangeRecordMapper.deleteArrangeRecoredId", taskId);
    }

    /**
     * 根据部门和任务ID查出详细信息
     */
    @Override
    public List<ArrangeRecord> getArrangeRecordGroudUsers(ArrangeRecord arrangeRecord) {
        // TODO Auto-generated method stub
        return this.selectList("ArrangeRecordMapper.getArrangeRecordGroudUsers", arrangeRecord);
    }

    @Override
    public ArrangeRecord getuserArrangeid(ArrangeRecord ArrangeRecord) {
        // TODO Auto-generated method stub
        return this.selectOne("ArrangeRecordMapper.getuserArrangeid", ArrangeRecord);
    }

    @Override
    public ArrangeRecord getGrouduserid(Long userid) {
        // TODO Auto-generated method stub
        return this.selectOne("ArrangeRecordMapper.getGrouduserid", userid);
    }

    @Override
    public Long getgruopArrange(Long groupid) {
        // TODO Auto-generated method stub
        return this.selectOne("ArrangeRecordMapper.getgruopArrange", groupid);
    }

    @Override
    public Long getcountgruopArrange(Long groupid) {
        // TODO Auto-generated method stub
        return this.selectOne("ArrangeRecordMapper.getcountgruopArrange", groupid);
    }

    @Override
    public Long getIscompleteArrange(Long groupid) {
        // TODO Auto-generated method stub
        return this.selectOne("ArrangeRecordMapper.getIscompleteArrange", groupid);
    }

    /**
     * 批量完成考试，根据考试任务idlist
     *
     * @param arrangeList
     */
    public void updateArrangeRecordStatusBatch(List<Arrange> arrangeList) {
        this.update("ArrangeRecordMapper.updateArrangeRecordStatusBatch", arrangeList);
    }

    /**
     * 提交考试安排修改考试记录信息
     *
     * @param arrangeRecord
     */
    public void updateArrangeRecordBySubmit(ArrangeRecord arrangeRecord) {
        this.update("ArrangeRecordMapper.updateArrangeRecordBySubmit", arrangeRecord);
    }

    /**
     * 获取ArrangeRecordAndArrange
     *
     * @param arrangeRecordId
     * @return
     */
    public ArrangeRecord getArrangeRecordAndArrangeById(Long arrangeRecordId) {
        return this.selectOne("ArrangeRecordMapper.getArrangeRecordAndArrangeById", arrangeRecordId);
    }

}