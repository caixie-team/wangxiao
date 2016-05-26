<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
	<c:if test="${fn:length(queryBlogAndReplyList)==0 }">
			<div class="Prompt">
				<img src="${ctximg}/static/sns/images/tishi.png"
					class="vam disIb">
				<p class="vam c-555 fsize14 disIb">还没有博文记录，快去写自己的博文吧。</p>
			</div>
		</c:if>
			<ul>
			<c:forEach items="${queryBlogAndReplyList }" var="blog">
				<li id="rem${blog.id }">
					<h5 class="clearfix">
						<div class="fr c-bbb fsize12">
							<span class="ml10"><tt class="c-888">最后发表：</tt>
							<c:if test="${blog.replyName!=null }">
							<tt class="c-888"><span class="c-green">${blog.replyName }</span>&nbsp;${blog.upmodelStr}</tt>
							</c:if>
							<c:if test="${blog.replyName==null }">
							<tt class="c-888"><span class="c-green">${blog.showName }</span>&nbsp;${blog.modelStr}</tt>
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
							<a href="${ctx}/blog/info/${blog.blogId}" class="c-blue article-q-l-link-txt" title="${blog.title }">${blog.title }</a>
						</div>
					</h5>
					<div class="QA-desc-2">
						${blog.summary}
					</div>
					<div class="mt5 clearfix">
						<c:if test="${type!=3 }">
						<span class="fr ml10"><tt class="c-888">评论/查看：</tt><a href="${ctx}/blog/info/${blog.blogId}" title="" class="c-555">${blog.replyCount}/${blog.viewCount}</a></span>
						</c:if>
						<c:if test="${type==3}">
						<span class="fr"><a class="c-green" href="${ctx}/blog/edit/${blog.id}">编辑</a>&nbsp;
						<a class="c-green" href="javascript:void(0)" onclick="delblog(${blog.id})">删除</a></span>
						</c:if>
						<span class="fl ml10"><tt class="c-888">作者：</tt><a href="${ctx}/p/${blog.cusId}/home" title="" class="c-blue">${blog.showName}</a></span> 
						
					</div>
				</li>
				</c:forEach>
			</ul>
	<!-- 公共分页 开始 -->
	<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"></jsp:include>
</html>