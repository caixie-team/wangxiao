<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>博客</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/blog/blog.js?v=<%=version%>"></script>
	<script type="text/javascript">
	function delblog(blogId){
		dialog_sns("确定删除？",2);
		$(".queding2").attr("href","javascript:del("+blogId+")");
	}
	var showname = '${userExpandDto.showname}';
	var avatar = '${userExpandDto.avatar}';
	var cusId = '${userExpandDto.id}';
	$(function(){
		if(avatar!=null&&avatar!=""){
			$(".avatar").attr("src",staticImageServer+""+avatar+"");
		}else{
			$(".avatar").attr("src",imagesPath+"/static/sns/pics/user.jpg");
		}
		$('.avatarhref').attr('href',''+baselocation +'/p/'+cusId+'/home');//修改链接
		$('.avatarhref').attr('title',showname);//修改title
		$('.cusshowname').html(showname);//用户名
		$('.cusshowname').attr('title',showname);//修改title
		$('.cusshowname').attr('href',''+baselocation +'/p/'+cusId+'/home');//修改链接
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
										<li class="current"><a href="${ctx}/blog/rele"
											title="博文">博文</a> <span class="c-green disIb mt10">共有${page.totalResultSize}篇博文</span>
											<div class="ct-tabarrow-bg">&nbsp;</div></li>
									</ul>
								</header>
							</section>
							<!-- /挑战信息 -->
							<c:if test="${page.totalResultSize==0 }">
										<div class="Prompt">
											<img src="${ctximg}/static/sns/images/tishi.png"
												class="vam disIb">
												<c:if test="${loginId==userid }">
											<p class="vam c-555 fsize14 disIb">您还没有博文呢。</p>
												</c:if>
												<c:if test="${loginId!=userid }">
											<p class="vam c-555 fsize14 disIb">他还没有博文呢。</p>
												</c:if>
										</div>
									</c:if>
							<div class="Q-article-list-2">
								<ul>
								<c:forEach items="${blogBlogList }" var="blog" >
									<li id="rem${blog.id }">
										<h5 class="clearfix">
											<div class="fr c-bbb fsize12">
												<span class="ml10"><tt class="c-888">最后发表：</tt>
												<c:if test="${blog.replyName!=null }">
												<tt class="c-888"><span class="c-green">${blog.replyName }</span> <fmt:formatDate type="date" value="${blog.updateTime}" ></fmt:formatDate></tt>
												</c:if>
												<c:if test="${blog.replyName==null }">
												<tt class="c-888"><span class="c-green">${blog.showName }</span> <fmt:formatDate type="date" value="${blog.addTime}" ></fmt:formatDate></tt>
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
												<a href="${ctx}/blog/info/${blog.id}" class="c-blue article-q-l-link-txt" title="${blog.title }">
												${blog.title }</a>
											</div>
										</h5>
										<div class="QA-desc-2">
											${blog.shortContent}
										</div>
										<div class="mt5 clearfix">
											<span class="fr"><tt class="c-888">评论/查看：</tt><a href="javascript:void(0)" title="" class="c-555">${blog.replyCount}/${blog.viewCount}</a></span>
											<span class="fl ml10"><tt class="c-888">作者：</tt><a href="${ctx}/p/${blog.cusId}/home" title="" class="c-blue">${blog.showName}</a></span> 
										</div>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>


						<!-- 公共分页 开始 -->
						<!-- <div class="pagination pagination-large">
								<ul>
									</ul>
							</div> -->
							<c:if test="${not empty  friend||userid==loginId}">
						<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
						</c:if>


						<form action="${ctx}/p/${userid}/blog" method="post"
							id="searchForm" method="post">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage"
								value="${page.currentPage}" />
						</form>
						<!-- 公共分页 结束 -->
					</section>
					<section class="W-main-right">
						<div class="pl20">
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l">一周发表文章/Top10</span>
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
					</section>
				</div>
			</section>
			<!-- 主体区域 -->
</body>
</html>