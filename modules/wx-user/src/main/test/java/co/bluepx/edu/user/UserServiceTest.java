package io.wangxiao.user.user;

import co.bluepx.edu.BaseTest;
import io.wangxiao.user.user.entity.User;
import io.wangxiao.user.user.service.UserService;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 1/14/16.
 * <p>
 * 用户数据服务
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    UserService userService;

    private Gson gson = new Gson();


    @Test
    public void testFindUserByEmailOrMobile() {

        User user = userService.findUserByEmailOrMobile("1000111@qq.com");
        System.out.println(gson.toJson(user));

    }

}
