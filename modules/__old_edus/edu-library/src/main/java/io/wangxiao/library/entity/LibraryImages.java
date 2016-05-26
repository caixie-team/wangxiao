package io.wangxiao.library.entity;

import java.io.Serializable;

public class LibraryImages implements Serializable{


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Long libraryId) {
		this.libraryId = libraryId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	private Long id;//主键
	private Long libraryId;//文库id
	private String imgUrl;//图片集url
	

}
