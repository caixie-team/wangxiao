<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>同学问题</title>
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
											<li class=" unsolved"><a href="javascript:unsolved()"
												title="未解决问题">未解决问题</a>
												</li>
											<li class="solved"><a href="javascript:solved()"
												title="已解决问题 ">已解决问题 </a></li> 
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
</body>
