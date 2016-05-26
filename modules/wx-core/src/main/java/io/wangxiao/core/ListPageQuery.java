package io.wangxiao.core;

import java.io.Serializable;

public class ListPageQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2504823418194131828L;

	private Pagination pagination;
	private ConditionMap conditions;

	public Pagination getPagination() {
		if (pagination == null)
			pagination = new Pagination();
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ConditionMap getConditions() {
		if (conditions == null)
			conditions = new ConditionMap();
		return conditions;
	}

	public void setConditions(ConditionMap conditions) {
		this.conditions = conditions;
	}

	@Override
	public String toString() {
		return "ListPageQuery [pagination=" + pagination + ", conditions="
				+ conditions + "]";
	}

}
