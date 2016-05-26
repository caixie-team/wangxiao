<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>我加入的小组</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
			<section class="W-main-c fl">
				<div class="W-main-cc">
					<section class="W-main-center">
						<div class="pl20">
							<section class="mt10">
								<header class="comm-title-1">
									<a href="${ctx}/dis/classify" class="fr c-888 mt10 mr10">创建小组&gt;&gt;</a>
									<ul class="clearfix">
										<li class="current"><a href="javascript:void(0)" title="我加入的小组">我加入的小组</a> <span
											class="c-green disIb mt10">共有${page.totalResultSize
												}个小组</span>
											<div class="ct-tabarrow-bg">&nbsp;</div></li>
									</ul>
								</header>
							</section>
							<!-- /挑战信息 -->
							<c:if test="${IsCreate==0 }">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png"
										class="vam disIb">
									<p class="vam c-555 fsize14 disIb">还没有创建小组，快去创建属于自己的小组吧...</p>
								</div>
							</c:if>
							<c:if test="${fn:length(disMyJoinGroupList)==0 }">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png"
										class="vam disIb">
									<p class="vam c-555 fsize14 disIb">还没有加入小组，快去加入小组吧...</p>
								</div>
							</c:if>
							<section class="mt20">
								<ul class="qun-list">
									<c:forEach items="${disMyJoinGroupList }" var="dis">
										<li id="shanchu${dis.id }">
											<section class="clearfix">
												<aside class="fl ">
													<%-- 	<c:if test="${dis.status==0||dis.status==2||dis.status==3 }">
													<span class="qun-face"><a href="javascript:void(0)"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></a></span>
													</c:if> 
													<c:if test="${dis.status==1 }"></c:if>--%>
													<span class="qun-face"><a href="${ctx}/dis/info/${dis.id}"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></a></span>
													
												</aside>
												<div class="fl qun-info-wrap">
											
													<section class="qun-ann-btn">
														<%-- <c:if test="${dis.status==1 }"></c:if> --%>
														<p>
															<a class="browes-qun c-blue" title=""
																href="${ctx}/dis/info/${dis.id}"><i
																class="icon16 mr5">&nbsp;</i>浏览该小组</a>
														</p>
														<c:if test="${dis.transferId==1}">
														<p class="mt10">
															<a class="add-qun c-red" title=""
																href="javascript:void(0)" onclick="disexit(${dis.id})"><i
																class="icon16 mr5">&nbsp;</i>退出该小组</a>
														</p>
														</c:if>
														<c:if test="${dis.type==1}">
														<div class="mt10 pr transferD" style="z-index: 1;" id="transferD${dis.id}">
															<a class="transferBtn transfer-qun c-orange" groupid="${dis.id}"
																title="" href="javascript: void(0)"> <i
																class="icon18"> </i> 转让该小组
															</a>
														</div>
														</c:if>
													<%-- 	
														<c:if test="${dis.status==2 }">
													<p><a class="add-qun c-red" title="" href="javascript:void(0)"><i class="icon16 mr5">&nbsp;</i>小组关闭</a></p>
													</c:if> --%>
													</section>
														<dl class="qun-info-txt">
													<%-- <c:if test="${dis.status==1}"></c:if> --%>
														<dt><span class="c-555 fsize14">名称：</span></dt>
														<dd><a href="${ctx}/dis/info/${dis.id}"><span class="c-blue fsize14">${dis.name}</span></a></dd>
														
														<%-- <c:if test="${dis.status==3||dis.status==2||dis.status==0 }">
														<dt><span class="c-555 fsize14">名称：</span></dt>
														<dd><a href="javascript:void(0)"><span class="c-blue fsize14">${dis.name}</span></a></dd>
														</c:if> --%>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">成员：</span>
														</dt>
														<dd>
															<span class="c-555 fsize14">${dis.memberNum}人</span>
														</dd>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">创建：</span>
														</dt>
														<dd>
															<span class="c-555 fsize14"><fmt:formatDate
																	value="${dis.createTime}" type="both" ></fmt:formatDate></span>
														</dd>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">组长：</span>
														</dt>
														<dd>
															<a href="${ctx}/p/${dis.cusId}/home"><span class="c-blue fsize14">${dis.showName}</span></a>
														</dd>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">话题数：</span>
														</dt>
														<c:if test="${dis.status==1}"></c:if>
														<dd>
														<a href="${ctx}/dis/art/${dis.id}"><span class="c-blue fsize14">${dis.articleCounts}</span></a>
														</dd>
														<%-- <c:if test="${dis.status==3||dis.status==2||dis.status==0 }">
														<span class="c-blue fsize14">${dis.articleCounts}</span>
														</c:if> --%>
													</dl>
												</div>
											</section>
										</li>
									</c:forEach>
								</ul>
							</section>
						</div>


						<!-- 公共分页 开始 -->
						<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
						<!-- 公共分页 结束 -->
						<form action="${ctx}/dis/join" name="searchForm"
							id="searchForm" method="post">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage"
								value="${page.currentPage}" />
						</form>
					</section>
					<!-- right 开始 -->
					<jsp:include page="/WEB-INF/view/sns/discuss/right.jsp"></jsp:include>
				</div>
			</section>
			<!-- 主体区域 -->
</body>
</html>