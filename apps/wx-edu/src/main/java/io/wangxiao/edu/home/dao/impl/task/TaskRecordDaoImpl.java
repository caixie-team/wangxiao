
package io.wangxiao.edu.home.dao.impl.task;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.task.TaskRecordDao;
import io.wangxiao.edu.home.entity.task.TaskRecord;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("taskRecordDao")
public class TaskRecordDaoImpl extends GenericDaoImpl implements TaskRecordDao {

    public Long addTaskRecord(TaskRecord taskRecord) {

        return this.insert("TaskRecordMapper.addTaskRecord", taskRecord);
    }

    /*public java.lang.Long addTaskRecord(TaskRecord taskRecord) {
        return this.insert("TaskRecordMapper.createTaskRecord",taskRecord);
    }
*/

    /**
     * 批量添加TaskRecord
     */
    public void batchAddTaskTecord(List<TaskRecord> taskRecords) {
        this.insert("TaskRecordMapper.batchAddTaskTecord", taskRecords);
    }

    public void deleteTaskRecordById(Long id) {
        this.delete("TaskRecordMapper.deleteTaskRecordById", id);
    }

    public void updateTaskRecord(TaskRecord taskRecord) {
        this.update("TaskRecordMapper.updateTaskRecord", taskRecord);
    }

    public List<TaskRecord> getTaskRecorduserId(Long userid) {
        return this.selectList("TaskRecordMapper.getTaskRecorduserId", userid);
    }

    /**
     * 我的任务
     */
    public List<TaskRecord> getTaskRecordList(TaskRecord taskRecord, PageEntity page) {
        return this.queryForListPage("TaskRecordMapper.getTaskRecordList", taskRecord, page);
    }


    public List<TaskRecord> getGroudTask(TaskRecord taskRecord) {
        // TODO Auto-generated method stub
        return this.selectList("TaskRecordMapper.getGroudTask", taskRecord);
    }


    @Override
    public TaskRecord getTaskRecordById(Long id) {
        return this.selectOne("TaskRecordMapper.getTaskRecordById", id);
    }

    /**
     * 删除任务下的记录
     */
    public void deleteTaskRecoredId(Long taskId) {
        this.delete("TaskRecordMapper.deleteTaskRecoredId", taskId);
    }

    /**
     * 根据部门和任务ID查出详细信息
     */
    @Override
    public List<TaskRecord> getTaskRecordGroudUsers(TaskRecord taskRecord) {
        return this.selectList("TaskRecordMapper.getTaskRecordGroudUsers", taskRecord);
    }

    @Override
    public TaskRecord getusertaskid(TaskRecord taskRecord) {
        return this.selectOne("TaskRecordMapper.getusertaskid", taskRecord);
    }

    @Override
    public TaskRecord getGrouduserid(Long userid) {
        return this.selectOne("TaskRecordMapper.getGrouduserid", userid);
    }

    @Override
    public Long getcountgruoptask(Long groupid) {
        return this.selectOne("TaskRecordMapper.getcountgruoptask", groupid);
    }

    @Override
    public Long getgruoptask(Long groupid) {
        return this.selectOne("TaskRecordMapper.getgruoptask", groupid);
    }

}