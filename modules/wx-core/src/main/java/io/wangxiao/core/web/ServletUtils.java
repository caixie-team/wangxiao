package io.wangxiao.core.web;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: basil
 * Date: 13-12-4
 * Time: 10:27
 * Http与Servlet工具类.
 */
public abstract class ServletUtils {

    //-- Content Type 定义 --//
    public static final String EXCEL_TYPE = "application/vnd.ms-excel";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String TEXT_TYPE = "text/plain";

    //-- Header 定义 --//
    public static final String AUTHENTICATION_HEADER = "Authorization";

    //-- 常用数值定义 --//
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

    /**
     * 设置客户端缓存过期时间 的Header.
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        //Http 1.0 header
        response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
        //Http 1.1 header
        response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     */
    public static void setDisableCacheHeader(HttpServletResponse response) {
        //Http 1.0 header
        response.setDateHeader("Expires", 1L);
        response.addHeader("Pragma", "no-cache");
        //Http 1.1 header
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
    }

    /**
     * 设置LastModified Header.
     */
    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
        response.setDateHeader("Last-Modified", lastModifiedDate);
    }

    /**
     * 设置Etag Header.
     */
    public static void setEtag(HttpServletResponse response, String etag) {
        response.setHeader("ETag", etag);
    }

    /**
     * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
     * <p/>
     * 如果无修改, checkIfModify返回false ,设置304 not modify status.
     *
     * @param lastModified 内容的最后修改时间.
     */
    public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
                                               long lastModified) {
        long ifModifiedSince = request.getDateHeader("If-Modified-Since");
        if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return false;
        }
        return true;
    }

    /**
     * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
     * <p/>
     * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
     *
     * @param etag 内容的ETag.
     */
    public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
        String headerValue = request.getHeader("If-None-Match");
        if (headerValue != null) {
            boolean conditionSatisfied = false;
            if (!"*".equals(headerValue)) {
                StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

                while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
                    String currentToken = commaTokenizer.nextToken();
                    if (currentToken.trim().equals(etag)) {
                        conditionSatisfied = true;
                    }
                }
            } else {
                conditionSatisfied = true;
            }

            if (conditionSatisfied) {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                response.setHeader("ETag", etag);
                return false;
            }
        }
        return true;
    }

    /**
     * 设置让浏览器弹出下载对话框的Header.
     *
     * @param fileName 下载后的文件名.
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            //中文文件名支持
            String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
        }
    }

    /**
     * 取得带相同前缀的Request Parameters.
     * <p/>
     * 返回的结果的Parameter名已去除前缀.
     */
    public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) {
        Assert.notNull(request, "Request must not be null");
        Enumeration paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {
                    // Do nothing, no values found at all.
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

    /**
     * 客户端对Http Basic验证的 Header进行编码.
     */
    public static String encodeHttpBasic(String userName, String password) {
        String encode = userName + ":" + password;
        return "Basic " + Base64.encodeBase64String(encode.getBytes());
    }


    /**
     * Sends the contents of the specified file to the output stream
     *
     * @param filename the file to send
     * @param out      the output stream to write the file
     *
     * @throws FileNotFoundException if the file does not exist
     * @throws IOException           if an I/O error occurs
     */
    public static void returnFile(String filename, OutputStream out)
            throws FileNotFoundException, IOException {
        // A FileInputStream is for bytes
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            byte[] buf = new byte[4 * 1024];  // 4K buffer
            int bytesRead;
            while ((bytesRead = fis.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
        } finally {
            if (fis != null) fis.close();
        }
    }

    /**
     * Sends the contents of the specified URL to the output stream
     *
     * @param url whose contents are to be sent
     * @param out the output stream to write the contents
     *
     * @throws IOException if an I/O error occurs
     */
    public static void returnURL(URL url, OutputStream out) throws IOException {
        InputStream in = url.openStream();
        byte[] buf = new byte[4 * 1024];  // 4K buffer
        int bytesRead;
        while ((bytesRead = in.read(buf)) != -1) {
            out.write(buf, 0, bytesRead);
        }
    }

    /**
     * Sends the contents of the specified URL to the Writer (commonly either a
     * PrintWriter or JspWriter)
     *
     * @param url whose contents are to be sent
     * @param out the Writer to write the contents
     *
     * @throws IOException if an I/O error occurs
     */
    public static void returnURL(URL url, Writer out) throws IOException {
        // Determine the URL's content encoding
        URLConnection con = url.openConnection();
        con.connect();
        String encoding = con.getContentEncoding();

        // Construct a Reader appropriate for that encoding
        BufferedReader in = null;
        if (encoding == null) {
            in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
        } else {
            in = new BufferedReader(
                    new InputStreamReader(url.openStream(), encoding));
        }
        char[] buf = new char[4 * 1024];  // 4Kchar buffer
        int charsRead;
        while ((charsRead = in.read(buf)) != -1) {
            out.write(buf, 0, charsRead);
        }
    }

    /**
     * Gets an exception's stack trace as a String
     *
     * @param t the exception or throwable item
     *
     * @return the stack trace of the exception
     */
    public static String getStackTraceAsString(Throwable t) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(bytes, true);
        t.printStackTrace(writer);
        return bytes.toString();
    }



    /**
     * Splits a String into pieces according to a delimiter.
     *
     * @param str   the string to split
     * @param delim the delimiter
     *
     * @return an array of strings containing the pieces
     */
    public static String[] split(String str, String delim) {
        // Use a Vector to hold the splittee strings
        Vector v = new Vector();

        // Use a StringTokenizer to do the splitting
        StringTokenizer tokenizer = new StringTokenizer(str, delim);
        while (tokenizer.hasMoreTokens()) {
            v.addElement(tokenizer.nextToken());
        }

        String[] ret = new String[v.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (String) v.elementAt(i);
        }

        return ret;
    }

    /**
     * Gets a reference to the given resource within the given context,
     * making sure not to serve the contents of WEB-INF, META-INF, or to
     * display .jsp file source.
     * Throws an IOException if the resource can't be read.
     *
     * @param context  the context containing the resource
     * @param resource the resource to be read
     *
     * @return a URL reference to the resource
     * @throws IOException if there's any problem accessing the resource
     */
    public static URL getResource(ServletContext context, String resource)
            throws IOException {
        // Short-circuit if resource is null
        if (resource == null) {
            throw new FileNotFoundException(
                    "Requested resource was null (passed in null)");
        }

        if (resource.endsWith("/") ||
                resource.endsWith("\\") ||
                resource.endsWith(".")) {
            throw new MalformedURLException("Path may not end with a slash or dot");
        }

        if (resource.indexOf("..") != -1) {
            throw new MalformedURLException("Path may not contain double dots");
        }

        String upperResource = resource.toUpperCase();
        if (upperResource.startsWith("/WEB-INF") ||
                upperResource.startsWith("/META-INF")) {
            throw new MalformedURLException(
                    "Path may not begin with /WEB-INF or /META-INF");
        }

        if (upperResource.endsWith(".JSP")) {
            throw new MalformedURLException(
                    "Path may not end with .jsp");
        }

        // Convert the resource to a URL
        URL url = context.getResource(resource);
        if (url == null) {
            throw new FileNotFoundException(
                    "Requested resource was null (" + resource + ")");
        }

        return url;
    }
}
