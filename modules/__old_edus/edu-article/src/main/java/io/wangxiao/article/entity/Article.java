package io.wangxiao.article.entity;

import io.wangxiao.core.annotation.OrderBy;
import io.wangxiao.core.model.BaseIncrementIdModel;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

@OrderBy("click_times desc")
@Table(name="edu_article")
public class Article extends BaseIncrementIdModel{

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Long clickTimes) {
		this.clickTimes = clickTimes;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	private String title;// 标题
	private String meta;// 标签
	private String description;// 简介（自动生成，前200个汉字）
	private String content;// 内容
	private String picture;// 图片
	private java.util.Date createTime;// 创建时间
	private java.util.Date updateTime;// 修改时间
	private String author;// 作者
	private Long clickTimes;// 点击数量
	private Long type;// 1资讯 2公告
}
