package com.atdld.os.edu.entity.library;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName  com.atdld.edu.library.domain.LibraryImages
 * @description
 * @author :
 * @Create Date : 2014年8月14日 下午3:18:36
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LibraryImages implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892132766289019807L;
	
	private Long id;//主键
	private Long libraryId;//文库id
	private String imgUrl;//图片集url
	

}
