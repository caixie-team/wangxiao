<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE HTML>

<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><sitemesh:title></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}"/>
    <%-- <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_right.css?v=${v}" /> --%>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/amazeui.min.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/amazeui.switch.css?v=${v}"/>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/admin.css?v=${v}"/>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/admin/js/amazeui.switch.min.js?v=${v}"></script>
    <!--[if lt IE 9]>
    <script src="${ctximg}/static/common/admin/js/html5.js"></script>
    <script src="${ctximg}/static/common/admin/js/modernizr.js"></script>
    <script src="${ctximg}/static/common/admin/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
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
        var imageUrl = "${imageUrl}";
        var staticUrl = "${staticUrl}";
        var userId = ${userId};
    </script>
    <sitemesh:head></sitemesh:head>
</head>
<body>
<!--[if lt IE 9]>
<div id="browsehappy"><p class="NXB-container">你正在使用过时的浏览器<i class="am-icon-internet-explorer"></i>，平台暂不支持，为了数据的安全及流畅，快速的体验。
    请 <a href="http://browsehappy.com/" title="升级浏览器" target="_blank">升级浏览器</a>
    以获得更好的体验！</p></div>
<![endif]-->

<!-- 公共头引入 -->
<jsp:include page="/WEB-INF/layouts/admin/header.jsp"/>

<!-- 全局主体区  开始 -->
<div class="NXB-main">
    <div class="NXB-container">
        <div class="NXB-bCrumb">
            <ol class="am-breadcrumb">
                <li><a href="${ctx}/admin/sys/main">首页</a></li>
                <li><a href="#">分类</a></li>
                <li class="am-active">内容</li>
            </ol>
        </div>
        <div class="am-cf NXB-m-box">
            <jsp:include page="/WEB-INF/layouts/admin/left.jsp"/>
            <div class="admin-content">
                <div class="am-margin">
                    <sitemesh:body></sitemesh:body>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 全局尾部区  开始 -->
<footer class="NXB-footer">
    <div class="NXB-container">
        <hr/>
    </div>
</footer>
<!-- 全局尾部区  结束 -->
<script type="text/javascript" src="${ctximg}/static/common/admin/js/admin-mine-n.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
<script src="${ctximg}/static/common/admin/js/amazeui.min.js"></script>
<script>
    //开启全屏模式
    (function ($) {
        'use strict';
        $(function () {
            var $fullText = $('.admin-fullText');
            $('#admin-fullscreen').on('click', function () {
                $.AMUI.fullscreen.toggle();
            });
            $(document).on($.AMUI.fullscreen.raw.fullscreenchange,
                    function () {
                        $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
                    });
        });
    })(jQuery);
</script>
</body>
</html>
