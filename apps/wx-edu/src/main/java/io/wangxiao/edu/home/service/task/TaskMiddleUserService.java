package io.wangxiao.edu.home.service.task;

import io.wangxiao.edu.home.entity.task.TaskMiddleUser;

import java.util.List;

public interface TaskMiddleUserService {

    /**
     * 添加TaskMiddleUser
     *
     * @param taskMiddleUser 要添加的TaskMiddleUser
     * @return id
     */
    java.lang.Long addTaskMiddleUser(TaskMiddleUser taskMiddleUser);

    /**
     * 批量添加员工
     */
    void batchAddTaskMiddleUser(List<TaskMiddleUser> taskMiddleUsers);

    /**
     * 根据id删除一个TaskMiddleUser
     *
     * @param id 要删除的id
     */
    void deleteTaskMiddleUserById(Long id);

    /**
     * 修改TaskMiddleUser
     *
     * @param taskMiddleUser 要修改的TaskMiddleUser
     */
    void updateTaskMiddleUser(TaskMiddleUser taskMiddleUser);

    /**
     * 根据id获取单个TaskMiddleUser对象
     *
     * @param id 要查询的id
     * @return TaskMiddleUser
     */
    TaskMiddleUser getTaskMiddleUserById(Long id);

    /**
     * 根据条件获取TaskMiddleUser列表
     *
     * @param taskMiddleUser 查询条件
     * @return List<TaskMiddleUser>
     */
    List<TaskMiddleUser> getTaskMiddleUserList(TaskMiddleUser taskMiddleUser);

    /**
     * 任务下的员工
     *
     * @param taskMiddleUser
     * @return
     */
    List<TaskMiddleUser> getTaakUserById(TaskMiddleUser taskMiddleUser);
}