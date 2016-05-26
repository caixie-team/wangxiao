package com.atdld.os.core.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atdld.os.core.util.ObjectUtils;

/**
 * @ClassName com.atdld.os.core.util.web.HttpUtil
 * @description
 * @author :
 * @Create Date : 2014-4-15 下午12:51:53
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     * 
     * @param url
     *            请求的URL地址
     * @param queryString
     *            请求的查询参数,可以为null
     * @return 返回请求响应的HTML
     */
    public static String doGet(String url, String queryString) {
        String response = null;
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            if (StringUtils.isNotBlank(queryString))
                method.setQueryString(URIUtil.encodeQuery(queryString));
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                response = method.getResponseBodyAsString();
            }
        } catch (URIException e) {

        } catch (IOException e) {
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     * 
     * @param url
     *            请求的URL地址
     * @param params
     *            请求的查询参数,可以为null
     * @return 返回请求响应的HTML
     */
    public static String doPost(String url, Map<String, String> params) {
        logger.debug("Post Url:" + url + ",Params:" + params);
        StringBuffer result = new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        // 设置Http Post数据
        if (!ObjectUtils.isNull(params)) {
            NameValuePair[] data = new NameValuePair[params.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                data[i++] = new NameValuePair(entry.getKey(), entry.getValue());
            }
            method.setRequestBody(data);
        }
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
                String str = null;
                while ((str = reader.readLine()) != null) {
                    result.append(str);
                }
            }
        } catch (IOException e) {
        } finally {
            method.releaseConnection();
        }
        logger.debug("Post Return:" + result.toString());
        return result.toString();
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     * 
     * @param url
     *            请求的URL地址
     * @param params
     *            请求的查询参数,可以为null
     * @return 返回请求响应的HTML
     */
    public static void doThreadPost(String url, Map<String, String> params) {
        HttpUtil httpUtil = new HttpUtil();
        HttpPostThread httpPostThread = httpUtil.new HttpPostThread(url, params);
        httpPostThread.start();
    }

    class HttpPostThread extends Thread {
        private String url;
        private Map<String, String> params;

        public HttpPostThread(String url, Map<String, String> params) {
            this.url = url;
            this.params = params;
        }

        @Override
        public void run() {
            doPost(url, params);
        }
    }

}
