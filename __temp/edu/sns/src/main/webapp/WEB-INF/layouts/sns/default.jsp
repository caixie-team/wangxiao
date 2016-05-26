<%@ page import="com.atdld.os.sns.constants.SnsConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html  compressJavaScript="false" >
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title><sitemesh:title ></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="author" content="${websitemap.web.author}"/>
    <meta name="keywords" content="${websitemap.web.keywords}"/>
    <meta name="description" content="${websitemap.web.description}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<link href="${ctximg}/static/sns/css/commonality-css.css?v=<%=version%>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/static/common/commonJs.js?v=<%=version%>"></script>
	<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js"></script><![endif]-->
	<!--[if lt IE 7]>
	<script src="${ctximg}/static/common/ie_png.js"></script>
	<script type="text/javascript">try { if(ie_png){ie_png.fix('.icon12,.icon16,.icon18,.icon22,.icon26,.ct-tabarrow-bg,.gt-btn,.gt-logo a,.gt-nav li.current a,.Prompt img,.ui-icon')};}catch(e){}</script>
	<![endif]-->
	<!-- chat -->
	<script src="${ctximg}/static/sns/js/header/header.js?v=<%=version%>" type="text/javascript"></script>
	<script type="text/javascript">
		var mydomain ="<%=mydomain%>";//主站域
		var usercookiekey="<%=usercookiekey%>";//用户key,只存key，不存实际信息
		var baselocation = "${ctx}";
		var baselocationweb = "${ctxweb}";
		var baselocationexam = "${ctxexam}";
		var imagesPath="${ctximg}";
		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
		var disFaceUpUrl="<%=disFaceUpUrl%>";//小组头像上传路径
        var staticImageServer="<%=staticImageServer%>";//上传后返回路径
		var uploadServerUrl="<%=uploadServerUrl%>";
		var uid="${uid}";
		var max_text_length=<%=SnsConstants.MAX_TEXT_LENGTH%>;//博文，建议的最多次文字数量
	</script>
	<sitemesh:head ></sitemesh:head>
</head>
<body class="W-body">
	<div class="W-wraper">
		<jsp:include page="/WEB-INF/layouts/sns/header.jsp"></jsp:include>
		<!-- default主体 开始-->
		<%--<c:if test="${keywordmap.keyword.verifySns=='ON' }">--%>
		<div class="W-main M-body">
			<div class="W-main-bg  W-noBanner clearfix">
				<jsp:include page="/WEB-INF/layouts/sns/left.jsp"></jsp:include>
				
				<sitemesh:body></sitemesh:body>
				
			</div>
		</div>
		<%--</c:if>--%>
		<!-- default主体 结束-->
		<jsp:include page="/WEB-INF/layouts/sns/footer.jsp"></jsp:include>
	</div>
        ${tongjiemap.censusCode.censusCodeString}
</body>

</html>
</compress:html>