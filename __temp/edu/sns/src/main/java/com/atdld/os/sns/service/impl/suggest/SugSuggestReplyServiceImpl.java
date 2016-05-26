package com.atdld.os.sns.service.impl.suggest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.constants.SuggestConstans;
import com.atdld.os.sns.dao.suggest.SugSuggestDao;
import com.atdld.os.sns.dao.suggest.SugSuggestReplyDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.suggest.SugSuggest;
import com.atdld.os.sns.entity.suggest.SugSuggestReply;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.impl.visitstat.VisitStatServiceImpl;
import com.atdld.os.sns.service.letter.MsgReceiveService;
import com.atdld.os.sns.service.suggest.SugSuggestReplyService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.visitstat.VisitStatService;

/**
 * SugSuggestReply管理接口 User:  Date: 2013-12-26
 */
@Service("sugSuggestReplyService")
public class SugSuggestReplyServiceImpl implements SugSuggestReplyService {

    @Autowired
    private SugSuggestReplyDao sugSuggestReplyDao;// 建议回复的Dao
    @Autowired
    private SugSuggestDao sugSuggestDao;// 建议的dao
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WebHessianService webHessianService;
    @Autowired
    private DynamicWebService dynamicWebService;// 动态service
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    private MsgReceiveService msgReceiveService;

    /**
     * 添加SugSuggestReply
     *
     * @param sugSuggestReply 要添加的SugSuggestReply
     * @return id
     */
    public String addSugSuggestReply(SugSuggestReply sugSuggestReply) throws Exception  {
        sugSuggestReply.setAddtime(new Date());
        if (snsUserService.checkLimitOpt(MemConstans.SUGGEST_REPLY_LIMIT, sugSuggestReply.getCusId())) {
            
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(sugSuggestReply.getCusId());
            
            sugSuggestReply.setShowname(userExpandDto.getShowname());// 获得用户信息

            sugSuggestReply.setContent(webHessianService.doFilter(sugSuggestReply.getContent()));
            Long falgNum = sugSuggestReplyDao.addSugSuggestReply(sugSuggestReply);// 添加SugSuggestReply
            if (falgNum.intValue() == 1) {// 添加SugSuggestReply 成功 建议的回复数加一
                SugSuggest sugSuggest = new SugSuggest();
                sugSuggest.setId(sugSuggestReply.getSuggestId());// set 建议id
                visitStatService.record(VisitStatServiceImpl.TYPES[5], sugSuggestReply.getSuggestId());// 5
                // 建议回复
                snsUserService.customerOptLimitCountAdd(MemConstans.SUGGEST_REPLY_LIMIT, sugSuggestReply.getCusId());// 给该用户添加一次一分钟回复的次数
                //添加积分
                addWebIntegral("answer_problem", sugSuggestReply.getCusId(), sugSuggestReply.getSuggestId(), 0L, "");
                try {
                	//建议标题
                	String title=sugSuggestDao.getSugSuggestById(sugSuggestReply.getSuggestId()).getTitle();
                	//发送系统通知
					msgReceiveService.addSystemMessageByCusId(userExpandDto.getShowname()+"回复了您的问答"+"<a href='"+CommonConstants.contextPath+"/sug/info/"+sugSuggestReply.getSuggestId()+"'>"+title+"</a>", sugSuggestReply.getCusId());
				} catch (Exception e) {
					e.printStackTrace();
				}
                return SnsConstants.SUCCESS;// 添加成功
            } else {
                return SnsConstants.FALSE;// 添加失败
            }
        } else {
            return SnsConstants.ADDMOST;// 添加频率过快请重试
        }
    }

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param id    要删除的id
     * @param cusId 当前用户id
     */
    public String deleteSugSuggestReplyById(Long id, Long cusId) {
        SugSuggestReply sugSuggestReply = sugSuggestReplyDao.getSugSuggestReplyById(id);
        if(ObjectUtils.isNull(sugSuggestReply)){
            return SnsConstants.FALSE;// 返回失败
        }
        if (sugSuggestReply.getCusId().longValue() == cusId.longValue()) {// 验证要删除的回复是不是自己的
            Long falgNum = sugSuggestReplyDao.deleteSugSuggestReplyById(id);// 删除回复
            if (falgNum.intValue() > 0) {
                SugSuggest sugSuggest = new SugSuggest();
                sugSuggest.setId(sugSuggestReply.getSuggestId());// set 建议id
                sugSuggestDao.updateSugSuggestBySuggestIdForReplycountSubtractOne(sugSuggest);// 建议回复数减一
                return SnsConstants.SUCCESS;// 返回成功
            } else {
                return SnsConstants.FALSE;// 返回失败
            }
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 根据id删除一个SugSuggestReply
     *
     * @param id 要删除的id
     */
    public String deleteSugSuggestReplyById(Long id) {
        SugSuggestReply sugSuggestReply = getSugSuggestReplyById(id);// 通过id
        // 查询回复
        Long falgNum = sugSuggestReplyDao.deleteSugSuggestReplyById(id);// 删除回复
        if (falgNum.intValue() > 0) {
            SugSuggest sugSuggest = new SugSuggest();

            sugSuggest.setId(sugSuggestReply.getSuggestId());// set 建议id
            sugSuggestDao.updateSugSuggestBySuggestIdForReplycountSubtractOne(sugSuggest);// 建议回复数减一
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;// 返回失败
        }
    }

    /**
     * 修改SugSuggestReply
     *
     * @param sugSuggestReply 要修改的SugSuggestReply
     */
    public void updateSugSuggestReply(SugSuggestReply sugSuggestReply) {
        sugSuggestReplyDao.updateSugSuggestReply(sugSuggestReply);
    }

    /**
     * 根据id获取单个SugSuggestReply对象
     *
     * @param id 要查询的id
     * @return SugSuggestReply
     */
    public SugSuggestReply getSugSuggestReplyById(Long id) {
        return sugSuggestReplyDao.getSugSuggestReplyById(id);
    }

    /**
     * 根据条件获取SugSuggestReply列表
     *
     * @param sugSuggestReply 查询条件
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> getSugSuggestReplyList(SugSuggestReply sugSuggestReply) {
        return sugSuggestReplyDao.getSugSuggestReplyList(sugSuggestReply);
    }

    /**
     * 通过建议id 查询该建议下的回复
     *
     * @param SuggestId 建议id
     * @param isBest    是否是最佳答案
     * @return List<SugSuggestReply>
     * @throws Exception
     */
    public List<SugSuggestReply> querySugSuggestReplyListBySuggestId(Long SuggestId, int isBest, PageEntity page) throws Exception {
        SugSuggestReply sugSuggestReply = new SugSuggestReply();
        sugSuggestReply.setSuggestId(SuggestId);// set 建议id
        sugSuggestReply.setIsbest(isBest);
        List<SugSuggestReply> sugSuggestReplyList = sugSuggestReplyDao.querySugSuggestReplyListBySuggestId(sugSuggestReply, page);// 通过建议id

        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestReplyList)) {
            String cusIdList = "";
            for (SugSuggestReply sug : sugSuggestReplyList) {
                cusIdList = cusIdList + sug.getCusId() + ",";
            }
            cusIdList = cusIdList.substring(0, cusIdList.length() - 1);
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIdList);// 查询用户的信息
            if (ObjectUtils.isNotNull(map)) {
                for (SugSuggestReply reply : sugSuggestReplyList) {
                    SnsUserExpandDto user = map.get(reply.getCusId() + "");
                    if (user != null) {// 如果能够查到则set 头像信息
                        reply.setShowname(user.getShowname());
                        reply.setUserExpandDto(user);
                    }
                }
            }
        }
        // 查询该建议下的回复
        return sugSuggestReplyList;
    }

    /**
     * 通过建议id 查询该建议下全部的回复
     *
     * @param SuggestId 建议id
     * @return List<SugSuggestReply>
     */
    public List<SugSuggestReply> querySugSuggestReplyAllListBySuggestId(Long SuggestId) throws Exception{
        SugSuggestReply sugSuggestReply = new SugSuggestReply();
        sugSuggestReply.setSuggestId(SuggestId);// set 建议id
        List<SugSuggestReply> sugSuggestReplyList = sugSuggestReplyDao.querySugSuggestReplyAllListBySuggestId(sugSuggestReply);// 通过建议id
        // 查询该建议下全部的回复
        //循环博客查询showname
        if (ObjectUtils.isNotNull(sugSuggestReplyList)) {
            String cusIdList = "";
            for (SugSuggestReply sug : sugSuggestReplyList) {
                cusIdList = cusIdList + sug.getCusId() + ",";
            }
            cusIdList = cusIdList.substring(0, cusIdList.length() - 1);
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIdList);// 查询用户的信息
            if (ObjectUtils.isNotNull(map)) {
                for (SugSuggestReply reply : sugSuggestReplyList) {
                    SnsUserExpandDto user = map.get(reply.getCusId() + "");
                    if (user != null) {// 如果能够查到则set 头像信息
                        reply.setShowname(user.getShowname());
                        reply.setUserExpandDto(user);
                    }
                }
            }
        }
        return sugSuggestReplyList;
    }

    /**
     * 通过回复id 更新isBest 推荐回复为最佳答案或取消最佳答案
     *
     * @param sugSuggestReplyId 回复的id
     * @param sugSuggestId      建议id
     * @param isBest            是否为最佳答案的状态 建议的status状态和回复的isbest一样所以才能这么用
     * @return int
     * @throws Exception
     */
    public Long updateSugSuggestReplyBySugSuggestReplyIdForIsBest(Long sugSuggestReplyId, Long sugSuggestId, int isBest, Long cusId) throws Exception {
        SugSuggestReply sugSuggestReply = getSugSuggestReplyById(sugSuggestReplyId);
        SugSuggest sugSuggest = sugSuggestDao.getSugSuggestById(sugSuggestId);
        if (sugSuggestReply != null && sugSuggest != null && sugSuggestReply.getCusId().longValue() == sugSuggest.getCusId().longValue()) {// 如果要推荐的最佳回复是建议创建着自己发的则失败
            return 0L;
        }
        if (sugSuggest.getCusId().longValue() == cusId.longValue()) {// 验证要推荐为正确答案的建议是否是当前登陆用户创建的
            sugSuggestReply = new SugSuggestReply();
            sugSuggestReply.setId(sugSuggestReplyId);// set 回复id
            sugSuggestReply.setIsbest(SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES);// 推荐回复
            sugSuggestReply.setSuggestId(sugSuggestId);// 建议id
            List<SugSuggestReply> sugSuggestReplyList = sugSuggestReplyDao.querySugSuggestReplyAllListBySuggestId(sugSuggestReply);
            if (isBest == SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES) {// 如果要推荐回复
                if (sugSuggestReplyList != null && sugSuggestReplyList.size() > 0) {// 该建议下存在最佳答案则不能在推荐最佳答案
                    return 0L;// 返回失败
                }
            }
            if (isBest == SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_NO) {// 如果要取消推荐回复
                if (sugSuggestReplyList == null || sugSuggestReplyList.size() == 0) {// 该建议下不存在最佳答案则不能取消推荐回复
                    return 0L;// 返回失败
                }
            }
            sugSuggestReply.setIsbest(isBest);// set 最佳答案
            Long flagnum = sugSuggestReplyDao.updateSugSuggestReplyBySugSuggestReplyIdForIsBest(sugSuggestReply);// 通过回复id
            // 推荐回复为最佳答案
            if (flagnum.intValue() > 0 && isBest == SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES) {// 当更新的条数大于0时则更新建议为不可回复状态
                sugSuggest = new SugSuggest();
                sugSuggest.setId(sugSuggestId);// set 建议id
                sugSuggest.setStatus(isBest);// set status 状态
                
                SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(cusId);
                
                sugSuggest.setAcceptshowname(userExpandDto.getShowname());// set 用户名
                sugSuggest.setAcceptuid(cusId);// 采纳者id
                sugSuggestDao.updateSugSuggestBySuggestIdForStatus(sugSuggest);// 更新建议状态为不可回复
                dynamicWebService.addDynamicWebForSuggestRecommend(sugSuggestId);
                //采纳最佳答案添加积分
                addWebIntegral("problem_accrept", sugSuggestReply.getCusId(), sugSuggestId, cusId, "");
            }
            if (flagnum.intValue() > 0 && isBest == SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_NO) {// 取消推荐则修改状态且采纳者人和id情况
                sugSuggest = new SugSuggest();
                sugSuggest.setId(sugSuggestId);// set 建议id
                sugSuggest.setStatus(isBest);// set status 状态
                sugSuggestDao.updateSugSuggestBySuggestIdForStatus(sugSuggest);// 更新建议状态为不可回复
            }
            return flagnum;
        } else {
            return 0L;
        }
    }

    /**
     * 通过回复id 更新isBest 推荐回复为最佳答案或取消最佳答案
     *
     * @param sugSuggestReplyId 回复的id
     * @param sugSuggestId      建议id
     * @param isBest            是否为最佳答案的状态 建议的status状态和回复的isbest一样所以才能这么用
     * @return int
     */
    public Long updateSugSuggestReplyBySugSuggestReplyIdForIsBest(Long sugSuggestReplyId, Long sugSuggestId, int isBest) {
        SugSuggestReply sugSuggestReply = new SugSuggestReply();
        sugSuggestReply.setId(sugSuggestReplyId);// set 回复id
        sugSuggestReply.setIsbest(SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES);// 推荐回复
        sugSuggestReply.setSuggestId(sugSuggestId);// 建议id
        List<SugSuggestReply> sugSuggestReplyList = sugSuggestReplyDao.querySugSuggestReplyAllListBySuggestId(sugSuggestReply);
        if (isBest == SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES) {// 如果要推荐回复
            if (sugSuggestReplyList != null && sugSuggestReplyList.size() > 0) {// 该建议下存在最佳答案则不能在推荐最佳答案
                return 0L;// 返回失败
            }
        }
        if (isBest == SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_NO) {// 如果要取消推荐回复
            if (sugSuggestReplyList == null || sugSuggestReplyList.size() == 0) {// 该建议下不存在最佳答案则不能取消推荐回复
                return 0L;// 返回失败
            }
        }
        sugSuggestReply.setIsbest(isBest);// set 最佳答案
        Long flagnum = sugSuggestReplyDao.updateSugSuggestReplyBySugSuggestReplyIdForIsBest(sugSuggestReply);// 通过回复id
        // 更新isBest
        // 推荐回复为最佳答案
        if (flagnum.intValue() > 0) {// 当更新的条数大于0时则更新建议为不可回复状态
            SugSuggest sugSuggest = new SugSuggest();
            sugSuggest.setId(sugSuggestId);// set 建议id
            sugSuggest.setStatus(isBest);// set status 状态
            sugSuggestDao.updateSugSuggestBySuggestIdForStatus(sugSuggest);// 更新建议状态为不可回复
        }
        return flagnum;
    }
    //添加网校考试积分
    public void addWebIntegral(String keyWord, Long userId, Long other, Long fromUserId, String otherScorel) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        //关键字
        map.put("keyWord",keyWord);
        // 用户id
        map.put("userId", userId + "");
        //辅助id
        map.put("other", other + "");
        // 来源id
        map.put("fromUserId", fromUserId + "");
        // otherScore
        map.put("otherScorel", otherScorel);
        webHessianService.addUserIntegral(map);
    }
}