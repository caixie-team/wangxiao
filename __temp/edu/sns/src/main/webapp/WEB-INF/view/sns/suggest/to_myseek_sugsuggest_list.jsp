<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>我的问题 </title>
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
										<a href="${ctx}/sug/add" class="fr c-888 mt10 mr10">提问题&gt;&gt;</a>
										<ul class="clearfix">
											<li class="mysug"><a href="javascript:mysug()"
												title="问题">问题</a>
												</li>
											<li class="mysughd"><a href="javascript:mysughd()"
											title="回答">回答</a>
											</li>
										<li class="myrecommend"><a href="javascript:myrecommend()" title="被采纳">被采纳</a>
										</li>
										</ul>
									</header>
								</section>
								<div class="question-list">
									<ul class="sugHtml">
									</ul>
								</div>
							</div>
						</section>
						<jsp:include page="/WEB-INF/view/sns/suggest/right.jsp" />
					</div>
				</section>
				<!-- 主体区域 -->
<form action="${ctx}/sug/my" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		</form>
</body>
