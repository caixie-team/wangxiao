package com.atdld.core.test;

import com.atdld.os.core.util.security.PurseSecurityUtils;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        getSkey();
        //dispalykeyinfos();
    }

    public static void getSkey() {

        String domain = "182.92.64.15";
        String losdt = "2017-03-01";

        String key = "0123453789ABCDEFGHIJKLMNOPQRSTUV";
        Map<String, String> map = new HashMap<String, String>();
        map.put("domain", domain);
        map.put("l", "1");
        map.put("w", "1");
        map.put("edu", "http://xx.cn/com");
        map.put("dt", losdt);
        Gson gson = new Gson();
        String value = gson.toJson(map);
        String domiankey = PurseSecurityUtils.secrect(value, key);
        System.out.println(domiankey);
    }

    public static void dispalykeyinfos() {
        try {
            String domiankey = "";
            String sekey = "0123453789ABCDEFGHIJKLMNOPQRSTUV";
            domiankey = PurseSecurityUtils.decryption(domiankey, sekey);
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(domiankey).getAsJsonObject();
            Map<String, String> mapss = gson.fromJson(jsonObject, new TypeToken<Map<String, String>>() {
            }.getType());
        } catch (Exception e) {
        }
    }
}
