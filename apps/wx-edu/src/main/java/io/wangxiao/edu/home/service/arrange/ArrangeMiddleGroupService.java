package io.wangxiao.edu.home.service.arrange;

import io.wangxiao.edu.home.entity.arrange.ArrangeMiddleGroup;

import java.util.List;

/**
 * ArrangeMiddleGroup管理接口
 */
public interface ArrangeMiddleGroupService {

    /**
     * 添加ArrangeMiddleGroup
     *
     * @param arrangeMiddleGroup 要添加的ArrangeMiddleGroup
     * @return id
     */
    java.lang.Long addArrangeMiddleGroup(ArrangeMiddleGroup arrangeMiddleGroup);

    /**
     * 批量添加部门
     *
     * @param arrangeMiddleGroups
     * @return
     */
    void batchAddArrangeMiddleGroup(List<ArrangeMiddleGroup> arrangeMiddleGroups);

    /**
     * 根据id删除一个ArrangeMiddleGroup
     *
     * @param id 要删除的id
     */
    void deleteArrangeMiddleGroupById(Long id);

    /**
     * 修改ArrangeMiddleGroup
     *
     * @param arrangeMiddleGroup 要修改的ArrangeMiddleGroup
     */
    void updateArrangeMiddleGroup(ArrangeMiddleGroup arrangeMiddleGroup);

    /**
     * 根据id获取单个ArrangeMiddleGroup对象
     *
     * @param id 要查询的id
     * @return ArrangeMiddleGroup
     */
    ArrangeMiddleGroup getArrangeMiddleGroupById(Long id);

    /**
     * 根据条件获取ArrangeMiddleGroup列表
     *
     * @param arrangeMiddleGroup 查询条件
     * @return List<ArrangeMiddleGroup>
     */
    List<ArrangeMiddleGroup> getArrangeMiddleGroupList(ArrangeMiddleGroup arrangeMiddleGroup);

    /**
     * 任务下的部门
     *
     * @param arrangeMiddleGroup
     * @return
     */
    List<ArrangeMiddleGroup> getArrangeGroupList(ArrangeMiddleGroup arrangeMiddleGroup);
}