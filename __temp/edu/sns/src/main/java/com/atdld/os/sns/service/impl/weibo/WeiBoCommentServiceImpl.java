package com.atdld.os.sns.service.impl.weibo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.dao.weibo.WeiBoCommentDao;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.QueryComment;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.weibo.WeiBoCommentService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author
 * @ClassName WeiBoCommentServiceImpl
 * @package com.atdld.open.sns.service.impl.weibo
 * @description 微博评论的实现
 * @Create Date: 2013-12-10 下午4:14:40
 */
@Service("weiBoCommentService")
public class WeiBoCommentServiceImpl implements WeiBoCommentService {
    @Autowired
    private WeiBoCommentDao weiBoCommentDao;
    @Autowired
    private WeiBoService weiBoService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WebHessianService webHessianService;
    /**
     * 添加微博的评论
     *
     * @param comment 评论实体
     * @throws Exception
     */
    public String addComment(Comment comment) throws Exception {
        if (snsUserService.checkLimitOpt(MemConstans.WEIBO_REPLY_LIMIT, comment.getCusId())) {
            comment.setAddTime(new Date());// set 添加时间
            comment.setUpdateTime(new Date());// set 更新时间
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(comment.getCusId());
            comment.setShowname(userExpandDto.getShowname());// set showname
            comment.setContent(WebUtils.replaceTagHref(webHessianService.doFilter(comment.getContent())));// 过滤评论内容
            weiBoCommentDao.addComment(comment);// 添加评论
            weiBoService.updateWeiBoCommentNumAddCount(comment.getWeiboId(), 1);// 微博评论数加一
            return SnsConstants.SUCCESS;// 添加评论成功
        } else {
            return SnsConstants.ADDMOST;// 添加过多
        }
    }

    /**
     * 查询该微博评论
     *
     * @param comment 评论实体
     * @param page    分页参数
     * @return List<QueryComment> 评论list
     * @throws Exception
     */
    public List<QueryComment> queryCommentByWbId(Comment comment, PageEntity page) throws Exception {
        List<QueryComment> queryCommentList = weiBoCommentDao.queryCommentByWbId(comment, page);// 查询该微博下的评论
        if(queryCommentList!=null&&queryCommentList.size()>0){
	        Map<String, SnsUserExpandDto> map = snsUserService.getUserExpandsByCusId(getQueryCommentListCusId(queryCommentList));// 查询用户的信息
	        if (queryCommentList != null && queryCommentList.size() > 0) {
	            for (QueryComment queryComment : queryCommentList) {
	                SnsUserExpandDto userExpandDto = map.get("" + queryComment.getCusId());// 查询用户的信息
	                if (userExpandDto != null) {// 如果能够查到则set 头像信息
	                    queryComment.setAvatar(userExpandDto.getAvatar());// set 头像信息
	                    queryComment.setShowname(userExpandDto.getShowname());
	                }
	            }
	        }
        }
        return queryCommentList;
    }

    public String getQueryCommentListCusId(List<QueryComment> queryCommentList) {// 获得用户ids
        String ids = "";
        if (queryCommentList != null && queryCommentList.size() > 0) {
            for (QueryComment queryComment : queryCommentList) {
                ids += queryComment.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 删除微博评论
     *
     * @param comment 传入id
     * @throws Exception
     */
    public String delCommentById(Comment comment) throws Exception {
        if (weiBoCommentDao.delCommentById(comment) == 0) {// 返回影响行数判断是否删除
            return SnsConstants.FALSE;// 删除失败
        } else {
            weiBoService.updateWeiBoCommentNumSubtractCount(comment.getWeiboId());
            return SnsConstants.SUCCESS;// 删除成功
        }

    }

}
