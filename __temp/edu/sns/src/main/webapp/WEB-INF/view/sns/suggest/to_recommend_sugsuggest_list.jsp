<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>已解决问题 </title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/suggest/suggest.js?v=<%=version%>"></script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center">
							<div class="pl20">
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li><li class="current"><a id="weijiejue" href="${ctx}/sug"
												title="未解决问题">未解决问题</a>
												</li>
											<li><a id="yijiejue" href="${ctx}/sug/tj"
												title="已解决问题 ">已解决问题 </a>
												<div class="ct-tabarrow-bg">&nbsp;</div></li> 
										</ul>
									</header>
								</section>
								<!-- /挑战信息 -->
								<div class="tj-question-list">
									<c:if test="${empty SugSuggestList}">
										<div class="Prompt">
											<img class="vam disIb"
												src="${ctximg}/static/sns/images/tishi.png">
											<p class="vam c-555 fsize14 disIb">您还没有推荐过问题呢。</p>
										</div>
									</c:if>
									<c:forEach items="${SugSuggestList}" var="sslst">
										<dl>
											<dt class="fsize14">
												<a
													href="javascript:window.location.href='${ctx}/sug/info/${sslst.id }'"
													class="c-blue" style="display: inline" title="${sslst.title}">${sslst.title}</a>
											</dt>
											<dd>
												<div class="tj-q-desc c-555">${sslst.summary}</div>
												<div class="mt10 of">
												</div>
											</dd>
										</dl>
									</c:forEach>
								</div>
							</div>
							<!-- 公共分页 开始 -->
							<jsp:include page="/WEB-INF/view/sns/common/page.jsp" />
							<!-- 公共分页 结束 -->
						</section>
						<jsp:include page="/WEB-INF/view/sns/suggest/right.jsp" />
					</div>
				</section>
				<!-- 主体区域 -->
<form action="" name="searchForm" id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage"
		value="${page.currentPage}" />
</form>
</body>
