package com.atdld.os.edu.service.user;

import java.net.URLEncoder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * @ClassName  com.atdld.os.edu.service.user.SmsServiceStub
 * @description
 * @author :
 * @Create Date : 2014年9月22日 下午5:30:39
 */
public class SmsServiceStub {
	private final String USERID = ""; 
    private final String PASSWORD = "";
    private String msgContent;
    private String destNumber;
    public void sendmsg(){
        try {
            HttpClient client = new HttpClient();
            String url="http://138.u59e.com/index.php?action=interface&op=sendmess";
            /* method.setParameter("username", USERID);//用户名称
             method.setParameter("userpwd", PASSWORD);//用户密码
             method.setParameter("logid", "");//短信id，同一批内容相同的短信第一次发送时可以为空，第二次可以用第一次用本函数后返回的值
             method.setParameter("mobiles", destNumber);
             method.setParameter("content", 
                     "0:"+msgContent+",1:"+URLEncoder.encode(msgContent,"UTF-8")+
                     ",2:"+new String(msgContent.getBytes(),"UTF-8")
             +"3:"+new String(msgContent.getBytes("ISO-8859-1"),"UTF-8") );
             */
             url=url+"&username="+USERID;
             url=url+"&userpwd="+PASSWORD;
             url=url+"&logid=";
             url=url+"&mobiles="+destNumber;
             url=url+"&content="+URLEncoder.encode(msgContent,"UTF-8");
             //要提交的短信内容，中文内容要使用UTF-8字符集进行URL编码，避免有特殊符号造成提交失败
            //例如c# 用HttpUtility.UrlEncode("发送内容",Encoding.UTF8) ，java用URLEncoder.encode("发送内容", "UTF-8")
             PostMethod method = new PostMethod(url);
             client.executeMethod(method);
             method.getResponseBodyAsStream();
             /*BufferedReader 
             reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"UTF-8"));
             String str = null;  
               while ((str = reader.readLine()) != null) {
                 System.out.println(str);
             }*/
            method.abort();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getMsgContent() {
        return msgContent;
    }
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    public String getDestNumber() {
        return destNumber;
    }
    public void setDestNumber(String destNumber) {
        this.destNumber = destNumber;
    }
}
