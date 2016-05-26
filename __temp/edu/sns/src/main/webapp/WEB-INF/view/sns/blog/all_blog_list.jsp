<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
<title>全部博文</title>
<script type="text/javascript" src="${ctximg}/static/sns/js/blog/blog.js?v=<%=version%>"></script>
<script type="text/javascript">
$(function() {
	initblog();
});
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
							<a href="${ctx}/blog/rele" class="fr c-888 mt10 mr10">发表博文&gt;&gt;</a>
							<ul class="clearfix">
								<li class="current"><a href="javascript:void(0)" title="博文">博文</a> 
									<div class="ct-tabarrow-bg">&nbsp;</div></li>
							</ul>
						</header>
						<div class="tab-nosep mt10">
								<ol class="clearfix">
									<li class="all"><a title="全部" href="javascript:all();">全部</a></li>
									<li class="hot"><a title="热门博文" href="javascript:hot();">热门博文</a></li>
									<li class="my"><a title="我的博文" href="javascript:my();">我的博文</a></li>
									<li class="attention"><a title="我关注的" href="javascript:attentionajax();">我关注的</a></li>
									<li class="most"><a title="评论最多" href="javascript:most();">评论最多</a></li>
								</ol>
							</div>
					</section>
					
				<div class="Q-article-list-2 ajaxHtml">
						
				</div>
				</div>
					
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
											<input type="hidden"   name="page.currentPage" value="1"/>
											<a href="javascript:searchblog()"  class="comm-btn-green"><span>搜索</span></a>
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
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l">一周发表博文/Top10</span>
									</section>
									<section class="cj-comment-list">
										<c:forEach items="${BlogBlogListWeek }" var="blogWeek" varStatus="index">
										<dl class="clearfix">
										<c:choose>
											 <c:when test="${index.count==1}">
											 	<dt><span class="cjc-1"></span></dt>
											 </c:when>
											 <c:when test="${index.count==2}">
											 	<dt><span class="cjc-2"></span></dt>
											 </c:when>
											 <c:when test="${index.count==3}">
											 	<dt><span class="cjc-3"></span></dt>
											 </c:when>
											 <c:otherwise>
											 	<dt><span>${index.count }</span></dt>
											 </c:otherwise>
										</c:choose>
											<dd>
												<div class="cj-c-txt">
													<a href="${ctx}/blog/info/${blogWeek.id}" title="${blogWeek.title }" class="c-blue">${blogWeek.title }</a>
												</div>
												<%-- <div class="cj-c-desc">
													${faclt.summary }
												</div> --%>
												<div class="clearfix mt5">
													<%-- <span class="fr c-888">${faclt.createTime }</span> --%>
													<span class="fl c-888"><i class="icon12 oComment-icon mr5">&nbsp;</i>回复数：${blogWeek.replyCount }</span>
												</div>
											</dd>
										</dl>
										</c:forEach>
									</section>
								</div>
							</div>
						</div>
					</section>
				</div>
			</section>
			<!-- 主体区域 -->
</body>
</html>