package io.wangxiao.edu.sysuser.controller;

import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.sysuser.entity.Keyword;
import io.wangxiao.edu.sysuser.service.KeywordService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class SysKeyWordController extends SysBaseController {

    private static final Logger logger = Logger.getLogger(SysKeyWordController.class);
    @Autowired
    private KeywordService keywordService;

    private static final String keyword_list = getViewPath("/sysuser/keyword_list");

    private static final String addkeyword = getViewPath("/sysuser/keyword_add");

    @Getter
    @Setter
    private Keyword keyword;

    @InitBinder("keyword")
    public void initBinderkeyword(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("keyword.");
    }

    /**
     * 敏感词列表
     *
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/keyword/list")
    public ModelAndView getKeyWordList(@ModelAttribute("page") PageEntity page, @ModelAttribute("keyword") Keyword keyword, HttpServletRequest request) {
        ModelAndView andView = new ModelAndView(keyword_list);
        try {

            if (ObjectUtils.isNull(keyword)) {
                keyword = new Keyword();
            }
            logger.info(keyword);
            List<Keyword> list = keywordService.getKeywordList(keyword, page);
            andView.addObject("page", page);
            andView.addObject("list", list);
        } catch (Exception e) {
            logger.error("getKeyWordList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return andView;
    }

    @RequestMapping("/keyword/add")
    public String addKeyWord(@ModelAttribute("keyword") Keyword keyword, HttpServletRequest request) {
        try {
            if (ObjectUtils.isNull(keyword) || ObjectUtils.isNull(keyword.getKeyword())) {
                return "redirect:/admin/keyword/add";
            }
            keywordService.addKeyword(keyword);

        } catch (Exception e) {
            logger.error("addKeyWord:", e);
            return setExceptionRequest(request, e);
        }

        return "redirect:/admin/keyword/list";
    }

    /**
     * 删除敏感词
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/keyword/delete")
    @ResponseBody
    public Object deleteKeyWord(@RequestParam Long id, HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        try {
            if (ObjectUtils.isNull(id) || id.longValue() == 0) {
                jsonObject.addProperty("message", "error");
                return jsonObject;
            }
            keywordService.deleteKeywordById(id);
            jsonObject.addProperty("message", "success");
        } catch (Exception e) {
            logger.error("deleteKeyWord", e);
            jsonObject.addProperty("message", "error");
        }
        return jsonObject;
    }

    @RequestMapping("/keyword/toadd")
    public String toaddKeyWord() {
        return addkeyword;
    }

}
