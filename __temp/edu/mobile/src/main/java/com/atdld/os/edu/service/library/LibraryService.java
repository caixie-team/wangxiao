package com.atdld.os.edu.service.library;

import java.util.List;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.library.Library;
import com.atdld.os.edu.entity.library.LibraryDTO;
import com.atdld.os.edu.entity.library.LibraryImages;
import com.atdld.os.edu.entity.library.QueryLibrary;



/**
 * 
 * @ClassName com.atdld.edu.library.service.ILibrary
 * @description
 * @author :
 * @Create Date : 2014年8月11日 下午2:22:45
 */
public interface LibraryService {
	/**
	 * 查询文库分页列表
	 * 
	 * @param library
	 * @return PageResult
	 * @throws Exception
	 */
	public List<LibraryDTO> queryLibrayList(QueryLibrary queryLibrary,PageEntity page);

	/**
	 * 查询单个library
	 * 
	 * @param library
	 * @return Library
	 * @throws Exception
	 */
	public Library queryLibraryById(Library library);
	/**
     * 更新单个library浏览数
     * 
     */
    public void updateLibraryBrowseNumById(Long id);

	/**
	 * 查询图片集
	 * 
	 * @param libraryId
	 * @return List<LibraryImages>
	 * @throws Exception
	 */
	public List<LibraryImages> queryLibraryImagesList(Long libraryId);
	
	/**
	 * 查询热门文库
	 * @return
	 */
	public List<Library> hotLibraries(int num);
	/**
     * 添加文库
     */
    public void addLibrary(Library library);
    /**
     * 更新文库
     */
    public void updateLibrary(Library library);
    /**
     * 删除文库
     */
    public void delLibrary(Long id);
}
