<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>站内信收件箱</title>
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
											<li class="current"><a title="站内信" href="javascript:void(0)">站内信</a>
												<div class="ct-tabarrow-bg">&nbsp;</div></li>
										</ul>
									</header>
									<div class="tab-nosep mt10">
										<ol class="clearfix">
											<!-- <li class="current"><a title="全部" href="javascript:void(0)">全部</a></li> -->
											<li><a title="收件箱" href="${ctx}/letter">收件箱</a></li>
											<li class="current"><a title="发件箱" href="javascript:void(0)">发件箱</a></li>
											<c:if test="${not empty msgSenderList }">
											<a href="javascript:delAllOutboxconfir()" class="fr mr10 reply-sub"><span>清空</span></a>
											<a href="javascript:delselectByidconfir('msgSender')" class="fr mr10 reply-sub"><span>删除</span></a>
											<a href="javascript:noselectAll()" class="fr mr10 reply-sub"><span>全部取消</span></a>
											<a href="javascript:selectAll()" class="fr mr10 reply-sub"><span>全选</span></a>
											</c:if>
											
										</ol>
									</div>
									<%-- <div class="sub-title-wrap clearfix">
										<section class="fr">
											<a href="${ctx}/letter/inbox"
												title="收件箱" class="comm-btn-orange"
												style="line-height: 18px;"><span>收件箱</span></a> <a
												href="${ctx}/letter/outbox" title=""
												class="comm-btn-orange ml10" style="line-height: 18px;"><span>发件箱</span></a>
										</section>
								</div> --%>
								</section>
								<section class="pr20">
									<c:if test="${empty msgSenderList }">
										<div class="Prompt">
											<img src="${ctximg}/static/sns/images/tishi.png"
												class="vam disIb">
											<p class="vam c-555 fsize14 disIb">您还没有任何站内信消息！</p>
										</div>
									</c:if>

									<div class="qun-news-list">
										<ol class="ml10 empty">
											<c:forEach items="${msgSenderList}" var="qltl">
												<li id="del${qltl.id }">
													<table cellspacing="0" cellpadding="0" border="0"
														width="100%">
														<tbody>
															<tr>
																<td width="5%" valign="center">
																	<input type="checkbox" value="${qltl.id }" name="ids">
																</td>
																<td width="75%">
																	<div class="pl10 pr10 of">
																		<a class="QN-face" title="${qltl.userExpandDto.showname }" href="${ctx}/p/${qltl.receivingCusId}/home">
																		<c:if test="${qltl.userExpandDto.avatar!=null&&qltl.userExpandDto.avatar!='' }">
																			<img src="<%=staticImageServer%>${qltl.userExpandDto.avatar }" width="60" height="60" alt="">
																		</c:if>
																		<c:if test="${qltl.userExpandDto.avatar==null||qltl.userExpandDto.avatar=='' }">
																			<img src="${ctximg}/static/sns/pics/user.jpg" width="60" height="60" alt="">
																		</c:if></a>
																		<h6>
																			<a class="c-blue" title="${qltl.userExpandDto.showname }" href="${ctx}/p/${qltl.receivingCusId}/home">${qltl.userExpandDto.showname }</a>
																		</h6>
																		<p class="mt5">
																			<span class="c-888">发信时间：<fmt:formatDate
																					type="both" value="${qltl.addTime }" ></fmt:formatDate></span>
																		</p>
																		<p class="mt10">
																			<span class="c-888">我对${qltl.userExpandDto.showname
																				}说：${qltl.content }</span>
																		</p>
																	</div>
																</td>
																<td width="23%" align="center" class="c-bbb"><a
																	class="c-orange mr10" title="删除"
																	href="javascript:void(0)"
																	onclick="delLetterOutbox(${qltl.id })"><u class="fsize14">删除</u></a></td>
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
		<form action="${ctx}/letter/outbox" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
		</form>
		<!-- 主体 结束-->
</body>
