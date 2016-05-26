package io.wangxiao.edu.home.service.impl.task;

import io.wangxiao.edu.home.dao.task.TaskMiddleGroupDao;
import io.wangxiao.edu.home.entity.task.TaskMiddleGroup;
import io.wangxiao.edu.home.service.task.TaskMiddleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TaskMiddleGroup管理接口
 */
@Service("taskMiddleGroupService")
public class TaskMiddleGroupServiceImpl implements TaskMiddleGroupService {

    @Autowired
    private TaskMiddleGroupDao taskMiddleGroupDao;

    /**
     * 添加TaskMiddleGroup
     *
     * @param taskMiddleGroup 要添加的TaskMiddleGroup
     * @return id
     */
    public java.lang.Long addTaskMiddleGroup(TaskMiddleGroup taskMiddleGroup) {
        return taskMiddleGroupDao.addTaskMiddleGroup(taskMiddleGroup);
    }

    /**
     * 批量添加部门
     */
    public void batchAddTaskMiddleGroup(List<TaskMiddleGroup> taskMiddleGroups) {
        taskMiddleGroupDao.batchAddTaskMiddleGroup(taskMiddleGroups);
    }

    /**
     * 根据id删除一个TaskMiddleGroup
     *
     * @param id 要删除的id
     */
    public void deleteTaskMiddleGroupById(Long id) {
        taskMiddleGroupDao.deleteTaskMiddleGroupById(id);
    }

    /**
     * 修改TaskMiddleGroup
     *
     * @param taskMiddleGroup 要修改的TaskMiddleGroup
     */
    public void updateTaskMiddleGroup(TaskMiddleGroup taskMiddleGroup) {
        taskMiddleGroupDao.updateTaskMiddleGroup(taskMiddleGroup);
    }

    /**
     * 根据id获取单个TaskMiddleGroup对象
     *
     * @param id 要查询的id
     * @return TaskMiddleGroup
     */
    public TaskMiddleGroup getTaskMiddleGroupById(Long id) {
        return taskMiddleGroupDao.getTaskMiddleGroupById(id);
    }

    /**
     * 根据条件获取TaskMiddleGroup列表
     *
     * @param taskMiddleGroup 查询条件
     * @return List<TaskMiddleGroup>
     */
    public List<TaskMiddleGroup> getTaskMiddleGroupList(TaskMiddleGroup taskMiddleGroup) {
        return taskMiddleGroupDao.getTaskMiddleGroupList(taskMiddleGroup);
    }

    /**
     * 任务下的部门
     */
    public List<TaskMiddleGroup> getTaskGroupList(TaskMiddleGroup taskMiddleGroup) {
        return this.taskMiddleGroupDao.getTaskGroupList(taskMiddleGroup);
    }

}