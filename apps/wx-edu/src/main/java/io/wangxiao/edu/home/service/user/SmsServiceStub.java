package io.wangxiao.edu.home.service.user;

import lombok.Data;

@Data
public class SmsServiceStub {
	private final String USERID = "";
    private final String PASSWORD = "";
    private String msgContent;
    private String destNumber;
    public void sendmsg(){
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
