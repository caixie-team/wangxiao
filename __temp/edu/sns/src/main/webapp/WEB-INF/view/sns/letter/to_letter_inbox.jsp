<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>站内信收件箱</title>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script src="${ctximg}/static/sns/js/letter/letter.js?v=<%=version%>" type="text/javascript" charset="utf-8"></script>
<script src="${ctximg}/static/sns/js/friend/friend.js?v=<%=version%>" type="text/javascript"></script>
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
								<li class="current"><a title="站内信"
									href="javascript:void(0)">站内信</a>
                                    <div class="ct-tabarrow-bg">&nbsp;</div>
									</li>

							</ul>
						</header>
						
						<div class="tab-nosep mt10">
							
							
							<ol class="clearfix">
								<!-- <li class="current"><a title="全部" href="javascript:void(0)">全部</a></li> -->
								<li class="current"><a title="收件箱"
									href="javascript:void(0)">收件箱</a></li>
							
								<li>
								<a title="发件箱" href="${ctx}/letter/outbox">发件箱</a></li>
								<c:if test="${not empty queryLetterList }">
								<a href="javascript:delAllInboxconfir()"
									class="fr reply-sub mr10"><span>清空</span></a>
								<a href="javascript:delselectByidconfir('Receive')"
									class="fr mr10 reply-sub"><span>删除</span></a>
								<!-- <a href="javascript:noselectAll(this)" class="fr mr10 reply-sub">全部取消</a> -->
								<a href="javascript:void(0)" onclick="selectAll(this)"
									class="fr mr10 reply-sub"><span>全选</span></a>
								</c:if>	
									<section class=" pr10 fl ml20">	
									<input type="hidden" value="dis" name="search.tab"> <input
										type="text" class="boke-search-inp" id="letterusername"
										name=""><a class="comm-btn-orange"
										href="javascript:letterSearch()"><span>搜索</span></a>
								</section>
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
						<c:if test="${empty queryLetterList }">
							<div class="Prompt">
								<img src="${ctximg}/static/sns/images/tishi.png"
									class="vam disIb">
								<p class="vam c-555 fsize14 disIb">您还没有任何站内信消息！</p>
							</div>
						</c:if>

						<div class="qun-news-list">
							<ol class="ml10 empty">
								<c:forEach items="${queryLetterList}" var="qltl">
									<li id="del${qltl.id }">
										<table cellspacing="0" cellpadding="0" border="0" width="100%">
											<tbody>
												<tr>
													<td width="5%" valign="center"><input type="checkbox"
														value="${qltl.id }" name="ids"></td>
													<td width="68%">
														<div class="pl10 pr10 of">
															<c:if test="${qltl.cusId>0}">
															<a class="QN-face" title="${qltl.userExpandDto.showname }" href="${ctx}/p/${qltl.cusId}/home"> 
																<c:if test="${qltl.userExpandDto.avatar!=null&&qltl.userExpandDto.avatar!='' }">
																	<img src="<%=staticImageServer%>${qltl.userExpandDto.avatar }" width="60" height="60" alt="">
																</c:if> 
																<c:if test="${qltl.userExpandDto.avatar==null||qltl.userExpandDto.avatar=='' }">
																	<img src="${ctximg}/static/sns/pics/user.jpg" width="60" height="60" alt="">
																</c:if>
															</a>
															<h6>
																<a class="c-blue" title="${qltl.userExpandDto.showname }" href="${ctx}/p/${qltl.cusId}/home">${qltl.userExpandDto.showname}</a>
															</h6>
															</c:if>
															<c:if test="${qltl.cusId==0}">
															<a class="QN-face" title="系统消息" href="javascript:void(0)"> 
																<img src="${ctximg}/static/sns/pics/user.jpg" width="60" height="60" alt="">
															</a>
															<h6>
																<a class="c-blue" title="系统消息" href="javascript:void(0)">系统消息</a>
															</h6>
															</c:if>
															<p class="mt5">
																<span class="c-888">发信时间：<fmt:formatDate type="both" value="${qltl.addTime }"></fmt:formatDate></span>
															</p>
															<p class="mt10">
																<span class="c-888">${qltl.userExpandDto.showname }对我说：${qltl.content }</span>
															</p>
														</div>
													</td>
													<td width="30%" align="center" class="c-bbb">
													<c:if test="${qltl.type!=1 }">
														<a class="c-orange mr10" title="回复" href="javascript:void(0)" onclick="addLetterInput('${qltl.userExpandDto.showname }',${qltl.cusId},this)">
															<u class="fsize14">回复</u>
														</a>|
													</c:if>
														<a class="c-orange mr10 ml10" title="删除" href="javascript:void(0)" onclick="delLetter(${qltl.id })">
														<u class="fsize14">删除</u>
														</a>
														<c:if test="${qltl.type!=1 }">|
														<%-- <a class="c-555 ml10 mr10" title="黑名单"
																	href="javascript:void(0)"
																	onclick="addblack(${qltl.cusId },${qltl.id })"><u
																		class="fsize14">黑名单</u></a>| --%> 
															<a class="c-555 ml10" title="历史记录" href="${ctx}/letter/history/${qltl.cusId}">
																<u class="fsize14">历史记录</u>
															</a>
														</c:if>
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
	<form action="${ctx}/letter/inbox" name="searchForm"
		id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage"
			value="${page.currentPage}" />
	</form>
</body>
