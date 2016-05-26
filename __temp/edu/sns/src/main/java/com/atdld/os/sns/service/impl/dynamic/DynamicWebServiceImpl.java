package com.atdld.os.sns.service.impl.dynamic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.sns.constants.DynamicConstans;
import com.atdld.os.sns.dao.discuss.DisArticleDao;
import com.atdld.os.sns.dao.discuss.DisGroupDao;
import com.atdld.os.sns.dao.dynamic.DynamicWebDao;
import com.atdld.os.sns.dao.friend.FriendDao;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisArticleReply;
import com.atdld.os.sns.entity.discuss.DisGroup;
import com.atdld.os.sns.entity.discuss.DisMember;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.suggest.SugSuggest;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.WeiBo;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.suggest.SugSuggestService;
import com.atdld.os.sns.service.user.SnsUserService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.dynamic.DynamicWebServiceImpl
 * @description
 * @Create Date : 2014-1-11 下午2:30:12
 */
@Service("dynamicWebService")
public class DynamicWebServiceImpl implements DynamicWebService {

    @Autowired
    private DynamicWebDao dynamicWebDao;
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private SugSuggestService SugSuggestService;
    @Autowired
    private DisGroupDao disGroupDao;
    @Autowired
    private DisArticleDao disArticleDao;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private SnsUserService snsUserService;
    
    /**
     * 添加小组文章动态
     *
     * @param disArticle 文章参数
     * @throws Exception
     */
    public void addDynamicWebForDisArticle(DisArticle disArticle) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(disArticle.getCusId());// 发表文章的用户id
        dynamicWeb.setCusName(disArticle.getShowName());// 获得发表用户的名字
        dynamicWeb.setType(3);// 类型3发表小组文章
        dynamicWeb.setTitle(disArticle.getTitle());// 获得文章标题

        dynamicWeb.setDescription("在小组" + "<a href='/dis/info/" + disArticle.getGroupId() + "' class='c-blue fsize14' target='_blank'>"
                + disArticleDao.queryDisArticleDetail(disArticle).getDisname() + "</a>" + "发表了小组话题");// 动态描述
        dynamicWeb.setAddTime(disArticle.getCreateTime());// 发表时间
        dynamicWeb.setBizId(disArticle.getId());// 获得发表文章的id
        String content = WebUtils.replaceTagHTML(disArticle.getContent());
        if (StringUtils.isNotEmpty(content)) {
            content = StringUtils.getLength(content, DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
            dynamicWeb.setContent(content);
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        dynamicWeb.setAssistId(disArticle.getGroupId());// 辅助存小组的id
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 添加回复小组文章动态
     *
     * @param disArticleReply
     * @throws Exception
     */
    public void replyDynamicWebForDisArticle(DisArticleReply disArticleReply) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(disArticleReply.getRecusId());// 回复人id
        dynamicWeb.setCusName(disArticleReply.getShowName());// 获得用户名字
        DisArticle disArticle = new DisArticle();
        disArticle.setGroupId(disArticleReply.getGroupId());// set 小组id
        disArticle.setId(disArticleReply.getArticleId());// set 文章id
        disArticle = disArticleDao.queryDisArticleDetail(disArticle);
        dynamicWeb.setTitle(disArticle.getTitle());// 添加文章标题
        dynamicWeb.setDescription("在小组" + "<a href='/dis/info/" + disArticle.getGroupId() + "' class='c-blue fsize14' target='_blank'>"
                + disArticle.getDisname() + "</a>" + "回复了小组话题");
        dynamicWeb.setBizId(disArticleReply.getArticleId());// 回复的文章id;
        dynamicWeb.setType(6);// 类型6为回复文章
        dynamicWeb.setAddTime(disArticleReply.getReplyTime());// 回复时间
        String content = WebUtils.replaceTagHTML(disArticleReply.getReplyContent());
        if (StringUtils.isNotEmpty(content)) {// 回复的内容
            content = StringUtils.getLength(content, DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
            dynamicWeb.setContent(content);
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        dynamicWeb.setAssistId(disArticleReply.getGroupId());// 辅助存小组的id
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 添加博文动态
     *
     * @param blogReply
     * @throws Exception
     */
    public void addDynamicWebForBlogBlog(BlogBlog blogBlog) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(blogBlog.getCusId());// 用户id
        dynamicWeb.setCusName(blogBlog.getShowName());// 用户名字
        dynamicWeb.setDescription("发表了博文");// 动态描述
        dynamicWeb.setTitle(blogBlog.getTitle());// 博文标题
        dynamicWeb.setBizId(blogBlog.getId());// 博文id
        dynamicWeb.setType(9);// 类型
        String content = WebUtils.replaceTagHTML(blogBlog.getContent());
        if (StringUtils.isNotEmpty(content)) {// 回复的内容
            content = StringUtils.getLength(content, DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
            dynamicWeb.setContent(content);
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        // dynamicWeb.setContent(blogBlog.getContent());// 博文内容
        dynamicWeb.setAddTime(blogBlog.getAddTime());// 发表时间
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 添加回复博文动态
     *
     * @param blogReply
     * @throws Exception
     */
    public void addDynamicWebForBlogBlogreply(BlogReply blogReply) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(blogReply.getCusId());// 用户id
        dynamicWeb.setCusName(blogReply.getShowName());// 回复人名字
        dynamicWeb.setDescription("回复了博文");// 动态描述
        dynamicWeb.setAddTime(blogReply.getAddTime());// 回复时间
        dynamicWeb.setBizId(blogReply.getBlogId());// 获得博文id
        dynamicWeb.setType(10);// 类型

        // 查询博文内容
        BlogBlog blogBlog = blogBlogService.getBlogBlogDetailById(blogReply.getBlogId());
        dynamicWeb.setTitle(blogBlog.getTitle());// 获得用户标题
        String content = WebUtils.replaceTagHTML(blogReply.getContent());
        if (StringUtils.isNotEmpty(content)) {// 回复的内容
            content = StringUtils.getLength(content, DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
            dynamicWeb.setContent(content);
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        // dynamicWeb.setContent(blogReply.getContent());// 回复内容
        dynamicWeb.setAssistId(blogReply.getId());// 获得回复id
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 添加微博动态
     *
     * @param weibo
     * @throws Exception
     */
    public void addDynamicWebForWeiBo(WeiBo weiBo) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(weiBo.getCusId());// 发表微博用户id
        dynamicWeb.setCusName(weiBo.getShowname());// 用户名字
        dynamicWeb.setDescription("发表了观点");// 动态描述
        dynamicWeb.setAddTime(weiBo.getAddTime());// 发表时间
        dynamicWeb.setType(2);// 设置类型2为发表微博
        dynamicWeb.setBizId(weiBo.getId());// 获得微博的id
        if (StringUtils.isNotEmpty(weiBo.getContent())) {// 回复的内容
            dynamicWeb.setContent(weiBo.getContent());
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        // dynamicWeb.setContent(weiBo.getContent());// 获得微博的内容
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 关注动态
     *
     * @throws Exception
     */
    public void addDynamicWebForFriends(Friend friend) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(friend.getCusId());// 用户的id
        
        SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(friend.getCusId());
        
        dynamicWeb.setCusName(userExpandDto.getShowname());
        // 获得好友名字
        SnsUserExpandDto userExpandDto2 = snsUserService.getUserExpandByCusId(friend.getCusFriendId());
        dynamicWeb.setContent(userExpandDto2.getShowname());
        dynamicWeb.setDescription("关注了");// 动态描述
        dynamicWeb.setBizId(friend.getCusFriendId());
        dynamicWeb.setTitle("");
        dynamicWeb.setType(7);// 7加关注1
        dynamicWeb.setAddTime(friend.getAddTime());// 添加时间
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 添加回复微博动态
     *
     * @param comment
     * @throws Exception
     */
    public void replyDynamicWebForWeiBo(Comment comment) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(comment.getCusId());// 回复的人
        dynamicWeb.setCusName(comment.getShowname());// 回复名字
        dynamicWeb.setDescription("回复了观点");// 动态描述
        dynamicWeb.setAddTime(comment.getAddTime());// 回复时间
        dynamicWeb.setType(5);// 类型5为回复微博
        dynamicWeb.setBizId(comment.getWeiboId());// 回复的微博id
        String content = WebUtils.replaceTagHTML(comment.getContent());
        if (StringUtils.isNotEmpty(content)) {// 回复的内容
            content = StringUtils.getLength(content, 30);
            dynamicWeb.setContent(content);
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        // dynamicWeb.setContent(comment.getContent());// 回复的内容
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 申请加入小组动态
     *
     * @param disMember
     * @throws Exception
     */
    public void addDynamicWebForDisMember(DisMember disMember) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(disMember.getCusId());// 用户id
        dynamicWeb.setCusName(disMember.getShowName());
        dynamicWeb.setDescription("加入小组");// 动态描述
        dynamicWeb.setBizId(disMember.getId());// 加入的小组成员的id
        dynamicWeb.setType(4);// 类型4为加小组
        dynamicWeb.setAddTime(disMember.getAddTime());// 加入时间
        dynamicWeb.setAssistId(disMember.getGroupId());// 存小组
        DisGroup disGroup = disGroupDao.queryDisGroupById(disMember.getGroupId());// 通过小组id查询小组
        if (disGroup != null) {
            dynamicWeb.setTitle(disGroup.getName());// 设置小组名称
        }
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 小组创建通过动态
     *
     * @param disGroup
     * @throws Exception
     */
    public void addDynamicWebForDisGroup(DisGroup disGroup) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(disGroup.getCusId());// 用户id
        dynamicWeb.setCusName(disGroup.getShowName());
        dynamicWeb.setDescription("创建了小组");
        dynamicWeb.setAssistId(disGroup.getId());// 小组id
        dynamicWeb.setContent(disGroup.getName());// 小组名称
        dynamicWeb.setAddTime(new Date());// 创建时间
        dynamicWeb.setTitle(disGroup.getName());// 辅助描述 小组名称
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }


    /**
     * 添加发布建议动态
     *
     * @param sugSuggest
     * @throws Exception
     */
    public void addDynamicWebForSuggest(SugSuggest sugSuggest) throws Exception {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8问题9.发表博文10.回复博文.11财经文章
        // 12回复财经
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(sugSuggest.getCusId());// 用户id
        dynamicWeb.setCusName(sugSuggest.getShowname());
        dynamicWeb.setDescription("发布了问题");// 动态描述
        dynamicWeb.setAddTime(sugSuggest.getAddtime());// 发表时间
        dynamicWeb.setBizId(sugSuggest.getId());// 问题id
        dynamicWeb.setType(8);// 问题类型
        String content = WebUtils.replaceTagHTML(sugSuggest.getContent());
        if (StringUtils.isNotEmpty(content)) {// 回复的内容
            content = StringUtils.getLength(content, DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
            dynamicWeb.setContent(content);
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        // dynamicWeb.setContent(sugSuggest.getContent());// 发布内容
        dynamicWeb.setUrl("");
        dynamicWeb.setTitle(sugSuggest.getTitle());// 发布的标题
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 添加推荐动态建议
     *
     * @param sugSuggestRecommend
     */
    public void addDynamicWebForSuggestRecommend(Long suggestId) {
        // 添加动态 0小组创建通过1加好友2微博动态3小组文章4加小组5回复微博6回复文章7加关注8推荐建议9.发表博文10.回复博文.11财经文章
        // 12回复财经
        SugSuggest sugSuggest = SugSuggestService.getSugSuggestById(suggestId);
        DynamicWeb dynamicWeb = new DynamicWeb();
        dynamicWeb.setCusId(sugSuggest.getCusId());// 用户id
        dynamicWeb.setCusName(sugSuggest.getShowname());// 获得用户名字
        dynamicWeb.setDescription("采纳问题");// 动态描述
        dynamicWeb.setBizId(sugSuggest.getId());// 推荐id
        // dynamicWeb.setAssistId(sugSuggestRecommend.getSuggestId());// 建议id
        dynamicWeb.setType(8);// 建议类型
        // 获得建议信息

        dynamicWeb.setTitle(sugSuggest.getTitle());// 获得建议标题
        String content = WebUtils.replaceTagHTML(sugSuggest.getContent());
        if (StringUtils.isNotEmpty(content)) {// 回复的内容
            content = StringUtils.getLength(content, DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
            dynamicWeb.setContent(content);
        } else {
            dynamicWeb.setContent("");// 如果为空则set空
        }
        dynamicWeb.setAddTime(new Date());// 推荐时间
        dynamicWeb.setUrl("");
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 添加学习动态接口
     *
     * @param map
     * @throws Exception
     */
    public void addDynamicWebForLearning(DynamicWeb dynamicWeb) throws Exception {
    	dynamicWeb.setCusName(getUserShowName(dynamicWeb.getCusId()));//set用户名
        dynamicWebDao.addDynamicWeb(dynamicWeb);
    }

    /**
     * 查看全站动态
     *
     * @return List<DynamicWeb> 动态列表list
     * @throws Exception
     */
    public List<DynamicWeb> queryDynamicWebList(Long cusId, PageEntity page) throws Exception {
        List<DynamicWeb> DynamicWebList = dynamicWebDao.queryDynamicWebList(cusId, page);
        
        if (DynamicWebList != null && DynamicWebList.size() > 0) {
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDynamicWebListCusId(DynamicWebList));// 查询用户的信息
            for (DynamicWeb dynamicWeb : DynamicWebList) {
                SnsUserExpandDto userExpandDto = map.get("" + dynamicWeb.getCusId());// 查询用户的信息
                if (userExpandDto != null) {// 如果能够查到则set 头像信息
                    dynamicWeb.setAvatar(userExpandDto.getAvatar());// set 头像信息
                    dynamicWeb.setCusName(userExpandDto.getShowname());// set 用户名
                }
                // 查询出类型为1（加好友） 和7（关注） 的
                List<DynamicWeb> dynamicWebListnew = new ArrayList<DynamicWeb>();
                if (dynamicWeb.getType() == 1 || dynamicWeb.getType() == 7) {
                    dynamicWebListnew.add(dynamicWeb);
                }
                // 查询要关注和要加的人的showname
                // 循环博客查询showname
               String cusIds="";
                if (ObjectUtils.isNotNull(dynamicWebListnew)) {
                    for (DynamicWeb dynamicWebnew : dynamicWebListnew) {
                    	cusIds+=dynamicWebnew.getBizId()+",";
                    }
                    // 通过用户id查询出customer的map
                    Map<String, SnsUserExpandDto> mapnew = snsUserService.getUserExpandsByCusId(cusIds);
                    if(ObjectUtils.isNotNull(map)){
                        for (DynamicWeb dynamicWebnew : dynamicWebListnew) {
                            SnsUserExpandDto customer =mapnew.get(dynamicWebnew.getBizId().toString());
                            if(ObjectUtils.isNotNull(customer)){
                                dynamicWebnew.setContent(customer.getShowname());
                            }
                        }
                    }
                    
                }
            }
        }

        return DynamicWebList;
    }

    private String getDynamicWebListCusId(List<DynamicWeb> dynamicWebList) {// 获得用户ids
        String ids = "";
        if (dynamicWebList != null && dynamicWebList.size() > 0) {
            for (DynamicWeb dynamicWeb : dynamicWebList) {
                ids += dynamicWeb.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 显示小组组动态，只有小组成员才能看到
     *
     * @param cusId 用户Id
     * @return List<DynamicWeb> 1发表小组文章 2回复小组文章 3加入小组 list
     * @throws Exception
     */
    public List<DynamicWeb> queryDynamicWebDisGroup(List<Long> ids, PageEntity page) throws Exception {
        List<DynamicWeb> dynamicWebList = dynamicWebDao.queryDynamicWebDisGroup(ids, page);
        if(dynamicWebList!=null&&dynamicWebList.size()>0){
	        Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDynamicWebListCusId(dynamicWebList));// 查询用户的信息
	        if (dynamicWebList != null && dynamicWebList.size() > 0) {
	            for (DynamicWeb dynamicWeb : dynamicWebList) {
	                SnsUserExpandDto userExpandDto = map.get("" + dynamicWeb.getCusId());// 查询用户的信息
	                if (userExpandDto != null) {// 如果能够查到则set 头像信息
	                    dynamicWeb.setAvatar(userExpandDto.getAvatar());// set 头像信息
	                    dynamicWeb.setCusName(userExpandDto.getShowname());// set 用户名
	                }
	            }
	        }
        }
        return dynamicWebList;
    }

    /**
     * 通过type查询动态
     *
     * @param page 分页参数
     * @return
     */
    public List<DynamicWeb> queryDynamicWebByTP(String type, PageEntity page)
            throws Exception {
        List<DynamicWeb> dynamicWebList = dynamicWebDao.queryDynamicWebByTP(type, page);
        if(dynamicWebList!=null&&dynamicWebList.size()>0){
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDynamicWebListCusId(dynamicWebList));// 查询用户的信息
            if (dynamicWebList != null && dynamicWebList.size() > 0) {
                for (DynamicWeb dynamicWeb : dynamicWebList) {
                    SnsUserExpandDto userExpandDto = map.get("" + dynamicWeb.getCusId());// 查询用户的信息
                    if (userExpandDto != null) {// 如果能够查到则set 头像信息
                        dynamicWeb.setAvatar(userExpandDto.getAvatar());// set 头像信息
                        dynamicWeb.setCusName(userExpandDto.getShowname());// set 用户名
                    }
                }
            }
        }
        
        return dynamicWebList;
    }

    /**
     * 查看加好友的动态
     *
     * @param cus_id 用户Id
     * @return List<DynamicWeb> 加好友Friend list
     * @throws Exception
     */
    public List<DynamicWeb> queryDynamicWebFriend(Long cusId, PageEntity page) throws Exception {
        return dynamicWebDao.queryDynamicWebFriend(cusId, page);
    }

    /**
     * 查看好友动态 1.发微博 2.回微博 3.加关注
     *
     * @param cusId 当前用户id
     * @param page  分页参数
     * @return
     * @throws Exception
     */
    public List<DynamicWeb> queryDynamicWebJunZiHui(Long cusId, PageEntity page) throws Exception {
        List<Long> ids = friendDao.queryMyFriend(cusId);// 我的好友的list
        if (ids != null && ids.size() > 0) {// 如果没有好友则返回null
            List<DynamicWeb> dynamicWebList = dynamicWebDao.queryDynamicWebJunZiHui(ids, page);// 查询我的好友的动态
            if(dynamicWebList!=null&&dynamicWebList.size()>0){
	            Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getDynamicWebListCusId(dynamicWebList));// 查询用户的信息
	            if (dynamicWebList != null && dynamicWebList.size() > 0) {
	                for (DynamicWeb dynamicWeb : dynamicWebList) {
	                    SnsUserExpandDto userExpandDto = map.get("" + dynamicWeb.getCusId());// 查询用户的信息
	                    if (userExpandDto != null) {// 如果能够查到则set 头像信息
	                        dynamicWeb.setAvatar(userExpandDto.getAvatar());// set 头像信息
	                        dynamicWeb.setCusName(userExpandDto.getShowname());// set
	                        // 用户名
	                    }
	                }
	            }
            }
            return dynamicWebList;
        } else {
            return null;
        }

    }

    /**
     * 公共删除动态方法 1微博 2博文 3小组文章 4建议
     *
     * @param dynamicWeb
     * @return
     * @throws Exception
     */
    public Long deleteDynamicWebByCondition(DynamicWeb dynamicWeb) throws Exception {
        return dynamicWebDao.deleteDynamicWebByCondition(dynamicWeb);
    }

    /**
     * 公共删除 回复动态 1 回复微博 2回复博文 3回复小组文章 4回复建议
     *
     * @param dynamicWeb
     * @return
     * @throws Exception
     */
    public Long deleteReplyDynamicByCondition(DynamicWeb dynamicWeb) throws Exception {
        return dynamicWebDao.deleteReplyDynamicByCondition(dynamicWeb);
    }
    /**
     * 设置是否关注的FLAG
     *
     * @param dynamicWebList
     * @return
     * @throws Exception
     */
    public List<DynamicWeb> setFriendFlag(List<DynamicWeb> dynamicWebList,Long userId) throws Exception{
        if(ObjectUtils.isNotNull(dynamicWebList)){
            List<Long> list = new ArrayList<Long>();
            for(DynamicWeb dynamicWeb:dynamicWebList){
                list.add(dynamicWeb.getCusId());
            }
            //查询是否是好友
            List<SnsUserExpandDto> userExpandDtos= snsUserService.queryIsFrirndByIds(list,userId);
            if(ObjectUtils.isNotNull(userExpandDtos)){
                for(DynamicWeb dynamicWeb:dynamicWebList){
                    for(SnsUserExpandDto expandDto:userExpandDtos){
                        if(dynamicWeb.getCusId().longValue()==expandDto.getId().longValue()){
                            dynamicWeb.setCusAttentionId(expandDto.getId());
                        }
                    }
                }
            }
        }
        return dynamicWebList;
    }
    /**
     * 获得用户名
     * @param userId
     * @return
     */
    public String getUserShowName(Long userId){
    	if(userId!=0){
    		SnsUserExpandDto user= snsUserService.getUserExpandByCusId(userId);
    		return user.getShowname();
    	}
    	return "";
    }
}
