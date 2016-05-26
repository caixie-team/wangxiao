<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
		<c:forEach items="${sugSuggestList }" var="sug">
			<li>
				<aside class="noter-pic">
					<c:if test="${empty sug.queryCustomer.avatar}">
					<img width="50" height="50" src="<%=imagesPath%>/static/edu/images/default/default_head.jpg">
					</c:if>
					<c:if test="${not empty sug.queryCustomer.avatar}">
					<img width="50" height="50" src="<%=staticImageServer%>${sug.queryCustomer.avatar}">
					</c:if>
				</aside>
				<div class="of">
					<span class="fl">
						<font class="fsize12 c-blue">${sug.showName}</font>
						<font class="fsize12 c-999 ml5">提出问题：</font>
					</span>
				</div>
				<div class="noter-txt mt5">
					<p>${sug.content}</p>
				</div>
				<div class="of mt5">
					<span class="fr">
						<font class="fsize12 c-999 ml5"><fmt:formatDate type="both" value="${sug.addTime}"/></font>
					</span>
					<span class="fl">
						<a class="noter-dy vam" title="回答" id="${sug.id}" href="javascript:void(0)">
							<em class="icon18">&nbsp;</em>
							(
							<font id="reCount${sug.id}">${sug.replyCount}</font>
							)
						</a>
					</span>
				</div>
				<div class="n-reply" id="rePrlblemId${sug.id}"></div>
			</li>
		</c:forEach>
		<c:if test="${empty sugSuggestList }">
			<section class="comment-question">
				<dl id="dayiListId">
					<section class="comm-tips-1">
						<p>
							<em class="vam c-tips-1">&nbsp;</em>
							<font class="c-999 fsize12 vam">还没有相关问答，快来抢沙发！</font>
						</p>
					</section>
				</dl>
			</section>
		</c:if>
		<c:choose>
		   <c:when test="${page.last}">
			 
		   </c:when>
		   <c:otherwise>
		   	<div class="lh-list-more"><a title="更多&gt;&gt;" href="javascript:goPageAjax(${page.currentPage+1 })">更多&gt;&gt;</a></div>
		   </c:otherwise>
		</c:choose>
<%-- <section class="mt20">
	<div class="pagination pagination-large tac">
		<!-- 公共分页 开始 -->
		<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp" />
		<!-- 公共分页 结束 -->
	</div>
</section> --%>