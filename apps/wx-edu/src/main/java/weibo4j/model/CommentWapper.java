package weibo4j.model;

import java.util.List;

public class CommentWapper {
	private List<weibo4j.model.Comment> comments;

	private long previousCursor;

	private long nextCursor;
	
	private long totalNumber;
	
	private String hasvisible;
	
	public CommentWapper(List<weibo4j.model.Comment> comments, long previousCursor,
			long nextCursor, long totalNumber,String hasvisible) {
		this.comments = comments;
		this.previousCursor = previousCursor;
		this.nextCursor = nextCursor;
		this.totalNumber = totalNumber;
		this.hasvisible = hasvisible;
	}

	public List<weibo4j.model.Comment> getComments() {
		return comments;
	}

	public void setComments(List<weibo4j.model.Comment> comments) {
		this.comments = comments;
	}

	public long getPreviousCursor() {
		return previousCursor;
	}

	public void setPreviousCursor(long previousCursor) {
		this.previousCursor = previousCursor;
	}

	public long getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(long nextCursor) {
		this.nextCursor = nextCursor;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getHasvisible() {
		return hasvisible;
	}

	public void setHasvisible(String hasvisible) {
		this.hasvisible = hasvisible;
	}
		
}
