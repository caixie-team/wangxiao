package io.wangxiao.edu.home.dao.task;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.task.Task;

import java.util.List;

/**
 * Task管理接口
 */
public interface TaskDao {

    /**
     * 添加Task
     *
     * @param task 要添加的Task
     * @return id
     */
    java.lang.Long addTask(Task task);

    /**
     * 根据id删除一个Task
     *
     * @param id 要删除的id
     */
    void deleteTaskById(Long id);

    /**
     * 修改Task
     *
     * @param task 要修改的Task
     */
    java.lang.Long updateTask(Task task);

    /**
     * 根据id获取单个Task对象
     *
     * @param id 要查询的id
     * @return Task
     */
    Task getTaskById(Long id);

    Task getTask(Task task);

    /**
     * 根据条件获取Task列表
     *
     * @param task 查询条件
     * @return List<Task>
     */
    List<Task> getTaskList(Task task, PageEntity page);

    /**
     * 我的部门任务
     *
     * @param task
     * @param page
     * @return
     */
    List<Task> getGroupTaskList(Task task, PageEntity page);

    /**
     * 任务列表详情
     *
     * @param task
     * @param page
     * @return
     */
    List<Task> taskDetailsById(Task task, PageEntity page);

    /**
     * 更改任务状态
     */
    void updateTaskStatus(Task task);

    /**
     * 前台我的任务
     *
     * @param userId
     * @param page
     * @return
     */
    List<Task> myTaskForWeb(Task task, PageEntity page);

    /**
     * 前台我的部门任务
     *
     * @param task
     * @param page
     * @return
     */
    List<Task> myGroupTaskFroweb(Task task, PageEntity page);
}