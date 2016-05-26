<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp"%>
<compress:html  compressJavaScript="false" >
<!DOCTYPE HTML>
<!--
-->
<html>
<head>
	<!-- Basic Page Needs
	================================================== -->
	<meta charset="utf-8">
	<title><sitemesh:title ></sitemesh:title> - ${websitemap.web.company}</title>
	<meta name="keywords" content="${websitemap.web.keywords}">
	<meta name="description" content="${websitemap.web.description}">
	<meta name="title" content="${websitemap.web.title}">
	<meta name="author" content="${websitemap.web.author}">
	<meta name="Copyright" content="${websitemap.web.copyright}">
	<!-- <meta name="description" content=""> -->
	<!-- 让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit 内核
	================================================== -->
	<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge">
	<!-- IOS6全屏 Chrome高版本全屏
	================================================== -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="mobile-web-app-capable" content="yes"> 
	<!-- 让360双核浏览器用webkit内核渲染页面
	================================================== -->
	<meta name="renderer" content="webkit">
	<!-- Mobile Specific Metas
	================================================== -->
	<!-- !!!注意 minimal-ui 是IOS7.1的新属性，最小化浏览器UI -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<!-- CSS
	================================================== -->
	<link href="${ctximg}/static/mobile/css/reset.css" rel="stylesheet" type="text/css">
	<link href="${ctximg}/static/mobile/css/style.css" rel="stylesheet" type="text/css">
	<link href="${ctximg}/static/mobile/css/am.css" rel="stylesheet" type="text/css">
	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="favicon.ico" >
	<!-- Android 主屏图标
	================================================== -->	
	<link rel="icon" sizes="196x196" href="${ctximg}/static/mobile/img/apple-touch-icon-140x140.jpg">
	<!-- IOS 主屏图标
	================================================== -->
	<link rel="apple-touch-icon-precomposed" href="${ctximg}/static/mobile/img/apple-touch-icon-76x76.jpg">
	<link rel="apple-touch-icon-precomposed" sizes="76x76" href="${ctximg}/static/mobile/img/apple-touch-icon-76x76.jpg">
	<link rel="apple-touch-icon-precomposed" sizes="120x120" href="${ctximg}/static/mobile/img/apple-touch-icon-120x120.jpg">
	<link rel="apple-touch-icon-precomposed" sizes="140x140" href="${ctximg}/static/mobile/img/apple-touch-icon-140x140.jpg">
	<!-- 页面内容加载未完成之前显示loading.gif图标 
	================================================== -->
	<script src="${ctximg}/static/mobile/js/pageLoading.js" type="text/javascript" charset="utf-8"></script> 
	
	
	<script src="${ctximg}/static/mobile/js/zepto.min.js" type="text/javascript"></script> 
	<script src="${ctximg}/static/mobile/js/wap-js.js" type="text/javascript"></script>
	
	<script src="${ctximg}/static/common/webutils.js?v=<%=version%>" type="text/javascript" ></script>
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
<body class="W-body">
	<sitemesh:body></sitemesh:body>
</body>
</html>
</compress:html>