package io.wangxiao.edu.home.service.impl.suggest;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.constants.web.LetterConstans;
import io.wangxiao.edu.home.constants.web.SuggestConstans;
import io.wangxiao.edu.home.dao.suggest.SugSuggestDao;
import io.wangxiao.edu.home.dao.suggest.SugSuggestReplyDao;
import io.wangxiao.edu.home.entity.suggest.SecondReply;
import io.wangxiao.edu.home.entity.suggest.SugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggestReply;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.letter.MsgReceiveService;
import io.wangxiao.edu.home.service.suggest.SugSuggestReplyService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import io.wangxiao.edu.sysuser.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sugSuggestReplyService")
public class SugSuggestReplyServiceImpl implements SugSuggestReplyService {

    @Autowired
    private SugSuggestReplyDao sugSuggestReplyDao;// 建议回复的Dao
    @Autowired
    private SugSuggestDao sugSuggestDao;// 建议的dao
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private UserIntegralService userIntegralService;

    /**
     * 添加SugSuggestReply
     *
     * @param sugSuggestReply 要添加的SugSuggestReply
     * @return id
     */
    public String addSugSuggestReply(SugSuggestReply sugSuggestReply) {
        Date date = new Date();
        sugSuggestReply.setAddtime(date);
        if (userExpandService.checkLimitOpt(MemConstans.SUGGEST_REPLY_LIMIT, sugSuggestReply.getCusId())) {
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(sugSuggestReply.getCusId());
            sugSuggestReply.setShowname(userExpandDto.getShowname());// 获得用户信息
            sugSuggestReply.setContent(keywordService.doFilter(sugSuggestReply.getContent()));
            Long falgNum = sugSuggestReplyDao.addSugSuggestReply(sugSuggestReply);// 添加SugSuggestReply
            if (falgNum.intValue() == 1) {// 添加SugSuggestReply 成功 建议的回复数加一
                SugSuggest sugSuggest = sugSuggestDao.getSugSuggestById(sugSuggestReply.getSuggestId());
                // 建议回复
                userExpandService.customerOptLimitCountAdd(MemConstans.SUGGEST_REPLY_LIMIT, sugSuggestReply.getCusId());// 给该用户添加一次一分钟回复的次数
                //更新回复次数
                //添加积分
                userIntegralService.addUserIntegral(IntegralKeyword.answer_problem.toString(), sugSuggestReply.getCusId(), sugSuggestReply.getSuggestId(), 0L, "");
                try {
                    //建议标题
                    String title = sugSuggestDao.getSugSuggestById(sugSuggestReply.getSuggestId()).getTitle();
                    //发送系统通知
                    msgReceiveService.addMessageByCusId(userExpandDto.getShowname() + "回复" + "<a href='" + CommonConstants.contextPath + "/front/question/info/" + sugSuggestReply.getSuggestId() + "'>" + title + "</a>",
                            sugSuggest.getCusId(),
                            LetterConstans.LETTER_TYPE_REPLY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sugSuggest.setReplyTime(date);
                sugSuggestDao.updateSugSuggest(sugSuggest);
                return "success";// 添加成功
            } else {
                return "false";// 添加失败
            }
        } else {
            return CommonConstants.ADDMOST;// 添加频率过快请重试
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
        if (ObjectUtils.isNull(sugSuggestReply)) {
            return "false";// 返回失败
        }
        if (sugSuggestReply.getCusId().longValue() == cusId.longValue()) {// 验证要删除的回复是不是自己的
            Long falgNum = sugSuggestReplyDao.deleteSugSuggestReplyById(id);// 删除回复
            if (falgNum.intValue() > 0) {
                SugSuggest sugSuggest = new SugSuggest();
                sugSuggest.setId(sugSuggestReply.getSuggestId());// set 建议id
                sugSuggestDao.updateSugSuggestBySuggestIdForReplycountSubtractOne(sugSuggest);// 建议回复数减一
                return "success";// 返回成功
            } else {
                return "false";// 返回失败
            }
        } else {
            return "false";// 返回失败
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
            return "success";// 返回成功
        } else {
            return "failure";// 返回失败
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
            Map<String, UserExpandDto> map = userExpandService.getUserExpandByUids(cusIdList);// 查询用户的信息
            if (ObjectUtils.isNotNull(map)) {
                for (SugSuggestReply reply : sugSuggestReplyList) {
                    UserExpandDto user = map.get(reply.getCusId() + "");
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
    public List<SugSuggestReply> querySugSuggestReplyAllListBySuggestId(Long SuggestId) throws Exception {
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
            Map<String, UserExpandDto> map = userExpandService.getUserExpandByUids(cusIdList);// 查询用户的信息
            if (ObjectUtils.isNotNull(map)) {
                for (SugSuggestReply reply : sugSuggestReplyList) {
                    UserExpandDto user = map.get(reply.getCusId() + "");
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

                UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(cusId);

                sugSuggest.setAcceptshowname(userExpandDto.getShowname());// set 用户名
                sugSuggest.setAcceptuid(cusId);// 采纳者id
                sugSuggestDao.updateSugSuggestBySuggestIdForStatus(sugSuggest);// 更新建议状态为不可回复


                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", sugSuggest.getCusId() + "");//用户id
                map.put("bizId", sugSuggest.getId() + "");// 学员活动（事件）id 商品id 试卷id
                map.put("type", 8 + "");//11观看课程 12购买了商品 13 考试
                map.put("desc", "采纳问题");// 动态描述
                map.put("title", sugSuggest.getTitle());// 辅助title
                map.put("assistId", 0L + "");// 辅助id
                String content = WebUtils.replaceTagHTML(sugSuggest.getContent());
                if (StringUtils.isNotEmpty(content)) {// 回复的内容
                    content = StringUtils.getLength(content, 300);
                    map.put("content", content);
                } else {
                    map.put("content", "");// 如果为空则set空
                }
                map.put("url", "");//操作url
                //snsHessianService.addDynamic(map);
                //采纳最佳答案添加积分
                userIntegralService.addUserIntegral(IntegralKeyword.problem_accrept.toString(), sugSuggestReply.getCusId(), sugSuggestId, cusId, "");
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

    @Override
    public Long createSecondReply(SecondReply secondReply) {
        secondReply.setCreateTime(new Date());
        return sugSuggestReplyDao.createSecondReply(secondReply);
    }

    @Override
    public List<SecondReply> getSecondReplyList(SecondReply secondReply, PageEntity page) {
        return sugSuggestReplyDao.getSecondReplyList(secondReply, page);
    }
}