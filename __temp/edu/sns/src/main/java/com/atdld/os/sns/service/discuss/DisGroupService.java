package com.atdld.os.sns.service.discuss;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.entity.discuss.DisMember;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.discuss.DisGroupService
 * @description 小组Service
 * @Create Date : 2013-12-11 下午1:44:57
 */
public interface DisGroupService {
    /**
     * 创建小组
     *
     * @param disGroup
     * @throws Exception
     */
    public String addDisGroup(DisGroup disGroup) throws Exception;

    /**
     * 分页查询所有小组
     *
     * @param pageEntity 分页的参数
     * @return List<DisGroup> 所有小组的list
     * @throws Exception
     */
    public List<DisGroup> queryDisGroupALLList(PageEntity pageEntity) throws Exception;

    /**
     * 查询我创建的小组
     *
     * @param cusId 用户的Id
     * @return List<DisGroup> 我创建小组的list
     * @throws Exception
     */
    public List<DisGroup> queryMyDisGroupList(Long cusId) throws Exception;

    /**
     * 分页查询我加入的小组
     *
     * @param disMember  用户id
     * @param pageEntity 分页参数
     * @return List<DisGroup> 我加入小组组list
     * @throws Exception
     */
    public List<DisGroup> queryMyJoinDisGroupList(DisMember disMember,
                                                  PageEntity pageEntity) throws Exception;

    /**
     * 分页查询热门小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup> 热门小组list
     * @throws Exception
     */
    public List<DisGroup> queryHotDisGroupList(PageEntity pageEntity) throws Exception;

    /**
     * 首页热门小组排行
     *
     * @param num 显示数量
     * @return List<DisGroup> 热门小组list
     * @throws Exception
     */
    public List<DisGroup> queryHotDisGroupList(int num) throws Exception;

    /**
     * 查询小组的信息
     *
     * @param groudId 小组的Id
     * @return DisGroup
     * @throws Exception
     */
    public DisGroup queryDisGroupById(Long groudId) throws Exception;

    /**
     * 查询小组的分类
     *
     * @return List<DisGroupList> 小组分类
     * @throws Exception
     */
    public List<DisGroupClassify> querydisGroupList() throws Exception;

    /**
     * 后台查询小组分类
     *
     * @return
     * @throws Exception
     */
    public List<DisGroupClassify> queryAdminDisGroupClassify() throws Exception;

    /**
     * 查找我创建的小组通过用户限制,只返回行数
     *
     * @param disGroup 小组用户Id
     * @return Integer 行数
     * @throws Exception
     */
    public Integer queryMyDisGroupByCusId(DisGroup disGroup) throws Exception;

    /**
     * 查询置顶小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup> 小组组list
     * @throws Exception
     */
    public List<DisGroup> queryDisGroupTop(PageEntity pageEntity) throws Exception;

    /**
     * 设置置顶小组根据TOP
     *
     * @param disGroup 小组Id
     * @throws Exception
     */
    public void updateDisGroupTop(DisGroup disGroup) throws Exception;

    /**
     * 小组首页排行 按照活跃度排行， 活跃度指标包括小组成员人数、文章数目、回复数目
     *
     * @param activity活跃度 groupId小组Id
     * @throws Exception
     */
    public void updateDisGroupActivity(DisGroup disGroup) throws Exception;

    /**
     * 小组文章数量统计+1
     *
     * @param groupId 小组id
     * @param count   +1
     */
    public void updateArticleCount(Long groupId, int count) throws Exception;

    /**
     * 小组文章数量统计-1
     *
     * @param groupId小组的id
     */
    public void updateArticleCountsReduce(Long groupId) throws Exception;

    /**
     * 小组首页排行 按照活跃度排行
     *
     * @throws Exception
     */
    public List<DisGroup> queryHomePageDisGroup() throws Exception;

    /**
     * 判断是否加入该小组
     *
     * @param disMember 用户id 小组id
     * @return
     */
    public Integer queryIsJoin(DisMember disMember) throws Exception;

    /**
     * 后台添加小组分类
     *
     * @param disGroupClassify
     */
    public void addDisGroupClassify(DisGroupClassify disGroupClassify) throws Exception;

    /**
     * 删除小组分类
     *
     * @param classifyId 小组分类id
     */
    public String deleteDisGroupClassify(Long classifyId) throws Exception;

    /**
     * 根据分类id查询
     *
     * @param classifyId 分类id
     * @return
     */
    public DisGroupClassify queryDisGroupClassifyById(Long classifyId) throws Exception;

    /**
     * 修改小组分类内容
     *
     * @param disGroupClassify
     */
    public void updateDisGroupClassify(DisGroupClassify disGroupClassify)
            throws Exception;

    /**
     * 根据名字查询 小组
     *
     * @param name
     * @return
     */
    public List<DisGroup> queryDisGroupListByName(String name) throws Exception;

    /**
     * 根据小组id删除
     *
     * @param groupId 小组id
     */
    public void deleteDisGroupById(Long groupId) throws Exception;

    /**
     * 通过小组审核
     *
     * @param groupId 小组id
     */
    public String updateDisGroupStatus(Long groupId) throws Exception;

    /**
     * 后台查询所有小组
     *
     * @param page
     * @return
     */
    public List<DisGroup> queryAdminDisGroupList(DisGroup disGroup, PageEntity page)
            throws Exception;

    /**
     * 修改小组
     *
     * @param disGroup
     */
    public void updateDisGroupById(DisGroup disGroup) throws Exception;

    /**
     * 小组搜索
     *
     * @param queryDisGroupCondition
     * @return
     */
    public List<DisGroup> queryDisGroupByCondition(DisGroup disGroup, PageEntity page)
            throws Exception;

    /**
     * 更新小组开启关闭
     *
     * @param groupId
     */
    public String updateOpenDisGroupStatus(Long groupId) throws Exception;

    /**
     * 开启小组
     *
     * @param groupId
     * @return
     */
    public String updateCloseDisGroupStatus(Long groupId) throws Exception;

    /**
     * 判断用户是否创建小组
     *
     * @param cusId 用戶id
     * @return
     */
    public Integer queryIsCreateDisGroup(Long cusId) throws Exception;

    /**
     * 拒绝通过
     *
     * @param groupId
     * @return
     */
    public String updaterefuseDisGroupStatus(Long groupId) throws Exception;

    /**
     * 查询小组信息
     *
     * @param groupId
     * @return
     */
    public DisGroup queryDisGroupDetailById(Long groupId) throws Exception;

    /**
     * 更新小组信息
     *
     * @param groupId
     * @return
     */
    public String updateDisGroupDetailById(DisGroup disGroup) throws Exception;

    /**
     * 查询小组分类是否存在
     *
     * @param disGroupClassify 小组分类
     * @return
     */
    public String queryDisGroupClassifyIsExist(DisGroupClassify disGroupClassify)
            throws Exception;

    /**
     * 查询分类下的小组
     *
     * @param classifyId 分类id
     * @param page       分页参数
     * @return
     */
    public List<DisGroup> queryClassifyDisGroupList(Long classifyId, PageEntity page)
            throws Exception;

    /**
     * 更新小组主
     *
     * @param groupId
     */
    public void updateDisgroupCreater(DisMember disMember) throws Exception;

    /**
     * 查询我加入的小组
     *
     * @param userId
     * @return
     */
    public List<DisGroup> queryPersonDisGroupById(Long userId) throws Exception;

    /**
     * 查询小组 根据小组号
     *
     * @param disGroup
     * @return
     * @throws Exception
     */
    public DisGroup queryDisGroupByDisNumber(String disNumber) throws Exception;
}
