<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>站内信系统消息</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script src="${ctximg}/static/sns/js/letter/letter.js?v=<%=version%>" type="text/javascript"></script>
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
											<li class="current"><a title="系统通知" href="javascript:void(0)">系统通知</a>
												<div class="ct-tabarrow-bg">&nbsp;</div></li>
												<a href="javascript:delAllMsgSysconfir()" class="fr mr10 mt10">清空</a>
												<a href="javascript:delselectByidconfir('Receive')" class="fr mr10 mt10">删除</a>
												<a href="javascript:noselectAll()" class="fr mr10 mt10">全部取消</a>
												<a href="javascript:selectAll()" class="fr mr10 mt10">全选</a>
												
										</ul>
										
									</header>
								</section>
								<section class="pr20">
									<c:if test="${empty queryLetterList }">
										<div class="Prompt">
											<img src="${ctximg}/static/sns/images/tishi.png"
												class="vam disIb">
											<p class="vam c-555 fsize14 disIb">您还没有任何系统消息！</p>
										</div>
									</c:if>

									<div class="qun-news-list">
										<ol class="ml10 empty">
											<c:forEach items="${queryLetterList}" var="qltl">
											<li id="del${qltl.id }">
												<table cellspacing="0" cellpadding="0" border="0" width="100%">
													<tbody>
														<tr>
															<td width="5%" valign="center">
																<input type="checkbox" value="${qltl.id }" name="ids">
															</td>
															<td width="85%">
																<div class="pl10 pr10 of">
																	<h6><a class="c-blue fsize14" title="${websitemap.web.author}" href="${ctx}" target="_blank"/>${websitemap.web.author}</a></h6>
																	<p class="mt5">
																		<span class="c-888">发信时间：<fmt:formatDate
																					type="both" value="${qltl.addTime }" ></fmt:formatDate></span>
																	</p>
																	<p class="mt5"><span class="c-555">${websitemap.web.author}对我说：${qltl.content }</span></p>
																</div>
															</td>
															<td width="10%" align="center" class="c-bbb">
																<a class="c-555 mr10" title="删除" href="javascript:void(0)" onclick="delLettersys(${qltl.id })"><u class="fsize14">删除</u></a>
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
		<form action="${ctx}/letter/sys" name="searchForm"
		id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage"
			value="${page.currentPage}" />
	</form>
</body>
