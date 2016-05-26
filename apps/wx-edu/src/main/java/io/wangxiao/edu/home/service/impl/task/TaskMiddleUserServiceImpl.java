package io.wangxiao.edu.home.service.impl.task;

import io.wangxiao.edu.home.dao.task.TaskMiddleUserDao;
import io.wangxiao.edu.home.entity.task.TaskMiddleUser;
import io.wangxiao.edu.home.service.task.TaskMiddleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskMiddleUserService")
public class TaskMiddleUserServiceImpl implements TaskMiddleUserService {

    @Autowired
    private TaskMiddleUserDao taskMiddleUserDao;

    /**
     * 添加TaskMiddleUser
     *
     * @param taskMiddleUser 要添加的TaskMiddleUser
     * @return id
     */
    public java.lang.Long addTaskMiddleUser(TaskMiddleUser taskMiddleUser) {
        return taskMiddleUserDao.addTaskMiddleUser(taskMiddleUser);
    }

    /**
     * 批量添加员工
     */
    public void batchAddTaskMiddleUser(List<TaskMiddleUser> taskMiddleUsers) {
        this.taskMiddleUserDao.batchAddTaskMiddleUser(taskMiddleUsers);
    }

    /**
     * 根据id删除一个TaskMiddleUser
     *
     * @param id 要删除的id
     */
    public void deleteTaskMiddleUserById(Long id) {
        taskMiddleUserDao.deleteTaskMiddleUserById(id);
    }

    /**
     * 修改TaskMiddleUser
     *
     * @param taskMiddleUser 要修改的TaskMiddleUser
     */
    public void updateTaskMiddleUser(TaskMiddleUser taskMiddleUser) {
        taskMiddleUserDao.updateTaskMiddleUser(taskMiddleUser);
    }

    /**
     * 根据id获取单个TaskMiddleUser对象
     *
     * @param id 要查询的id
     * @return TaskMiddleUser
     */
    public TaskMiddleUser getTaskMiddleUserById(Long id) {
        return taskMiddleUserDao.getTaskMiddleUserById(id);
    }

    /**
     * 根据条件获取TaskMiddleUser列表
     *
     * @param taskMiddleUser 查询条件
     * @return List<TaskMiddleUser>
     */
    public List<TaskMiddleUser> getTaskMiddleUserList(TaskMiddleUser taskMiddleUser) {
        return taskMiddleUserDao.getTaskMiddleUserList(taskMiddleUser);
    }

    /**
     * 任务下的员工
     */
    public List<TaskMiddleUser> getTaakUserById(TaskMiddleUser taskMiddleUser) {
        return this.taskMiddleUserDao.getTaakUserById(taskMiddleUser);
    }

}