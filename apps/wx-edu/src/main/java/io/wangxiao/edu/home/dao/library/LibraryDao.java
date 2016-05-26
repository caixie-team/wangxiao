package io.wangxiao.edu.home.dao.library;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.library.Library;
import io.wangxiao.edu.home.entity.library.LibraryDTO;
import io.wangxiao.edu.home.entity.library.LibraryImages;
import io.wangxiao.edu.home.entity.library.QueryLibrary;

import java.util.List;


public interface LibraryDao {
    /**
     * 查询文库分页列表
     *
     * @param library
     * @return PageResult
     * @throws Exception
     */
    List<LibraryDTO> queryLibrayList(QueryLibrary queryLibrary, PageEntity page);

    /**
     * 查询单个library
     *
     * @param library
     * @return Library
     * @throws Exception
     */
    Library queryLibraryById(Library library);

    /**
     * 更新单个library浏览数
     */
    void updateLibraryBrowseNumById(Long id);

    /**
     * 查询图片集
     *
     * @param libraryId
     * @return List<LibraryImages>
     * @throws Exception
     */
    List<LibraryImages> queryLibraryImagesList(Long libraryId);

    /**
     * 查询热门文库
     *
     * @return
     */
    List<Library> hotLibraries(int num);

    /**
     * 添加文库
     */
    void addLibrary(Library library);

    /**
     * 更新文库
     */
    void updateLibrary(Library library);

    /**
     * 删除文库
     */
    void delLibrary(Long id);
}
