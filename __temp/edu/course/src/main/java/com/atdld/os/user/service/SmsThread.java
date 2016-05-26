package com.atdld.os.user.service;


/**
 * 
 * @ClassName  com.atdld.os.edu.service.user.SmsThread
 * @description
 * @author :
 * @Create Date : 2014年9月22日 下午5:32:52
 */
public class SmsThread extends Thread{
    private final SmsServiceStub sendSms;

    public SmsThread(SmsServiceStub sendSms) {
        this.sendSms = sendSms;
    }

    @Override
    public void run() {
        sendSms.sendmsg();
    }
}
