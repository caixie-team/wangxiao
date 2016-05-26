package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.letter.MsgReceive;
import io.wangxiao.edu.home.entity.letter.QueryMsgReceive;
import io.wangxiao.edu.home.service.letter.MsgReceiveService;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppMsgController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppMsgController.class);
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Setter
    @Getter
    private QueryCourse queryCourse;
    @Autowired
    GuidGeneratorService guidGeneratorService;

    /**
     * 查询该用户有多少未读消息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/letter/unread")
    @ResponseBody
    public Map<String, Object> queryUnReadLetter(@RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            // 未登录不可操作
            if (userId == 0) {
                json = this.getJsonMap(false, "请登录", null);
                return json;
            }
            Map<String, String> queryletter = msgReceiveService.queryUnReadMsgReceiveNumByCusId(userId);// 查询该用户有多少未读消息
            json = this.getJsonMap(true, "查询成功", queryletter.get("unReadNum"));
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryUnReadLetter()---error", e);
        }
        return json;
    }

    /**
     * app查询个人消息列表
     *
     * @param page
     * @param userId
     * @return
     */
    @RequestMapping("/user/letter")
    @ResponseBody
    public Map<String, Object> userLetter(@ModelAttribute("page") PageEntity page,
                                          @RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            page.setPageSize(10);// 分页页数为6
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setReceivingCusId(userId);// set用户id
            // 查询站内信收件箱
            List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive, page);
            // 封装数据并返回
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("letterList", queryLetterList);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("userLetter()---error", e);
        }
        return json;
    }

}
