package io.wangxiao.edu.home.dao.impl.arrange;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.arrange.ArrangeMiddleGroupDao;
import io.wangxiao.edu.home.entity.arrange.ArrangeMiddleGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("arrangeMiddleGroupDao")
public class ArrangeMiddleGroupDaoImpl extends GenericDaoImpl implements ArrangeMiddleGroupDao {

    public Long addArrangeMiddleGroup(ArrangeMiddleGroup arrangeMiddleGroup) {
        return this.insert("ArrangeMiddleGroupMapper.createArrangeMiddleGroup", arrangeMiddleGroup);
    }

    /**
     * 批量添加部门
     */
    public void batchAddArrangeMiddleGroup(List<ArrangeMiddleGroup> arrangeMiddleGroups) {
        this.insert("ArrangeMiddleGroupMapper.batchAddArrangeMiddleGroup", arrangeMiddleGroups);
    }

    public void deleteArrangeMiddleGroupById(Long id) {
        this.delete("ArrangeMiddleGroupMapper.deleteArrangeMiddleGroupById", id);
    }

    public void updateArrangeMiddleGroup(ArrangeMiddleGroup arrangeMiddleGroup) {
        this.update("ArrangeMiddleGroupMapper.updateArrangeMiddleGroup", arrangeMiddleGroup);
    }

    public ArrangeMiddleGroup getArrangeMiddleGroupById(Long id) {
        return this.selectOne("ArrangeMiddleGroupMapper.getArrangeMiddleGroupById", id);
    }

    public List<ArrangeMiddleGroup> getArrangeMiddleGroupList(ArrangeMiddleGroup arrangeMiddleGroup) {
        return this.selectList("ArrangeMiddleGroupMapper.getArrangeMiddleGroupList", arrangeMiddleGroup);
    }

    /**
     * 任务下的部门
     */
    public List<ArrangeMiddleGroup> getArrangeGroupList(ArrangeMiddleGroup arrangeMiddleGroup) {
        return this.selectList("ArrangeMiddleGroupMapper.getArrangeGroupList", arrangeMiddleGroup);
    }
}
