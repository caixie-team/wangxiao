package com.atdld.os.sns.service.impl.blog;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.constants.BlogConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.dao.blog.BlogReplyDao;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.blog.BlogReplyService;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.impl.visitstat.VisitStatServiceImpl;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.visitstat.VisitStatService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.impl.blog.BlogReplyServiceImpl
 * @description 博客回复
 * @Create Date : 2013-12-31 下午5:08:18
 */
@Service("blogReplyService")
public class BlogReplyServiceImpl implements BlogReplyService {

    @Autowired
    private BlogReplyDao blogReplyDao;
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private DynamicWebService dynamicWebService;
    @Autowired
    private VisitStatService visitStatService;
    @Autowired
    private WebHessianService webHessianService;
    @Autowired
    private SnsUserService snsUserService;

    /**
     * 添加BlogReply
     *
     * @param blogReply 要添加的BlogReply
     * @return
     */
    public String addBlogReply(BlogReply blogReply) throws Exception {
        // 验证用户是否操作频繁
        boolean bloo = snsUserService.checkLimitOpt(MemConstans.ARTICLE_LIMIT,blogReply.getCusId());
        if (bloo == true) {
            //获得showname
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(blogReply.getCusId());
            
            blogReply.setShowName(userExpandDto.getShowname());
            blogReply.setContent(webHessianService.doFilter(blogReply.getContent()));
            Long num = blogReplyDao.addBlogReply(blogReply);// 返回结果
            if (num == 1) {
                // 回复数+1
                visitStatService.record(VisitStatServiceImpl.TYPES[3], blogReply.getBlogId());
                // 回复活跃度+1
                BlogBlog blogBlog = new BlogBlog();
                blogBlog.setReplyName(blogReply.getShowName());
                blogBlog.setUpdateTime(new Date());
                blogBlog.setId(blogReply.getBlogId());// 博文id
                blogBlog.setActivity(BlogConstans.BLOG_ACTIVITY_REPLY);// 添加活跃度
                blogBlogService.updateBlogActivity(blogBlog);// 更新活跃度添加回复
                blogBlogService.updateBlogBlogLastReply(blogBlog);//更新博客回复的最后时间和人
                // 添加用户操作次数
                snsUserService.customerOptLimitCountAdd(MemConstans.ARTICLE_LIMIT,blogReply.getCusId());
                //添加财经文章动态
                //BlogBlog blogBlog1 = blogBlogService.getBlogBlogDetailById(blogReply.getBlogId());

                dynamicWebService.addDynamicWebForBlogBlogreply(blogReply);

                return SnsConstants.SUCCESS;// 返回成功
            } else {
                return SnsConstants.FALSE;//返回失败
            }
        } else {
            return SnsConstants.LIMITOPT;// 返回提示操作太频繁
        }
    }

    /**
     * 删除博文回复
     *
     * @param blogReply 博文id 和回复id
     * @return
     */
    public String deleteBlogReplyById(BlogReply blogReply) throws Exception {
        Long num = blogReplyDao.deleteBlogReplyById(blogReply);
        if (num == 1) {
            //回复数减一
            blogBlogService.delBlogReplycount(blogReply.getBlogId());
            DynamicWeb dynamicWeb = new DynamicWeb();
            dynamicWeb.setBizId(blogReply.getBlogId());
            dynamicWeb.setAssistId(blogReply.getId());
            dynamicWeb.setCusId(blogReply.getCusId());
            dynamicWebService.deleteReplyDynamicByCondition(dynamicWeb);
            return SnsConstants.SUCCESS;// 返回成功
        } else {
            return SnsConstants.FALSE;//返回失败
        }
    }

    /**
     * 修改BlogReply
     *
     * @param blogReply 要修改的BlogReply
     */
    public void updateBlogReply(BlogReply blogReply) throws Exception {
        blogReplyDao.updateBlogReply(blogReply);

    }

    /**
     * 根据id获取单个BlogReply对象
     *
     * @param id 要查询的id
     * @return BlogReply
     */
    public BlogReply getBlogReplyById(int id) throws Exception {
        return blogReplyDao.getBlogReplyById(id);
    }

    /**
     * 根据条件获取BlogReply列表
     *
     * @param blogReply 查询条件
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyList(BlogReply blogReply) throws Exception {
        return blogReplyDao.getBlogReplyList(blogReply);
    }

    /**
     * 查询回复列表
     *
     * @param blogId 博客id
     * @param page   分页参数
     * @return List<BlogReply>
     */
    public List<BlogReply> getBlogReplyByBlogId(Long blogId, PageEntity page)
            throws Exception {
        List<BlogReply> blogReplyList = blogReplyDao.getBlogReplyByBlogId(blogId, page);
        if(blogReplyList!=null&&blogReplyList.size()>0){
        	Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getBlogReplyListCusId(blogReplyList));// 查询用户的信息
            if (ObjectUtils.isNotNull(blogReplyList) && blogReplyList.size() > 0) {
                for (BlogReply blogReply : blogReplyList) {
                    SnsUserExpandDto userExpandDto = map.get("" + blogReply.getCusId());
                    if (ObjectUtils.isNotNull(userExpandDto)) {
                        blogReply.setUserExpandDto(userExpandDto);
                        blogReply.setShowName(userExpandDto.getShowname());
                    }
                }
            }
        }
        
        return blogReplyList;
    }

    public String getBlogReplyListCusId(List<BlogReply> blogReplyList) {//获得用户ids
        String ids = "";
        if (blogReplyList != null && blogReplyList.size() > 0) {
            for (BlogReply blogReply : blogReplyList) {
                ids += blogReply.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 更新回复数+1
     *
     * @param blogId 博文id
     * @param count  +1
     * @throws Exception
     */
    public void updateBlogReplyCount(Long blogId, int count) throws Exception {
        blogReplyDao.updateBlogReplyCount(blogId, count);
    }

    /**
     * 根据博文id删除博文
     *
     * @param blogId
     */
    public void deleteBlogReplyByBlogId(Long blogId) throws Exception {
        blogReplyDao.deleteBlogReplyByBlogId(blogId);
    }

    /**
     * 删除博文回复判断是否是本人
     *
     * @param blogReply 回复id 和 cusId
     * @return
     */
    public Integer getBlogReplyIsMine(BlogReply blogReply) throws Exception {
        return blogReplyDao.getBlogReplyIsMine(blogReply);
    }
}