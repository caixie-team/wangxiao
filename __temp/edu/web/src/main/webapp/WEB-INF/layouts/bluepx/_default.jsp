<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp"%>
<compress:html  compressJavaScript="false" >
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <meta name="author" content="${websitemap.web.author}"/>
    <meta name="keywords" content="${websitemap.web.keywords}" />
    <meta name="description" content="${websitemap.web.description}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">

    <!-- Google font -->
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:300,400,700,900' rel='stylesheet' type='text/css'>
    <!-- Css -->
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/bluepx/css/library/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/bluepx/css/library/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/bluepx/css/library/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/bluepx/css/md-font.css">
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/bluepx/css/style.css">
    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <![endif]-->

    <%--<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/common.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/page-style.css">--%>

    <title><sitemesh:title ></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>

<%--<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>--%>
    <%--<script type="text/javascript" src="${ctximg}/static/common/pageJs.js?v=<%=version%>"></script>--%>
    <%--<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>--%>
    <%--<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>--%>
    <%--<script type="text/javascript" src="${ctximg}/static/common/header_msg.js?v=<%=version%>"></script>--%>
    <%--<script type="text/javascript" src="${ctximg}/static/common/emailList.js"></script>--%>
    <%--<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js"></script><![endif]-->--%>

    <script type="text/javascript">
        var baselocation = "${ctx}";
        var baselocationsns = "${ctxsns}";
        var baselocationexam = "${ctxexam}";
        var imagesPath = "${ctximg}";
        var usercookiekey="<%=usercookiekey%>";
        var mydomain="<%=mydomain%>";
        var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
        var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
        var uploadSwfUrl="<%=uploadSwfUrl%>";//uplopad的js上传使用的路径
        var staticImageServer ="<%=staticImageServer%>";//上传后返回路径
        var loginkeyword='${keywordmap.keyword.verifyLogin}';
        var upUserId = "<%=CommonConstants.UP_USER_ID%>";
    </script>
    <sitemesh:head ></sitemesh:head>
</head>

<body id="page-top" class="home">

<!-- PAGE WRAP -->
<div id="page-wrap">

    <!-- END / PRELOADER -->
    <!-- 公共头引入 -->
    <jsp:include page="/WEB-INF/layouts/bluepx/_header.jsp"/>
    <!-- 公共头引入 -->
    <sitemesh:body></sitemesh:body>
    <!-- 公共底引入 -->
    <jsp:include page="/WEB-INF/layouts/bluepx/_footer.jsp"/>
    <!-- 公共底引入 -->
    <!-- 统计代码 -->

</div>
<!-- END / PAGE WRAP -->

<!-- Load jQuery -->
<script type="text/javascript" src="${ctximg}/static/bluepx/js/library/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/bluepx/js/library/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/bluepx/js/library/jquery.owl.carousel.js"></script>
<script type="text/javascript" src="${ctximg}/static/bluepx/js/library/jquery.appear.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/bluepx/js/library/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/bluepx/js/library/jquery.easing.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/bluepx/js/scripts.js"></script>
<script type="text/javascript" src="http://cn.vuejs.org/js/vue.js"></script>

<script type="text/javascript">
    var vm = new Vue({
        // 选项
        el: '#menu',
        data: {
            navs: ${navigatemap}
        }
    })
</script>
</body>
</html>
</compress:html>