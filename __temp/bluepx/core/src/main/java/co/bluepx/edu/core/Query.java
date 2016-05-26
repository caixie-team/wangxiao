package co.bluepx.edu.core;

import java.io.Serializable;
import java.util.Map;

public class Query implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2477057113977455138L;
	
	private Pagination page;
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public Map<String, Object> getConditions() {
		return conditions;
	}
	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}
	private Map<String,Object> conditions;
	

}
