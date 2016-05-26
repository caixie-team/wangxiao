<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>分类小组话题</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<!-- 左侧目录区域 -->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center" style="width: 610px">
							<div class="pl20 pr20">
								<section class="mt10">
									<header class="comm-title-1">
										<%-- <a href="javascript:void(0)" class="fr c-888 mt10 mr20" onclick="isJoinPublish(${isJoin},${groupId})">发表话题&gt;&gt;</a> --%>
										<ul class="clearfix">
											<li class="one current">
												<a title="小组话题" href="javascript:void(0)">分类小组话题</a>
												<span class="c-green disIb mt10">共有${page.totalResultSize  }篇话题</span>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<div>
								<div>
									<c:if test="${fn:length(disArticleList)==0}">
									<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
									<p class="vam c-555 fsize14 disIb">还没有小组话题，快去创建第一篇小组话题吧...</p>
									</div>
									</c:if>					
									<div class="Q-article-list-2">
										<ul>
											<c:forEach items="${disArticleList }" var="dal">
												<li>
													<h5 class="clearfix">
														<div class="fr c-bbb fsize12">
															<span class="ml10"><tt class="c-888">最后发表：</tt> <c:if
																	test="${dal.lastReply!=null }">
																	<tt class="c-888">
																		<span class="c-green mr5">${dal.lastReply
																			}</span>
																		<fmt:formatDate type="date" value="${dal.lastTime}"></fmt:formatDate>
																	</tt>
																</c:if> <c:if test="${dal.lastReply==null }">
																	<tt class="c-888">
																		<span class="c-green">${dal.showName
																			}</span>
																		<fmt:formatDate type="date" value="${dal.createTime}"></fmt:formatDate>
																	</tt>
																</c:if> </span>
														</div>
														<div class="fl">
															<c:if test="${dal.selType==0 }">
												<tt class="icon22 yuan">&nbsp;</tt>
												</c:if>
												<c:if test="${dal.selType==1 }">
												<tt class="icon22 zhuan">&nbsp;</tt>
												</c:if>
															<a
																href="${ctx}/dis/artinfor/${dal.id}/${dal.groupId}"
																class="c-blue article-q-l-link-txt"
																title="${dal.title }" style="display: block;height: 56px;line-height: 26px; text-indent: 0;white-space: normal;">${dal.title }</a>
														</div>
													</h5>
													<div class="QA-desc-2">${dal.summary}</div>
													<div class="mt5 clearfix">
														<span class="fr"><tt class="c-888">评论/查看：</tt><a
															href="${ctx}/dis/artinfor/${dal.id}/${dal.groupId}" title="" class="c-555">${dal.reNum}/${dal.countView}</a></span>
														<span class="fl ml10"><tt class="c-888">作者：</tt><a
															href="${ctx}/p/${dal.cusId}/home" title=""
															class="c-blue">${dal.showName}</a></span>
													</div>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
								<%-- <div class="tar mt20"><a href="javascript:void(0)" class="comm-btn-orange" onclick="isJoinPublish(${isJoin},${groupId})"><span style="padding: 3px 30px;font-size: 15px;">发表话题</span></a></div> --%>
							</div>

								<!-- 公共分页 开始 -->
								<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
								<!-- 公共分页 结束 -->
								<form action="${ctx}/dis/classifyart/${classifyId}" name="searchForm" id="searchForm" method="post">
										<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
										</form>
							</div>
						</section>
							<section class="W-main-right">
						<div class="pl20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l">话题分类</span>
								</section>
								<section class="mt20 pr10">
									<ul class="boke-sort-list">
										<c:forEach items="${artClassifyList }" var="art">
											<li><a href="${ctx}/dis/classifyart/${art.artId}"
												title="" class="fsize14 c-orange">${art.name }</a><span
												class="c-green">（${art.disArticleNum}）</span></li>
										</c:forEach>
									</ul>
								</section>
							</div>
						</div>
					</section>
					</div>
				</section>
</body>
</html>