package com.atdld.os.sns.service.impl.discuss;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.service.gain.GuidGeneratorService;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.constants.DisGroupConstans;
import com.atdld.os.sns.constants.LetterConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.dao.discuss.DisGroupDao;
import com.atdld.os.sns.dao.letter.MsgReceiveDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.entity.discuss.DisMember;
import com.atdld.os.sns.entity.letter.MsgReceive;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.discuss.DisMemberService;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.user.SnsUserService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.discuss.DisGroupServiceImpl
 * @description 小组ServiceImpl
 * @Create Date : 2013-12-11 下午2:09:18
 */
@Service("disGroupService")
public class DisGroupServiceImpl implements DisGroupService {

    @Autowired
    private DisGroupDao disGroupDao;// 小组dao
    @Autowired
    private DisMemberService disMemberService;// 小组成员表Service
    @Autowired
    private GuidGeneratorService guidGeneratorService;// 小组规则Service
    @Autowired
    private DynamicWebService dynamicWebService;// 动态service
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private MsgReceiveDao msgReceiveDao;

    private MemCache memCache = MemCache.getInstance();// 获得memcache

    /**
     * 创建小组
     *
     * @param disGroup
     * @throws Exception
     */
    public String addDisGroup(DisGroup disGroup) throws Exception {
        // 查询小组分类
        DisGroupClassify disGroupClassify = disGroupDao.queryDisGroupClassifyById(disGroup.getDisclassifyId());
        // 获得分类代码
        String code = disGroupClassify.getCode();
        // 生成小组号
        String disnumber = guidGeneratorService.gainDisCode(code, 4);
        disGroup.setCreateTime(new Date());
        disGroup.setDisNumber(disnumber);
        // 获得showname

        SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(disGroup.getCusId());

        disGroup.setShowName(userExpandDto.getShowname());
        // 添加小组
        Long num = disGroupDao.addDisGroup(disGroup);
        if (num == 1) {
            DisMember disMember = new DisMember();
            disMember.setAddTime(new Date());
            disMember.setCusId(disGroup.getCusId());// set小组创建人加入小组成员里
            disMember.setGroupId(disGroup.getId());// 小组的id
            disMember.setTransferId(DisGroupConstans.GROUP_MEMBER_TRANSFERID_ADMINISTRATOR);// 小组的创建人就是管理管理员
            disMember.setShowName(disGroup.getShowName());
            dynamicWebService.addDynamicWebForDisGroup(disGroup);// 添加动态
            disMemberService.addDisMember(disMember);// 添加小组成员
            return SnsConstants.SUCCESS;// 返回成功

        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 分页查询所有小组
     *
     * @param pageEntity 分页的参数
     * @return List<DisGroup> 所有小组的list
     * @throws Exception
     */
    public List<DisGroup> queryDisGroupALLList(PageEntity pageEntity) throws Exception {
        List<DisGroup> disGroupList = disGroupDao.queryDisGroupALLList(pageEntity);
        
        if (ObjectUtils.isNotNull(disGroupList) && disGroupList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisGroupListCusId(disGroupList));
            for (DisGroup disGroup : disGroupList) {
                SnsUserExpandDto userExpandDto = map.get(disGroup.getCusId() + "");
                if (ObjectUtils.isNotNull(userExpandDto)) {
                    disGroup.setShowName(userExpandDto.getShowname());
                }
            }
        }
        return disGroupList;
    }

    /**
     * 查询我创建的小组
     *
     * @param cusId 用户的Id
     * @return List<DisGroup> 我创建小组的list
     * @throws Exception
     */
    public List<DisGroup> queryMyDisGroupList(Long cusId) throws Exception {
        List<DisGroup> disGroupList = disGroupDao.queryMyDisGroupList(cusId);
        if (ObjectUtils.isNotNull(disGroupList) && disGroupList.size() > 0) {
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(cusId);
            for (DisGroup disGroup : disGroupList) {
                if (ObjectUtils.isNotNull(userExpandDto))
                    disGroup.setShowName(userExpandDto.getShowname());
            }
        }
        return disGroupList;
    }

    /**
     * 分页查询我加入的小组
     *
     * @param disMember  用户id
     * @param pageEntity 分页参数
     * @return List<DisGroup> 我加入小组组list
     * @throws Exception
     */
    public List<DisGroup> queryMyJoinDisGroupList(DisMember disMember, PageEntity pageEntity) throws Exception {
        List<DisGroup> disGroupList = disGroupDao.queryMyJoinDisGroupList(disMember, pageEntity);
        
        if (ObjectUtils.isNotNull(disGroupList) && disGroupList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId( getDisGroupListCusId(disGroupList));
            for (DisGroup disGroup : disGroupList) {
                SnsUserExpandDto userExpandDto = map.get(disGroup.getCusId() + "");
                if (ObjectUtils.isNotNull(userExpandDto)) {
                    disGroup.setShowName(userExpandDto.getShowname());
                }
            }
        }
        return disGroupList;
    }

    /**
     * 分页查询热门小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup> 热门小组list
     * @throws Exception
     */
    public List<DisGroup> queryHotDisGroupList(PageEntity pageEntity) throws Exception {
        List<DisGroup> disGroupList = disGroupDao.queryHotDisGroupList(pageEntity);// 分页查询热门小组
        
        if (ObjectUtils.isNotNull(disGroupList) && disGroupList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisGroupListCusId(disGroupList));
            for (DisGroup disGroup : disGroupList) {
                SnsUserExpandDto userExpandDto = map.get(disGroup.getCusId() + "");
                if (ObjectUtils.isNotNull(userExpandDto)) {
                    disGroup.setShowName(userExpandDto.getShowname());
                }
            }
        }
        return disGroupList;
    }

    /**
     * 首页热门小组排行
     *
     * @param num 显示数量
     * @return List<DisGroup> 热门小组list
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<DisGroup> queryHotDisGroupList(int num) throws Exception {
        PageEntity page = new PageEntity();
        List<DisGroup> disGroupList = (List<DisGroup>) memCache.get(MemConstans.INDEX_DIS_GROUP);// 从缓存中读取
        if (ObjectUtils.isNull(disGroupList)) {// 如果为null则调用接口获取数据
            page.setPageSize(num);
            disGroupList = disGroupDao.queryHotDisGroupList(page);
            memCache.set(MemConstans.INDEX_DIS_GROUP, disGroupList, MemConstans.INDEX_DIS_GROUP_TIME);// 把数据放入缓存中
        }
        return disGroupList;
    }

    /**
     * 查询小组的信息
     *
     * @param groudId 小组的Id
     * @return DisGroup
     * @throws Exception
     */
    public DisGroup queryDisGroupById(Long groudId) throws Exception {
        // 根据key值从memcache取值
        DisGroup disGroup = (DisGroup) memCache.get(MemConstans.DISGROUP_INFO + groudId);
        // 看memcache数据是否有，如果为空set的memcache中
        if (ObjectUtils.isNull(disGroup)) {
            disGroup = disGroupDao.queryDisGroupById(groudId);
            if(ObjectUtils.isNotNull(disGroup)){
            	 SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(disGroup.getCusId());
                 disGroup.setShowName(userExpandDto.getShowname());
                 memCache.set(MemConstans.DISGROUP_INFO + groudId, disGroup, MemConstans.DISGROUP_INFO_TIME);// 有效时间10分钟	
            }
        }
        return disGroup;
    }

    /**
     * 查询小组的分类
     *
     * @throws Exception
     */
    public List<DisGroupClassify> querydisGroupList() throws Exception {
        // 根据key值从memcache取值
         @SuppressWarnings("unchecked")
		List<DisGroupClassify> disGroupClassifyList =(List<DisGroupClassify>) memCache.get(MemConstans.DISGROUP_CLASSIFY);
        // 看memcache数据是否有，如果为空set的memcache中
        if (ObjectUtils.isNull(disGroupClassifyList)) {
            disGroupClassifyList = disGroupDao.querydisGroupList();
            memCache.set(MemConstans.DISGROUP_CLASSIFY, disGroupClassifyList, MemConstans.DISGROUP_INFO_TIME);
        }
        return disGroupClassifyList;
    }

    /**
     * 查找我创建的小组通过用户限制,只返回行数
     *
     * @param disGroup 小组用户Id
     * @return Integer 行数
     * @throws Exception
     */
    public Integer queryMyDisGroupByCusId(DisGroup disGroup) throws Exception {

        return disGroupDao.queryMyDisGroupByCusId(disGroup);
    }

    /**
     * 查询置顶小组
     *
     * @param pageEntity 分页参数
     * @return List<DisGroup> 小组组list
     * @throws Exception
     */
    public List<DisGroup> queryDisGroupTop(PageEntity pageEntity) throws Exception {

        return disGroupDao.queryDisGroupTop(pageEntity);
    }

    /**
     * 设置置顶小组根据TOP
     *
     * @param disGroup 小组Id
     * @throws Exception
     */
    public void updateDisGroupTop(DisGroup disGroup) throws Exception {

        disGroupDao.updateDisGroupTop(disGroup);
    }

    /**
     * 小组首页排行 按照活跃度排行， 活跃度指标包括小组成员人数、文章数目、回复数目
     *
     * @param disGroup 活跃度 groupId小组Id
     * @throws Exception
     */
    public void updateDisGroupActivity(DisGroup disGroup) throws Exception {

        disGroupDao.updateDisGroupActivity(disGroup);

    }

    /**
     * 小组文章数量统计+1
     *
     * @param groupId 小组id
     * @param count   +1
     */
    public void updateArticleCount(Long groupId, int count) throws Exception {

        disGroupDao.updateArticleCount(groupId, count);
    }

    /**
     * 小组首页排行 按照活跃度排行
     *
     * @throws Exception
     */
    public List<DisGroup> queryHomePageDisGroup() throws Exception {

        return disGroupDao.queryHomePageDisGroup();
    }

    /**
     * 判断是否加入该小组
     *
     * @param disMember 用户id 小组id
     */
    public Integer queryIsJoin(DisMember disMember) throws Exception {
        return disGroupDao.queryIsJoin(disMember);
    }

    /**
     * 后台添加小组分类
     *
     * @param disGroupClassify
     */
    public void addDisGroupClassify(DisGroupClassify disGroupClassify) throws Exception {
        // 删除缓存
        memCache.remove(MemConstans.DISGROUP_CLASSIFY);
        // 添加小组分类
        disGroupDao.addDisGroupClassify(disGroupClassify);
    }

    /**
     * 删除小组分类
     *
     * @param classifyId 小组分类id
     */
    public String deleteDisGroupClassify(Long classifyId) throws Exception {
        // 删除缓存
        memCache.remove(MemConstans.DISGROUP_CLASSIFY);
        // 删除
        Long num = disGroupDao.deleteDisGroupClassify(classifyId);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 根据分类id查询
     *
     * @param classifyId 分类id
     */
    public DisGroupClassify queryDisGroupClassifyById(Long classifyId) throws Exception {

        return disGroupDao.queryDisGroupClassifyById(classifyId);
    }

    /**
     * 修改小组分类内容
     *
     * @param disGroupClassify
     */
    public void updateDisGroupClassify(DisGroupClassify disGroupClassify) throws Exception {
        // 删除缓存
        memCache.remove(MemConstans.DISGROUP_CLASSIFY);
        // 进行更新
        disGroupDao.updateDisGroupClassify(disGroupClassify);
    }

    /**
     * 根据名字查询 小组
     *
     * @param name
     */
    public List<DisGroup> queryDisGroupListByName(String name) throws Exception {
        return disGroupDao.queryDisGroupListByName(name);
    }

    /**
     * 根据小组id删除
     *
     * @param groupId 小组id
     */
    public void deleteDisGroupById(Long groupId) throws Exception {
        memCache.remove(MemConstans.DISGROUP_INFO + groupId);
        disGroupDao.deleteDisGroupById(groupId);
    }

    /**
     * 通过小组审核
     *
     * @param groupId
     */
    public String updateDisGroupStatus(Long groupId) throws Exception {
        Long num = disGroupDao.updateDisGroupStatus(groupId);
        if (num == 1) {
            DisGroup disGroup = disGroupDao.queryDisGroupById(groupId);// 通过groupId查找小组
            DisMember disMember = new DisMember();
            disMember.setAddTime(new Date());
            disMember.setCusId(disGroup.getCusId());// set小组创建人加入小组成员里
            disMember.setGroupId(disGroup.getId());// 小组的id
            disMember.setTransferId(DisGroupConstans.GROUP_MEMBER_TRANSFERID_ADMINISTRATOR);// 小组的创建人就是管理管理员
            disMember.setShowName(disGroup.getShowName());
            dynamicWebService.addDynamicWebForDisGroup(disGroup);// 添加动态
            disMemberService.addDisMember(disMember);// 添加小组成员
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 后台查询所有小组
     *
     * @param page
     */
    public List<DisGroup> queryAdminDisGroupList(DisGroup disGroup, PageEntity page) throws Exception {
        return disGroupDao.queryAdminDisGroupList(disGroup, page);
    }

    /**
     * 修改小组
     *
     * @param disGroup
     */
    public void updateDisGroupById(DisGroup disGroup) throws Exception {
        memCache.remove(MemConstans.DISGROUP_INFO + disGroup.getId());
        disGroupDao.updateDisGroupById(disGroup);
    }

    /**
     * 小组搜索
     *
     * @param disGroup
     */
    public List<DisGroup> queryDisGroupByCondition(DisGroup disGroup, PageEntity page) throws Exception {
        List<DisGroup> disGroupList = disGroupDao.queryDisGroupByCondition(disGroup, page);
        
        if (ObjectUtils.isNotNull(disGroupList) && disGroupList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisGroupListCusId(disGroupList));
            for (DisGroup disGroup1 : disGroupList) {
                SnsUserExpandDto userExpandDto = map.get(disGroup1.getCusId());
                if (ObjectUtils.isNotNull(userExpandDto)) {
                    disGroup1.setShowName(userExpandDto.getShowname());
                }
            }
        }
        return disGroupList;
    }

    /**
     * 更新小组开启关闭
     *
     * @param groupId
     */
    public String updateOpenDisGroupStatus(Long groupId) throws Exception {
        Long num = disGroupDao.updateOpenDisGroupStatus(groupId);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }

    }

    /**
     * 开启小组
     *
     * @param groupId
     */
    public String updateCloseDisGroupStatus(Long groupId) throws Exception {
        Long num = disGroupDao.updateCloseDisGroupStatus(groupId);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 判断用户是否创建小组
     *
     * @param cusId 用戶id
     */
    public Integer queryIsCreateDisGroup(Long cusId) throws Exception {
        return disGroupDao.queryIsCreateDisGroup(cusId);
    }

    /**
     * 小组文章数量统计-1
     *
     * @param groupId
     * 小组的id
     */
    public void updateArticleCountsReduce(Long groupId) throws Exception {
        disGroupDao.updateArticleCountsReduce(groupId);

    }

    /**
     * 拒绝通过
     *
     * @param groupId
     */
    public String updaterefuseDisGroupStatus(Long groupId) throws Exception {
        Long num = disGroupDao.updaterefuseDisGroupStatus(groupId);
        if (num == 1) {
            DisGroup gisGroup = disGroupDao.queryDisGroupById(Long.valueOf(groupId));
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setContent("您申请的小组<" + gisGroup.getName() + ">未通过,请重新提交。");// 添加站内信的内容
            msgReceive.setCusId(Long.valueOf(0));
            msgReceive.setReceivingCusId(gisGroup.getCusId());// 要发送的用户id
            msgReceive.setStatus(LetterConstans.LETTER_STATUS_UNREAD);// 消息未读状态
            msgReceive.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);// 系统消息
            msgReceive.setUpdateTime(new Date());// 更新时间s
            msgReceive.setAddTime(new Date());// 添加时间
            msgReceive.setShowname(gisGroup.getShowName());// 会员名
            msgReceiveDao.addMsgReceive(msgReceive);// 添加站内信
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 查询小组信息
     *
     * @param groupId
     */
    public DisGroup queryDisGroupDetailById(Long groupId) throws Exception {
        return disGroupDao.queryDisGroupDetailById(groupId);
    }

    /**
     * 更新小组信息
     *
     * @param disGroup
     */
    public String updateDisGroupDetailById(DisGroup disGroup) throws Exception {
        memCache.remove(MemConstans.DISGROUP_INFO + disGroup.getId());
        Long num = disGroupDao.updateDisGroupDetailById(disGroup);
        if (num == 1) {

            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 查询小组分类是否存在
     *
     * @param disGroupClassify 小组分类
     */
    public String queryDisGroupClassifyIsExist(DisGroupClassify disGroupClassify) throws Exception {
        Integer num = disGroupDao.queryDisGroupClassifyIsExist(disGroupClassify);
        if (num == 1) {
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 查询分类下的小组
     *
     * @param classifyId 分类id
     * @param page       分页参数
     */
    public List<DisGroup> queryClassifyDisGroupList(Long classifyId, PageEntity page) throws Exception {
        List<DisGroup> disGroupList = disGroupDao.queryClassifyDisGroupList(classifyId, page);
        
        if (ObjectUtils.isNotNull(disGroupList) && disGroupList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisGroupListCusId(disGroupList));
            for (DisGroup disGroup : disGroupList) {
                SnsUserExpandDto userExpandDto = map.get(disGroup.getCusId());
                if (ObjectUtils.isNotNull(userExpandDto)) {
                    disGroup.setShowName(userExpandDto.getShowname());
                }
            }
        }
        return disGroupList;
    }

    /**
     * 更新小组主
     *
     * @param disMember
     */
    public void updateDisgroupCreater(DisMember disMember) throws Exception {
        disGroupDao.updateDisgroupCreater(disMember);
    }

    /**
     * 查询我加入的小组
     *
     * @param userId
     */
    public List<DisGroup> queryPersonDisGroupById(Long userId) throws Exception {
        return disGroupDao.queryPersonDisGroupById(userId);
    }

    /**
     * 查询小组 根据小组号
     *
     * @param disNumber
     * @throws Exception
     */
    public DisGroup queryDisGroupByDisNumber(String disNumber) throws Exception {
        DisGroup disGroup1 = (DisGroup) memCache.get(MemConstans.DISGROUP_INFO + disNumber);
        if (ObjectUtils.isNull(disGroup1)) {
            disGroup1 = disGroupDao.queryDisGroupByDisNumber(disNumber);
            memCache.set(MemConstans.DISGROUP_INFO + disNumber, disGroup1, MemConstans.DISGROUP_INFO_TIME);
        }
        return disGroup1;
    }

    /**
     * 后台查询小组分类
     *
     * @throws Exception
     */
    public List<DisGroupClassify> queryAdminDisGroupClassify() throws Exception {
        return disGroupDao.querydisGroupList();
    }

    public String getDisGroupListCusId(List<DisGroup> disGroupList) {// 获得用户ids
        String ids = "";
        if (disGroupList != null && disGroupList.size() > 0) {
            for (DisGroup disGroup : disGroupList) {
                ids += disGroup.getCusId() + ",";
            }
        }
        return ids;
    }

}
