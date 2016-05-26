package weibo4j.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 话题
 *
 * @author SinaWeibo
 * @since weibo4j-V2 1.0.0
 */
public class UserTrend extends weibo4j.model.WeiboResponse {
    private String num;               
    private String hotword = null;
    private String trendId = null;
    private static final long serialVersionUID = 1925956704460743946L;
 
	public UserTrend() {
		super();
	}
	public UserTrend(weibo4j.http.Response res) throws weibo4j.model.WeiboException {
		super(res);
		weibo4j.org.json.JSONObject json = res.asJSONObject();
		try {
			num = json.getString("num");
			hotword = json.getString("hotword");
			trendId = json.getString("trend_id");
			if( json.getString("topicid")!=null)
				trendId = json.getString("topicid");
		} catch (weibo4j.org.json.JSONException je) {
			throw new weibo4j.model.WeiboException(je.getMessage() + ":" + json.toString(),
					je);
		}
	}
	public UserTrend(weibo4j.org.json.JSONObject json) throws weibo4j.model.WeiboException {
		try {
			num = json.getString("num");
			hotword = json.getString("hotword");
			trendId = json.getString("trend_id");
		} catch (weibo4j.org.json.JSONException je) {
			throw new weibo4j.model.WeiboException(je.getMessage() + ":" + json.toString(),
					je);
		}
	}
	
	public static List<UserTrend> constructTrendList(weibo4j.http.Response res) throws weibo4j.model.WeiboException {
	   	 try {
	            weibo4j.org.json.JSONArray list = res.asJSONArray();
	            int size = list.length();
	            List<UserTrend> trends = new ArrayList<UserTrend>(size);
	            for (int i = 0; i < size; i++) {
	            	trends.add(new UserTrend(list.getJSONObject(i)));
	            }
	            return trends;
	        } catch (weibo4j.org.json.JSONException jsone) {
	            throw new weibo4j.model.WeiboException(jsone);
	        } catch (weibo4j.model.WeiboException te) {
	            throw te;
	        }

	   }
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getHotword() {
		return hotword;
	}
	public void setHotword(String hotword) {
		this.hotword = hotword;
	}
	public String gettrendId() {
		return trendId;
	}
	public void settrendId(String trendId) {
		this.trendId = trendId;
	}
	@Override
	public String toString() {
		return "Trend [num=" + num + ", hotword=" + hotword + ", trendId="
				+ trendId + "]";
	}
    
    
}