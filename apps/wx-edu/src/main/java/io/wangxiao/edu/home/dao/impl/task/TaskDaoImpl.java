package io.wangxiao.edu.home.dao.impl.task;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.task.TaskDao;
import io.wangxiao.edu.home.entity.task.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskDao")
public class TaskDaoImpl extends GenericDaoImpl implements TaskDao {
    public Long addTask(Task task) {
        return this.insert("TaskMapper.createTask", task);
    }

    public void deleteTaskById(Long id) {
        this.delete("TaskMapper.deleteTaskById", id);
    }

    public java.lang.Long updateTask(Task task) {
        return this.update("TaskMapper.updateTask", task);
    }

    public Task getTaskById(Long id) {
        return this.selectOne("TaskMapper.getTaskById", id);
    }

    public Task getTask(Task task) {
        return this.selectOne("TaskMapper.getTask", task);
    }

    public List<Task> getTaskList(Task task, PageEntity page) {
        return this.queryForListPage("TaskMapper.getTaskList", task, page);
    }

    /**
     * 我的部门任务
     */
    public List<Task> getGroupTaskList(Task task, PageEntity page) {
        return this.queryForListPage("TaskMapper.getGroupTaskList", task, page);
    }

    /**
     * 任务列表详情
     */
    public List<Task> taskDetailsById(Task task, PageEntity page) {
        return this.queryForListPage("TaskMapper.taskDetailsById", task, page);
    }

    /**
     * 更改任务状态
     */
    public void updateTaskStatus(Task task) {
        this.update("TaskMapper.updateTaskStatus", task);
    }

    /**
     * 前台我的任务
     */
    public List<Task> myTaskForWeb(Task task, PageEntity page) {
        return this.queryForListPage("TaskMapper.myTaskForWeb", task, page);
    }

    /**
     * 前台我的部门任务
     */
    public List<Task> myGroupTaskFroweb(Task task, PageEntity page) {
        return this.queryForListPage("TaskMapper.myGroupTaskFroweb", task, page);
    }

}
