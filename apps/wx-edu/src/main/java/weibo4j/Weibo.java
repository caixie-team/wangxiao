package weibo4j;

public class Weibo implements java.io.Serializable {

	private static final long serialVersionUID = 4282616848978535016L;

	public weibo4j.http.HttpClient client = new weibo4j.http.HttpClient();

	public  void setToken(String token) {
		client.setToken(token);
	}

}