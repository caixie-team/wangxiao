
package io.wangxiao.edu.home.service.impl.task;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.edu.home.dao.task.TaskRecordDao;
import io.wangxiao.edu.home.entity.task.TaskRecord;
import io.wangxiao.edu.home.service.task.TaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskRecordService")
public class TaskRecordServiceImpl implements TaskRecordService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private TaskRecordDao taskRecordDao;

    /**
     * 添加TaskRecord
     *
     * @param taskRecord 要添加的TaskRecord
     * @return id
     */
    @Override
    public Long addTaskRecord(TaskRecord taskRecord) {
        // TODO Auto-generated method stub
        return taskRecordDao.addTaskRecord(taskRecord);
    }

    @Override
    public TaskRecord getTaskRecordByTaskRecord(TaskRecord taskRecord) {
        List<TaskRecord> taskRecordList = taskRecordDao.getTaskRecordList(taskRecord, new PageEntity());
        if (taskRecordList.size() > 0) {
            return taskRecordList.get(0);
        }
        return null;
    }
    /*
     * public java.lang.Long addTaskRecord(TaskRecord taskRecord){ return
     * taskRecordDao.addTaskRecord(taskRecord); }
     */

    /**
     * 批量添加TaskRecord
     */
    public void batchAddTaskTecord(List<TaskRecord> taskRecords) {
        this.taskRecordDao.batchAddTaskTecord(taskRecords);
    }


    /**
     * 根据部门和任务ID查出分数
     */
    public List<TaskRecord> getGroudTask(TaskRecord taskRecord) {

        return taskRecordDao.getGroudTask(taskRecord);

    }

    ;

    /**
     * 根据id删除一个TaskRecord
     *
     * @param id 要删除的id
     */
    public void deleteTaskRecordById(Long id) {
        taskRecordDao.deleteTaskRecordById(id);
    }

    /**
     * 修改TaskRecord
     *
     * @param taskRecord 要修改的TaskRecord
     */
    public void updateTaskRecord(TaskRecord taskRecord) {
        taskRecordDao.updateTaskRecord(taskRecord);
    }

    /**
     * 根据id获取单个TaskRecord对象
     *
     * @param id 要查询的id
     * @return TaskRecord
     */
    public TaskRecord getTaskRecordById(Long id) {
        return taskRecordDao.getTaskRecordById(id);
    }

    /**
     * 根据条件获取TaskRecord列表
     *
     * @param taskRecord 查询条件
     * @return List<TaskRecord>
     */
    public List<TaskRecord> getTaskRecordList(TaskRecord taskRecord, PageEntity page) {
        return taskRecordDao.getTaskRecordList(taskRecord, page);
    }

    @Override
    public List<TaskRecord> getTaskRecorduserId(Long userid) {
        return taskRecordDao.getTaskRecorduserId(userid);
    }

    /**
     * 删除任务下的记录
     */
    public void deleteTaskRecoredId(Long taskId) {
        this.taskRecordDao.deleteTaskRecoredId(taskId);
    }

    @Override
    public List<TaskRecord> getTaskRecordList(TaskRecord taskRecord) {
        return null;
    }

    @Override
    public List<TaskRecord> getTaskRecordGroudUsers(TaskRecord taskRecord) {
        return taskRecordDao.getTaskRecordGroudUsers(taskRecord);
    }

    @Override
    public TaskRecord getusertaskid(TaskRecord taskRecord) {
        return taskRecordDao.getusertaskid(taskRecord);
    }

    @Override
    public TaskRecord getGrouduserid(Long userid) {
        return taskRecordDao.getGrouduserid(userid);
    }

    @Override
    public Long getcountgruoptask(Long groupid) {
        return taskRecordDao.getcountgruoptask(groupid);
    }

    @Override
    public Long getgruoptask(Long groupid) {
        return taskRecordDao.getgruoptask(groupid);
    }

}
