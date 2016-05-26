package io.wangxiao.edu.home.service.task;

import io.wangxiao.edu.home.entity.task.TaskMiddleGroup;

import java.util.List;

/**
 * TaskMiddleGroup管理接口
 */
public interface TaskMiddleGroupService {

    /**
     * 添加TaskMiddleGroup
     *
     * @param taskMiddleGroup 要添加的TaskMiddleGroup
     * @return id
     */
    java.lang.Long addTaskMiddleGroup(TaskMiddleGroup taskMiddleGroup);

    /**
     * 批量添加部门
     *
     * @param taskMiddleGroups
     * @return
     */
    void batchAddTaskMiddleGroup(List<TaskMiddleGroup> taskMiddleGroups);

    /**
     * 根据id删除一个TaskMiddleGroup
     *
     * @param id 要删除的id
     */
    void deleteTaskMiddleGroupById(Long id);

    /**
     * 修改TaskMiddleGroup
     *
     * @param taskMiddleGroup 要修改的TaskMiddleGroup
     */
    void updateTaskMiddleGroup(TaskMiddleGroup taskMiddleGroup);

    /**
     * 根据id获取单个TaskMiddleGroup对象
     *
     * @param id 要查询的id
     * @return TaskMiddleGroup
     */
    TaskMiddleGroup getTaskMiddleGroupById(Long id);

    /**
     * 根据条件获取TaskMiddleGroup列表
     *
     * @param taskMiddleGroup 查询条件
     * @return List<TaskMiddleGroup>
     */
    List<TaskMiddleGroup> getTaskMiddleGroupList(TaskMiddleGroup taskMiddleGroup);

    /**
     * 任务下的部门
     *
     * @param taskMiddleGroup
     * @return
     */
    List<TaskMiddleGroup> getTaskGroupList(TaskMiddleGroup taskMiddleGroup);
}