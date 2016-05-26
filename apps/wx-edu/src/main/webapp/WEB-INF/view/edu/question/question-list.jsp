<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>问答</title>
<!--[if lt IE 9]><script src="js/html5.js"></script><![endif]-->
</head>
<body>
	<div class="">
		<div class="h-mobile-mask"></div>
		<div class="bg-fa of">
			<!-- /课程列表 开始 -->
			<section class="container">
				<section class="path-wrap txtOf hLh30">
					<a class="c-999 fsize14" title="" href="${ctx}/">首页</a> \ <span
						class="c-333 fsize14">问答</span>
				</section>
				<div class="clearfix">
					<div class="fl col-75">
						<div class="mr20 mb20">
							<div class="i-box">
								<ul class="q-a-title clearfix of">
									<li <c:if test="${empty flag}">class="current"</c:if>><a href="${ctx}/front/question">全部问答</a></li>
									<li <c:if test="${not empty flag and flag=='solved'}">class="current"</c:if>><a href="${ctx}/front/question?flag=solved">已解决</a></li>
									<li <c:if test="${not empty flag and flag=='unsolved'}">class="current"</c:if>><a href="${ctx}/front/question?flag=unsolved">未解决</a></li>
								</ul>
								<c:if test="${empty SugSuggestList }">
									<div class="mt40">
										<section class="no-data-wrap">
											<em class="no-data-ico">&nbsp;</em>
											<span class="c-666 fsize14 ml10 vam">还没有问题！建议你<a class="c-orange" title="" href="${ctx}/front/questionAdd">去问问题</a></span>
										</section>
									</div>
								</c:if>
								<c:if test="${not empty SugSuggestList }">
									<div class="qlist mt15">
										<ul>
											<c:forEach items="${SugSuggestList}" var="sug">
												<li>
													<aside class="q-head-pic">
														<c:if test="${not empty sug.queryCustomer.avatar}">
															<c:if test="${fn:contains(sug.queryCustomer.avatar, 'http')}">
																<img src="${sug.queryCustomer.avatar}" width="60" height="60">
															</c:if>
															<c:if test="${!fn:contains(sug.queryCustomer.avatar, 'http')}">
																<img src="<%=staticImageServer %>${sug.queryCustomer.avatar}" width="60" height="60">
															</c:if>
															<p class="hLh30 txtOf">
																<span class="c-999"> ${sug.queryCustomer.showname}</span>
															</p>
														</c:if>
														<c:if test="${ empty sug.queryCustomer.avatar}">
															<img alt="" src="${ctx}/static/nxb/web/img/avatar-boy.gif">
															<p class="hLh30 txtOf">
																<span class="c-999"> ${sug.queryCustomer.showname}</span>
															</p>
														</c:if>
													</aside>
													<section class="q-txt-box">
														<h3 class="hLh30 txtOf">
															<a class="fsize16 c-333 vam" title="" href="${ctx}/front/question/info/${sug.id}">${sug.title}</a>
														</h3>
														<h3 class="hLh30 txtOf mt5 ques-return">
															<span class="fsize14 c-999 vam">
																<c:choose>
																	<c:when test="${sug.replycount>0}">
																		<tt	class=" f-fM mr5">最新回复：</tt>
																		${sug.replyContent }
																	</c:when>
																</c:choose>
															</span>
														</h3>
														<div title="" href="" class="replyBrowseNum">
															<span class="c-999 vam"><%----%>
																<c:if test="${not empty sug.modelStr}">${sug.modelStr}</c:if>
																<c:if test="${empty sug.modelStr}"><fmt:formatDate value="${sug.addtime}" type="both" pattern="yyyy-MM-dd HH:mm"/></c:if>
															</span>
															<section class="of ques-desc">
																<span title="浏览数量" class="c-999 vam disIb">
																	<em class="icon14 q-view mr5 vam"></em>
																	<tt class="vam f-fM">${sug.browseNum}</tt>
																</span>
																<span title="回复数量" class="disIb c-999 ml10">
																	<em class="icon14 q-review mr5"></em>
																	<tt class="vam f-fM">${sug.replycount}</tt>
																</span>
															</section>
														</div>
													</section>
												</li>
											</c:forEach>
										</ul>
										<div class="mt40">
											<section class="">
												<form action="${ctx}/front/question" method="post" id="searchForm">
													<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
													<input type="hidden" value="${flag}" name="flag"/>
												</form>
												<jsp:include page="/WEB-INF/view/common/web_page.jsp" />
											</section>
										</div>
									</div>
								</c:if>
							</div>
						</div>
					</div>
					<div class="fr col-25">
						<div class="i-box">
							<div class="of">
								<a href="${ctx}/front/questionAdd" class="bm-lr-btn q-ask-btn">我要提问</a>
							</div>
						</div>
						<div class="i-box mt20">
						  <c:if test="${empty userExpandDto}">
								<div class="mt40">
									<section class="no-data-wrap">
										<em class="no-data-ico">&nbsp;</em>
										<span class="c-666 fsize14 ml10 vam">你还没登录，<a class="c-orange" title="" href="${ctx}/login">去登录</a></span>
									</section>
								</div>
							</c:if>
							<div class="clearfix q-a-box">
								<c:if test="${not empty userExpandDto}">
									<div class="q-a-pic">
										<c:choose>
											<c:when test="${not empty userExpandDto.avatar }">
												<c:if test="${fn:contains(userExpandDto.avatar, 'http')}">
													<img src="${userExpandDto.avatar}" width="60" height="60">
												</c:if>
												<c:if test="${!fn:contains(userExpandDto.avatar, 'http')}">
													<img src="<%=staticImageServer %>${userExpandDto.avatar}" width="60" height="60">
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${ctx}/static/nxb/web/img/avatar-boy.gif" width="60" height="60">
											</c:otherwise>
										</c:choose>
									</div>
									<p class="hLh30 c-666">
										提问：<span class="c-blue">${publishedNum}</span>
									</p>
									<p class="hLh30 c-666">
										回答：<span class="c-blue">${replycountNum}</span>
									</p>
									<div class="q-a-btn">
										<a href="${ctx}/uc/ques/my">我的问答</a>
									</div>
								</c:if>
							</div>
						</div>
						<div class="i-box mt20 mb20">
							<div class="of">
								<div class="mb10">
									<span class="fsize16 c-333">热门问答</span>
								</div>
							</div>
							<ul class="q-hot-list comm-new-list">
							<c:forEach items="${querySuggestHtoComments}" var="querySuggestHtoComments">
								<li>
									<p>
										<a href="${ctx}/front/question/info/${querySuggestHtoComments.id}" class="txtOf">${querySuggestHtoComments.title}</a>
									</p> <span title="" class="q-view-box c-999 vam disIb"> <em
										class="icon14 q-view mr5 vam"></em> <tt class="vam f-fM">${querySuggestHtoComments.browseNum }</tt>
								</span>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>
</html>