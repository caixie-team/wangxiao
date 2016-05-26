package io.wangxiao.edu.home.service.impl.weixin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.weixin.WeixinSetDao;
import io.wangxiao.edu.home.entity.weixin.WeixinSetReply;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.home.service.weixin.WeixinSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信常规回复设置service
 */
@Service("weixinSetService")
public class WeixinSetServiceImpl implements WeixinSetService {
    @Autowired
    private WeixinSetDao weixinSetDao;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    CacheKit cacheKit = CacheKit.getInstance();

    /**
     * 根据回复类型（如默认、关注时回复）获取常规回复设置
     *
     * @param type
     * @return
     */
    public WeixinSetReply getWeixinSetReply(String type) {
        return weixinSetDao.getWeixinSetReply(type);
    }

    /**
     * 添加常规回复
     *
     * @param weixinSetReply
     */
    public void addSetWeixinReply(WeixinSetReply weixinSetReply) {
        weixinSetDao.addSetWeixinReply(weixinSetReply);
    }

    /**
     * 更新常规回复
     *
     * @param weixinSetReply
     */
    public void updateSetWeixinReply(WeixinSetReply weixinSetReply) {
        weixinSetDao.updateSetWeixinReply(weixinSetReply);
    }

    /**
     * 根据回复素材ID删除常规回复（回复素材被删除时，同时删除引用者中的回复）
     *
     * @param id
     */
    public void delWeixinSetReply(Long id) {
        weixinSetDao.delWeixinSetReply(id);
    }

    /**
     * 获取微信access_token
     *
     * @return
     */
    public String getWeixinAccessToken() {
        String access_token = "";
        access_token = (String) cacheKit.get(MemConstans.WX_ACCESS_TOKEN);
        if (StringUtils.isNotEmpty(access_token)) {
            return access_token;
        }
        /**
         * 发送get请求，获取access_token
         * grant_type 	 	获取access_token填写client_credential
         appid 	 	第三方用户唯一凭证
         secret 	 	第三方用户唯一凭证密钥，既appsecret
         * */
        Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixin");
        @SuppressWarnings("unchecked")
        Map<String, Object> weixinMapVal = (Map<String, Object>) weixinMap.get("weixin");
        String APPID = (String) weixinMapVal.get("wxAppID");
        String APPSECRET = (String) weixinMapVal.get("wxAppSecret");

        String urlStr = "https://api.weixin.qq.com/cgi-bin/token";
        String param = "grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET + "";
        String resMsg = HttpUtil.doGet(urlStr, param);
        Gson gson = new Gson();
        Map<String, String> ps = gson.fromJson(resMsg, new TypeToken<Map<String, String>>() {
        }.getType());
        access_token = ps.get("access_token");
        if (StringUtils.isNotEmpty(access_token)) {
            cacheKit.set(MemConstans.WX_ACCESS_TOKEN, access_token, MemConstans.WX_ACCESS_TOKEN_TIME);
        }

        return access_token;
    }
}


