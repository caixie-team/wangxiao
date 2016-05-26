package io.wangxiao.bookstore.service;

import co.bluepx.edu.bookstore.BaseTest;
import io.wangxiao.bookstore.entity.QueryBookCondition;
import com.google.gson.Gson;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 12/30/15.
 */
public class BookSeviceTest extends BaseTest {

    @Autowired
    BookService bookService;

    @Autowired
    SpringBeetlSql springBeetlSql;

    private Gson gson = new Gson();


    /**
     * 测试按图书 id 查询图书详情
     */
    @Test
    public void testFindBookById() {

//        System.out.println(gson.toJson(bookService.findBookById(2L)));
        System.out.println(gson.toJson(bookService.findById(2)));

    }
    @Test
    public void testFindBooksPage() {

        QueryBookCondition query = new QueryBookCondition();
        query.setBookId(0);
//
        System.out.printf(gson.toJson(bookService.findBookDTOByPage(query, 1, 10)));
    }

    @Test
    public void testGenSql() throws Exception {
        SQLManager sql = springBeetlSql.getSQLMananger();
        sql.genSQLTemplateToConsole("edu_book");
    }
}
