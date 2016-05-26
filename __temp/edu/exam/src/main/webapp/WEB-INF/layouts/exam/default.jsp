<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html compressJavaScript="false" >
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>

<html>
<head>
	<meta charset="utf-8" http-equiv="Content-Type"/>
	<title><sitemesh:title />-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"/>
	<meta name="author" content="${websitemap.web.author}"/> 
	<meta name="keywords" content="${websitemap.web.keywords}"/>
	<meta name="description" content="${websitemap.web.description}"/>
	<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js?v=${v}"></script><![endif]-->
	<!--[if lt IE 7]>
	<script src="${ctximg}/static/common/ie_png.js?v=${v}"></script>
	<script type="text/javascript">try {ie_png.fix('.png');}catch(e){}</script>
	<![endif]-->
	<link rel="stylesheet" href="${ctximg}/static/exam/css/${style}-matter.css?v=${v}">
	<link rel="stylesheet" href="${ctximg}/static/exam/css/exam-style.css?v=${v}">
	<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/common/webutils.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/exam/js/examJs.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/exam/js/header.js?v=${v}" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		
		var mydomain ="<%=mydomain%>";//主站域
		var usercookiekey="<%=usercookiekey%>";//用户key,只存key，不存实际信息
		var baselocation = "${ctx}";
		var imagesPath="${ctximg}";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var disFaceUpUrl="<%=disFaceUpUrl%>";//小组头像上传路径
        var staticImageServer ="<%=staticImageServer%>";//上传后返回路径
		var uploadServerUrl="<%=uploadServerUrl%>";
		var uid="${uid}";
	</script>
	<sitemesh:head />
</head>
<body >
	<div class="e-wrap">
		<!-- e-header -->
		<jsp:include page="/WEB-INF/layouts/exam/header.jsp" />
		<!-- /e-header -->
		<%-- <c:if test="${keywordmap.keyword.verifyExam=='ON' }"> --%>
		<sitemesh:body />
		<%-- </c:if> --%>
		<!-- e-footer begin -->
		<jsp:include page="/WEB-INF/layouts/exam/footer.jsp" />
		<!-- e-footer end -->
	</div>
	<!-- 统计代码 -->
	${tongjiemap.censusCode.censusCodeString}
</body>
</html>
</compress:html>