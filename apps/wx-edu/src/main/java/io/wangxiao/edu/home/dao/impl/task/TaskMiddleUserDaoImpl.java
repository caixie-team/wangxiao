package io.wangxiao.edu.home.dao.impl.task;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.task.TaskMiddleUserDao;
import io.wangxiao.edu.home.entity.task.TaskMiddleUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskMiddleUserDao")
public class TaskMiddleUserDaoImpl extends GenericDaoImpl implements TaskMiddleUserDao {

    public Long addTaskMiddleUser(TaskMiddleUser taskMiddleUser) {
        return this.insert("TaskMiddleUserMapper.createTaskMiddleUser", taskMiddleUser);
    }

    /**
     * 批量添加员工
     */
    public void batchAddTaskMiddleUser(List<TaskMiddleUser> taskMiddleUsers) {
        this.insert("TaskMiddleUserMapper.batchAddTaskMiddleUser", taskMiddleUsers);
    }

    public void deleteTaskMiddleUserById(Long id) {
        this.delete("TaskMiddleUserMapper.deleteTaskMiddleUserById", id);
    }

    public void updateTaskMiddleUser(TaskMiddleUser taskMiddleUser) {
        this.update("TaskMiddleUserMapper.updateTaskMiddleUser", taskMiddleUser);
    }

    public TaskMiddleUser getTaskMiddleUserById(Long id) {
        return this.selectOne("TaskMiddleUserMapper.getTaskMiddleUserById", id);
    }

    public List<TaskMiddleUser> getTaskMiddleUserList(TaskMiddleUser taskMiddleUser) {
        return this.selectList("TaskMiddleUserMapper.getTaskMiddleUserList", taskMiddleUser);
    }

    /**
     * 任务下的员工
     */
    public List<TaskMiddleUser> getTaakUserById(TaskMiddleUser taskMiddleUser) {
        return this.selectList("TaskMiddleUserMapper.getTaakUserById", taskMiddleUser);
    }
}
