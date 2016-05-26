package io.wangxiao.library.service;

import io.wangxiao.core.BeetlSqlService;
import io.wangxiao.core.PageInfo;
import io.wangxiao.library.entity.Library;
import io.wangxiao.library.entity.LibraryDTO;
import io.wangxiao.library.entity.QueryLibrary;
import org.springframework.stereotype.Service;


@Service
public class LibraryService extends BeetlSqlService<Library> {


	/**
	 * 分页查询文库 DTO 列表
	 *
	 * @param queryLibrary
	 * @param pageNum
	 * @param pageSize
     * @return
     */
	public PageInfo<LibraryDTO> findLibraryDTOByPage(QueryLibrary queryLibrary, int pageNum, int pageSize){

		return findDtosAllByPage("library.dtos", LibraryDTO.class, queryLibrary, pageNum, pageSize);
	}

//	public PageInfo<LibraryDTO> findLibraryDTOByPage(QueryLibrary queryLibrary, int pageNum, int pageSize){
//		return PageHelper
//				.startPage(pageNum, pageSize)
//				.doSelectPageInfo(
//						() -> baseDao.findLibraryDTOList(queryLibrary)
//				);
//	}

	// 热门文库
	// 文库排序
}
