package io.wangxiao.api.rest.pay;

/**
 * Created by bison on 1/22/16.
 * <p>
 * 支付
 */

import co.bluepx.edu.pay.AlipayService;
import me.hao0.alipay.model.pay.WapPayDetail;
import me.hao0.alipay.model.pay.WebPayDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/pays")
public class Pays {

    private static final Logger logger = LoggerFactory.getLogger(Pays.class);

    @Autowired
    private AlipayService alipayService;



    /**
     * WEB支付
     */
    @RequestMapping("/web")
    public void webPay(@RequestParam("orderNumber") String orderNumber, HttpServletResponse resp) {

        WebPayDetail detail = new WebPayDetail(orderNumber, "测试订单-" + orderNumber, "0.01");
        String form = alipayService.webPay(detail);
        logger.info("web pay form: {}", form);

        _resp(form,resp);
    }

    /**
     * WAP支付
     */
    @RequestMapping("/wap")
    public void wapPay(@RequestParam("orderNumber") String orderNumber, HttpServletResponse resp) {

        WapPayDetail detail = new WapPayDetail(orderNumber, "测试订单-" + orderNumber, "0.01");
        String form = alipayService.wapPay(detail);
        logger.info("wap pay form: {}", form);

        _resp(form,resp);


    }

    /**
     * Response pay info
     * @param form
     * @param resp
     */
    private void _resp(String form, HttpServletResponse resp) {

        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(form);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            // ignore
        }
    }

}
