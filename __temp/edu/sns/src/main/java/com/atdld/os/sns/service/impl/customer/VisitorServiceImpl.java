package com.atdld.os.sns.service.impl.customer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.dao.customer.VisitorDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.customer.Visitor;
import com.atdld.os.sns.service.customer.VisitorService;
import com.atdld.os.sns.service.user.SnsUserService;

@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorDao VisitorDao;
    @Autowired
    private SnsUserService snsUserService;

    /**
     * 查询该用户的最近访客
     *
     * @param cusId 用户id
     * @return
     */
    public List<Visitor> queryVisitorByCusId(Long cusId) throws Exception{
        List<Visitor> visitorList = VisitorDao.queryVisitorByCusId(cusId);// 查询该用户的最近访客
        if(ObjectUtils.isNotNull(visitorList)){
            Map<String, SnsUserExpandDto> map =snsUserService.getUserExpandsByCusId(getVisitorListVisitorCusId(visitorList));// 查询用户的信息
            if (visitorList != null && visitorList.size() > 0) {
                for (Visitor visitor : visitorList) {
                    SnsUserExpandDto userExpandDto = map.get("" + visitor.getVisitorCusId());//查询用户的信息
                    if (userExpandDto != null) {//如果能够查到则set 头像信息
                        visitor.setAvatar(userExpandDto.getAvatar());//set 头像信息
                        visitor.setShowname(userExpandDto.getShowname());//set用户名
                    }
                }
            }
        }
        return visitorList;// 查询该用户的最近访客
    }

    /**
     * 查询该用户的最近访客分页
     *
     * @param cusId 用户id
     * @return
     */
    public List<Visitor> queryVisitorByCusId(Long cusId, PageEntity page) throws Exception{
        List<Visitor> visitorList = VisitorDao.queryVisitorByCusId(cusId, page);// 查询该用户的最近访客
       
        if (visitorList != null && visitorList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getVisitorListVisitorCusId(visitorList));// 查询用户的信息
            for (Visitor visitor : visitorList) {
                SnsUserExpandDto userExpandDto = map.get("" + visitor.getVisitorCusId());//查询用户的信息
                if (userExpandDto != null) {//如果能够查到则set 头像信息
                    visitor.setAvatar(userExpandDto.getAvatar());//set 头像信息
                    visitor.setShowname(userExpandDto.getShowname());//set用户名
                    visitor.setUserExpandDto(userExpandDto);
                }
            }
        }
        return visitorList;// 查询该用户的最近访客
    }

    public String getVisitorListVisitorCusId(List<Visitor> visitorList) {//获得用户ids
        String ids = "";
        if (visitorList != null && visitorList.size() > 0) {
            for (Visitor visitor : visitorList) {
                ids += visitor.getVisitorCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 添加访客
     *
     * @param visitor 访客
     * @return
     */
    public Long addVisitor(Visitor visitor) {
        visitor.setViewDay(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));// set 每天记一次存年月日
        if (VisitorDao.clickVisitorByCusIdAndVisitorCusId(visitor) == 0) {// 如果已经添加了访客则更新添加时间字段如果没有添加过则添加访客
            return VisitorDao.addVisitor(visitor);// 添加访客
        } else {
            //VisitorDao.updateVisitorByCusIdAndVisitorCusId(visitor);
            return 0L;
        }
    }

    /**
     * 查询该用户的最近访问量
     *
     * @param cusId 用户id
     * @return
     */
    public int queryVisitorNumByCusId(Long cusId) {
        return VisitorDao.queryVisitorNumByCusId(cusId);// 查询该用户的最近访问量
    }
}
