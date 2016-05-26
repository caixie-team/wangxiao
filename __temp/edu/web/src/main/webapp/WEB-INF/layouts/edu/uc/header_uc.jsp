<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- /个人中心公共头 -->
	<header id="u-header">
		<section class="w1000">
			<div class="u-h-left">
				<section class="u-logo-slogn">
					<a href="/" title="${websitemap.web.company}" class="png"><img src="<%=staticImageServer %>${logomap.logo.url}" width="72" height="45" class="logo-2013" ></a>
					<span class="u-slogn" style="cursor: pointer;vertical-align: top;" onclick="location.href='/uc/home'"><strong class="c-master">个人中心</strong></span>
				</section>
				<nav class="u-nav ml10">
					<ul class="clearfix" id="userGuideInfo">
						<li class="current"><a title="我的首页">我的首页</a></li>
						<c:forEach items="${navigatemap.USER}" var="userNavigate">
        					<li><a href="${userNavigate.url}" title="${userNavigate.name}" <c:if test="${userNavigate.newPage==0}">target="_blank"</c:if>>${userNavigate.name}</a></li>
						</c:forEach>
					</ul>
				</nav>
			</div>
			<div class="u-h-right">
				<section class="u-h-r-user">
					<div class="tar">
						<span class="u-h-name-wrap">你好，<tt class="u-h-name" id="unameheader"></tt></span><span class="pr"><tt id="msgCountId" class="undis tip-news pa"></tt><tt><a class="c-666" href="${ctx}/uc/letter ">消息</a></tt></span><span><a href="javascript:exit()" title="退出" class="c-666">退出</a></span>
					</div>
				</section>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</section>
	</header>
	<!-- /个人中心公共头 结束 -->
	<section class="u-banner">
		<a href="javascript:void(0)" onclick="skinChange();return false;" title="点击更换模板" class="u-tem-change"></a>
		<div id="uPosition">&nbsp;</div>
	</section>