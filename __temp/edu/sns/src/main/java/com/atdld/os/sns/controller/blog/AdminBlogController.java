package com.atdld.os.sns.controller.blog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.blog.BlogBlog;
import com.atdld.os.sns.entity.blog.BlogReply;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.Artclassify;
import com.atdld.os.sns.service.blog.BlogBlogService;
import com.atdld.os.sns.service.blog.BlogReplyService;
import com.atdld.os.sns.service.discuss.DisArticleService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.blog.AdminBlogAction
 * @description
 * @Create Date : 2013-12-30 下午6:14:00
 */
@Controller
@RequestMapping("/admin")
public class AdminBlogController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(AdminBlogController.class);
    @Autowired
    private BlogBlogService blogBlogService;
    @Autowired
    private DisArticleService disArticleService;
    @Autowired
    private BlogReplyService blogReplyService;

    private static String get_AdminBlogBlogList = getViewPath("/admin/blog/blog_list");// 博客列表
    private static String get_AdminBlogBlogById = getViewPath("/admin/blog/update_blog");// 返回更新页面
    private static String get_AdminBlogReplyByBlogId = getViewPath("/admin/blog/reply_blog");// 回复列表页面
    private static String get_AdminArtclassifyList = getViewPath("/admin/blog/blog_article_classify");// 文章分类页面
    private static String get_ArticleClassifyById = getViewPath("/admin/blog/update_article_classify");// 文章分类修改页面
    private static String to_addArticleClassify = getViewPath("/admin/blog/add_article_classify");// 文章分类添加页面

    /**
     * 后台查询所有博文
     *
     * @param request
     * @param page     分页参数
     * @param blogBlog 博客实体
     * @return
     */
    @RequestMapping("/blog/getAdminBlogBlogList")
    public ModelAndView getAdminBlogBlogList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("blogBlog") BlogBlog blogBlog) {
        ModelAndView modelAndView = new ModelAndView(get_AdminBlogBlogList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 后台查询博客
            List<BlogBlog> blogBlogList = blogBlogService.getAdminBlogBlogList(blogBlog, this.getPage());
            // 查询文章分类
            List<Artclassify> artclassify = disArticleService.queryArtclassifyList();
            // 把数据放到modelAndView中
            modelAndView.addObject("blogBlogList", blogBlogList);
            modelAndView.addObject("artclassify", artclassify);
        } catch (Exception e) {
            logger.error("AdminBlogAction.getAdminBlogBlogList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 根据id删除博文
     *
     * @param request
     * @param id      博文id
     * @return
     */
    @RequestMapping("/blog/deleteAdminBlogBlogById")
    @ResponseBody
    public Map<String, Object> deleteAdminBlogBlogById(HttpServletRequest request, @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 根据id删除博文，返回结果
            String flag = blogBlogService.deleteBlogBlogById(id);
            // 把返回结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminBlogAction.deleteBlogBlogById", e);// 记录log
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 根据id获得博文信息
     *
     * @param request
     * @param id      博文id
     * @return
     */
    @RequestMapping("/blog/getAdminBlogBlogById/{blogId}")
    public ModelAndView getAdminBlogBlogById(HttpServletRequest request, @PathVariable Long blogId) {
        ModelAndView modelAndView = new ModelAndView(get_AdminBlogBlogById);
        try {
            // 获得博客信息
            BlogBlog blogBlog = blogBlogService.getAdminBlogBlogById(blogId);
            // 把获得的数据放到ModelAndView
            modelAndView.addObject("blogBlog", blogBlog);
        } catch (Exception e) {
            logger.error("AdminBlogAction.getAdminBlogBlogById", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回到后台404界面
        }
        return modelAndView;
    }

    // 创建小组 绑定变量名字和属性，把参数封装到类
    @InitBinder("blogBlog")
    public void initBinderBlogBlog(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blogBlog.");
    }

    /**
     * 后台修改博文信息
     *
     * @param request
     * @param blogBlog 博客实体
     * @return
     */
    @RequestMapping("/blog/updateAdminBlogBlog")
    public ModelAndView updateAdminBlogBlog(HttpServletRequest request, @ModelAttribute("blogBlog") BlogBlog blogBlog) {
        try {
            // 更新博客
            blogBlog.setUpdateTime(new Date());
            blogBlogService.updateBlogBlog(blogBlog);
        } catch (Exception e) {
            logger.error("AdminBlogAction.updateAdminBlogBlog", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回到后台404界面
        }
        return new ModelAndView("redirect:/admin/blog/getAdminBlogBlogList");// 重定向到列表页面
    }

    /**
     * 置顶博文
     *
     * @param request
     * @param id      博文id
     * @return
     */
    @RequestMapping("/blog/updateBlogBlogByTop")
    @ResponseBody
    public Map<String, Object> updateBlogBlogByTop(HttpServletRequest request, @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得返回更新结果
            String flag = blogBlogService.updateBlogBlogByTop(id);
            // 把获得结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminBlogAction.updateBlogBlogByTop", e);// 记录log
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 取消置顶博文
     *
     * @param request
     * @param id      博文id
     * @return
     */
    @RequestMapping("/blog/updateCancelBlogBlogByTop")
    @ResponseBody
    public Map<String, Object> updateCancelBlogBlogByTop(HttpServletRequest request, @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得返回更新结果
            String flag = blogBlogService.updateCancelBlogBlogByTop(id);
            // 把获得结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminBlogAction.updateBlogBlogByTop", e);// 记录log
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 查询博文回复列表
     *
     * @param request
     * @param blogId  博文id
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/blog/getAdminBlogReplyByBlogId/{blogId}")
    public ModelAndView getAdminBlogReplyByBlogId(HttpServletRequest request, @PathVariable Long blogId, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(get_AdminBlogReplyByBlogId);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            // 查询回复列表
            List<BlogReply> blogReplyList = blogReplyService.getBlogReplyByBlogId(blogId, page);
            // 把返回的list放到了modelAndView中
            modelAndView.addObject("blogReplyList", blogReplyList);
            modelAndView.addObject("blogId", blogId);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("AdminBlogAction.getAdminBlogReplyByBlogId", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 返回404界面
        }
        return modelAndView;
    }

    // 创建小组 绑定变量名字和属性，把参数封装到类
    @InitBinder("blogReply")
    public void initBinderBlogReply(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blogReply.");
    }

    /**
     * 根据id删除回复
     *
     * @param request
     * @param id      回复id
     * @return
     */
    @RequestMapping("/blog/deleteAdminBlogReplyById")
    @ResponseBody
    public Map<String, Object> deleteAdminBlogReplyById(HttpServletRequest request, @ModelAttribute("blogReply") BlogReply blogReply) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 根据id删除回复，返回结果
            String flag = blogReplyService.deleteBlogReplyById(blogReply);
            // 把返回结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminBlogAction.deleteBlogReplyById", e);// 记录log
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 查询文章分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/blog/getAdminArtclassifyList")
    public ModelAndView getAdminArtclassifyList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(get_AdminArtclassifyList);
        try {
            // 查询文章分类
            List<Artclassify> artclassify = disArticleService.queryArtclassifyList();
            // 把返回的数据放到modelAndView
            modelAndView.addObject("artclassify", artclassify);
        } catch (Exception e) {
            logger.error("AdminBlogAction.getAdminArtclassifyList", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    // 创建小组 绑定变量名字和属性，把参数封装到类
    @InitBinder("artclassify")
    public void initBinderArticleClassify(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("artclassify.");
    }
    /**
     * 跳转添加页面
     * @return
     */
    @RequestMapping("/blog/toaddArticleClassify")
    public String toAddArticleClassify(){
    	return to_addArticleClassify;
    }
    /**
     * 添加文章分类
     *
     * @param request
     * @param artclassify
     * @return
     */
    @RequestMapping("/blog/addArticleClassify")
    public ModelAndView addArticleClassify(HttpServletRequest request, @ModelAttribute("artclassify") Artclassify artclassify) {
        try {
            // 添加文章分类
            artclassify.setAddTime(new Date());
            blogBlogService.addArticleClassify(artclassify);
        } catch (Exception e) {
            logger.error("AdminBlogAction.addArticleClassify", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回到404界面
        }
        return new ModelAndView("redirect:/admin/blog/getAdminArtclassifyList");// 重定向
    }

    /**
     * 根据id删除文章分类
     *
     * @param request
     * @param id      文章分类id
     * @return
     */
    @RequestMapping("/blog/deleteArticleClassifyById")
    @ResponseBody
    public Map<String, Object> deleteArticleClassifyById(HttpServletRequest request, @RequestParam("artId") Long artId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 根据id删除博文，返回结果
            String flag = blogBlogService.updateArticleClassifyById(artId);
            // 把返回结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("AdminBlogAction.deleteArticleClassifyById", e);// 记录log
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 根据id查询文章分类
     *
     * @param request
     * @param artId   文章分类id
     * @return
     */
    @RequestMapping("/blog/getArticleClassifyById/{artId}")
    public ModelAndView getArticleClassifyById(HttpServletRequest request, @PathVariable Long artId) {
        ModelAndView modelAndView = new ModelAndView(get_ArticleClassifyById);
        try {
            // 根据id查询文章分类
            Artclassify artclassify = blogBlogService.getArticleClassifyById(artId);
            // 把返回结果放到modelAndView中
            modelAndView.addObject("artclassify", artclassify);
        } catch (Exception e) {
            logger.error("AdminBlogAction.getArticleClassifyById", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回到404界面
        }
        return modelAndView;
    }

    /**
     * 修改文章分类
     *
     * @param request
     * @param artclassify 文章分类参数
     * @return
     */
    @RequestMapping("/blog/updateArticleClassify")
    public ModelAndView updateArticleClassify(HttpServletRequest request, @ModelAttribute("artclassify") Artclassify artclassify) {
        try {
            // 修改文章分类
            artclassify.setAddTime(new Date());
            blogBlogService.updateArticleClassify(artclassify);
        } catch (Exception e) {
            logger.error("AdminBlogAction.updateArticleClassify", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回到404界面
        }
        return new ModelAndView("redirect:/admin/blog/getAdminArtclassifyList");// 重定向
    }

    /**
     * 查询文章分类名称是否存在
     *
     * @param request
     * @param artclassify 小组分类
     * @return
     */
    @RequestMapping("/blog/queryDisArticleIsExsit")
    @ResponseBody
    public Map<String, Object> queryDisArticleIsExsit(HttpServletRequest request, @ModelAttribute("artclassify") Artclassify artclassify) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询是否存在返回结果
            Integer flag = disArticleService.queryDisArticleIsExsit(artclassify);
            if (flag == 1) {
                map.put("message", SnsConstants.SUCCESS);// 存在
            } else {
                map.put("message", SnsConstants.FALSE);// 不存在
            }
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.queryDisArticleIsExsit", e);// 记录log
            map.put("message", "false");// 报错返回提示信息
        }
        return map;
    }

    /**
     * 同步财经文章
     *
     * @param request
     * @param blogId  博文id
     * @return map
     */
    @RequestMapping("/blog/syncFinBlog")
    @ResponseBody
    public Map<String, Object> syncFinBlog(HttpServletRequest request, @RequestParam("blogId") Long blogId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String result = "";
            if (blogId != null) {

                // 此处添加同步接口，需要博文数据可根据id
                // 调/blog/getAdminBlogBlogById/{blogId}方法进行获得
                // 返回成功执行更改同步与活跃度
                // 发送数据调用 HttpUtil dopost方法
                BlogBlog blogBlog = blogBlogService.getAdminBlogBlogById(blogId);
                if (blogBlog != null) {
                    //result=userExpandService.syncFinBlog(blogBlog);
                    map.put("message", result);
                }
            }
        } catch (Exception e) {
            logger.error("AdminDisGroupAction.syncFinBlog", e);
            map.put("message", "false");
        }
        return map;
    }
}
