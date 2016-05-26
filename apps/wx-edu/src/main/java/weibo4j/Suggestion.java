package weibo4j;

public class Suggestion extends weibo4j.Weibo {
/**
	 * 
	 */
	private static final long serialVersionUID = 1861364044145921824L;
	//---------------------------------推荐接口---------------------------------------------------
	/**
	 * 返回系统推荐的热门用户列表
	 * 
	 * @return list of the users
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/hot">suggestions/users/hot</a>
	 * @since JDK 1.5
	 */
	
	public weibo4j.org.json.JSONArray suggestionsUsersHot() throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL")+"suggestions/users/hot.json").asJSONArray();
	}
	
	public weibo4j.org.json.JSONArray suggestionsUsersHot(String category) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL")+"suggestions/users/hot.json",new weibo4j.model.PostParameter[]{
			new weibo4j.model.PostParameter("category", category)
		}).asJSONArray();
	}
	/**
	 * 获取用户可能感兴趣的人 
	 * 
	 * @return list of the user's id
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/may_interested">suggestions/users/may_interested</a>
	 * @since JDK 1.5
	 */
	public weibo4j.org.json.JSONArray suggestionsUsersMayInterested() throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL")+"suggestions/users/may_interested.json").asJSONArray();
	}
	public weibo4j.org.json.JSONArray suggestionsUsersMayInterested(int count,int page) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL")+"suggestions/users/may_interested.json",new weibo4j.model.PostParameter[]{
			new weibo4j.model.PostParameter("count", count),
			new weibo4j.model.PostParameter("page", page)
		}).asJSONArray();
	}
	/**
	 * 根据一段微博正文推荐相关微博用户
	 * 
	 * @return list of the users
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/by_status">suggestions/users/by_status</a>
	 * @since JDK 1.5
	 */
	public weibo4j.model.UserWapper suggestionsUsersByStatus(String content) throws weibo4j.model.WeiboException {
		return weibo4j.model.User.constructWapperUsers(client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "suggestions/users/by_status.json", new weibo4j.model.PostParameter[]{
				new weibo4j.model.PostParameter("content", content)
		}));
	}
	public weibo4j.model.UserWapper suggestionsUsersByStatus(String content,int num) throws weibo4j.model.WeiboException {
		return weibo4j.model.User.constructWapperUsers(client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "suggestions/users/by_status.json", new weibo4j.model.PostParameter[]{
				new weibo4j.model.PostParameter("content", content),
				new weibo4j.model.PostParameter("num", num)
		}));
	}
	/**
	 * 获取微博精选推荐
	 * 
	 * @return list of the status
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/statuses/hot">suggestions/statuses/hot</a>
	 * @since JDK 1.5
	 */
	public weibo4j.model.StatusWapper suggestionsStatusesHot(int type,int isPic) throws weibo4j.model.WeiboException {
		return weibo4j.model.Status.constructWapperStatus(client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "suggestions/statuses/hot.json", new weibo4j.model.PostParameter[]{
				new weibo4j.model.PostParameter("type", type),
				new weibo4j.model.PostParameter("is_pic", isPic)
		}));
	}
	public weibo4j.model.StatusWapper suggestionsStatusesHot(int type,int isPic, weibo4j.model.Paging page) throws weibo4j.model.WeiboException {
		return weibo4j.model.Status.constructWapperStatus(client.get(weibo4j.util.WeiboConfig.getValue("baseURL") + "suggestions/statuses/hot.json", new weibo4j.model.PostParameter[]{
				new weibo4j.model.PostParameter("type", type),
				new weibo4j.model.PostParameter("is_pic", isPic)
		}, page));
	}
	/**
	 * 返回系统推荐的热门收藏 
	 * 
	 * @return list of the status
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/favorites/hot">suggestions/favorites/hot</a>
	 * @since JDK 1.5
	 */
	public weibo4j.org.json.JSONArray suggestionsFavoritesHot() throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL")+"suggestions/favorites/hot.json").asJSONArray();
	}
	public weibo4j.org.json.JSONArray suggestionsFavoritesHot(int page,int count) throws weibo4j.model.WeiboException {
		return client.get(weibo4j.util.WeiboConfig.getValue("baseURL")+"suggestions/favorites/hot.json",new weibo4j.model.PostParameter[]{
			new weibo4j.model.PostParameter("page", page),
			new weibo4j.model.PostParameter("count", count)
		}).asJSONArray();
	}
	/**
	 * 把某人标识为不感兴趣的人  
	 * 
	 * @return user
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/not_interested">suggestions/users/not_interested</a>
	 * @since JDK 1.5
	 */
	public weibo4j.model.User suggestionsUsersNot_interested(String uid) throws weibo4j.model.WeiboException {
		return new weibo4j.model.User(client.post(weibo4j.util.WeiboConfig.getValue("baseURL")+"suggestions/users/not_interested.json",new weibo4j.model.PostParameter[]{
			new weibo4j.model.PostParameter("uid", uid)
		}).asJSONObject());
	}
}
