package io.wangxiao.user.user.service;

import io.wangxiao.core.BaseService;
import io.wangxiao.core.Condition;
import io.wangxiao.user.user.dao.UserDao;
import io.wangxiao.user.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bison on 1/14/16.
 * <p>
 * 用户数据服务
 */
@Service
public class UserService extends BaseService<User, UserDao> {

    /**
     * 按邮箱或手机号查询用户
     *
     * @param value
     * @return
     */
    public User findUserByEmailOrMobile(String value) {
        Condition condition = null;


//        if (ValidateUtils.isEmail(value))
//            condition = Condition.parseCondition("email.string.eq").setValue(value);
//
//        if (ValidateUtils.isMobile(value))
//            condition = Condition.parseCondition("mobile.string.eq").setValue(value);
//
        List<User> users = baseDao.find(null,
                Arrays.asList(
//                        condition,
                        Condition.parseCondition("email.string.eq").setValue(value),
                        Condition.parseCondition("isavalible.int.eq").setValue(0)
                ));

        if (users != null && users.size() > 0) return users.get(0);

        return new User();
    }
}
