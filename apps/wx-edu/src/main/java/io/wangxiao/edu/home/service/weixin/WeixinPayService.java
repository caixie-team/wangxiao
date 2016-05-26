package io.wangxiao.edu.home.service.weixin;

/**
 * 微信常规回复设置service
 *
 */
public interface WeixinPayService {
	/**
     * 微信支付请求
     *
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    String getWxpayUrl(String requestId, String type);
}


