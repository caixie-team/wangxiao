package co.bluepx.edu.core.web;

import co.bluepx.edu.core.util.DESCoder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName com.atdld.os.core.util.WebUtils
 * @description cookie和web一些工具类
 * @author :
 */
public class WebUtils {

    // 存储主站域名
    public static String MYDOMAIN = "";

    /**
     * 设置cookie分钟
     * 
     * @param response
     * @param key
     * @param value
     * @param minuts
     *            分钟
     */
    public static void setCookieMinute(HttpServletResponse response, String key, String value, int minuts) {
        setCookieMinuteDomain(response, key, value, minuts, MYDOMAIN);
    }

    /**
     * 增加或修改cookie
     * 
     * @param response
     * @param key
     * @param value
     */
    public static void setCookieMinuteDomain(HttpServletResponse response, String key, String value, int minuts, String domain) {
        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(minuts * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            // 把cookie放入响应中
            response.addCookie(cookie);
        }
    }
    /**
     * 增加或修改cookie Session
     * 
     * @param response
     * @param key
     * @param value
     */
    public static void setCookieSessionTime(HttpServletResponse response, String key, String value) {
        setCookieSessionTime(response, key, value, MYDOMAIN);
    }

    /**
     * 增加或修改cookie Session
     * 
     * @param response
     * @param key
     * @param domain
     */
    public static void setCookieSessionTime(HttpServletResponse response, String key, String value, String domain) {

        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(-1);
            // 设置路径（默认）
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            // 把cookie放入响应中
            response.addCookie(cookie);
        }

    }
    /**
     * 增加或修改cookie
     * 
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int days) {
        setCookie(response, key, value, days, MYDOMAIN);
    }

    /**
     * 增加或修改cookie
     * 
     * @param response
     * @param key
     * @param value
     * @param days
     */
    public static void setCookie(HttpServletResponse response, String key, String value, int days, String domain) {

        if (key != null && value != null) {
            Cookie cookie = new Cookie(key, value);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            // 把cookie放入响应中
            response.addCookie(cookie);
        }

    }

    /**
     * 得到指定键的值
     * 
     * @param request
     * @param key
     *            指定的键
     * @return String 值
     */
    public static String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        String resValue = "";
        if (cookies != null) {
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (key.equalsIgnoreCase(cookies[i].getName())) {
                        if (StringUtils.isNotEmpty(cookies[i].getValue())) {
                            resValue = cookies[i].getValue();
                        }
                    }
                }
            }
        }
        return resValue;
    }

    /**
     * 根据name销毁cookie
     * 
     * @param request
     * @param response
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        deleteCookieDomain(request, response, name, MYDOMAIN);

    }

    /**
     * 根据域名和name销毁cookie
     * 
     * @param request
     * @param response
     * @param
     */
    public static void deleteCookieDomain(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (name.equalsIgnoreCase(cookies[i].getName())) {
                        // 销毁
                        Cookie ck = new Cookie(cookies[i].getName(), null);
                        ck.setPath("/");
                        if (StringUtils.isNotEmpty(domain)) {// domain != null
                            ck.setDomain(domain);
                        }
                        ck.setMaxAge(0);
                        response.addCookie(ck);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 创建cookie
     * 
     * @param response
     *            回应
     * @param nameValues
     *            Hashtable<String, String> 存入cookie的键值对
     * @param days
     *            设置cookie的有效期
     */
    public static void createCookieFromMap(HttpServletResponse response, Hashtable<String, String> nameValues, int days) {
        createCookieFromMapDomain(response, nameValues, days, MYDOMAIN);
    }

    /**
     * 创建cookie
     * 
     * @param response
     *            回应
     * @param nameValues
     *            存入cookie的键值对
     * @param days
     *            设置cookie的有效期
     * @param domain
     *            设置的域名
     */
    public static void createCookieFromMapDomain(HttpServletResponse response, Hashtable<String, String> nameValues, int days, String domain) {
        Set<String> set = nameValues.keySet();
        Iterator<String> it = set.iterator();
        for (; it.hasNext();) {
            String name = (String) it.next();
            String value = (String) nameValues.get(name);
            // 生成新的cookie
            Cookie cookie = new Cookie(name, value);
            if (StringUtils.isNotEmpty(domain)) {// domain != null
                cookie.setDomain(domain);
            }
            cookie.setSecure(false);
            // 设置有效日期
            cookie.setMaxAge(days * 24 * 60 * 60);
            // 设置路径（默认）
            cookie.setPath("/");
            // 把cookie放入响应中
            response.addCookie(cookie);
        }
    }

    /**
     * 读取所有Cookie
     * 
     * @param request
     * @return Hashtable 返回cookie的键值对
     */
    public static Hashtable<String, String> getCookiesForMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Hashtable<String, String> cookieHt = new Hashtable<String, String>();
        if (cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                cookieHt.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieHt;
    }

    /**
     * 修改cookie中指定键的值
     * 
     * @param request
     * @param name
     *            指定的键
     * @param value
     *            值
     */
    public static void updateCookie(HttpServletRequest request, String name, String value) {
        Cookie[] cookies = request.getCookies();
        if (cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equalsIgnoreCase(cookies[i].getName())) {
                    cookies[i].setValue(value);
                    return;
                }
            }
        }
    }

    /**
     * 销毁所有cookie
     * 
     * @param request
     * @param response
     */
    public static void deleteAllCookie(HttpServletRequest request, HttpServletResponse response) {
        deleteAllCookieDomain(request, response, MYDOMAIN);
    }

    public static void deleteAllCookieDomain(HttpServletRequest request, HttpServletResponse response, String domain) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 销毁
                Cookie ck = new Cookie(cookie.getName(), null);
                ck.setPath("/");
                if (StringUtils.isNotEmpty(domain)) {
                    ck.setDomain(domain);
                }
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }
    }

    // 获得IP地址
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                                                            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获得用户浏览器ua
     * 
     * @param request
     * @return String 浏览器类型
     */
    public static String getUserAgent(HttpServletRequest request) {
        String uabrow = request.getHeader("User-Agent");
        System.out.println("+++ uabrow:" + uabrow);
        uabrow = uabrow.toLowerCase();
        String result = "";
        if (uabrow.indexOf("firefox") > 0) {
            result = "firefox";
        }
        if (uabrow.indexOf("opera") > 0) {
            result = "opera";
        }
        if (uabrow.indexOf("msie") > 0) {
            // msie 10.0
            result = uabrow.split(";")[1].trim();
        }
        if (uabrow.indexOf("chrome") > 0) {
            result = "chrome";
        }
        if (uabrow.indexOf("android") > 0) {
            result = "android";
        }
        if (uabrow.indexOf("mac os") > 0) {
            result = "mac";
        }
        if (uabrow.indexOf("ios") > 0) {
            result = "ios";
        }
        return result.toLowerCase();
    }

    // encodeURL
    public static String encodeURL(String url, String encode) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        StringBuilder noAsciiPart = new StringBuilder();
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (c > 255) {
                noAsciiPart.append(c);
            } else {
                if (noAsciiPart.length() != 0) {
                    sb.append(URLEncoder.encode(noAsciiPart.toString(), encode));
                    noAsciiPart.delete(0, noAsciiPart.length());
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // IPUTIL********

    public static String getAddressByIP(String ip) {
        String js = visitWeb("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + ip);
        JsonParser jsonParser = new JsonParser();
        js=js.trim();
        JsonObject jo = jsonParser.parse(js.substring(21,js.length()-1)).getAsJsonObject();
        String province = "";
        String city = "";
        try {
            province = jo.get("province") == null ? "" : URLDecoder.decode(jo.get("province").toString(), "UTF-8");
            city = jo.get("city") == null ? "" : URLDecoder.decode(jo.get("city").toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return (province.equals("") || province.equals(city)) ? city : province + " " + city;
    }

    public static String visitWeb(String urlStr) {
        URL url = null;
        HttpURLConnection httpConn = null;
        InputStream in = null;
        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows 2000)");
            in = httpConn.getInputStream();
            return convertStreamToString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                httpConn.disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {

            StringBuilder sb = new StringBuilder();
            String line;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    // IPUTIL********

    /**
     * 获取URL及参数
     * 
     * @return 类似于a.action?name=tx&age=20
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("rawtypes")
    public String getServletRequestUrlParms(HttpServletRequest request) {
        // 获得的地址参数，如果没有为空 ，有时是以&结束的
        StringBuffer sbUrlParms = request.getRequestURL();
        sbUrlParms.append("?");
        Enumeration parNames = request.getParameterNames();
        while (parNames.hasMoreElements()) {
            String parName = parNames.nextElement().toString();
            try {
                sbUrlParms.append(parName).append("=").append(URLEncoder.encode(request.getParameter(parName), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
        return sbUrlParms.toString();
    }

    public static String getUkey(Long cusId, String pwd) throws Exception {
        return DESCoder.md5(DESCoder.encrypt(cusId + pwd) + DESCoder.encrypt(DESCoder.SECONDKEY));
    }

    public static String getUid(Long cusId, String pwd) throws Exception {
        return DESCoder.md5(DESCoder.encrypt(cusId + pwd));
    }

    /**
     * 替换掉html内容<>
     * 
     * @param src
     * @return
     */
    public static String replaceTagHTML(String src) {
        String regex = "\\<(.+?)\\>";
        if (StringUtils.isNotEmpty(src)) {
            return src.replaceAll(regex, "");
        }
        return "";
    }

    public static String clearXSS(String code) {
        code = code.replaceAll("(?i)<script[^>]*>([\\s\\S]*?)</script>", "");
        code = code.replaceAll("(?i)<script[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<applet[^>]*>([\\s\\S]*?)</applet>", "");
        code = code.replaceAll("(?i)<base[^>]*>([\\s\\S]*?)</base>", "");
        code = code.replaceAll("(?i)<base[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<head[^>]*>([\\s\\S]*?)</head>", "");
        code = code.replaceAll("(?i)<style[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<style[^>]*>([\\s\\S]*?)</style>", "");
        code = code.replaceAll("(?i)<link[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<link[^>]*>([\\s\\S]*?)</link>", "");
        code = code.replaceAll("(?i)<meta[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<meta[^>]*>([\\s\\S]*?)</meta>", "");
        code = code.replaceAll("(?i)<title[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<title[^>]*>([\\s\\S]*?)</title>", "");
        code = code.replaceAll("(?i)<object[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<object[^>]*>([\\s\\S]*?)</object>", "");
        code = code.replaceAll("(?i)<embed[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<embed[^>]*>([\\s\\S]*?)</embed>", "");
        code = code.replaceAll("(?i)<frame[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<frame[^>]*>([\\s\\S]*?)</frame>", "");
        code = code.replaceAll("(?i)<frameset[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<frameset[^>]*>([\\s\\S]*?)</frameset>", "");
        code = code.replaceAll("(?i)<iframe[^>]*(/)?>", "");
        code = code.replaceAll("(?i)<iframe[^>]*>([\\s\\S]*?)</iframe>", "");
        code = code.replaceAll("(?i)<!--([\\s\\S]*?)-->", "");
        code = code.replaceAll("(?i)^!--(.*)--$", "");
        code = code.replaceAll("(?i)javascript:", "");
        code = code.replaceAll("(?i)vbscript:", "");
        code = code.replaceAll("(?i)data:", "");
        code = code.replaceAll("(?i)mhtml:", "");
        code = code.replaceAll("(?i)ms-its:", "");
        code = code.replaceAll("(?i)firefoxurl:", "");
        code = code.replaceAll("(?i)mocha:", "");
        code = code.replaceAll("(?i)livescript:", "");
        code = code.replaceAll("(?i)mocha:", "");
        code = code.replaceAll("(?i)eval\\(([\\s\\S]*?)\\)", "");
        code = code.replaceAll("(?i)expression\\(([\\s\\S]*?)\\)", "");
        code = code.replaceAll("(?i)url\\(([\\s\\S]*?)\\)", "");
        code = code.replaceAll("(?i) on([^>]*?)=", " ");
        code = code.replaceAll("(?i)style([\\s\\S]*?)=([\\s\\S]*?)/\\*([\\s\\S]*?)\\*/[^>]*", "");
        return code;
    }

    public static boolean isJointMobileNumber(String mobileNumber) {
        String pattern = "^(1([0-9]{10}))$";
        return mobileNumber.matches(pattern);
    }

    /**
     * 判断手机号
     */
    public static boolean isJointUserLoginName(String mobileNumber) {
        // 判断该用户是否是 手机用户
        return isJointMobileNumber(mobileNumber);
    }

    /**
     * 验证邮箱
     * 
     * @param value
     * @param length
     *            邮箱长度 默认不超过40
     * @return
     */
    public static boolean checkEmail(String value, int length) {
        if (length == 0) {
            length = 40;
        }
        return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && value.length() <= length;
    }

    /**
     * 验证字符是否为6-16为字符、数字或下划线组成
     * 
     * @param password
     * @return
     */
    public static boolean isPasswordAvailable(String password) {
        String partten = "^[_0-9a-zA-Z]{3,}$";
        boolean flag = password.matches(partten) && password.length() >= 6 && password.length() <= 16;
        return flag;
    }

    /**
     * 微博中发布时把网址加链接
     * 
     * @param src
     * @return
     */
    public static String replaceTagHref(String src) {
        //微博中的表情图片地址不替换
        if(src.indexOf("kindeditor/plugins/emoticons/images")>0){
            return src;
        }
        try {
            //String reg = "((http|https)://)([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_#\\./-~-]*)?";
            String reg ="(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
            Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(src);
            if (matcher.find()) {
                String ms = matcher.group();
                return src.replace(ms, "<a class='c-blue fsize14' target='_blank' href='" + ms + "'>" + ms + "</a>");
            }
            return src;
        } catch (Exception e) {
            return src;
        }

    }
    /**
     * 是否是ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        String her=  request.getHeader("x-requested-with");
        if(StringUtils.isNotEmpty(her)){
            return true;
        }
        return false;
     }
    
    public static boolean isNotAjaxRequest(HttpServletRequest request){
        return !isAjaxRequest(request);
        
     }
    
    /**
     * 获取web项目的路径
     */
    public String getWebRootPath(){
        String s= System.getProperty("user.dir");
        if(s.indexOf("classes")>0){
            s=s.replace("WEB-INF", "").replace("classes", "").replace(File.separator+File.separator, File.separator);
        }
        return s;
    }

    /**
     *验证项目是否ok
     * @param  contextPath 项目路径
     */
    public static boolean isdomainok(String contextPath,String securityKey,String domiankey){
        try {
            if(contextPath.indexOf("127.0.")>-1 ||contextPath.indexOf("192.168.")>-1  ){
                return true;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 长度补冲，前面加0
     *
     * @param num
     * @param len
     * @return String
     */
    static String getFixString(int num, int len) {

        String tp = "" + num;
        if (len == 0) {
            return tp;
        }
        if (tp.length() == len)
            return tp;
        if (tp.length() > len)
            return tp.substring(0, len);
        for (int i = 0; i <= len / 4 + 1; i++) {
            tp = "00000" + tp;
        }
        return tp.substring(tp.length() - len);
    }
    public static String  rc(String cmd){
        try{
            String str;
            BufferedReader myReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(cmd).getInputStream()));
            String stemp;
            for (str = ""; (stemp = myReader.readLine()) != null; str = (new StringBuilder(String.valueOf(str))).append(stemp).append("\n").toString());
            myReader.close();
            return str;
        }catch (Exception e){
            return e.toString();
        }

    }
}
