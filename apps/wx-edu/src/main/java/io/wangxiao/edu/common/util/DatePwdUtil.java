package io.wangxiao.edu.common.util;

import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.entity.user.User;

/**
 * 用户密码加密
 */
public class DatePwdUtil {
    private static final int HMAC_KEY_LEN = 60;

    /**
     * 用于账号注册
     *
     * @param user
     * @return
     */
    public static User getUserSecretUser(User user) {
        String customerKey = StringUtils.getRandomString(HMAC_KEY_LEN);
        String password = PurseSecurityUtils.secrect(user.getPassword(), customerKey);
        user.setCustomerkey(customerKey);
        user.setPassword(password);
        return user;
    }


    /**
     * 获取加密密码
     * 用于登录时，信息对比
     *
     * @param pwd
     * @param customerKey
     * @return
     */
    public static String getUserSecretPwd(String pwd, String customerKey) {
        String password = PurseSecurityUtils.secrect(pwd, customerKey);
        return password;
    }


    /**
     * 判断密码是否正确
     *
     * @param dbPassword
     * @param userPassword
     * @param userke
     * @return
     */
    public static boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
        String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
        return despassword.equals(dbPassword);
    }
}
