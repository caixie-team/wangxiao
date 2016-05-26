package io.wangxiao.article.service;

import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 12/30/15.
 */
public class ArticleSeviceTest extends BaseTest {

    @Autowired
    ArticleService articleService;

    private Gson gson = new Gson();


    @Test
    public void testFindArticleById() {

        System.out.println(gson.toJson(articleService.findById(33L)));

    }

    @Test
    public void testFindArticlesByPage() {
        System.out.println(gson.toJson(articleService.findArticlesByPage(1L, 1, 10)));

//        System.out.println(gson.toJson(articleService.findArticlesByPage(0, 1,10)));
    }

}
