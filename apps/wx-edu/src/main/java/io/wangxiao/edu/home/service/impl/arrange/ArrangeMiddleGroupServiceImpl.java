package io.wangxiao.edu.home.service.impl.arrange;

import io.wangxiao.edu.home.dao.arrange.ArrangeMiddleGroupDao;
import io.wangxiao.edu.home.entity.arrange.ArrangeMiddleGroup;
import io.wangxiao.edu.home.service.arrange.ArrangeMiddleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ArrangeMiddleGroup管理接口
 */
@Service("arrangeMiddleGroupService")
public class ArrangeMiddleGroupServiceImpl implements ArrangeMiddleGroupService {

    @Autowired
    private ArrangeMiddleGroupDao arrangeMiddleGroupDao;

    /**
     * 添加ArrangeMiddleGroup
     *
     * @param arrangeMiddleGroup 要添加的ArrangeMiddleGroup
     * @return id
     */
    public java.lang.Long addArrangeMiddleGroup(ArrangeMiddleGroup arrangeMiddleGroup) {
        return arrangeMiddleGroupDao.addArrangeMiddleGroup(arrangeMiddleGroup);
    }

    /**
     * 批量添加部门
     */
    public void batchAddArrangeMiddleGroup(List<ArrangeMiddleGroup> arrangeMiddleGroups) {
        arrangeMiddleGroupDao.batchAddArrangeMiddleGroup(arrangeMiddleGroups);
    }

    /**
     * 根据id删除一个ArrangeMiddleGroup
     *
     * @param id 要删除的id
     */
    public void deleteArrangeMiddleGroupById(Long id) {
        arrangeMiddleGroupDao.deleteArrangeMiddleGroupById(id);
    }

    /**
     * 修改ArrangeMiddleGroup
     *
     * @param arrangeMiddleGroup 要修改的ArrangeMiddleGroup
     */
    public void updateArrangeMiddleGroup(ArrangeMiddleGroup arrangeMiddleGroup) {
        arrangeMiddleGroupDao.updateArrangeMiddleGroup(arrangeMiddleGroup);
    }

    /**
     * 根据id获取单个ArrangeMiddleGroup对象
     *
     * @param id 要查询的id
     * @return ArrangeMiddleGroup
     */
    public ArrangeMiddleGroup getArrangeMiddleGroupById(Long id) {
        return arrangeMiddleGroupDao.getArrangeMiddleGroupById(id);
    }

    /**
     * 根据条件获取ArrangeMiddleGroup列表
     *
     * @param arrangeMiddleGroup 查询条件
     * @return List<ArrangeMiddleGroup>
     */
    public List<ArrangeMiddleGroup> getArrangeMiddleGroupList(ArrangeMiddleGroup arrangeMiddleGroup) {
        return arrangeMiddleGroupDao.getArrangeMiddleGroupList(arrangeMiddleGroup);
    }

    /**
     * 任务下的部门
     */
    public List<ArrangeMiddleGroup> getArrangeGroupList(ArrangeMiddleGroup arrangeMiddleGroup) {
        return this.arrangeMiddleGroupDao.getArrangeGroupList(arrangeMiddleGroup);
    }

}