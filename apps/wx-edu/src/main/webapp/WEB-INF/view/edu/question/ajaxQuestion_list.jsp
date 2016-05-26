<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty SugSuggestList }">
	<div class="mt40">
		<!-- /无数据提示 开始-->
		<section class="no-data-wrap">
			<em class="no-data-ico">&nbsp;</em> <span
				class="c-666 fsize14 ml10 vam">还没有问题！建议你
				<a
				class="c-orange" title=""
				href="${ctx}/front/questionAdd">去问问题</a>
				</span>
		</section>
		<!-- /无数据提示 结束-->
	</div>
</c:if>
<c:if test="${!empty SugSuggestList }">
<div id="solveProblems">
<ul>
<c:forEach items="${SugSuggestList}" var="sug">
	<li>
		<aside class="q-head-pic">
		 <c:if test="${not empty sug.queryCustomer.avatar}">
			 <c:if test="${fn:contains(sug.queryCustomer.avatar, 'http')}">
				 <img src="${sug.queryCustomer.avatar}" width="60" height="60">
			 </c:if>
			 <c:if test="${!fn:contains(sug.queryCustomer.avatar, 'http')}">
				 <img src="<%=staticImageServer %>${sug.queryCustomer.avatar}" width="60" height="60">
			 </c:if>
				<p class="hLh30 txtOf p-h-name">
					<span class="c-999"> ${sug.queryCustomer.showname}</span>
				</p>
         </c:if>
         <c:if test="${ empty sug.queryCustomer.avatar}">
        		<img alt="" src="${ctx}/static/nxb/web/img/avatar-boy.gif">
				<p class="hLh30 txtOf">
					<span class="c-999"> ${sug.queryCustomer.showname}</span>
				</p>
         </c:if>
		</aside>
		<section class="q-txt-box">
			<h3 class="hLh30 txtOf">
				<a class="fsize16 c-333 vam" title="" href="${ctx}/front/question/info/${sug.id}">${sug.title}</a>
			</h3>
			<h3 class="hLh30 txtOf mt5 ques-return">
				<span class="fsize14 c-999 vam">
					<c:choose>
						<c:when test="${sug.replycount>0}">
						<tt	class=" f-fM mr5">最新回复：</tt>
								${sug.replyContent }
						</c:when>
					</c:choose>
				</span>
			</h3>
			<div title="" href="" class="replyBrowseNum">
				<span class="c-999 vam"><%--<fmt:formatDate value="${sug.addtime}" type="both" />--%>${sug.modelStr}</span>
				<section class="of ques-desc">
					<span title="浏览数量" class="c-999 vam disIb"> <em
						class="icon14 q-view mr5 vam"></em> <tt class="vam f-fM">${sug.browseNum}</tt>
					</span> <span title="回复数量" class="disIb c-999 ml10"> <em
						class="icon14 q-review mr5"></em> <tt class="vam f-fM">${sug.replycount}</tt>
					</span>
				</section>
			</div>
		</section>
	</li>
	</c:forEach>
</ul>
		<div class="mt40">
			<section>
				<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />
			</section>
		</div>
</div>
</c:if>