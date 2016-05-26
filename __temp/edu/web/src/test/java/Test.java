import com.atdld.os.core.util.web.HttpUtil;
import com.atdld.os.edu.entity.user.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.util.Map;

/**
 * Created by liuqinggang on 15/8/29.
 */
public class Test {
    public static void main(String [] a){
        // System.out.println(HttpUtil.doGet("http://127.0.0.1:8080/testjson", null));

        //System.out.println(HttpUtil.doPost("http://127.0.0.1:8080/testjson", null));

        // gson日期默认格式设置
         Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();


        User user = new User();
        user.setIsavalible(98);
        user.setId(198L);
        user.setMobileIsavalible(1);
        String jsonStr = gson.toJson(user);
        Map<String, Object> resultMap = gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {}.getType());
        System.out.println("map:" + resultMap);
        //map:{id=198.0, nickname=, email=, emailIsavalible=0.0, mobile=, mobileIsavalible=1.0, isavalible=98.0, userip=}
        Map<String, String> resultMap2 = gson.fromJson( jsonStr, new TypeToken<Map<String, String>>() { }.getType());
        System.out.println("map2:" + resultMap2);
        //map2:{id=198, nickname=, email=, emailIsavalible=0, mobile=, mobileIsavalible=1, isavalible=98, userip=}


       // System.out.println("user:"+user);


    }
}
