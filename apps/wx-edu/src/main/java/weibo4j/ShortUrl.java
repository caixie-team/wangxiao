package weibo4j;

public class ShortUrl extends weibo4j.Weibo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**  
	 * 长链接转为短链接
	 *
	 *
	 */
	public weibo4j.org.json.JSONObject longToShortUrl (String url_long) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/shorten.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_long",url_long)
		}).asJSONObject();
	}
	
	/**
	 * 短链接转为长链接
	 * 
	 */
	public weibo4j.org.json.JSONObject shortToLongUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/expand.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 获取短链接的总点击数
	 * 
	 * 
	 */
	public weibo4j.org.json.JSONObject clicksOfUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/clicks.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 获取一个短链接点击的referer来源和数量 
	 * 
	 * 
	 */
	public weibo4j.org.json.JSONObject referersOfUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/referers.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 
	 * 获取一个短链接点击的地区来源和数量 
	 * 
	 */
	public weibo4j.org.json.JSONObject locationsOfUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/locations.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 获取短链接在微博上的微博分享数 
	 * 
	 * 
	 * 
	 */
	public weibo4j.org.json.JSONObject shareCountsOfUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/share/counts.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * 获取包含指定单个短链接的最新微博内容 
	 * 
	 * 
	 */
	public weibo4j.org.json.JSONObject statusesContentUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/share/statuses.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * 获取短链接在微博上的微博评论数 
	 * 
	 * 
	 */
	public weibo4j.org.json.JSONObject commentCountOfUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/comment/counts.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * 获取包含指定单个短链接的最新微博评论 
	 * 
	 */
	public weibo4j.org.json.JSONObject commentsContentUrl (String url_short) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "short_url/comment/comments.json",new weibo4j.model.PostParameter[] {
			new weibo4j.model.PostParameter("url_short",url_short)
		}).asJSONObject();
	}
}
