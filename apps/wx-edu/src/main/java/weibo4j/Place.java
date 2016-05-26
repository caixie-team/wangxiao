package weibo4j;

public class Place extends weibo4j.Weibo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****************动态读取************************/
	/**
	 * 注：没有写完
	 * 
	 * 获取当前登录用户与其好友的位置动态 
	 */
	public weibo4j.model.StatusWapper friendsTimeLine () throws weibo4j.model.WeiboException {
		return weibo4j.model.Status.constructWapperStatus(client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/friends_timeline.json"));
	}
	
	
	/****************用户读取************************/
	/**
	 * 获取LBS位置服务内的用户信息 
	 * 
	 * 
	 */
	public weibo4j.org.json.JSONObject userInfoInLBS (String uid) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/users/show.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	public weibo4j.org.json.JSONObject userInfoInLBS (String uid,int base_app) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/users/show.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("uid", uid),
			new weibo4j.model.PostParameter("base_app", base_app)
		}).asJSONObject();
	}
	
	/**
	 * 获取用户签到过的地点列表
	 * 
	 *   注：没有写完
	 */
	public weibo4j.org.json.JSONObject checkinsList (String uid) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/users/checkins.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/**
	 * 获取用户的照片列表 
	 * 
	 * 注：没有写完
	 */
	
	public weibo4j.org.json.JSONObject photoList (String uid) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/users/photos.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/**
	 * 获取用户的点评列表 
	 * 
	 * 注：没有写完
	 */
	
	public weibo4j.org.json.JSONObject tipsList (String uid) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/users/tips.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/****************地点读取************************/
	/**
	 * 获取地点详情 
	 * 
	 * 
	 */
	public weibo4j.org.json.JSONObject poisShow (String poiid) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/pois/show.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("poiid", poiid)
		}).asJSONObject();
	}
	
	public weibo4j.org.json.JSONObject poisShow (String poiid,int base_app) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/pois/show.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("poiid", poiid),
			new weibo4j.model.PostParameter("base_app",base_app)
		}).asJSONObject();
	}
	
	/**
	 * 获取在某个地点签到的人的列表 
	 * 
	 * 注：没写完
	 */
	public weibo4j.org.json.JSONObject poisUsersList (String poiid) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/pois/show.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("poiid", poiid) }).asJSONObject();
	}
	
	/**
	 * 获取在某个地点点评的列表 
	 * 
	 * 注：没写完
	 */
	public weibo4j.model.User poisTipsList (String poiid) throws weibo4j.model.WeiboException {
		return new weibo4j.model.User(client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "place/pois/tips.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("poiid", poiid) }).asJSONObject());
	}
	
}
