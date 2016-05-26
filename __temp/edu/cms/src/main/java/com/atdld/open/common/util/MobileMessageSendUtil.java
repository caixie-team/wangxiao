package com.atdld.open.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 发送短信
 *
 */
public class MobileMessageSendUtil {
	
	public static void main(String[] args) {
		//'sendMobileMessage("15811419149,18911893513,18515035085","测试短信发送");
	}
	
	/**
	 * 发送短信
	 * @param mobiles 手机号,如果是多个手机号用“,”隔开
	 * @param contents 短信内容
	 */
	public static void sendMobileMessage(String mobiles,String contents){
		try {
			HttpClient client = new HttpClient();

			client.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

			PostMethod post = new PostMethod("http://www.sp1086.com/sms/smsInterface.do");
			NameValuePair username = new NameValuePair("username", "lichenjy");
			NameValuePair password = new NameValuePair("password", "112233");
			NameValuePair mobile = new NameValuePair("mobile",mobiles);
			NameValuePair content = new NameValuePair("content",contents+"【理臣教育】");

			post.setRequestBody(new NameValuePair[] { username, password,mobile, content });
			client.executeMethod(post);
			String result = post.getResponseBodyAsString();

			System.out.println(result);

			post.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
