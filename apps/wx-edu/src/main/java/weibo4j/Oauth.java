package weibo4j;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Oauth extends weibo4j.Weibo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7003420545330439247L;
	// ----------------------------针对站内应用处理SignedRequest获取accesstoken----------------------------------------
	public String access_token;
	public String user_id;

	public String getToken() {
		return access_token;
	}

	/*
	 * 解析站内应用post的SignedRequest split为part1和part2两部分
	 */
	public String parseSignedRequest(String signed_request) throws IOException,
			InvalidKeyException, NoSuchAlgorithmException {
		String[] t = signed_request.split("\\.", 2);
		// 为了和 url encode/decode 不冲突，base64url 编码方式会将
		// '+'，'/'转换成'-'，'_'，并且去掉结尾的'='。 因此解码之前需要还原到默认的base64编码，结尾的'='可以用以下算法还原
		int padding = (4 - t[0].length() % 4);
		for (int i = 0; i < padding; i++)
			t[0] += "=";
		String part1 = t[0].replace("-", "+").replace("_", "/");

		SecretKey key = new SecretKeySpec(weibo4j.util.WeiboConfig
				.getValue("client_SERCRET").getBytes(), "hmacSHA256");
		Mac m;
		m = Mac.getInstance("hmacSHA256");
		m.init(key);
		m.update(t[1].getBytes());
		String part1Expect = weibo4j.http.BASE64Encoder.encode(m.doFinal());

		sun.misc.BASE64Decoder decode = new sun.misc.BASE64Decoder();
		String s = new String(decode.decodeBuffer(t[1]));
		if (part1.equals(part1Expect)) {
			return ts(s);
		} else {
			return null;
		}
	}

	/*
	 * 处理解析后的json解析
	 */
	public String ts(String json) {
		try {
			weibo4j.org.json.JSONObject jsonObject = new weibo4j.org.json.JSONObject(json);
			access_token = jsonObject.getString("oauth_token");
			user_id = jsonObject.getString("user_id");
		} catch (weibo4j.org.json.JSONException e) {
			e.printStackTrace();
		}
		return access_token;

	}

	/*----------------------------Oauth接口--------------------------------------*/

	public weibo4j.http.AccessToken getAccessTokenByCode(String code) throws weibo4j.model.WeiboException {
		return new weibo4j.http.AccessToken(client.post(
				weibo4j.util.WeiboConfig.getValue("accessTokenURL"),
				new weibo4j.model.PostParameter[] {
						new weibo4j.model.PostParameter("client_id", weibo4j.util.WeiboConfig
								.getValue("client_ID")),
						new weibo4j.model.PostParameter("client_secret", weibo4j.util.WeiboConfig
								.getValue("client_SERCRET")),
						new weibo4j.model.PostParameter("grant_type", "authorization_code"),
						new weibo4j.model.PostParameter("code", code),
						new weibo4j.model.PostParameter("redirect_uri", weibo4j.util.WeiboConfig
								.getValue("redirect_URI")) }, false));
	}

	public String authorize(String response_type,String state) throws weibo4j.model.WeiboException {
		return weibo4j.util.WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
				+ weibo4j.util.WeiboConfig.getValue("client_ID").trim() + "&redirect_uri="
				+ weibo4j.util.WeiboConfig.getValue("redirect_URI").trim()
				+ "&response_type=" + response_type
				+ "&state="+state;
	}
	public String authorize(String response_type,String state,String scope) throws weibo4j.model.WeiboException {
		return weibo4j.util.WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
				+ weibo4j.util.WeiboConfig.getValue("client_ID").trim() + "&redirect_uri="
				+ weibo4j.util.WeiboConfig.getValue("redirect_URI").trim()
				+ "&response_type=" + response_type
				+ "&state="+state
				+ "&scope="+scope;
	}
}
