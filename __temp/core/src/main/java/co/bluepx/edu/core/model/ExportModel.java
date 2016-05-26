package co.bluepx.edu.core.model;

import java.util.List;

public class ExportModel {
	
	private List<?> list;      //数据
	private String templatePath;                 //模版所在路径
	private String title;                        //标题
	private String postfix;//文件序号
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostfix() {
		return postfix;
	}
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	
}
