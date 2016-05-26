package io.wangxiao.edu.home.service.impl.user;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.home.dao.user.UserCodeDao;
import io.wangxiao.edu.home.entity.user.UserCode;
import io.wangxiao.edu.home.service.user.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service("userCodeService")
public class UserCodeServiceImpl implements UserCodeService {

    @Autowired
    private UserCodeDao userCodeDao;

    /**
     * 添加UserCode
     *
     * @param userCode 要添加的UserCode
     * @return id
     */
    public UserCode addUserCode(UserCode userCode) {
        userCode.setCreateTime(new Date());
        userCode.setStatus(0L);
        userCodeDao.addUserCode(userCode);
        return userCode;
    }

    /**
     * 根据id删除一个UserCode
     *
     * @param id 要删除的id
     */
    public void deleteUserCodeById(Long id) {
        userCodeDao.deleteUserCodeById(id);
    }

    /**
     * 修改UserCode
     *
     * @param userCode 要修改的UserCode
     */
    public void updateUserCode(UserCode userCode) {
        userCodeDao.updateUserCode(userCode);
    }

    /**
     * 根据id获取单个UserCode对象
     *
     * @param id 要查询的id
     * @return UserCode
     */
    public UserCode getUserCodeById(Long id) {
        return userCodeDao.getUserCodeById(id);
    }

    /*
     * 检查code是否核查不合法返回null
     */
    @Override
    public UserCode checkUserCode(String code) throws Exception {
        //获取加密后的信息解密
        String msg = PurseSecurityUtils.decryption(code, CommonConstants.SecurityKey);
        //解密后的json格式验证是否正确
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(msg).getAsJsonObject();
        String createTime = jsonObject.get("createTime").getAsString();
        Long userId = jsonObject.get("userId").getAsLong();
        Long id = jsonObject.get("id").getAsLong();
        //验证信息为空
        if (StringUtils.isEmpty(createTime) || ObjectUtils.isNull(userId) || ObjectUtils.isNull(id)) {
            return null;
        }
        UserCode userCode = userCodeDao.getUserCodeById(id);
        //查询userCode 状态是否已经使用过
        if (ObjectUtils.isNotNull(userCode) && userCode.getStatus().longValue() == 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(userCode.getCreateTime());
            c.add(Calendar.DAY_OF_MONTH, 3);
            if (new Date().getTime() > c.getTime().getTime()) {//超过三天
                return null;
            }
            return userCode;
        }
        return null;
    }
}