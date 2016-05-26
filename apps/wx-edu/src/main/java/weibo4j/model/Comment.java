package weibo4j.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment extends WeiboResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1272011191310628589L;
	private Date createdAt;                    //评论时间
	private long id;                           //评论id
	private String mid;						   //评论id
	private String idstr;					   //评论id
	private String text;                       //评论内容
	private String source;                     //内容来源
	private Comment replycomment = null;       //回复的评论内容
	private User user = null;                  //User对象
	private weibo4j.model.Status status = null;              //Status对象

	/*package*/public Comment(weibo4j.http.Response res) throws WeiboException {
		super(res);
		weibo4j.org.json.JSONObject json =res.asJSONObject();
		try {
			id = json.getLong("id");
			mid = json.getString("mid");
			idstr = json.getString("idstr");
			text = json.getString("text");
			source = json.getString("source");
			createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
			if(!json.isNull("user"))
				user = new User(json.getJSONObject("user"));
			if(!json.isNull("status"))
				status = new weibo4j.model.Status(json.getJSONObject("status"));
			if(!json.isNull("reply_comment"))
				replycomment = (new Comment(json.getJSONObject("reply_comment")));
		} catch (weibo4j.org.json.JSONException je) {
			throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
		}
	}

	public Comment(weibo4j.org.json.JSONObject json)throws WeiboException, weibo4j.org.json.JSONException {
		id = json.getLong("id");
		mid = json.getString("mid");
		idstr = json.getString("idstr");
		text = json.getString("text");
		source = json.getString("source");
		createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
		if(!json.isNull("user"))
			user = new User(json.getJSONObject("user"));
		if(!json.isNull("status"))
			status = new weibo4j.model.Status(json.getJSONObject("status"));
		if(!json.isNull("reply_comment"))
			replycomment = (new Comment(json.getJSONObject("reply_comment")));
	}

	public Comment(String str) throws WeiboException, weibo4j.org.json.JSONException {
		// StatusStream uses this constructor
		super();
		weibo4j.org.json.JSONObject json = new weibo4j.org.json.JSONObject(str);
		id = json.getLong("id");
		mid = json.getString("mid");
		idstr = json.getString("idstr");
		text = json.getString("text");
		source = json.getString("source");
		createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
		if(!json.isNull("user"))
			user = new User(json.getJSONObject("user"));
		if(!json.isNull("status"))
			status = new weibo4j.model.Status(json.getJSONObject("status"));
		if(!json.isNull("reply_comment"))
			replycomment = (new Comment(json.getJSONObject("reply_comment")));
	}

	public static weibo4j.model.CommentWapper constructWapperComments(weibo4j.http.Response res) throws WeiboException {
		weibo4j.org.json.JSONObject json = res.asJSONObject(); //asJSONArray();
		try {
			weibo4j.org.json.JSONArray comments = json.getJSONArray("comments");
			int size = comments.length();
			List<Comment> comment = new ArrayList<Comment>(size);
			for (int i = 0; i < size; i++) {
				comment.add(new Comment(comments.getJSONObject(i)));
			}
			long previousCursor = json.getLong("previous_curosr");
			long nextCursor = json.getLong("next_cursor");
			long totalNumber = json.getLong("total_number");
			String hasvisible = json.getString("hasvisible");
			return new weibo4j.model.CommentWapper(comment, previousCursor, nextCursor,totalNumber,hasvisible);
		} catch (weibo4j.org.json.JSONException jsone) {
			throw new WeiboException(jsone);
		}
	}
	public Date getCreatedAt() {
		return createdAt;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getSource() {
		return source;
	}

	public Comment getReplycomment() {
		return replycomment;
	}

	public User getUser() {
		return user;
	}

	public weibo4j.model.Status getStatus() {
		return status;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setReplycomment(Comment replycomment) {
		this.replycomment = replycomment;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(weibo4j.model.Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [createdAt=" + createdAt + ", id=" + id + ", mid="
				+ mid + ", idstr=" + idstr + ", text=" + text + ", source="
				+ source + ", replycomment=" + replycomment + ", user=" + user
				+ ", status=" + status +"]";
	}

}
