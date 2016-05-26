
package io.wangxiao.edu.home.service.task;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.task.TaskRecord;

import java.util.List;

/**
 * TaskRecord管理接口
 */
public interface TaskRecordService {

    /**
     * 添加TaskRecord
     *
     * @param taskRecord 要添加的TaskRecord
     * @return id
     */
    java.lang.Long addTaskRecord(TaskRecord taskRecord);
    /*public java.lang.Long addTaskRecord(TaskRecord taskRecord);*/

    /**
     * 批量添加TaskRecord
     *
     * @param taskRecords
     */
    void batchAddTaskTecord(List<TaskRecord> taskRecords);

    /**
     * 根据id删除一个TaskRecord
     *
     * @param id 要删除的id
     */
    void deleteTaskRecordById(Long id);

    /**
     * 修改TaskRecord
     *
     * @param taskRecord 要修改的TaskRecord
     */
    void updateTaskRecord(TaskRecord taskRecord);

    /**
     * 根据id获取单个TaskRecord对象
     *
     * @param id 要查询的id
     * @return TaskRecord
     */
    TaskRecord getTaskRecordById(Long id);

    /**
     * 根据条件获取TaskRecord列表
     *
     * @param taskRecord 查询条件
     * @return List<TaskRecord>
     */
    List<TaskRecord> getTaskRecordList(TaskRecord taskRecord, PageEntity page);

    List<TaskRecord> getTaskRecordList(TaskRecord taskRecord);


    /**
     * 根据条件获取TaskRecord列表
     *
     * @param taskRecord 查询条件
     * @return taskRecord
     */
    TaskRecord getTaskRecordByTaskRecord(TaskRecord taskRecord);

    List<TaskRecord> getTaskRecorduserId(Long userid);


    /**
     * 根据部门和任务ID查出分数
     */
    List<TaskRecord> getGroudTask(TaskRecord taskRecord);

    /**
     * 删除任务下的记录
     *
     * @param taskId
     */
    void deleteTaskRecoredId(Long taskId);

    /**
     * 根据部门和任务ID查出详细信息
     */
    List<TaskRecord> getTaskRecordGroudUsers(TaskRecord taskRecord);

    /**
     * 根据taskid和userid查出id
     */
    TaskRecord getusertaskid(TaskRecord taskRecord);

    /**
     * 根据userid查出部门（一个人对应一个部门的基础前提下）
     */
    TaskRecord getGrouduserid(Long userid);

    /**
     * 根据gruopid查出部门下有多少个任务(已完成)
     */
    Long getgruoptask(Long groupid);

    /**
     * 根据gruopid查出部门下有多少个任务（总数）
     */
    Long getcountgruoptask(Long groupid);

}
