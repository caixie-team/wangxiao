package com.atdld.os.core.util;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.atdld.os.core.util.security.PurseSecurityUtils;

import java.util.HashMap;
import java.util.Map;

public class Tkey {
    public static void main(String[] args) {
        getSkey();
        dispalykeyinfos();
        System.out.println();
    }

    public static  void getSkey(){
        String key ="0123453789ABCDEFGHIJKLMNOPQRSTUV";
        Map<String, String> map = new HashMap<String, String>();
        map.put("domain", "182.92.64.15");
        map.put("l", "1");
        map.put("w", "1");
        map.put("edu", "http://tj.268xue.com/com/p.action");
        map.put("dt", "2016-01-01");
        Gson gson = new Gson();
        String value=gson.toJson(map);
        String domiankey= PurseSecurityUtils.secrect(value, key);
        System.out.println(domiankey);
    }

    public  static void dispalykeyinfos(){
        try {
            String domiankey ="9FZ01x8Fsux1yMCmKIYgOpXgZhLvlNXJKMPHjKGCPNMN+AJZzziG+HZCXCecFUbGQVvzU0N2cWego7ihbpvuzhQzVUVqJBeVjUj0ojGUqfBhDyndmig0h/ea20zJKokoaht7saWXkvM=";
            String sekey ="0123453789ABCDEFGHIJKLMNOPQRSTUV";
            domiankey=PurseSecurityUtils.decryption(domiankey, sekey);
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject  = jsonParser.parse(domiankey).getAsJsonObject();
            Map<String, String> mapss = gson.fromJson( jsonObject, new TypeToken<Map<String, String>>() {}.getType());
            System.out.println("++mapss:"+mapss);
        } catch (Exception e) {
        }
    }
}
