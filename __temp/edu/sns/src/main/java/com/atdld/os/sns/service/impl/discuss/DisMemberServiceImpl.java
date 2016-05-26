package com.atdld.os.sns.service.impl.discuss;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.constants.DisGroupConstans;
import com.atdld.os.sns.constants.LetterConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.dao.discuss.DisMemberDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisMember;
import com.atdld.os.sns.entity.letter.MsgReceive;
import com.atdld.os.sns.service.discuss.DisGroupService;
import com.atdld.os.sns.service.discuss.DisMemberService;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.letter.MsgReceiveService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.visitstat.VisitStatService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.discuss.DisMemberServiceImpl
 * @description 小组成员ServiceImpl
 * @Create Date : 2013-12-11 下午2:09:49
 */
@Service("disMemberService")
public class DisMemberServiceImpl implements DisMemberService {
    @Autowired
    private DisMemberDao disMemberDao;

    @Autowired
    private DynamicWebService dynamicWebService;

    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WebHessianService webHessianService;
    @Autowired
    private MsgReceiveService msgReceiveService;
    // 获得memcache
    private MemCache memCache = MemCache.getInstance();

    /**
     * 添加小组成员
     *
     * @param disMember 用户的Id,小组的Id
     * @throws Exception
     */
    public String addDisMember(DisMember disMember) throws Exception {
        if (disMemberDao.queryDisMemberByGroupIdAndCusId(disMember) == 0) {// 检验是否已经加入小组成员中
            Long num = disMemberDao.addDisMember(disMember);
            if (num == 1) {// 判断是否添加成功
                // 小组组表里成员数+1
                this.updateDisGroupMemberCount(disMember.getGroupId(), 1); // 9.小组成员数
                // 成员数+1 活跃度加 x=小组成员数 A=30
                DisGroup disGroup = new DisGroup();
                disGroup.setActivity(DisGroupConstans.GROUP_ACTIVITY_MEMBER);
                disGroup.setId(disMember.getGroupId());
                disGroupService.updateDisGroupActivity(disGroup);
                // 申请成员
                dynamicWebService.addDynamicWebForDisMember(disMember);

                return SnsConstants.SUCCESS;// 返回成功
            } else {
                return SnsConstants.FALSE;// 返回失败
            }
        } else {
            return SnsConstants.EXSIT;// 用户已经加入该小组
        }

    }

    /**
     * 查看所有成员列表
     *
     * @param groupId    小组Id
     * @param pageEntity 分页参数
     * @return List<DisMember> 小组成员list
     * @throws Exception
     */
    public List<DisMember> queryDisMemberByGroupId(Long groupId, PageEntity pageEntity) throws Exception {
        List<DisMember> disMemberList = disMemberDao.queryDisMemberByGroupId(groupId, pageEntity);
        if(disMemberList!=null&&disMemberList.size()>0){
	        Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisMemberListCusId(disMemberList));// 查询用户的信息
	        if (ObjectUtils.isNotNull(disMemberList) && disMemberList.size() > 0) {
	            for (DisMember disMember : disMemberList) {
	                SnsUserExpandDto userExpandDto = map.get("" + disMember.getCusId());
	                if (ObjectUtils.isNotNull(userExpandDto)) {
	                    disMember.setUserExpandDto(userExpandDto);
	                    disMember.setShowName(userExpandDto.getShowname());
	                }
	            }
	        }
        }
        return disMemberList;
    }

    /**
     * 按照小组成员加入时间排序
     *
     * @param groupId 小组Id
     * @return List<DisMember> 小组成员
     * @throws Exception
     */
    public List<DisMember> queryDisMemberByaddTime(Long groupId) throws Exception {
        List<DisMember> disMemberList = disMemberDao.queryDisMemberByaddTime(groupId);
        if(disMemberList!=null&&disMemberList.size()>0){
	        Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisMemberListCusId(disMemberList));// 查询用户的信息
	        if (ObjectUtils.isNotNull(disMemberList) && disMemberList.size() > 0) {
	            for (DisMember disMember : disMemberList) {
	                SnsUserExpandDto userExpandDto = map.get("" + disMember.getCusId());// 获得用户信息
	                if (ObjectUtils.isNotNull(userExpandDto)) {
	                    disMember.setUserExpandDto(userExpandDto);
	                    disMember.setShowName(userExpandDto.getShowname());
	                }// 存用户信息
	            }
	        }
        }
        return disMemberList;
    }

    /**
     * 退出小组
     *
     * @param groupId 小组Id
     * @throws Exception
     */
    public void updateDisGroupExit(Long groupId) throws Exception {
        disMemberDao.updateDisGroupExit(groupId);
    }

    /**
     * 退出小组删除该用户信息
     *
     * @param id
     */
    public String deleteDisGroupExit(DisMember disMember) throws Exception {
        Long num = disMemberDao.deleteDisGroupExit(disMember);
        if (num == 1) {
            // 退出小组人员数-1
            this.updateDisGroupExit(disMember.getGroupId());
            return SnsConstants.SUCCESS;
        } else {
            return SnsConstants.FALSE;
        }
    }

    /**
     * 通过小组id查出该小组的管理员
     *
     * @param groupId 小组id
     * @return DisMember
     * @throws Exception
     */
    public List<DisMember> queryDisMemberAdministratorByGroup(Long groupId) throws Exception {
        List<DisMember> disMemberList = disMemberDao.queryDisMemberAdministratorByGroup(groupId);// 通过小组id查出该小组的管理员
        if(disMemberList!=null&&disMemberList.size()>0){
	        Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisMemberListCusId(disMemberList));// 查询用户的信息
	        if (ObjectUtils.isNotNull(disMemberList) && disMemberList.size() > 0) {
	            for (DisMember disMember : disMemberList) {
	                SnsUserExpandDto userExpandDto = map.get("" + disMember.getCusId());
	                if (ObjectUtils.isNotNull(userExpandDto)) {
	                    disMember.setUserExpandDto(userExpandDto);
	                    disMember.setShowName(userExpandDto.getShowname());
	                }
	            }
	        }
        }
        return disMemberList;
    }

    /**
     * 通过group和cusId查询是否存在
     *
     * @param disMember
     * @return int 返回存在的条数
     * @throws Exception
     */
    public Integer queryDisMemberByGroupIdAndCusId(DisMember disMember) throws Exception {
        return disMemberDao.queryDisMemberByGroupIdAndCusId(disMember);// 通过group和cusId查询是否存在
    }

    /**
     * 通过小组id和用户id查询用户是否有权限操作转让小组
     *
     * @param disMember
     * @return
     */
    public Integer queryMemberTransferId(DisMember disMember) throws Exception {
        return disMemberDao.queryMemberTransferId(disMember);
    }

    /**
     * 查询成员
     *
     * @param disMember
     * @return List<DisMember>
     */
    public List<DisMember> querydisMemberByzr(DisMember disMember) throws Exception {
        List<DisMember> disMemberList = disMemberDao.querydisMemberByzr(disMember);
       
        if (disMemberList != null && disMemberList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDisMemberListCusId(disMemberList));// 查询用户的信息
            for (DisMember disMember2 : disMemberList) {
                SnsUserExpandDto userExpandDto = map.get("" + disMember2.getCusId());
                if (userExpandDto != null) {
                    disMember2.setUserExpandDto(userExpandDto);
                    disMember2.setShowName(disMember2.getShowName());
                }
            }
        }
        return disMemberList;
    }

    public String getDisMemberListCusId(List<DisMember> disMemberList) {// 获得用户ids
        String ids = "";
        if (disMemberList != null && disMemberList.size() > 0) {
            for (DisMember disMember : disMemberList) {
                ids += disMember.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 转让小组
     *
     * @param groupId 小组id
     * @return
     */
    public String updateMemberTransferId(DisMember disMember, Long cusId) throws Exception {
        Long num = disMemberDao.updateMemberTransferId(disMember, cusId);
        if (num == 1) {
            DisMember disMeber1 = new DisMember();
            disMeber1.setGroupId(disMember.getGroupId());
            disMeber1.setCusId(cusId);
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(cusId);
            disMeber1.setShowName(userExpandDto.getShowname());
            // 更新当前小组成员权限
            this.updateCustomerType(disMeber1);
            // 更新小组创建者
            disGroupService.updateDisgroupCreater(disMeber1);

            DisGroup disGroup = disGroupService.queryDisGroupDetailById(disMember.getGroupId());
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setAddTime(new Date());// 添加时间
            msgReceive.setContent("小组" + disGroup.getName() + "管理员转让给了你");
            msgReceive.setCusId(disMember.getCusId());// 我的用户id
            msgReceive.setReceivingCusId(cusId);// 给加入小组的小组成员发送站内信
            msgReceive.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);// 站内信
            msgReceive.setUpdateTime(new Date());// 添加时间
            msgReceiveService.addMsgReceive(msgReceive);// 发送站内信
            webHessianService.readMsgNumAddOrReset("sysMsgNum", cusId,"add");
            return SnsConstants.SUCCESS;// 返回成功
        }
        return SnsConstants.FALSE;// 返回失败
    }

    /**
     * 更新用户的类型
     *
     * @param disMember
     */
    public void updateCustomerType(DisMember disMember) throws Exception {
        disMemberDao.updateCustomerType(disMember);
        // 更新小组创建者
        disGroupService.updateDisgroupCreater(disMember);
    }

    /**
     * 查询用户所属小组id
     *
     * @param cusId
     * @return
     */
    public List<DisMember> queryGroupIdByCusId(Long cusId) throws Exception {
        @SuppressWarnings("unchecked")
        //获得当前用户信息
                List<DisMember> disMemberList = (List<DisMember>) memCache.get(MemConstans.DISGROUP_DYNAMIC + cusId);
        if (ObjectUtils.isNull(disMemberList)) {//如果为空查询库
            disMemberList = disMemberDao.queryGroupIdByCusId(cusId);
            //存缓存，5分钟
            memCache.set(MemConstans.DISGROUP_DYNAMIC + cusId, disMemberList, MemConstans.DISGROUP_DYNAMIC_TIME);
        }

        return disMemberList;
    }

    /**
     * 查询用户信息
     *
     * @param disMember
     * @return
     */
    public DisMember queryDisMemebrDetail(DisMember disMember) throws Exception {
        disMember = disMemberDao.queryDisMemebrDetail(disMember);
        if (ObjectUtils.isNotNull(disMember)) {
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(disMember.getCusId());
            if (ObjectUtils.isNotNull(userExpandDto)) {
                disMember.setUserExpandDto(userExpandDto);
                disMember.setShowName(userExpandDto.getShowname());
            }
        }
        return disMember;
    }

    /**
     * 人员每增加一次，dis_group成员则加1
     *
     * @param groupId 小组id
     * @param count   +1
     */
    public void updateDisGroupMemberCount(Long groupId, int count) throws Exception {
        disMemberDao.updateDisGroupMemberCount(groupId, count);

    }

    /**
     * 删除小组下的所有成员
     *
     * @param groupId
     * @throws Exception
     */
    public void deleteAllMemberByGroupId(Long groupId) throws Exception {
        disMemberDao.deleteAllMemberByGroupId(groupId);
    }

    /**
     * 提拔管理员解除管理员
     *
     * @param disMember
     * @return
     * @throws Exception
     */
    public String updatePromoteUserTransfer(DisMember disMember) throws Exception {
        Long num = disMemberDao.updatePromoteUserTransfer(disMember);
        if (num == 1) {
            return SnsConstants.SUCCESS;
        }
        return SnsConstants.FALSE;
    }
}
