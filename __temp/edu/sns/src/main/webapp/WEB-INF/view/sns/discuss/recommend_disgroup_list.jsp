<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>小组首页</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
					<section class="W-main-c fl">
					<div class="W-main-cc" style="overflow: visible">
						<div class="W-main-center">
							<section class="W-main-center" style="overflow: visible;">
							<c:forEach items="${disGroupClassify }" var="disclassify" >
							
								<div class="pl20 pr20 mb20">
									<section class="mt10">
										<header class="comm-title-1">
											<a href="${ctx}/dis/classifygroup/${disclassify.id}" class="fr c-888 mt10 mr10 fsize12">更多&gt;</a>
											<ul class="clearfix" >
												<li class="one current">
													<a title="${disclassify.name }" href="javascript:void(0)">${disclassify.name }</a>
													<div class="ct-tabarrow-bg">&nbsp;</div>
												</li>
											</ul>
										</header>
									</section>
									<div class="QM-sort-qun">
									
										<section id="QM-list" class="QM-list">
											<ol class="clearfix">
											<c:forEach items="${disclassify.disGroupList }" var="dis"  varStatus="index">
											<c:if test="${index.index<4}">
												<li style="margin:30px 22px 0;">
													<a href="${ctx}/dis/info/${dis.id}" title="" class="QM-face">
														<img src="<%=staticImageServer%>${dis.imageUrl}" widht="100" height="100" alt="">
														<p class="mt5">${dis.name}</p>
													</a>
												</li>
													</c:if>
												</c:forEach>
											</ol>
										</section>
										
									</div>
								</div>
						</c:forEach>
								</section>
							</div>
							<!-- right 开始 -->
							<jsp:include page="/WEB-INF/view/sns/discuss/right.jsp"></jsp:include>
							</div>
						</section>
</body>
</html>