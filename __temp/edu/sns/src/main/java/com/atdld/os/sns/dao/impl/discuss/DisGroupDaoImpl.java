package com.atdld.os.sns.dao.impl.discuss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.sns.dao.discuss.DisGroupDao;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.entity.discuss.DisMember;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.impl.discuss.DisGroupDaoImpl
 * @description 小组的Daoimpl
 * @Create Date : 2013-12-11 下午1:42:29
 */
@Repository("disGroupDao")
public class DisGroupDaoImpl extends GenericDaoImpl implements DisGroupDao {

    /**
     * 创建小组
     *
     * @param disGroup
     */
    public Long addDisGroup(DisGroup disGroup) {
        return this.insert("DisGroupMapper.addDisGroup", disGroup);

    }

    /**
     * 分页查询所有小组
     *
     * @param pageEntity 分页的参数
     * @return List<DisGroup> 所有小组 list
     */
    public List<DisGroup> queryDisGroupALLList(PageEntity pageEntity) {
        return this.queryForListPage("DisGroupMapper.queryDisGroupALLList", null, pageEntity);

    }

    /**
     * 分页查询热门小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup> 热门小组
     */
    public List<DisGroup> queryHotDisGroupList(PageEntity pageEntity) {
        return this.queryForListPage("DisGroupMapper.queryHotDisGroupList", null, pageEntity);
    }

    /**
     * 分页查询我加入的小组
     *
     * @param disMember  用户id
     * @param pageEntity 分页参数
     * @return List<DisGroup> 我加入小组 list
     */
    public List<DisGroup> queryMyJoinDisGroupList(DisMember disMember, PageEntity pageEntity) {
        return this.queryForListPage("DisGroupMapper.queryMyJoinDisGroupList", disMember, pageEntity);
    }

    /**
     * 查询我创建的小组
     *
     * @param cusId 用户的Id
     * @return List<DisGroup> 我的小组 list
     */
    public List<DisGroup> queryMyDisGroupList(Long cusId) {
        return this.selectList("DisGroupMapper.queryMyDisGroupList", cusId);
    }

    /**
     * 查询小组的信息
     *
     * @param groudId 小组的Id
     * @return DisGroup
     */
    public DisGroup queryDisGroupById(Long groudId) {
        List<DisGroup> list = this.selectList("DisGroupMapper.queryDisGroupById", groudId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询小组的分类
     *
     * @return List<DisGroupList> 小组分类 list
     */
    public List<DisGroupClassify> querydisGroupList() {
        return this.selectList("DisGroupMapper.querydisGroupList", null);
    }

    /**
     * 查找我创建的小组通过手机号限制,只返回行数
     *
     * @param disGroup 小组用户Id
     * @return
     */
    public Integer queryMyDisGroupByCusId(DisGroup disGroup) {
        List<Integer> integerList = this.selectList("DisGroupMapper.queryMyDisGroupByCusId", disGroup);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return null;
    }

    /**
     * 查询置顶小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup>
     */
    public List<DisGroup> queryDisGroupTop(PageEntity pageEntity) {
        return this.queryForListPage("DisGroupMapper.queryDisGroupTop", null, pageEntity);
    }

    /**
     * 设置置顶小组根据TOP
     *
     * @param disGroup 小组Id
     */
    public void updateDisGroupTop(DisGroup disGroup) {
        this.update("DisGroupMapper.updateDisGroupTop", disGroup);
    }

    /**
     * 转让小组
     *
     * @param disMember
     */
    public void updateDisGroupTransfer(DisMember disMember) {
        this.update("DisGroupMapper.updateDisGroupTransfer", disMember);
    }

    /**
     * 小组首页排行 按照活跃度排行， 活跃度指标包括小组成员人数、文章数目、回复数目
     *
     * @param activity活跃度 groupId小组Id
     */
    public void updateDisGroupActivity(DisGroup disGroup) {
        this.update("DisGroupMapper.updateDisGroupActivity", disGroup);

    }

    /**
     * 小组文章数量统计+1
     *
     * @param groupId 小组id
     * @param count   +1
     */
    public void updateArticleCount(Long groupId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupId", groupId);
        map.put("count", count);
        this.update("DisGroupMapper.updateArticleCount", map);
    }

    /**
     * 小组文章数量统计-1
     *
     * @param groupId小组的id
     */
    public void updateArticleCountsReduce(Long groupId) {
        this.update("DisGroupMapper.updateArticleCountsReduce", groupId);
    }

    /**
     * 小组首页排行 按照活跃度排行
     *
     * @return List<DisGroup> 按照活跃度排的list
     */
    public List<DisGroup> queryHomePageDisGroup() {
        return this.selectList("DisGroupMapper.queryHomePageDisGroup", null);
    }

    /**
     * 判断是否加入该小组
     *
     * @param disMember 用户id 小组id
     * @return
     */
    public Integer queryIsJoin(DisMember disMember) {
        List<Integer> integerList = this.selectList("DisGroupMapper.queryIsJoin", disMember);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 后台添加小组分类
     *
     * @param disGroupClassify
     */
    public void addDisGroupClassify(DisGroupClassify disGroupClassify) {
        this.insert("DisGroupMapper.addDisGroupClassify", disGroupClassify);

    }

    /**
     * 删除小组分类
     *
     * @param classifyId 小组分类id
     */
    public Long deleteDisGroupClassify(Long classifyId) {
        return this.delete("DisGroupMapper.deleteDisGroupClassify", classifyId);

    }

    /**
     * 根据分类id查询
     *
     * @param classifyId 分类id
     * @return
     */
    public DisGroupClassify queryDisGroupClassifyById(Long classifyId) {
        List<DisGroupClassify> disGroupClassify = this.selectList("DisGroupMapper.queryDisGroupClassifyById", classifyId);
        if (disGroupClassify != null && disGroupClassify.size() > 0) {
            return disGroupClassify.get(0);
        }
        return null;
    }

    /**
     * 修改小组分类内容
     *
     * @param disGroupClassify
     */
    public void updateDisGroupClassify(DisGroupClassify disGroupClassify) {
        this.update("DisGroupMapper.updateDisGroupClassify", disGroupClassify);
    }

    /**
     * 根据名字查询 小组
     *
     * @param name
     * @return
     */
    public List<DisGroup> queryDisGroupListByName(String name) {

        return this.selectList("DisGroupMapper.queryDisGroupListByName", name);
    }

    /**
     * 根据小组id删除
     *
     * @param groupId
     */
    public void deleteDisGroupById(Long groupId) {
        this.delete("DisGroupMapper.deleteDisGroupById", groupId);

    }

    /**
     * 通过小组审核
     *
     * @param groupId
     */
    public Long updateDisGroupStatus(Long groupId) {
        return this.update("DisGroupMapper.updateDisGroupStatus", groupId);
    }

    /**
     * 后台查询所有小组
     *
     * @param page
     * @return
     */
    public List<DisGroup> queryAdminDisGroupList(DisGroup disGroup, PageEntity page) {
        return this.queryForListPage("DisGroupMapper.queryAdminDisGroupList", disGroup, page);
    }

    /**
     * 修改小组
     *
     * @param disGroup
     */
    public void updateDisGroupById(DisGroup disGroup) {
        this.update("DisGroupMapper.updateDisGroupById", disGroup);

    }

    /**
     * 小组搜索
     *
     * @param disGroup 根据 小组名称 小组分类 小组主
     * @param page     分页参数
     * @return
     */
    public List<DisGroup> queryDisGroupByCondition(DisGroup disGroup, PageEntity page) {

        return this.queryForListPage("DisGroupMapper.queryDisGroupByCondition", disGroup, page);
    }

    /**
     * 更新小组开启关闭
     *
     * @param groupId
     */
    public Long updateOpenDisGroupStatus(Long groupId) {
        return this.update("DisGroupMapper.updateOpenDisGroupStatus", groupId);

    }

    /**
     * 开启小组
     *
     * @param groupId
     * @return
     */
    public Long updateCloseDisGroupStatus(Long groupId) {

        return this.update("DisGroupMapper.updateCloseDisGroupStatus", groupId);
    }

    /**
     * 判断用户是否创建小组
     *
     * @param cusId 用戶id
     * @return
     */
    public Integer queryIsCreateDisGroup(Long cusId) {
        List<Integer> integerList = this.selectList("DisGroupMapper.queryIsCreateDisGroup", cusId);
        if (integerList != null && integerList.size() > 0) {
            integerList.get(0);
        }
        return null;
    }

    /**
     * 拒绝通过
     *
     * @param groupId
     * @return
     */
    public Long updaterefuseDisGroupStatus(Long groupId) {
        return this.update("DisGroupMapper.updaterefuseDisGroupStatus", groupId);
    }

    /**
     * 查询小组信息编辑时
     *
     * @param groupId
     * @return
     */
    public DisGroup queryDisGroupDetailById(Long groupId) {
        List<DisGroup> disGroupList = this.selectList("DisGroupMapper.queryDisGroupDetailById", groupId);
        if (disGroupList != null && disGroupList.size() > 0) {
            return disGroupList.get(0);
        }
        return null;
    }

    /**
     * 更新小组信息
     *
     * @param groupId
     * @return
     */
    public Long updateDisGroupDetailById(DisGroup disGroup) {
        return this.update("DisGroupMapper.updateDisGroupDetailById", disGroup);
    }

    /**
     * 查询小组分类是否存在
     *
     * @param disGroupClassify 小组分类
     * @return
     */
    public Integer queryDisGroupClassifyIsExist(DisGroupClassify disGroupClassify) {
        List<Integer> integerList = this.selectList("DisGroupMapper.queryDisGroupClassifyIsExist", disGroupClassify);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 查询分类下的小组
     *
     * @param classifyId 分类id
     * @param page       分页参数
     * @return
     */
    public List<DisGroup> queryClassifyDisGroupList(Long classifyId, PageEntity page) {
        return this.queryForListPage("DisGroupMapper.queryClassifyDisGroupList", classifyId, page);
    }

    /**
     * 更新小组主
     *
     * @param groupId
     */
    public void updateDisgroupCreater(DisMember disMember) {
        this.update("DisGroupMapper.updateDisgroupCreater", disMember);
    }

    /**
     * 查询我加入的小组
     *
     * @param userId
     * @return
     */
    public List<DisGroup> queryPersonDisGroupById(Long userId) {
        return this.selectList("DisGroupMapper.queryPersonDisGroupById", userId);
    }

    /**
     * 查询小组 根据小组号
     *
     * @param disNumber
     * @return
     */
    public DisGroup queryDisGroupByDisNumber(String disNumber) {
        List<DisGroup> disGroupList = this.selectList("DisGroupMapper.queryDisGroupByDisNumber", disNumber);
        if (disGroupList != null && disGroupList.size() > 0) {
            return disGroupList.get(0);
        }
        return null;
    }

}