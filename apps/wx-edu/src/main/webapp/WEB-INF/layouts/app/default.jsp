<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp" %>
<compress:html compressJavaScript="false">
    <!DOCTYPE HTML>

    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
        <title><sitemesh:title></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
        <meta name="author" content="${websitemap.web.author}"/>
        <meta name="keywords" content="${websitemap.web.keywords}"/>
        <meta name="description" content="${websitemap.web.description}"/>
        <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="renderer" content="webkit">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <script type="text/javascript">
            var baselocation = "${ctx}";
            var baselocationsns = "${ctxsns}";
            var baselocationexam = "${ctxexam}";
            var imagesPath = "${ctximg}";
            var usercookiekey = "<%=usercookiekey%>";
            var mydomain = "<%=mydomain%>";
            var keImageUploadUrl = "<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
            var uploadSimpleUrl = "<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
            var uploadSwfUrl = "<%=uploadSwfUrl%>";//uplopad的js上传使用的路径
            var staticImageServer = "<%=staticImageServer%>";//上传后返回路径
            var loginkeyword = '${keywordmap.keyword.verifyLogin}';
            var upUserId = "<%=CommonConstants.UP_USER_ID%>";
        </script>
        <sitemesh:head></sitemesh:head>
    </head>
    <body class="W-body">
    <!-- 公共头引入 -->
    <sitemesh:body></sitemesh:body>
    </body>
    </html>
</compress:html>