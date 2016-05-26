<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<head>
	<title>历史记录</title>
	<script src="${ctximg}/static/sns/js/letter/letter.js?v=<%=version%>" type="text/javascript" charset="utf-8"></script>
</head>   
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center W-main-c-r">
							<div class="pl20">
								<section class="mt10 pr20">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="current">
												<a title="历史记录" href="javscript:void(0)">历史记录</a>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<section class="pr20">
								<c:if test="${empty queryLetterList }">
									<div class="Prompt">
										<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
										<p class="vam c-555 fsize14 disIb">您还没有任何站内信消息！</p>
									</div>
									</c:if>
									
									<div class="qun-news-list">
										<ol class="ml10">
											<c:forEach items="${queryLetterList}" var="qltl">
											<li id="del${ qltl.id}">
												<table cellspacing="0" cellpadding="0" border="0" width="100%">
													<tbody>
														<tr>
															<td width="3%" valign="center">
															</td>
															<td width="75%">
																<c:if test="${qltl.cusId!=cusId}">
																<div class="pl10 pr10 of">
																	<a class="QN-face" title="${qltl.showname}" href="${ctx}/p/${qltl.cusId}/home">
																	<c:if test="${qltl.userExpandDto.avatar!=null&&qltl.userExpandDto.avatar!='' }">
																	<img src="<%=staticImageServer%>${qltl.userExpandDto.avatar }" width="60" height="60" alt="">
																	</c:if>
																	<c:if test="${qltl.userExpandDto.avatar==null||qltl.userExpandDto.avatar=='' }">
																	<img src="${ctximg}/static/sns/pics/user.jpg" width="60" height="60" alt="">
																	</c:if></a>
																	<h6><a class="c-blue" title="${qltl.showname}" href="${ctx}/p/${qltl.cusId}/home">${qltl.showname}</a></h6>
																	<p class="mt5">
																		<span class="c-888">发信时间：<fmt:formatDate type="both" value="${qltl.addTime }" ></fmt:formatDate></span>
																	</p>
																	<p class="mt10"><span class="c-888">${qltl.showname}对我说：${qltl.content }</span></p>
																</div>
																</c:if>
																<c:if test="${qltl.cusId==cusId}">
																<div class="pl10 pr10 of">
																	<a class="QN-face" title="${qltl.userExpandDto.showname}" href="${ctx}/p/${qltl.cusId}/home">
																	<c:if test="${qltl.userExpandDto.avatar!=null&&qltl.userExpandDto.avatar!='' }">
																	<img src="<%=staticImageServer%>${qltl.userExpandDto.avatar }" width="60" height="60" alt="">
																	</c:if>
																	<c:if test="${qltl.userExpandDto.avatar==null||qltl.userExpandDto.avatar=='' }">
																	<img src="${ctximg}/static/sns/pics/user.jpg" width="60" height="60" alt="">
																	</c:if></a>
																	<h6><a class="c-blue" title="${qltl.userExpandDto.showname}" href="${ctx}/p/${qltl.cusId}/home">${qltl.userExpandDto.showname}</a></h6>
																	<p class="mt5">
																		<span class="c-888">发信时间：<fmt:formatDate type="both" value="${qltl.addTime }" ></fmt:formatDate></span>
																	</p>
																	<p class="mt10"><span class="c-888">我对${qltl.showname}说：${qltl.content }</span></p>
																</div>
																</c:if>
															</td>
															<td width="20%" align="center" class="c-bbb">
																<a class="c-orange mr10" title="删除" href="javascript:void(0)" onclick="delLetter(${qltl.id })"><u class="fsize14">删除</u></a>
															</td>
														</tr>
													</tbody>
												</table>
											</li>
											</c:forEach>
										</ol>
									</div>
								</section>

								<!-- 公共分页 开始 -->
								<jsp:include page="/WEB-INF/view/sns/common/page.jsp" />
								<!-- 公共分页 结束 -->
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
		<form action="${ctx}/letter/history/${userid}" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
		</form>
		<!-- 主体 结束-->
</body>
