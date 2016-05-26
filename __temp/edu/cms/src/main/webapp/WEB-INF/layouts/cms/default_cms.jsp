<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp"%>
<compress:html  compressJavaScript="false" >
<!DOCTYPE HTML>
<!--
    Service Support:   try    http://www.xxxxx.com
-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
	<title><sitemesh:title ></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}"/> 
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}"/> 
	<link rel="stylesheet" type="text/css" href="${ctx}/static/cms/css/style.css"/>
	<script type="text/javascript" src="${ctx}/static/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/cms/js/cms-util.js"></script>
	<script type="text/javascript" src="${ctx}/static/common/webutils.js"></script>
	<!--[if lt IE 9]><script src="${ctx}/static/common/html5.js" type="text/javascript"></script><![endif]-->
	<script type="text/javascript">
		var baselocation = "${ctx}";
		var imagesPath = "${ctximg}";
		var usercookiekey="<%=usercookiekey%>";
		var mydomain="<%=mydomain%>";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var uploadSwfUrl="<%=uploadSwfUrl%>";//uplopad的js上传使用的路径
        var staticImageServer ="<%=staticImageServer%>";//上传后返回路径
	</script>
	<sitemesh:head ></sitemesh:head>
</head>
<body class="W-body">
	<!-- 公共头引入 -->
	<jsp:include page="/WEB-INF/layouts/cms/header_cms.jsp"/>
	<!-- 公共头引入 -->
	<sitemesh:body></sitemesh:body>
	<!-- 公共底引入 -->
	<jsp:include page="/WEB-INF/layouts/cms/footer_cms.jsp"/>
	<!-- 公共底引入 -->
	<!-- 统计代码 -->
	${tongjiemap.censusCode.censusCodeString}
</body>
</html>
</compress:html>