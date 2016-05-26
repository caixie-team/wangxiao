package com.atdld.os.edu.dao.impl.library;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.library.LibraryDao;
import com.atdld.os.edu.entity.library.Library;
import com.atdld.os.edu.entity.library.LibraryDTO;
import com.atdld.os.edu.entity.library.LibraryImages;
import com.atdld.os.edu.entity.library.QueryLibrary;



/**
 * 
 * @ClassName  com.atdld.edu.library.service.LibraryService
 * @description
 * @author :
 * @Create Date : 2014年8月11日 下午2:25:20
 */
@Repository("libraryDao")
public class LibraryDaoImpl extends GenericDaoImpl implements LibraryDao{

	/**
	 * 查询文库
	 * 
	 * @param queryLibraryCondition
	 * @return
	 * @throws Exception
	 */
	public List<LibraryDTO> queryLibrayList(QueryLibrary queryLibrary,PageEntity page){
		return this.queryForListPage("LibraryMapper.queryLibrayPage", queryLibrary, page);
	}
	/**
	 * 查询单个library
	 * 
	 * @param library
	 * @return Library
	 * @throws Exception
	 */
	public Library queryLibraryById(Library library) {
		return this.selectOne("LibraryMapper.queryLibraryById", library);
	}
	/**
     * 更新单个library浏览数
     * 
     */
    public void updateLibraryBrowseNumById(Long id){
        this.update("LibraryMapper.updateLibraryBrowseNumById", id);
    }
	/**
	 * 查询图片集
	 * 
	 * @param libraryId
	 * @return List<LibraryImages>
	 * @throws Exception
	 */
	public List<LibraryImages> queryLibraryImagesList(Long libraryId) {
		return this.selectList("LibraryMapper.queryLibraryImagesList", libraryId);
	}


	/**
	 * 查询热门文库
	 */
	public List<Library> hotLibraries(int num) {
		return this.selectList("LibraryMapper.hotLibraries", num);
	}
	
	/**
     * 添加文库
     */
    public void addLibrary(Library library){
    	this.insert("LibraryMapper.addLibrary",library);
    }
    /**
     * 更新文库
     */
    public void updateLibrary(Library library){
    	this.update("LibraryMapper.updateLibrary",library);
    }
    /**
     * 删除文库
     */
    public void delLibrary(Long id){
    	this.delete("LibraryMapper.delLibrary",id);
    }
	
}
