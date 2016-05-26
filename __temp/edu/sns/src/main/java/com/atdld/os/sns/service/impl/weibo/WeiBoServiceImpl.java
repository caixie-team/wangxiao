package com.atdld.os.sns.service.impl.weibo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.sns.constants.DynamicConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.constants.eunms.SnsUserExpandType;
import com.atdld.os.sns.dao.weibo.WeiBoCommentDao;
import com.atdld.os.sns.dao.weibo.WeiBoDao;
import com.atdld.os.sns.entity.customer.SnsUserExpand;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author
 * @ClassName WeiBoServiceImpl
 * @package com.atdld.open.sns.service.impl.weibo
 * @description 微博的service
 * @Create Date: 2013-12-10 下午4:14:50
 */
@Service("weiBoService")
public class WeiBoServiceImpl implements WeiBoService {
    @Autowired
    private WeiBoDao weiBoDao;// 微博dao
    @Autowired
    private WeiBoCommentDao weiBoCommentDao;// 微博评论dao
    @Autowired
    private DynamicWebService dynamicWebService;// 动态service
    @Autowired
    private WebHessianService webHessianService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private FriendService friendService;
    private MemCache memCache = MemCache.getInstance();// 缓存

    /**
     * 添加微博
     *
     * @param weiBo
     * @throws Exception
     */
    public String addWeiBo(WeiBo weiBo) throws Exception {
        if (snsUserService.checkLimitOpt(MemConstans.WEIBO_LIMIT, weiBo.getCusId())) {
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(weiBo.getCusId());
            weiBo.setShowname(userExpandDto.getShowname());// 会员名
            weiBo.setContent(WebUtils.replaceTagHref(webHessianService.doFilter(weiBo.getContent())));
            weiBoDao.addWeiBo(weiBo);// 添加微博
            snsUserService.customerOptLimitCountAdd(MemConstans.WEIBO_LIMIT, weiBo.getCusId());// 给该用户添加一次添加微博的操作次数
            Map<String,String> map=new HashMap<String, String>();
            map.put("cusId", weiBo.getCusId()+"");
            map.put("count", 1L+"");
            map.put("operation", SnsUserExpand.ADD);
            // 微博数加一
            map.put("type", SnsUserExpandType.weiboNum.toString());
            webHessianService.updateUserExpandCount(map);
            memCache.remove(MemConstans.USEREXPAND_INFO + userExpandDto.getCusId());// 移除该用户的缓存保证微博数正确
            if (weiBo.getStatus() == 0) {// 如果该微博的状态为公开才添加动态 不公开不添加动态
                dynamicWebService.addDynamicWebForWeiBo(weiBo);// 添加微博动态
            }
            return SnsConstants.SUCCESS;// 添加微博成功
        } else {
            return SnsConstants.ADDMOST;// 添加微博过多
        }
    }

    /**
     * 转发微博
     */
    public String addForward(Long weiboId, Long cusId, String content) throws Exception {
        WeiBo weiBo = new WeiBo();
        weiBo.setId(weiboId);
        weiBo.setCusId(cusId);
        //查询要转载的微博
        QueryWeiBo queryWeiBo = weiBoDao.queryWeiBoById(weiBo);
        //如果 查询出的转载的id为null则为原始微博则用id查询，如果转载id不为null 则说明这篇微博也是转载的则用原始微博id进行判断
        weiBo.setForwardId(ObjectUtils.isNull(queryWeiBo.getForwardId()) ? queryWeiBo.getId() : queryWeiBo.getForwardId());
        if (queryIsForwardWeiBo(weiBo)) {
            //转发过该微博
            return SnsConstants.FORWARD;
        }
        WeiBo newweiBo = new WeiBo();
        newweiBo.setId(weiBo.getForwardId());
        QueryWeiBo newQueryWB = weiBoDao.queryWeiBoById(newweiBo);
        if (ObjectUtils.isNull(newQueryWB)) {
            //转发的该微博原始微博已删除
            return SnsConstants.DELFORWARD;
        }
        if (newQueryWB.getCusId().longValue() == cusId.longValue()) {
            //转发的该微博为自己原创
            return SnsConstants.FORWARDSELF;
        }
        if (ObjectUtils.isNull(queryWeiBo)) {
            return SnsConstants.FALSE;
        }
        weiBo.setId(0L);
        weiBo.setCusId(cusId);
        weiBo.setAddTime(new Date());
        weiBo.setUpdateTime(new Date());
        weiBo.setContent(content);
        // 添加转发微博
        weiBoDao.addWeiBo(weiBo);
        //更新原创微博转发数
        weiBoDao.updateWeiBoForwardNumAddOne(weiBo.getForwardId());
        
        Map<String,String> map=new HashMap<String, String>();
        map.put("cusId", cusId+"");
        map.put("count", 1L+"");
        map.put("operation", SnsUserExpand.ADD);
        // 微博数加一
        map.put("type", SnsUserExpandType.weiboNum.toString());
        webHessianService.updateUserExpandCount(map);// 用户微博数加一
        return SnsConstants.SUCCESS;
    }

    /**
     * 验证某用户是否转发过该微博
     *
     * @param weiBo 微博实体
     */
    public boolean queryIsForwardWeiBo(WeiBo weiBo) {
        //查询该用户是否转发过该微博
        WeiBo wb = weiBoDao.queryIsForwardWeiBo(weiBo);
        //如果为空则没有转发过 如果不为空则转发过
        if (ObjectUtils.isNotNull(wb)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除微博
     *
     * @param weiBo
     * @throws Exception
     */
    public String delWeiBo(WeiBo weiBo) throws Exception {
        if (weiBoDao.clickMyWeiBo(weiBo.getCusId(), weiBo.getId())) {
            Long flagNum = weiBoDao.delWeiBo(weiBo);// 删除微博
            if (flagNum > 0) {
                Comment comment = new Comment();
                comment.setWeiboId(weiBo.getId());// set微博id 删除评论
                weiBoCommentDao.delCommentByWbId(comment);// 删除微博评论
                SnsUserExpand customer = new SnsUserExpand();
                customer.setCusId(weiBo.getCusId());// set用户id
                if (!ObjectUtils.isNull(snsUserService.getUserExpandByCusId(weiBo.getCusId()))) {// 如果是第一次添加微博则往customer表中添加数据
                	 Map<String,String> map=new HashMap<String, String>();
                     map.put("cusId", weiBo.getCusId()+"");
                     map.put("count", 1L+"");
                     map.put("operation", SnsUserExpand.SUBTRACTION);
                     // 微博数减一
                     map.put("type", SnsUserExpandType.weiboNum.toString());
                     webHessianService.updateUserExpandCount(map);
                }
                // 删除动态
                DynamicWeb dynamicWeb = new DynamicWeb();
                dynamicWeb.setBizId(weiBo.getId());
                dynamicWeb.setType(DynamicConstans.DYNAMICWEB_TYPE_WEIBO);
                dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
                return SnsConstants.SUCCESS;
            } else {
                return SnsConstants.FALSE;
            }
        } else {
            return SnsConstants.FALSE;
        }

    }

    /**
     * 查询我的微博
     *
     * @param cusId 用户id
     * @param page  分页参数
     * @return List<QueryWeiBo> 我的微博list
     */
    public List<QueryWeiBo> queryMyWeiBo(Long cusId, int status, PageEntity page) throws Exception {
        WeiBo weiBo = new WeiBo();
        weiBo.setCusId(cusId);// set用户id
        weiBo.setStatus(status);
        List<QueryWeiBo> queryWeiBoList = weiBoDao.queryMyWeiBo(weiBo, page);// 通过用户id
        // 查询
        // 我的微博
        queryWeiBoList = initQueryWeiBoList(queryWeiBoList, weiBo.getCusId());
        return queryWeiBoList;
    }

    public String getWeiBoListCusId(List<QueryWeiBo> queryWeiBoList) {// 获得用户ids
        String ids = "";
        if (queryWeiBoList != null && queryWeiBoList.size() > 0) {
            for (QueryWeiBo queryWeiBo : queryWeiBoList) {
                ids += queryWeiBo.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 查询我关注的人的微博
     *
     * @param cusId    用具id
     * @param page 分页参数
     * @return  List<QueryWeiBo>
     */
    public List<QueryWeiBo> queryAttentionWeiBo(Long cusId, PageEntity page) throws Exception {
        WeiBo weiBo = new WeiBo();
        weiBo.setCusId(cusId);// set用户id
        List<QueryWeiBo> queryWeiBoList = weiBoDao.queryAttentionWeiBo(weiBo, page);// 通过用户id
        // 查询我关注的人的微博
        queryWeiBoList = initQueryWeiBoList(queryWeiBoList, weiBo.getCusId());
        return queryWeiBoList;
    }

    /**
     * 查询热门微博
     *
     * @param weiBo 微博
     * @param page  分页参数
     * @return List<QueryWeiBo> 微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryHotWeiBo(WeiBo weiBo, PageEntity page) throws Exception {
        List<QueryWeiBo> queryWeiBoList = weiBoDao.queryHotWeiBo(weiBo, page);
        queryWeiBoList = initQueryWeiBoList(queryWeiBoList, weiBo.getCusId());
        // 查询热门微博
        return queryWeiBoList;
    }

    /**
     * 查询一个星期发表微博最多的用户数
     *
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<QueryWeiBo> queryCustomerForWeiBoNumByWeek() throws Exception {
        List<QueryWeiBo> queryWeiBoList = (List<QueryWeiBo>) memCache.get(MemConstans.WEIBO_NUM_WEEK);// 从缓存中读取
        if (ObjectUtils.isNull(queryWeiBoList)) {// 如果为null则从备份缓存中读取
            queryWeiBoList = (List<QueryWeiBo>) memCache.get(MemConstans.WEIBO_NUM_WEEK + MemConstans.MEM_HOT_BAK);// 从备份缓存中取值
            if (ObjectUtils.isNull(queryWeiBoList)) {// 如果备份缓存中没有 则从数据库中查
                queryWeiBoList = weiBoDao.queryCustomerForWeiBoNumByWeek();// 查询一个星期发表微博最多的用户数
                if (queryWeiBoList != null && queryWeiBoList.size() > 0) {
                	 Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getWeiBoListCusId(queryWeiBoList));// 查询用户的信息
                    for (QueryWeiBo queryWeiBo : queryWeiBoList) {
                        SnsUserExpandDto userExpandDto = map.get("" + queryWeiBo.getCusId());// 查询用户的信息
                        if (userExpandDto != null) {// 如果能够查到则set 头像信息
                            queryWeiBo.setAvatar(userExpandDto.getAvatar());// set// 头像信息
                            queryWeiBo.setShowname(userExpandDto.getShowname());
                        }
                    }
                    memCache.set(MemConstans.WEIBO_NUM_WEEK, queryWeiBoList, MemConstans.WEIBO_NUM_WEEK_TIME);// 把数据放入缓存中
                    memCache.set(MemConstans.WEIBO_NUM_WEEK + MemConstans.MEM_HOT_BAK, queryWeiBoList, MemConstans.WEIBO_NUM_WEEK_TIME);// 把数据放入备份缓存中
                }
            }
        }

        return queryWeiBoList;
    }

    /**
     * 评论最多的微博 根据评论数排序
     *
     * @param weiBo 传入cusId 用户id
     * @param page  分页参数
     * @return List<QueryWeiBo> 查询的微博list
     * @throws Exception
     */
    public List<QueryWeiBo> queryCommentMostWeiBo(WeiBo weiBo, PageEntity page) throws Exception {
        List<QueryWeiBo> queryWeiBoList = weiBoDao.queryCommentMostWeiBo(weiBo, page);// 评论最多的微博
        // 根据评论数排序
        queryWeiBoList = initQueryWeiBoList(queryWeiBoList, weiBo.getCusId());
        return queryWeiBoList;
    }

    /**
     * 查询所有微博
     *
     * @param weiBo 微博
     * @param page  分页参数
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> queryAllWeiBo(WeiBo weiBo, PageEntity page) throws Exception {
        List<QueryWeiBo> queryWeiBoList = weiBoDao.queryAllWeiBo(weiBo, page);// 查询所有微博
        queryWeiBoList = initQueryWeiBoList(queryWeiBoList, weiBo.getCusId());
        return queryWeiBoList;
    }


    /**
     * 查询我关注的用户的列表
     *
     * @param friend 用户关注的实体
     * @param page         分页参数
     * @return List<Customer>用户list
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryMyAttentionCustomer(Friend friend, PageEntity page) throws Exception {
        //查询我关注的，只查询好友表
        List<SnsUserExpandDto> queryCustomerList = snsUserService.queryMyAttentionCustomer(friend, page);// 查询我关注的用户的列表
        String cusIds="";
        List<Long> friends = new ArrayList<Long>();
        Map<Long,Integer> friendMap=new HashMap<Long,Integer>();
        Map<Long,String> remarksMap=new HashMap<Long,String>();
        if(ObjectUtils.isNotNull(queryCustomerList)){
            for(SnsUserExpandDto dto:queryCustomerList){
            	cusIds+=dto.getCusFriendId()+",";
            	friends.add(dto.getCusFriendId());
                friendMap.put(dto.getCusFriendId(), dto.getFriendId());
                remarksMap.put(dto.getCusFriendId(), dto.getRemarks());//备注
            }
            Map<String, SnsUserExpandDto>  map= snsUserService.getUserExpandsByCusId(cusIds);
            List<SnsUserExpandDto> userTemp=new ArrayList<SnsUserExpandDto>();
            for(Entry<String, SnsUserExpandDto> entry: map.entrySet()) { //判断是否关注
            	entry.getValue().setFriendId(friendMap.get(entry.getValue().getId()));
            	if(remarksMap.get(entry.getValue().getId())!=""){
            		entry.getValue().setRemarks(remarksMap.get(entry.getValue().getId()));
            	}
            	userTemp.add(entry.getValue());
			}
            queryCustomerList=userTemp;
        }else{
            return null;
        }
        //查询共同好友
        Map<Long, Integer> map = friendService.getCommonfriend(friend.getCusId(), friends);// 获得共同好友
        for (SnsUserExpandDto queryCustomer : queryCustomerList) {// 循环好友
            Integer num = map.get(queryCustomer.getCusId());// 从map 中获得好友
            if (num != null) {// 如果不为null则set 到 共同好友里
                queryCustomer.setCommonFriendNum(num);
            }
        }
        return queryCustomerList;
    }

    public String getQueryCustomerListCusId(List<SnsUserExpandDto> queryCustomerList) {// 获得用户ids
        String ids = "";
        if (queryCustomerList != null && queryCustomerList.size() > 0) {
            for (SnsUserExpandDto queryCustomer : queryCustomerList) {
                ids += queryCustomer.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 取消关注用户
     *
     * @param friend 需要 当前用户id.取消者用户id
     * @throws Exception
     */
    public String delCusAttention(Friend friend) throws Exception {
        // 查询是否关注过该用户 如果关注过才可以取消关注
//        if (cusAttentionDao.queryCusAttentionByCusIdAndAttentionIdNum(cusAttention) > 0) {// 验证是否关注过该用户如果关注过才做取消关注的操作
//            cusAttentionDao.delCusAttentionByCusIdAndAttentionId(cusAttention);// 取消关注用户
        SnsUserExpand customer = new SnsUserExpand();
        customer.setCusId(friend.getCusId());// set用户id
        Map<String,String> map=new HashMap<String, String>();
        map.put("cusId", customer.getCusId()+"");
        map.put("count", 1L+"");
        map.put("operation", SnsUserExpand.SUBTRACTION);
        
        if (!ObjectUtils.isNull(snsUserService.getUserExpandByCusId(friend.getCusId()))) {// 如果是第一次添加微博则往customer表中添加数据
            // 我的关注数减一
        	map.put("type", SnsUserExpandType.attentionNum.toString());
            webHessianService.updateUserExpandCount(map);
        }
        customer = new SnsUserExpand();
        customer.setCusId(friend.getCusFriendId());// set我关注的人的用户id
        if (!ObjectUtils.isNull(snsUserService.getUserExpandByCusId(friend.getCusFriendId()))) {// 如果是第一次添加微博则往customer表中添加数据
            // 我关注的人的粉丝数减一
        	map.put("type", SnsUserExpandType.fansNum.toString());
            webHessianService.updateUserExpandCount(map);;
        }
        return SnsConstants.SUCCESS;// 成功
//        } else {
//            return SnsConstants.FALSE;// 失败
//        }
    }

    /**
     * 查询我的粉丝用户的列表
     *
     * @param friend
     * @param page         分页参数
     * @return List<Customer> 用户关注的list
     * @throws Exception
     */
    public List<SnsUserExpandDto> queryMyFans(Friend friend, PageEntity page) throws Exception {
        //查询粉丝的列表
        List<SnsUserExpandDto> queryCustomerList = snsUserService.queryMyFans(friend, page);// 查询我的粉丝用户的列表
        //根据获取的用户id再去查询用户的信息
        if(ObjectUtils.isNotNull(queryCustomerList)){
            String cusIds="";
            for(SnsUserExpandDto dto:queryCustomerList){
            	cusIds+=dto.getCusId()+",";
            }
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(cusIds);
            if(ObjectUtils.isNotNull(map)){
                for(SnsUserExpandDto expandDto :queryCustomerList){
                    SnsUserExpandDto userExpandDto1=map.get(expandDto.getCusId().toString());
                    if(ObjectUtils.isNotNull(userExpandDto1)){
                        expandDto.setExtendInfo(userExpandDto1);
                    }
                }
            }
        }
        return queryCustomerList;
    }

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博
     * @throws Exception
     */
    public String updateWeiBoForTop(WeiBo weiBo) throws Exception {
        Long num = weiBoDao.updateWeiBoForTop(weiBo);// 设置该微博置顶
        if (num == 1) {
            return SnsConstants.SUCCESS;// 成功
        } else {
            return SnsConstants.FALSE;// 失败
        }
    }

    /**
     * 设置该微博置顶
     *
     * @param weiBo 微博
     * @throws Exception
     */
    public void updateQuXiaoWeiBoForTop(WeiBo weiBo) throws Exception {
        weiBoDao.updateQuXiaoWeiBoForTop(weiBo);// 设置该微博置顶
    }

    /**
     * 后台查询微博
     *
     * @param queryWeiBo 微博
     * @param page  分页参数
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> queryAdminAllWeiBo(QueryWeiBo queryWeiBo, PageEntity page) throws Exception {
        return weiBoDao.queryAdminAllWeiBo(queryWeiBo, page);// 后台查询微博
    }

    /**
     * 通过微博id 查询微博
     *
     * @param weiBo 传入微博id
     * @return QueryWeiBo
     * @throws Exception
     */
    public QueryWeiBo queryWeiBoById(WeiBo weiBo) throws Exception {
        return weiBoDao.queryWeiBoById(weiBo);// 通过微博id 查询微博
    }

    /**
     * 根据微博id让该微博评论数更新
     *
     * @param weiboId 微博id
     * @param count   要更新的数量
     */
    public void updateWeiBoCommentNumAddCount(Long weiboId, int count) {
        weiBoDao.updateWeiBoCommentNumAddCount(weiboId, count);// 根据微博id让该微博评论数更新
    }

    /**
     * 根据微博id让该微博评论数减一
     *
     * @param weiboId 微博id
     */
    public void updateWeiBoCommentNumSubtractCount(Long weiboId) {
        weiBoDao.updateWeiBoCommentNumSubtractCount(weiboId);
    }

    /**
     * lucene方法 查询传入微博id 查询微博
     *
     * @param wbIds 微博id的list
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<QueryWeiBo> getLuceneWeiBoByIds(List<Long> wbIds, Long cusId) throws Exception {
        return weiBoDao.getLuceneWeiBoByIds(wbIds, cusId);// lucene方法 查询传入微博id
        // 查询微博
    }

    /**
     * 查出这个微博id之后的微博行数和最大微博id
     *
     * @param weiBoId 起始微博id
     * @return Map<String,Object> 返回两个参数一个是微博行数和最大微博id
     * @throws Exception
     */
    public Map<String, Object> getWeiBoCountAfterId(Long weiBoId) throws Exception {
        return weiBoDao.getWeiBoCountAfterId(weiBoId);// 查出这个微博id之后的微博行数和最大微博id
    }

    /**
     * 查出从starWeiBoId（起始行数）开始的微博 一共 pageSize（需要查出多少条）条
     * 要在minWeiBoId（查询的where条件的最小微博id）和maxWeiBoId（查询的where条件的最大微博id）之间查
     *
     * @param starWeiBoId 起始行数
     * @param pageSize    需要查出多少条
     * @param minWeiBoId  查询的where条件的最小微博id
     * @param maxWeiBoId  查询的where条件的最大微博id
     * @return List<QueryWeiBo>
     * @throws Exception
     */
    public List<WeiBo> getQuestionByPageQuery(Long starWeiBoId, Long pageSize, Long minWeiBoId, Long maxWeiBoId) throws Exception {
        return weiBoDao.getQuestionByPageQuery(starWeiBoId, pageSize, minWeiBoId, maxWeiBoId);
    }

    /**
     * 查询微博根据用户id
     *
     * @param userId num
     * @return List<WeiBo>
     * @throws Exception
     */
    public List<WeiBo> queryPersonWeiBoById(Long userId, int status, int num) throws Exception {
        List<WeiBo> weiBoList = weiBoDao.queryPersonWeiBoById(userId, status, num);
        if (ObjectUtils.isNotNull(weiBoList) && weiBoList.size() > 0) {
            SnsUserExpandDto customer = new SnsUserExpandDto();
            customer.setCusId(userId);
            customer = snsUserService.getUserExpandByCusId(userId);
            for (WeiBo weiBo : weiBoList) {
                weiBo.setShowname(customer.getShowname());
            }
        }
        return weiBoList;
    }

    /**
     * 处理微博的数据查询微博的用户名 用户头像  转发数等
     *
     * @param queryWeiBoList
     * @return
     * @throws Exception
     */
    public List<QueryWeiBo> initQueryWeiBoList(List<QueryWeiBo> queryWeiBoList, Long cusId) throws Exception {
        String ids = "";
        if (ObjectUtils.isNotNull(queryWeiBoList)) {
            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getWeiBoListCusId(queryWeiBoList));// 查询用户的信息
            //用户登陆状态才查询是否关注
            List<SnsUserExpandDto>  userExpandDtos=null;
            if (cusId != null && cusId.longValue() != 0) {
                List<Long> list = new ArrayList<Long>();
                for (QueryWeiBo queryWeiBo : queryWeiBoList) {
                    list.add(queryWeiBo.getCusId());
                }
                userExpandDtos = snsUserService.queryIsFrirndByIds(list, cusId);
            }
            for (QueryWeiBo queryWeiBo : queryWeiBoList) {
                //设置是否关注标识
                if(ObjectUtils.isNotNull(userExpandDtos)){
                    for (SnsUserExpandDto userExpandDto : userExpandDtos) {
                        if(queryWeiBo.getCusId().longValue()==userExpandDto.getId().longValue()){
                            queryWeiBo.setCusAttentionId(userExpandDto.getId());
                        }
                    }
                }
                //设置头像和名称
                SnsUserExpandDto userExpandDto = map.get("" + queryWeiBo.getCusId());// 查询用户的信息
                if (userExpandDto != null) {// 如果能够查到则set 头像信息
                    queryWeiBo.setAvatar(userExpandDto.getAvatar());// set 头像信息
                    queryWeiBo.setShowname(userExpandDto.getShowname());//会员名
                }
                if (ObjectUtils.isNotNull(queryWeiBo.getForwardId())) {
                    ids += queryWeiBo.getForwardId() + ",";
                }
            }
        }

        //微博转发查询
        Map<Long, QueryWeiBo> mapweibo = null;
        if (!ids.trim().equals("")) {
            mapweibo = weiBoDao.getWeiBoByIds(ids);
            if (ObjectUtils.isNotNull(queryWeiBoList)) {
                for (QueryWeiBo queryWeiBo : queryWeiBoList) {
                    QueryWeiBo zhuanWeiBo = mapweibo.get(queryWeiBo.getForwardId());
                    if (ObjectUtils.isNotNull(queryWeiBo.getForwardId())) {
                        if (ObjectUtils.isNotNull(zhuanWeiBo)) {
                            queryWeiBo.setForwardContent("@" + zhuanWeiBo.getShowname() + "：" + zhuanWeiBo.getContent());
                            queryWeiBo.setForwardNum(zhuanWeiBo.getForwardNum());
                        } else {
                            queryWeiBo.setForwardContent("原微博已删除");
                            queryWeiBo.setForwardNum(0);
                        }
                    }
                }
            }
        }
        // 查询热门微博
        return queryWeiBoList;
    }
}
