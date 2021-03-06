package com.atdld.os.sns.dao.discuss;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.entity.discuss.DisMember;

/**
 * @author :
 * @ClassName com.atdld.os.sns.dao.discuss.DisGroupDao
 * @description 小组Dao
 * @Create Date : 2013-12-11 下午1:39:51
 */
public interface DisGroupDao {
    /**
     * 创建小组
     *
     * @param disGroup
     */
    public Long addDisGroup(DisGroup disGroup);

    /**
     * 分页查询所有小组
     *
     * @param pageEntity 分页的参数
     * @return List<DisGroup> 所有小组 list
     */
    public List<DisGroup> queryDisGroupALLList(PageEntity pageEntity);

    /**
     * 查询我创建的小组
     *
     * @param cusId 用户的Id
     * @return List<DisGroup> 我的小组 list
     */
    public List<DisGroup> queryMyDisGroupList(Long cusId);

    /**
     * 分页查询我加入的小组
     *
     * @param disMember  用户id
     * @param pageEntity 分页参数
     * @return List<DisGroup> 我加入小组 list
     */
    public List<DisGroup> queryMyJoinDisGroupList(DisMember disMember, PageEntity pageEntity);

    /**
     * 分页查询热门小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup> 热门小组
     */
    public List<DisGroup> queryHotDisGroupList(PageEntity pageEntity);

    /**
     * 查询小组的信息
     *
     * @param groudId 小组的Id
     * @return DisGroup
     */
    public DisGroup queryDisGroupById(Long groudId);

    /**
     * 查询小组的分类
     *
     * @return List<DisGroupList> 小组分类 list
     */
    public List<DisGroupClassify> querydisGroupList();

    /**
     * 查找我创建的小组通过用户限制,只返回行数
     *
     * @param disGroup 小组用户Id
     * @return
     */
    public Integer queryMyDisGroupByCusId(DisGroup disGroup);

    /**
     * 查询置顶小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup>
     */
    public List<DisGroup> queryDisGroupTop(PageEntity pageEntity);

    /**
     * 设置置顶小组根据TOP
     *
     * @param disGroup 小组Id
     */
    public void updateDisGroupTop(DisGroup disGroup);

    /**
     * 转让小组
     *
     * @param disMember
     */
    public void updateDisGroupTransfer(DisMember disMember);

    /**
     * 小组首页排行 按照活跃度排行， 活跃度指标包括小组成员人数、文章数目、回复数目
     *
     * @param activity活跃度 groupId小组Id
     */
    public void updateDisGroupActivity(DisGroup disGroup);

    /**
     * 小组首页排行 按照活跃度排行
     *
     * @return List<DisGroup> 按照活跃度排的list
     */
    public List<DisGroup> queryHomePageDisGroup();

    /**
     * 小组文章数量统计+1
     *
     * @param groupId 小组id
     * @param count   +1
     */
    public void updateArticleCount(Long groupId, int count);

    /**
     * 小组文章数量统计-1
     *
     * @param groupId小组的id
     */
    public void updateArticleCountsReduce(Long groupId);

    /**
     * 判断是否加入该小组
     *
     * @param disMember 用户id 小组id
     * @return
     */
    public Integer queryIsJoin(DisMember disMember);

    /**
     * 判断用户是否创建小组
     *
     * @param cusId 用戶id
     * @return
     */
    public Integer queryIsCreateDisGroup(Long cusId);

    /**
     * 后台添加小组分类
     *
     * @param disGroupClassify
     */
    public void addDisGroupClassify(DisGroupClassify disGroupClassify);

    /**
     * 删除小组分类
     *
     * @param classifyId 小组分类id
     */
    public Long deleteDisGroupClassify(Long classifyId);

    /**
     * 根据分类id查询
     *
     * @param classifyId 分类id
     * @return
     */
    public DisGroupClassify queryDisGroupClassifyById(Long classifyId);

    /**
     * 修改小组分类内容
     *
     * @param disGroupClassify
     */
    public void updateDisGroupClassify(DisGroupClassify disGroupClassify);

    /**
     * 根据名字查询 小组
     *
     * @param name
     * @return
     */
    public List<DisGroup> queryDisGroupListByName(String name);

    /**
     * 根据小组id删除
     *
     * @param groupId
     */
    public void deleteDisGroupById(Long groupId);

    /**
     * 通过小组审核
     *
     * @param groupId
     */
    public Long updateDisGroupStatus(Long groupId);

    /**
     * 后台查询所有小组
     *
     * @param page
     * @return
     */
    public List<DisGroup> queryAdminDisGroupList(DisGroup disGroup, PageEntity page);

    /**
     * 修改小组
     *
     * @param disGroup
     */
    public void updateDisGroupById(DisGroup disGroup);

    /**
     * 小组搜索
     *
     * @param name 根据 小组名称 小组分类 小组主
     * @param page 分页参数
     * @return
     */
    public List<DisGroup> queryDisGroupByCondition(DisGroup disGroup, PageEntity page);

    /**
     * 更新小组开启关闭
     *
     * @param groupId
     */
    public Long updateOpenDisGroupStatus(Long groupId);

    /**
     * 开启小组
     *
     * @param groupId
     * @return
     */
    public Long updateCloseDisGroupStatus(Long groupId);

    /**
     * 拒绝通过
     *
     * @param groupId
     * @return
     */
    public Long updaterefuseDisGroupStatus(Long groupId);

    /**
     * 查询小组信息
     *
     * @param groupId
     * @return
     */
    public DisGroup queryDisGroupDetailById(Long groupId);

    /**
     * 更新小组信息
     *
     * @param disGroup
     * @return
     */
    public Long updateDisGroupDetailById(DisGroup disGroup);

    /**
     * 查询小组分类是否存在
     *
     * @param disGroupClassify 小组分类
     * @return
     */
    public Integer queryDisGroupClassifyIsExist(DisGroupClassify disGroupClassify);

    /**
     * 查询分类下的小组
     *
     * @param classifyId 分类id
     * @param page       分页参数
     * @return
     */
    public List<DisGroup> queryClassifyDisGroupList(Long classifyId, PageEntity page);

    /**
     * 更新小组主
     *
     * @param groupId
     */
    public void updateDisgroupCreater(DisMember disMember);

    /**
     * 查询我加入的小组
     *
     * @param userId
     * @return
     */
    public List<DisGroup> queryPersonDisGroupById(Long userId);

    /**
     * 查询小组 根据小组号
     *
     * @param disNumber
     * @return
     */
    public DisGroup queryDisGroupByDisNumber(String disNumber);

}
