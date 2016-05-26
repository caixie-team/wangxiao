<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>分类博文</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/blog/blog.js?v=<%=version%>"></script>
</head>
<body class="W-body">
	<!-- 主体 开始-->
			<!-- 左侧目录区域 -->
			<section class="W-main-c fl">
				<div class="W-main-cc">
					<section class="W-main-center">
						<div class="pl20">
							<section class="mt10">
								<header class="comm-title-1">
									<a href="${ctx}/blog/rele" class="fr c-888 mt10 mr10">发表博文&gt;&gt;</a>
									<ul class="clearfix">
										<li class="current"><a href="javascript:void(0)" title="分类博文">分类博文</a> <span
											class="c-green disIb mt10">共有${page.totalResultSize}篇博文</span>
											<div class="ct-tabarrow-bg">&nbsp;</div></li>
									</ul>
								</header>
							</section>
							<!-- /挑战信息 -->
							<c:if test="${fn:length(queryBlogAndReplyList)==0 }">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png"
										class="vam disIb">
									<p class="vam c-555 fsize14 disIb">该分类下还没有博文记录，快去写自己的博文吧。</p>
								</div>
							</c:if>
						<div class="Q-article-list-2">
								<ul>
								<c:forEach items="${queryBlogAndReplyList }" var="blog">
									<li>
										<h5 class="clearfix">
											<div class="fr c-bbb fsize12">
												<span class="ml10"><tt class="c-888">最后发表：</tt>
												<c:if test="${blog.replyName!=null }">
												<tt class="c-888"><span class="c-green">${blog.replyName }</span> <fmt:formatDate type="date" value="${blog.updateTime}" ></fmt:formatDate></tt>
												</c:if>
												<c:if test="${blog.replyName==null }">
												<tt class="c-888"><span class="c-green">${blog.showName }</span>  <fmt:formatDate type="date" value="${blog.addTime}" ></fmt:formatDate></tt>
												</c:if>
												</span>
											</div>
											<div class="fl">
												<c:if test="${blog.selType==1 }">
												<tt class="icon22 yuan">&nbsp;</tt>
												</c:if>
												<c:if test="${blog.selType==2 }">
												<tt class="icon22 zhuan">&nbsp;</tt>
												</c:if>
												<a href="${ctx}/blog/info/${blog.blogId}" class="c-blue article-q-l-link-txt" title="${blog.title }" style="display: inline">${blog.title }</a>
											</div>
										</h5>
										<div class="QA-desc-2">
											${blog.shortContent}
										</div>
										<div class="mt5 clearfix">
											<span class="fr"><tt class="c-888">评论/查看：</tt><a href="${ctx}/blog/info/${blog.blogId}" title="" class="c-555">${blog.replyCount}/${blog.viewCount}</a></span>
											<span class="fl ml10"><tt class="c-888">作者：</tt><a href="${ctx}/p/${blog.cusId}/home" title="" class="c-blue">${blog.showName}</a></span> 
										</div>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>


						<!-- 公共分页 开始 -->

						<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>

						<!-- 公共分页 结束 -->
						<form action="${ctx}/blog/art/${classifyId }" method="post"
							id="searchForm" name="searchForm">
							<input name="classifyId" type="hidden" value="${classifyId }">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage"
								value="${page.currentPage}" />
						</form>
					</section>
					<section class="W-main-right">
						<div class="pl20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l">博文搜索&nbsp;<i class="gt-btn"></i></span>
								</section>
								<section class="mt20 pr10">
										<form action="${ctx}/search" method="post" id="searchblogform">
											<input type="hidden" name="search.tab"   value="blog">
											<input type="text" name="search.keyword"  id="searchblog" class="boke-search-inp">
											<a href="javascript:searchblog()" class="comm-btn-green"><span>搜索</span></a>
										</form>
								</section>
							</div>
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l">博文分类</span>
								</section>
								<section class="mt20 pr10">
									<ul class="boke-sort-list">
										<c:forEach items="${artClassifyList }" var="art">
											<li><a href="${ctx}/blog/art/${art.artId}"
												title="" class="fsize14 c-orange">${art.name }</a><span
												class="c-green">（${art.blogNum }）</span></li>
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