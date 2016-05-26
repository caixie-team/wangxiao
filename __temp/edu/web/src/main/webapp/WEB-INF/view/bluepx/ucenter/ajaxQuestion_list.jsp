<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty SugSuggestList }">
	<section class="comm-tips-1">
		<p>
			<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">还没有问题！建议你<a
				class="c-orange" title=""
				href="${ctx}/uc/ques/add">去问问题</a></font>
		</p>
	</section>
</c:if>
<c:if test="${!empty SugSuggestList }">
<div id="solveProblems">
	<table class="tab-question-list mt20" width="100%" cellspacing="0"
		cellpadding="0" border="0">
		<thead>
			<tr>
				<th width="10%">&nbsp;</th>
				<th width="48%" align="left">标题</th>
				<th width="11%">被采纳</th>
				<th width="10%">回答数</th>
				<th width="22%">提问时间</th>
			</tr>
		</thead>
		<tbody class="t-q-list-cell">
	<c:forEach items="${SugSuggestList}" var="sug">
			<tr>
                <c:if test="${not empty sug.queryCustomer.avatar}">
                    <td align="center"><img width="40" height="40" title="${sug.queryCustomer.showname}" alt="${sug.queryCustomer.showname}" src="<%=staticImageServer%> ${ sug.queryCustomer.avatar}"></td>
                </c:if>
                <c:if test="${ empty sug.queryCustomer.avatar}">
                    <td align="center"><img width="40" height="40" title="${sug.queryCustomer.showname}" alt="${sug.queryCustomer.showname}" src="${ctximg}/static/sns/pics/user.jpg"></td>
                </c:if>

				<td align="left"><a href="${ctx}/uc/question/info/${sug.id}">${sug.title}</a></td>
				<td align="center"><c:if test="${sug.status==1 }">
						<em class="icon-2-24 cn-Q" title="满意答案">&nbsp;</em>
					</c:if></td>
				<td align="center">${sug.replycount}</td>
				<td align="center"><fmt:formatDate value="${sug.addtime}" type="both"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/view/common/u_ajaxpage.jsp"></jsp:include>
</div>
</c:if>