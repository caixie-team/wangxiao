package co.bluepx.edu.pay.service;

import io.wangxiao.bookstore.entity.QueryBookCondition;
import io.wangxiao.pay.AlipayService;
import co.bluepx.edu.pay.BaseTest;
import com.google.gson.Gson;
import org.beetl.sql.ext.spring.SpringBeetlSql;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bison on 12/30/15.
 */
public class AlipaySeviceTest extends BaseTest {

    @Autowired
    AlipayService alipayService;

    @Autowired
    SpringBeetlSql springBeetlSql;

    private Gson gson = new Gson();


}
