<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>小组话题</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
					<div class="l-o-menu">
						<div class="mt20">
							<section class="l-level">
								<a href="${ctx}/dis/home" title="" class="lev-15 ">
									<i class="icon18">&nbsp;</i>
									小组首页
								</a>
							</section>
						<section class="l-level">
								<a href="${ctx}/dis/${userid }/hisart" title="" class="lev-15 current">
									<i class="icon18">&nbsp;</i>
									小组话题
								</a>
							</section>
						</div>
					</div>
				</aside>
				<!-- 左侧目录区域 -->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center W-main-c-r">
							<div class="pl20 pr20">
								<section class="mt10">
									<header class="comm-title-1">
										<a href="javascript:history.go(-1)" class="fr c-888 mt10 mr20">返回&gt;&gt;</a>
										<ul class="clearfix">
											<li class="one current">
												<a title="小组话题" href="javascript:void(0)">小组话题</a>
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
									<p class="vam c-555 fsize14 disIb">还没有小组话题，快去创建自己的小组话题吧...</p>
									</div>
									</c:if>					
								<%-- 	<div class="question-list">
										<ul>
										<c:forEach items="${myDisArticle}" var="my">
											<li id="rem${my.id }">
												<section class="clearfix">
													<div class="fr">
														<span class="replay-num c-blue">${my.showName }</span>
														<span class="Q-name-data"><tt class="ml10 c-555"><fmt:formatDate type="date" value="${my.createTime }"></fmt:formatDate></tt></span>
														<span class="replay-num c-555">${my.reNum }/${my.countView}</span>
			 											<span class="Q-name-data mr10"><a class="c-green" href="${ctx}/dis/artail/${my.id}/${my.groupId}" >编辑</a>&nbsp;&nbsp;<a class="c-green" href="javascript:void(0)" onclick="delarticle(${my.id},${my.groupId})">删除</a></span>
													</div>
			 										<div class="fl"><a href="${ctx}/dis/artinfor/${my.id}/${my.groupId}" class="article-q-l-link-txt c-blue">${my.title }</a></div>
												</section>
											</li>
											</c:forEach>
										</ul>
									</div> --%>
									<div class="Q-article-list-2">
										<ul>
											<c:forEach items="${disArticleList }" var="dal">
												<li id="rem${dal.id }">
													<h5 class="clearfix">
														<div class="fr c-bbb fsize12">
															<span class="ml10"><tt class="c-888">最后发表：</tt> <c:if
																	test="${dal.lastReply!=null }">
																	<tt class="c-888">
																		<span class="c-green">${dal.lastReply
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
																title="${dal.title }" style="display: inline">${dal.title }</a>
														</div>
													</h5>
													<div class="QA-desc-2">${dal.summary}</div>
													<div class="mt5 clearfix">
													<span class="fr"><tt class="c-888">评论/查看：</tt><a
															href="${ctx}/dis/artinfor/${dal.id}/${dal.groupId}" title="" class="c-555">${dal.reNum}/${dal.countView}</a></span> <span
															class="fl ml10"><tt class="c-888">作者：</tt><a
															href="${ctx}/p/${dal.cusId}/home" title=""
															class="c-blue">${dal.showName}</a></span>
													</div>
												</li>
											</c:forEach>
										</ul>
									</div>
								</div>
								<%-- <div class="tar mt20"><a href="${ctx}/dis/item/${groupId}" class="comm-btn-orange"><span style="padding: 3px 30px;font-size: 15px;">发表话题</span></a></div> --%>
							</div>

								<!-- 公共分页 开始 -->
								<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
								<!-- 公共分页 结束 -->
								<form action="${ctx}/dis/${userid }/hisart" method="post" id="searchForm">
										<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
										</form>
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
</body>
</html>