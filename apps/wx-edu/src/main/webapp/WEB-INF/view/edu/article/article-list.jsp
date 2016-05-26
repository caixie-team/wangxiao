<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>资讯</title>
</head>
<body>
<form id="searchForm" action="${ctx}/front/articlelist/${type}" method="post">
	<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
</form>
<div class="bg-fa of" id="aCoursesList">
<!-- /课程列表 开始 -->
	<section class="container">
		<section class="path-wrap txtOf hLh30"> 
			<a class="c-999 fsize14" title="" href="${ctx}">首页</a> \ 
			<span class="c-333 fsize14">资讯公告</span> 
		</section>
		<div class="clearfix">
			<div class="fl col-75">
				<div class="mr20 mb20">
					<div class="i-box">
						<ul class="ar-list-title clearfix of">
							<li id="article_all"><a href="${ctx}/front/articlelist/all">全部</a></li>
							<c:forEach items="${classifyList}" var="classify">
								<li  id="article_${classify.name}"><a href="${ctx}/front/articlelist/${classify.name}">${classify.explain}</a></li>
							</c:forEach>
						</ul>
						<article class="i-article-list mt30">
							<ul>
								<c:if test="${not empty articleList }">
									<c:forEach items="${articleList}" var="atricle" varStatus="index">
										<li>
											<aside class="i-article-pic">
												<c:choose>
													<c:when test="${not empty atricle.picture}">
														<img alt="${atricle.title}" src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="<%=staticImageServer%>${atricle.picture}"/>
													</c:when>
													<c:otherwise>
														<img alt="${atricle.title}" src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="${ctximg}/static/common/images/default_goods.png"/>
													</c:otherwise>
												</c:choose>
											</aside>
											<h3 class="hLh30 txtOf">
												<a class="i-art-title" title="" href="${ctx}/front/toArticle/${atricle.id}">${atricle.title}</a>
											</h3>
											<section class="i-q-txt mt5 i-q-txt2">
												<p>
													<span class="c-999 f-fA">${atricle.description}</span>
												</p>
											</section>
											<section class="hLh30 txtOf mt5 pr10 a-list-extrainfo">
												<div class="fr">
													<%-- <span title="评论数" class="c-999 vam disIb">
														<em class="icon14 q-review mr5 vam"></em><tt class="vam f-fM">${Atricle.clickTimes}</tt>
													</span>  --%>
													<span title="浏览数" class="disIb c-999 ml15 vam">
														<em class="icon14 q-view mr5"></em> <tt class="vam f-fM">${atricle.clickTimes}</tt>
													</span>
													<tt class="c-999 f-fM ml15 vam"><fmt:formatDate value="${atricle.updateTime }"  pattern="yyyy-MM-dd HH:mm"/></tt>
												</div>
											</section>
										</li>
									</c:forEach>
								</c:if>
								<c:if test="${empty articleList}">
								<div class="mt40">
									<!-- /无数据提示 开始-->
									<section class="no-data-wrap">
										<em class="no-data-ico">&nbsp;</em>
										<span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
									</section>
								</div>
								</c:if>
							</ul>
								<!-- 公共分页 开始 -->
								<jsp:include page="/WEB-INF/view/common/web_page.jsp" />
								<!-- 公共分页 结束 -->
						</article>
					</div>
				</div>
			</div>
			<div class="fr col-25">
				<div class="i-box1 mb20">
					<header class="com-title-box">
						<span class="fsize18 c-333">点击排行</span>
					</header>
					<ul class="ar-rank-list">
						<c:forEach items="${articleListOrderclickTimes}" var="circuitRanking" varStatus="index">
							<li>
								<tt class="ord"><font class="f-fM fsize12">${index.index+1}</font></tt>
								<p class="txtOf"><a href="${ctx}/front/toArticle/${circuitRanking.id}" title="${circuitRanking.title}">${circuitRanking.title}</a></p>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
		</div>
	</section>
</div>
<script type="text/javascript">
$(function (){
	scrollLoad();
	$("#article_${type}").addClass('current');
	aRankFun();//点击排名的前三个样式
});
function aRankFun(){
	$(".ar-rank-list>li .ord:eq(0)").addClass("ord-fir");
	$(".ar-rank-list>li .ord:eq(1)").addClass("ord-sec");
	$(".ar-rank-list>li .ord:eq(2)").addClass("ord-thi");
}
</script>
</body>
</html>
