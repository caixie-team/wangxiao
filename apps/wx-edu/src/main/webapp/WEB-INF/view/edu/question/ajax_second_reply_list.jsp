<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty replyList}">

</c:if>
<c:if test="${not empty replyList}">
	<div class="mt10 reply-list">
		<section class="review-box mt15">
			<ul>
				<c:forEach items="${replyList}" var="reply">
					<li class="pr">
						<aside class="noter-pic">
							<c:if test="${empty reply.avatar}">
								<img width="50" height="50" src="${ctximg}/static/common/images/user_default.png" class="picImg">
							</c:if>
							<c:if test="${not empty reply.avatar}">
								<c:if test="${fn:contains(reply.avatar,'http' )}">
									<img width="50" height="50" src="${reply.avatar}" class="picImg">
								</c:if>
								<c:if test="${!fn:contains(reply.avatar,'http' )}">
									<img width="50" height="50" src="<%=staticImageServer%>${reply.avatar}" class="picImg">
								</c:if>
							</c:if>
						</aside>
						<div class="of">
							<span class="fl"> <font class="fsize14 c-blue1">${not empty reply.nickname?reply.nickname:not empty reply.email?reply.email:reply.mobile}</font></span>
							<span class="fr c-666 fsize12"><font><fmt:formatDate value="${reply.createTime}" type="both" pattern="yyyy-MM-dd"/></font></span>
						</div>
						<div class="noter-txt mt5">
							<p>${reply.content}</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</section>
		<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />
	</div>
</c:if>