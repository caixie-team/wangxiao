package com.atdld.os.core.common.hessian.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.caucho.hessian.client.HessianProxyFactory;
import com.atdld.os.core.util.PropertyUtil;

/**
 * @ClassName HessianClientProxyFactory
 * @package com.fairyhawk.service.hessian
 * @description 客户端代理，统一添加请求验签串
 * @author
 * @Create Date: 2013-3-14 下午5:39:41
 * 
 */
public class HessianClientProxyFactory extends HessianProxyFactory {

    PropertyUtil propertyUtil = PropertyUtil.getInstance("project");

    private final String hessianAuth = propertyUtil.getProperty("hessianAuth");

    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        URLConnection conn = super.openConnection(url);
        // 添加验证信息，服务端需验证是否相等，防止非法调用
        conn.setRequestProperty("hessianAuth", hessianAuth);
        return conn;
    }

}
