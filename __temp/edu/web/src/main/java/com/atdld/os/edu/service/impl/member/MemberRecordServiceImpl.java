package com.atdld.os.edu.service.impl.member;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.member.MemberOrder;
import com.atdld.os.edu.entity.member.MemberOrderOptRecord;
import com.atdld.os.edu.entity.member.MemberRecord;
import com.atdld.os.edu.entity.member.MemberRecordDTO;
import com.atdld.os.edu.entity.member.MemberSale;
import com.atdld.os.edu.entity.member.MemberType;
import com.atdld.os.edu.constants.enums.MemberOptType;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.dao.member.MemberOrderOptRecordDao;
import com.atdld.os.edu.dao.member.MemberRecordDao;
import com.atdld.os.edu.service.member.MemberRecordService;
import com.atdld.os.edu.service.member.MemberSaleService;
import com.atdld.os.edu.service.member.MemberTypeService;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.atdld.os.sysuser.entity.SysUser;
/**
 * MemberRecord管理接口
 * User:
 * Date: 2014-09-26
 */
@Service("memberRecordService")
public class MemberRecordServiceImpl implements MemberRecordService{

 	@Autowired
    private MemberRecordDao memberRecordDao;
 	@Autowired
    private MemberSaleService memberSaleService;
 	@Autowired
    private WebsiteProfileService websiteProfileService;
 	@Autowired
    private MemberTypeService memberTypeService;
	@Autowired
 	private MemberOrderOptRecordDao memberOrderOptRecordDao;
	@Autowired
	private UserService userService;
    /**
     * 添加MemberRecord
     * @param memberRecord 要添加的MemberRecord
     * @return id
     */
    public void addMemberRecord(MemberOrder memberOrder){
    	//插入会员信息表
        MemberSale memberSale =memberSaleService.getMemberSaleById(memberOrder.getMemberId());//开通的会员类型
       
        MemberRecord memberRecord=getMemberRecordByCondition(memberOrder.getUserId(),memberOrder.getMemberType());
        Calendar now = Calendar.getInstance();
        if(memberRecord!=null)//该用户开通过该类型的会员
        {
        	if(memberRecord.getEndDate().getTime()>new Date().getTime()){//之前开通的会员未过期
        		now.setTime(memberRecord.getEndDate());
        	}
        	now.add(Calendar.DAY_OF_MONTH, memberSale.getDays());
    		memberRecord.setEndDate(now.getTime());
    		updateMemberRecord(memberRecord);
        }else{//该用户未开通过该类型的会员
        	memberRecord = new MemberRecord();
            memberRecord.setUserId(memberOrder.getUserId());
            memberRecord.setDescription("购买会员");
            now.add(Calendar.DAY_OF_MONTH, memberSale.getDays());
            memberRecord.setEndDate(now.getTime());
            memberRecord.setBeginDate(new Date());
            memberRecord.setMemberType(memberSale.getType());
            memberRecord.setStatus(0L);
            memberRecordDao.addMemberRecord(memberRecord);
        }
    	
    }

    /**
     * 修改MemberRecord
     * @param memberRecord 要修改的MemberRecord
     */
    public void updateMemberRecord(MemberRecord memberRecord){
     	memberRecordDao.updateMemberRecord(memberRecord);
    }

    /**
     * 根据id获取单个MemberRecord对象
     * @param id 要查询的id
     * @return MemberRecord
     */
    public MemberRecord getMemberRecordById(Long id){
    	return memberRecordDao.getMemberRecordById( id);
    }
    /**
     * 根据用户id、会员类型查询MemberRecord对象
     * @param userId
     * @param type
     * @return
     */
    public MemberRecord getMemberRecordByCondition(Long userId,Long type){
    	return memberRecordDao.getMemberRecordByCondition(userId,type);
    }
    /**
     * 查询用户的全部会员开通记录
     * @param userId
     * @return
     */
    public List<MemberRecordDTO> getMemberRecordByUser(Long userId){
    	return memberRecordDao.getMemberRecordByUser(userId);
    }
    /**
     * 根据条件获取MemberRecord列表
     * @param memberRecord 查询条件
     * @return List<MemberRecord>
     */
    public List<MemberRecordDTO> getMemberRecordPage(MemberRecordDTO memberRecordDTO,PageEntity page){
    	return memberRecordDao.getMemberRecordPage(memberRecordDTO,page);
    }

    /**
     * 判断用户会员课程权限
     * @param type
     * @return
     */
	public boolean checkUserMember(Long userId,Long courseId) {
		List<MemberType> memberTypes=memberTypeService.getMemberTypesBycourse(courseId);
		if(memberTypes.size()==0){//课程未设置会员类型
			return false;
		}
		//查找售卖方式开关配置
		Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
	  	@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) saleMap.get(WebSiteProfileType.sale.toString());
	  	if(map.get("verifyMember").equals("OFF")){//会员购买功能关闭
			return false;
		}
		boolean flag=false;//默认无权限
		Date date=new Date();
		if(map.get("verifyLevel").equals("OFF")){//会员课程权限单一判断
			for(MemberType memberType:memberTypes){//课程所属会员集合
				MemberRecord memberRecord=getMemberRecordByCondition(userId,memberType.getId());//查询用户某会员开通记录
				if(memberRecord!=null&&memberRecord.getEndDate().getTime()>date.getTime()){
					flag=true;//用户开通了此会员且未到期
				}
			}
			
		}else if(map.get("verifyLevel").equals("ON")){//会员课程权限大于等于判断
			List<MemberRecordDTO> memberRecords=getMemberRecordByUser(userId);
			for(MemberType memberType:memberTypes){//课程所属会员集合
				for(MemberRecordDTO memberRecord:memberRecords){
					if(memberRecord!=null&&memberRecord.getEndDate().getTime()>date.getTime()&&memberRecord.getMemberType()>=memberType.getId()){
						flag=true;//用户只要开通了大于等于该会员课程限制的未到期会员就可以观看
						return flag;
					}
				}
			}
		}
		return flag;
	}
	/**
	 * 获得记录详情
	 * 
	 * @param id
	 * @return
	 */
	public MemberRecordDTO getMemberRecordInfo(Long id) {
		return memberRecordDao.getMemberRecordInfo(id);
	}
	/**
	 * 延期操作
	 * 
	 * @param memberRecord
	 * @param sysUser
	 */
	public void updateMemberRecordEndDate(MemberRecord memberRecord,SysUser sysUser) {
		//更新操作 延期
		memberRecordDao.updateMemberRecord(memberRecord);
		//添加操作记录
		MemberOrderOptRecord memberOrderOptRecord = new MemberOrderOptRecord();
		memberOrderOptRecord.setUserId(memberRecord.getUserId());//用户id
		memberOrderOptRecord.setMemberRecordId(memberRecord.getId());//记录id
		memberOrderOptRecord.setOptuser(sysUser.getUserId());//操作人id
		memberOrderOptRecord.setOptusername(sysUser.getLoginName());//操作者名称
		memberOrderOptRecord.setCreateTime(new Date());
		memberOrderOptRecord.setType(MemberOptType.DELAY.toString());//类型
		memberOrderOptRecord.setDescription("延期操作");
		memberOrderOptRecordDao.addMemberOrderOptRecord(memberOrderOptRecord);
		//添加用户总操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		map.put("memberType", memberRecord.getMemberType()+"--会员类型");
		map.put("type", MemberOptType.DELAY.toString()+"--延期操作");
		map.put("memberRecordId", memberRecord.getId()+"--会员记录id");
		userService.addUserOptRecord(memberRecord.getUserId(), MemberOptType.DELAY.toString(), sysUser.getUserId(), sysUser.getLoginName(), memberRecord.getId(), gson.toJson(map));
	}
	/**
	 * 会员关闭 更新状态
	 * 
	 * @param memberRecord
	 */
	public void updateMemberStatus(MemberRecord memberRecord) {
		memberRecordDao.updateMemberStatus(memberRecord);
	}
}