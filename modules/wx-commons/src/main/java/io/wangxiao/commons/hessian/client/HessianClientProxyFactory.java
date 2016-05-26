package io.wangxiao.commons.hessian.client;

import com.caucho.hessian.client.HessianProxyFactory;
import io.wangxiao.commons.util.PropertyUtil;

/**
 * @ClassName HessianClientProxyFactory
 * @description 客户端代理，统一添加请求验签串
 *
 */
public class HessianClientProxyFactory extends HessianProxyFactory {

    PropertyUtil propertyUtil = PropertyUtil.getInstance("project");

    private final String hessianAuth = propertyUtil.getProperty("hessianAuth");

   /* @Override
    protected URLConnection openConnection(URL url) throws IOException {
        URLConnection conn = super.openConnection(url);
        // 添加验证信息，服务端需验证是否相等，防止非法调用
        conn.setRequestProperty("hessianAuth", hessianAuth);
        return conn;
    }*/

}
