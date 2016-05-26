<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>我管理的小组</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc" style="overflow: visible;padding-bottom: 60px;">
						<section class="W-main-center" style="overflow: visible;">
							<div class="pl20">
								<section class="mt10">
									<header class="comm-title-1">
										<a href="${ctx}/dis/classify" class="fr c-888 mt10 mr10">创建小组&gt;&gt;</a>
										<ul class="clearfix">
											<li class="current">
												<a href="javascript:void(0)" title="我管理的小组">我管理的小组</a>
												<span class="c-green disIb mt10">共有${fn:length(disMyGroupList)}个小组,还能创建${20-count}个小组</span>
												<div class="ct-tabarrow-bg" >&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<!-- /挑战信息 -->
								<c:if test="${fn:length(disMyGroupList)==0 }">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
									<p class="vam c-555 fsize14 disIb">还没有创建小组，快去创建属于自己的小组吧...</p>
								</div>
								</c:if>
								<section class="mt20">
									<ul class="qun-list">
									<c:forEach items="${disMyGroupList }" var="dis">
									<input type="hidden" value="${dis.id}">
										<li id="shanchu${dis.id }">
											<section class="clearfix">
												<aside class="fl ">
												<%-- <c:if test="${dis.status==2||dis.status==0||dis.status==3 }">
													<span class="qun-face"><a href="javascript:void(0)"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></a></span>
													</c:if> --%>
													<c:if test="${dis.status==1 }"></c:if>
													<span class="qun-face"><a href="${ctx}/dis/info/${dis.id}"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></a></span>
													
												</aside>
												<div class="fl qun-info-wrap">
													<section class="qun-ann-btn">
													<%-- <c:if test="${dis.status==0 }">
													<p><a class="add-qun c-red" title="" href="javascript:void(0)"><i class="icon16 mr5">&nbsp;</i>待审批</a></p>
													</c:if> --%>
													<%-- <c:if test="${dis.status==1 }"></c:if> --%>
														<p><a class="browes-qun c-blue" title="" href="${ctx}/dis/info/${dis.id}"><i class="icon16 mr5">&nbsp;</i>浏览该小组</a></p>
														<c:if test="${dis.transferId==0 }">
														<div class="mt10 pr transferD" style="z-index: 1;" id="transferD${dis.id}">
															<a class="transferBtn transfer-qun c-orange" href="javascript: void(0)" title="" groupId="${dis.id}">
															<i class="icon18"> </i>
															转让该小组
															</a>
															</div>
															</c:if>
														
													<%-- <c:if test="${dis.status==2 }">
													<p><a class="add-qun c-red" title="" href="javascript:void(0)"><i class="icon16 mr5">&nbsp;</i>小组关闭</a></p>
													</c:if> --%>
													<%-- <c:if test="${dis.status==3||dis.status==1}"></c:if> --%>
													<div class="mt10 pr">
													<p><a class="browes-qun c-blue" title="" href="${ctx}/dis/disedit/${dis.id}"><i class="icon16 mr5">&nbsp;</i>修改</a></p>
													</div>
													
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
														<dt><span class="c-555 fsize14">成员：</span></dt>
														<dd><span class="c-555 fsize14">${dis.memberNum}人</span></dd>
													</dl>
													<dl class="qun-info-txt">
														<dt><span class="c-555 fsize14">创建：</span></dt>
														<dd><span class="c-555 fsize14"><fmt:formatDate value="${dis.createTime}" type="both" ></fmt:formatDate></span></dd>
													</dl>
													<dl class="qun-info-txt">
														<dt><span class="c-555 fsize14">组长：</span></dt>
														<dd><a href="${ctx}/p/${dis.cusId}/home" ><span class="c-blue fsize14">${dis.showName}</span></a></dd>
													</dl>
													<dl class="qun-info-txt">
														<dt><span class="c-555 fsize14">话题数：</span></dt>
														<dd><a href="${ctx}/dis/art/${dis.id}"><span class="c-blue fsize14">${dis.articleCounts}</span></a></dd>
														
														<%-- <c:if test="${dis.status==3||dis.status==2||dis.status==0 }">
														<dt><span class="c-555 fsize14">话题数：</span></dt>
														<dd><span class="c-blue fsize14">${dis.articleCounts}</span></dd>
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
							<%-- <jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include> --%>
							<!-- 公共分页 结束 -->

						</section>
						<!-- right 开始 -->
							<jsp:include page="/WEB-INF/view/sns/discuss/right.jsp"></jsp:include>
					</div>
				</section>
				<!-- 主体区域 -->
</body>
</html>