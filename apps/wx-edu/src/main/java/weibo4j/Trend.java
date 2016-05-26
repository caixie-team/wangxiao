package weibo4j;

import java.util.List;

public class Trend extends weibo4j.Weibo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 903299515334415487L;

	/*----------------------------话题接口----------------------------------------*/
	/**
	 * 获取某人的话题列表
	 * 
	 * @param uid
	 *            需要获取话题的用户的UID
	 * @return list of the userTrend
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/trends">trends</a>
	 * @since JDK 1.5
	 */
	public List<weibo4j.model.UserTrend> getTrends(String uid) throws weibo4j.model.WeiboException {
		return weibo4j.model.UserTrend
				.constructTrendList(client.get(
						weibo4j.util.WeiboConfig.getValue("baseURL") + "trends.json",
						new weibo4j.model.PostParameter[]{new weibo4j.model.PostParameter("uid", uid)}));
	}

	/**
	 * 获取某人的话题列表
	 * 
	 * @param uid
	 *            需要获取话题的用户的UID
	 * @param page
	 *            返回结果的页码，默认为1
	 * @return list of the userTrend
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/trends">trends</a>
	 * @since JDK 1.5
	 */
	public List<weibo4j.model.UserTrend> getTrends(String uid, weibo4j.model.Paging page)
			throws weibo4j.model.WeiboException {
		return weibo4j.model.UserTrend
				.constructTrendList(client.get(
						weibo4j.util.WeiboConfig.getValue("baseURL") + "trends.json",
						new weibo4j.model.PostParameter[]{new weibo4j.model.PostParameter("uid", uid)}, page));
	}

	/**
	 * 判断当前用户是否关注某话题
	 * 
	 * @param trend_name
	 *            话题关键字，必须做URLencode
	 * @return jsonobject
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws weibo4j.org.json.JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/is_follow">trends/is_follow</a>
	 * @since JDK 1.5
	 */
	public weibo4j.org.json.JSONObject isFollow(String trend_name) throws weibo4j.model.WeiboException {
			return client.get(weibo4j.util.WeiboConfig.getValue("baseURL")+ "trends/is_follow.json",
							new weibo4j.model.PostParameter[] {
				new weibo4j.model.PostParameter("trend_name", trend_name) }).asJSONObject();
	}

	/**
	 * 返回最近一小时内的热门话题
	 * 
	 * @param base_app
	 *            是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0
	 * @return list of trends
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws weibo4j.org.json.JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/hourly">trends/hourly</a>
	 * @since JDK 1.5
	 */
	public List<weibo4j.model.Trends> getTrendsHourly() throws weibo4j.model.WeiboException {
		return weibo4j.model.Trends.constructTrendsList(client.get(
				weibo4j.util.WeiboConfig.getValue("baseURL") + "trends/hourly.json"));
	}
	
	public List<weibo4j.model.Trends> getTrendsHourly(Integer base_app) throws weibo4j.model.WeiboException {
		return weibo4j.model.Trends.constructTrendsList(client.get(
				weibo4j.util.WeiboConfig.getValue("baseURL") + "trends/hourly.json",
				new weibo4j.model.PostParameter[]{new weibo4j.model.PostParameter("base_app", base_app.toString())}));
	}

	/**
	 * 返回最近一天内的热门话题
	 * 
	 * @param base_app
	 *            是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0
	 * @return list of trends
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws weibo4j.org.json.JSONException
	 * @see <a href="http://open.weibo.com/wiki/2/trends/daily">trends/daily</a>
	 * @since JDK 1.5
	 */
	public List<weibo4j.model.Trends> getTrendsDaily() throws weibo4j.model.WeiboException {
		return weibo4j.model.Trends.constructTrendsList(client.get(
				weibo4j.util.WeiboConfig.getValue("baseURL") + "trends/daily.json"));
	}
	
	public List<weibo4j.model.Trends> getTrendsDaily(Integer base_app) throws weibo4j.model.WeiboException {
		return weibo4j.model.Trends.constructTrendsList(client.get(
				weibo4j.util.WeiboConfig.getValue("baseURL") + "trends/daily.json",
				new weibo4j.model.PostParameter[]{new weibo4j.model.PostParameter("base_app", base_app
						.toString())}));
	}

	/**
	 * 返回最近一周内的热门话题
	 * 
	 * @param base_app
	 *            是否只获取当前应用的数据。0为否（所有数据），1为是（仅当前应用），默认为0
	 * @return list of trends
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws weibo4j.org.json.JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/weekly">trends/weekly</a>
	 * @since JDK 1.5
	 */
	public List<weibo4j.model.Trends> getTrendsWeekly() throws weibo4j.model.WeiboException {
		return weibo4j.model.Trends.constructTrendsList(client.get(
				weibo4j.util.WeiboConfig.getValue("baseURL") + "trends/weekly.json"));
	}
	
	public List<weibo4j.model.Trends> getTrendsWeekly(Integer base_app) throws weibo4j.model.WeiboException {
		return weibo4j.model.Trends.constructTrendsList(client.get(
				weibo4j.util.WeiboConfig.getValue("baseURL") + "trends/weekly.json",
				new weibo4j.model.PostParameter[]{new weibo4j.model.PostParameter("base_app", base_app.toString())}));
	}

	/**
	 * 关注某话题
	 * 
	 * @param trend_name
	 *            要关注的话题关键词。
	 * @return UserTrend
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws weibo4j.org.json.JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/follow">trends/follow</a>
	 * @since JDK 1.5
	 */
	public weibo4j.model.UserTrend trendsFollow(String trend_name) throws weibo4j.model.WeiboException {
		return new weibo4j.model.UserTrend(client.post(weibo4j.util.WeiboConfig.getValue("baseURL")
				+ "trends/follow.json",
				new weibo4j.model.PostParameter[] { new weibo4j.model.PostParameter("trend_name",
						trend_name) }));
	}

	/**
	 * 取消对某话题的关注
	 * 
	 * @param trend_id
	 *            要取消关注的话题ID
	 * @return jsonobject
	 * @throws weibo4j.model.WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws weibo4j.org.json.JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/destroy">trends/destroy</a>
	 * @since JDK 1.5
	 */
	public weibo4j.org.json.JSONObject trendsDestroy(Integer trend_id) throws weibo4j.model.WeiboException {
			return client.post(weibo4j.util.WeiboConfig.getValue("baseURL")
							+ "trends/destroy.json",
							new weibo4j.model.PostParameter[] { new weibo4j.model.PostParameter("trend_id",trend_id.toString()) }).asJSONObject();
	}

}
