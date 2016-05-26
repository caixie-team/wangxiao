package io.wangxiao.edu.sysuser.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserOptRecord implements Serializable {
    public static Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 操作人账号
     */
    private String optAccount;
    /**
     * 操作人
     */
    private String optUsername;
    /**
     * 操作行为
     */
    private String optName;
    /**
     * 操作对象
     */
    private String optObj;
    /**
     * 操作ip
     */
    private String optIp;
    /**
     * 操作前数据
     */
    private String modifyBefore;
    /**
     * 操作后数据
     */
    private String modifyAfter;


    private String startTime;
    private String endTime;

    public SysUserOptRecord() {
    }

    public SysUserOptRecord(HttpServletRequest request, String optName, String optObj, Object beforeObj, Object afterObj) {
        if (ObjectUtils.isNotNull(beforeObj)) {
            String before = gson.toJson(beforeObj);
            String after = afterObj != null ? gson.toJson(afterObj) : "";
            if (!before.equals(after)) {
                this.createTime = new Date();
                this.optUsername = SingletonLoginUtils.getLoginUserName(request);
                JsonObject jsonObject = SingletonLoginUtils.getLoginUser(request);
                this.optAccount = jsonObject != null ? jsonObject.get("email").getAsString() : "";
                this.optIp = WebUtils.getIpAddr(request);
                this.optName = optName;
                this.optObj = optObj;
                this.modifyBefore = before;
                this.modifyAfter = after;
            }
        }
    }
}
