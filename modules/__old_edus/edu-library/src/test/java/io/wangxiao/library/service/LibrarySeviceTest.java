package io.wangxiao.library.service;

import io.wangxiao.core.PageInfo;
import co.bluepx.edu.library.BaseTest;
import io.wangxiao.library.entity.LibraryDTO;
import io.wangxiao.library.entity.QueryLibrary;
import com.google.gson.Gson;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 12/30/15.
 */
public class LibrarySeviceTest extends BaseTest {

    @Autowired
    LibraryService libraryService;

    @Autowired
    SpringBeetlSql springBeetlSql;

    private Gson gson = new Gson();


    /**
     * 测试按图书 id 查询图书详情
     */
    @Test
    public void testFindLibraryById() {

        // 2014 会计从业资格证题库
        System.out.println(gson.toJson(libraryService.findById(11)));

    }
    @Test
    public void testFindLibrarayDTOPage() {

        QueryLibrary query = new QueryLibrary();
        query.setSubjectId(171L);

        PageInfo<LibraryDTO> libarays = libraryService.findLibraryDTOByPage(query, 1, 10);

        System.out.println(gson.toJson(libarays));

    }

    @Test
    public void testGenSql() throws Exception {
        SQLManager sql = springBeetlSql.getSQLMananger();
        sql.genSQLTemplateToConsole("edu_library");
    }
}
