<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh"%>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp"%>
<compress:html compressJavaScript="false" >
<!DOCTYPE HTML>

<!--[if lt IE 7]>      <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html> <!--<![endif]-->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><sitemesh:title ></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
	<meta name="author" content="${websitemap.web.author}"/> 
	<meta name="keywords" content="${websitemap.web.keywords}" />
	<meta name="description" content="${websitemap.web.description}"/> 
	<link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon"> 
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/nxb/web/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/nxb/web/css/global.css">
	<link rel="stylesheet" type="text/css" href="${ctximg}/static/nxb/web/css/uCenter-nxb.css">
	<link href="${ctximg}/static/nxb/web/css/mw_320_768.css" rel="stylesheet" type="text/css" media="screen and (min-width: 320px) and (max-width: 768px)">
	<script src="${ctximg}/static/common/jquery-1.11.1.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]><script src="${ctximg}/static/nxb/web/js/html5.js"></script><![endif]-->
	<sitemesh:head ></sitemesh:head>
</head>
<body>
<div class="in-wrap">
	<!-- 公共头引入，开始 -->
	<jsp:include page="/WEB-INF/layouts/nxb/header.jsp"></jsp:include>
	<div class="bg-fa of">
	<section class="container">
			<!-- /个人中心封面区域 开始-->
			<div class="uc-ban-box">
				<div class="uc-ban" style="background-image: url(${ctximg}/static/nxb/web/img/pic/uc-1.jpg);">
					<!-- <aside class="u-tem-change">
						<a href="" title="更换封面"><em class="icon24"></em></a>
					</aside> -->
					<section class="u-elephant">
						<aside> 
							<c:if test="${empty loginUser.avatar}">
							<img width="140" height="140" alt=""  src="${ctximg}/static/nxb/web/img/avatar-boy.gif"> 
							</c:if>
							<c:if test="${not empty loginUser.avatar}">
								<c:if test="${fn:contains(loginUser.avatar,'http:')}">
								<img width="140" height="140" alt=""  src="${loginUser.avatar}"> 
								</c:if>
								<c:if test="${fn:contains(loginUser.avatar,'http:')==false}">
								<img width="140" height="140" alt=""  src="${staticUrl}${loginUser.avatar}"> 
								</c:if>
							</c:if>
							
							<a class="c-fff fsize12" title="修改头像" href="${ctx}/uc/avatar">修改头像</a>
						</aside>
					</section>
					<section class="pt20">
						<h4 class="hLh30">
							<span class="fsize20 c-fff">${loginUser.showname}</span>
							<c:if test="${loginUser.gender==0}">
								<span  class="ml5 icon14 boy" title="男">&nbsp;</span>
							</c:if>
							<c:if test="${loginUser.gender==1}">
								<span  class="ml5 icon14 girl" title="女">&nbsp;</span>
							</c:if>
						</h4>
					</section>
					<!-- <section class="u-desc-of">
						<p class="fsize12 c-ccc">这个人很懒，什么也没有留下...</p>
					</section> -->
				</div>
				<div class="uc-tab-wrap">
					<ul class="uc-tab-ul fl">
						<li>
							<a href="${ctx}/uc/avatar" title="" class="set-ph"><em class="icon14"></em><span class="c-666 fsize14 vam">修改头像</span></a>
						</li>
						<li>
							<a href="${ctx}/uc/uinfo" title="" class="set-pr"><em class="icon14"></em><span class="c-666 fsize14 vam">个人设置</span></a>
						</li>
						<li>
							<a href="${ctx}/uc/myinte" title="" class="set-pj"><em class="icon16"></em><span class="c-666 fsize14 vam">我的积分：</span><small id="my_score" class="vam fsize14 c-master">${fn:replace(loginUser.score, ".0", "")}  </small></a>
							<a href="${ctx}/uc/integift" title="" class="ml10 c-666 fsize12">积分兑换&gt;</a>
						</li>
					</ul>
					<c:if test="${hasRole}">
						<ul class="uc-tab-ul fr uc-sys-tab">
							<li>
								<a href="${ctx}/admin/sys/main" title="" class="set-ph"><em class="icon14"></em><span class="c-666 fsize16 vam">系统管理</span></a>
							</li>
						</ul>
					</c:if>
					<div class="clear"></div>
				</div>
			</div>
			<!-- /个人中心封面区域 结束-->
	
			<div class="uc-main">
				<div class="uc-m-box">
					<!-- 个人中心左侧 -->
					<jsp:include page="/WEB-INF/layouts/nxb/uc/left_uc.jsp"></jsp:include>
					<!-- /u-main -->
					<sitemesh:body></sitemesh:body>
				</div>
			</div>
			<!-- /个人中心主区域 -->
		</section>
	</div>
	<!-- 公共头引入，结束 -->
	<!-- 公共尾引入，开始 -->
	<jsp:include page="/WEB-INF/layouts/nxb/footer.jsp"></jsp:include>
	<!-- 公共尾引入，结束 -->
</div>
	<jsp:include page="/WEB-INF/layouts/nxb/right_help.jsp"/>
<script src="${ctximg}/static/nxb/web/js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
	<script>
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
    var imageUrl = "${imageUrl}";
    var staticUrl = "${staticUrl}";
    var userId = ${userId};
	$(function() {
		lmenu(); //目录菜单
		mbFun(); //移动端方法调取
		goTop();
	})
</script>
<script type="text/javascript" src="${ctximg}/static/common/ucenter.js?v=<%=version%>"></script>

<!-- 统计代码 -->
${tongjiemap.censusCode.censusCodeString}
</body>

</html>
</compress:html>