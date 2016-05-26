<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>分类小组</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
	<script type="text/javascript">
	</script>
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
											<li class="current">
												<a href="javascript:void(0)" title="分类小组">分类小组</a>
												<span class="c-green disIb mt10">共有${page.totalResultSize }个小组</span>
												<div class="ct-tabarrow-bg" >&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<!-- /挑战信息 -->
								<c:if test="${fn:length(disGroupList)==0}">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
									<p class="vam c-555 fsize14 disIb">该分类下没有相关小组信息...</p>
								</div>
								</c:if>
								<section class="mt20">
									<ul class="qun-list">
									<c:forEach items="${disGroupList }" var="dis">
										<li>
											<section class="clearfix">
												<aside class="fl ">
													<span class="qun-face"><a href="${ctx}/dis/info/${dis.id}"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></a></span>
												</aside>
												<div class="fl qun-info-wrap">
													<section class="qun-ann-btn">
														<p><a class="browes-qun c-blue" title="" href="${ctx}/dis/info/${dis.id}"><i class="icon16 mr5">&nbsp;</i>浏览该小组</a></p>
														<p class="mt10"><a class="add-qun c-red" title="加入该小组" href="javascript:addGroup(${dis.id})"><i class="icon16 mr5">&nbsp;</i>加入该小组</a></p>
													</section>
													<dl class="qun-info-txt">
														<dt><span class="c-555 fsize14">名称：</span></dt>
														<dd><a href="${ctx}/dis/info/${dis.id}" class="c-blue fsize14">${dis.name}</a></dd>
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
														<dd><a href="${ctx}/p/${dis.cusId}/home" class="c-blue fsize14">${dis.showName}</a></dd>
													</dl>
													<dl class="qun-info-txt">
														<dt><span class="c-555 fsize14">话题数：</span></dt>
														<dd><a href="${ctx}/dis/art/${dis.id}" class="c-blue fsize14">${dis.articleCounts}</a></dd>
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
							<form action="${ctx}/dis/classifygroup/${classifyId }" name="searchForm" id="searchForm" method="post">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
							</form>
						</section>
						<section class="W-main-right">
							<div class="pl20">
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l">小组搜索&nbsp;<i class="gt-btn"></i></span>
									</section>
									<section class="mt20 pr10">
										<form action="${ctx}/search" method="post" id="dissearchform"> 
											<input type="hidden" name="search.tab"   value="dis">
											<input type="text" name="search.keyword"  id="searchdis" class="boke-search-inp">
											<input type="hidden"   name="page.currentPage" value="1"/>
											<a href="javascript:dissearch()" class="comm-btn-orange"><span>搜索</span></a>
										</form>
									</section>
								</div>
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l">小组分类</span>
									</section>
									<section class="mt20 pr10">
										<ul class="boke-sort-list">
										<c:forEach items="${disGroupClassifyList }" var="disclassify">
											<li><a href="${ctx}/dis/classifygroup/${disclassify.id}" title="" class="fsize14 c-orange">${disclassify.name }</a><span class="c-green">（${disclassify.groupNum }）</span></li>
											</c:forEach>
										</ul>
									</section>
								</div>
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
</body>
</html>