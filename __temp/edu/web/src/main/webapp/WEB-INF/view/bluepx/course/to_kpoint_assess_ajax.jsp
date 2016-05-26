<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${!empty  courseAssessList}">
	<c:forEach items="${courseAssessList }" var="assess">
		<li>
			<aside class="noter-pic">
				<c:if test="${!empty assess.avatar }">
					<img width="50" height="50" alt="" src="<%=staticImageServer%>${assess.avatar }">
				</c:if>
				<c:if test="${empty assess.avatar }">
					<img width="50" height="50" alt="" src="<%=imagesPath%>/static/edu/images/default/default_head.jpg">
				</c:if>
			</aside>
			<div class="of">
				<span class="fl">
					<font class="fsize12 c-blue"> <c:if test="${empty assess.nickname }">
				${assess.email }
				</c:if> <c:if test="${!empty assess.nickname }">
				${assess.nickname }
				</c:if>
					</font><font class="fsize12 c-999 ml5">评论：</font>
				</span>
			</div>
			<div class="noter-txt mt5">
				<p>${assess.content }</p>
			</div>
			<div class="of mt5">
				<span class="fr">
					<font class="fsize12 c-999 ml5"><fmt:formatDate value="${assess.createTime }" type="both" /></font>
				</span>
			</div>
		</li>
	</c:forEach>
</c:if>
<!-- 公共分页 开始 -->
<%-- <jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" /> --%>
	<c:choose>
	   <c:when test="${page.last}">
		
	   </c:when>
	   <c:otherwise>
	   	<div class="lh-list-more"><a title="更多&gt;&gt;" href="javascript:goPageAjax(${page.currentPage+1 })">更多&gt;&gt;</a></div>
	   </c:otherwise>
	</c:choose>
<!-- 公共分页 结束 -->
<c:if test="${empty courseAssessList}">
	<section id="notassessListId" class="comm-tips-1">
		<p>
			<em class="c-tips-2 icon24">&nbsp;</em> <font class="c-999 fsize12 vam">还没有评论，快去抢沙发吧~</font>
		</p>
	</section>
</c:if>
