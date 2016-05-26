package io.wangxiao.edu.home.entity.library;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LibraryImages implements Serializable{

	private Long id;//主键
	private Long libraryId;//文库id
	private String imgUrl;//图片集url
	

}
