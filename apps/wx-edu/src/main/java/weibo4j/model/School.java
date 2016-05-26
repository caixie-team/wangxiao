package weibo4j.model;

import java.util.ArrayList;
import java.util.List;

public class School extends weibo4j.model.WeiboResponse {

	private static final long serialVersionUID = -5991828656755790609L;
	private int id; // 学校id
	private String name; // 学校名称

	public School(weibo4j.http.Response res) throws weibo4j.model.WeiboException {
		super(res);
		weibo4j.org.json.JSONObject json = res.asJSONObject();
		try {
			id = json.getInt("id");
			name = json.getString("name");
		} catch (weibo4j.org.json.JSONException je) {
			throw new weibo4j.model.WeiboException(je.getMessage() + ":" + json.toString(),
					je);
		}
	}

	public School(weibo4j.org.json.JSONObject json) throws weibo4j.model.WeiboException {
		try {
			id = json.getInt("id");
			name = json.getString("name");
		} catch (weibo4j.org.json.JSONException je) {
			throw new weibo4j.model.WeiboException(je.getMessage() + ":" + json.toString(),
					je);
		}
	}

	public static List<School> constructSchool(weibo4j.http.Response res) throws weibo4j.model.WeiboException {
		try {
			weibo4j.org.json.JSONArray list = res.asJSONArray();
			int size = list.length();
			List<School> schools = new ArrayList<School>(size);
			for (int i = 0; i < size; i++) {
				schools.add(new School(list.getJSONObject(i)));
			}
			return schools;
		} catch (weibo4j.org.json.JSONException jsone) {
			throw new weibo4j.model.WeiboException(jsone);
		} catch (weibo4j.model.WeiboException te) {
			throw te;
		}

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + "]";
	}

}
