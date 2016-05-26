package com.atdld.os.sns.controller.keyword;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.sns.service.lucene.LuceneLastNumService;
import com.atdld.os.sns.service.quartz.SnsDataTimingService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.keyword.LuceneAction
 * @description
 * @Create Date : 2014-4-3 下午12:39:44
 */
@Controller
@RequestMapping("/admin")
public class AdminLuceneController extends SnsBaseController {

    @Autowired
    private SnsDataTimingService snsDataTimingService;

    @Autowired
    private LuceneLastNumService luceneLastNumService;

    private MemCache memCache = MemCache.getInstance();
    private static final String lucene = getViewPath("/admin/lucene/lucene");

    private Logger logger = LoggerFactory.getLogger(AdminLuceneController.class);
    @RequestMapping("/lucenecreat")
    public
    @ResponseBody
    Map<String, Object> createLuceneIndex() {
        Map<String, Object> map = new HashMap<String, Object>(2);
        try {
            Object isCreating = memCache.get(MemConstans.MEM_LUCENE_CREATING);
            // 判断是否在定时生成定时生成时会将值放置到memcache中.
            if (isCreating != null) {
                map.put("msg", "系统正在生成索引，请稍后再试...");
                return map;// 正在生成直接返回
            }
            // 清楚数据
            luceneLastNumService.updateLuceneLastClear();
            // 重新生成
            snsDataTimingService.initLucenIndex();
            map.put("msg", "生成成功.");
        } catch (Exception e) {
            memCache.remove(MemConstans.MEM_LUCENE_CREATING);
            logger.error("lucenecreat:",e);
        }
        return map;
    }

    @RequestMapping("/tolucenecreat")
    public String tocreatLuceneIndex() {
        return lucene;
    }
}
