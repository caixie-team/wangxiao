<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty queryCustomerList}">
	<div class="Prompt">
		<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
		<p class="vam c-555 fsize14 disIb">您还没有拉黑过任何人呢</p>
	</div>
</c:if>
<c:forEach items="${queryCustomerList}" var="cusList">
	<article class="myF-l-box" id="del${cusList.cusId }">
		<div class="F-face">
			<a href="${ctx}/p/${cusList.cusId}/home"> <c:if
					test="${cusList.avatar!=null&&cusList.avatar!='' }">
					<img src="<%=staticImageServer%>${cusList.avatar }"
						width="50" height="50" alt="">
				</c:if> <c:if
					test="${cusList.avatar==null||cusList.avatar=='' }">
					<img src="${ctximg}/static/sns/pics/user.jpg" width="50"
						height="50" alt="">
				</c:if></a>
		</div>
		<ul class="F-info">
			<li><a href="${ctx}/p/${cusList.cusId}/home"
				class="c-blue customerName${cusList.cusId}">
					${cusList.showname}</a></li>
			<li><span class="c-888"> <c:if
						test="${cusList.gender=='0' }">
						<tt class="boy vam">
							<i class="icon12">&nbsp;</i>
						</tt>
					</c:if> <c:if test="${cusList.gender=='1' }">
						<tt class="girl vam">
							<i class="icon12">&nbsp;</i>
						</tt>
					</c:if></span></li>
			<li><span class="c-888"></span><span
				class="c-888 ml10"></span></li>
		</ul>
		<div class="F-intor">
			<%-- 简介： --%>
		</div>
		<div class="clearfix mt5">
			<span class="fr"> <a href="javascript:void(0)" title="移除黑名单"
				onclick="delblackList(${cusList.cusId })" class="F-remove"><i
					class="icon12 mr5">&nbsp;</i>移除黑名单</a>
			</span>
		</div>
		<br /> <span id="remarks${cusList.cusId }" style="display: none;"><input
			type="text" id="remarksContent${cusList.cusId }" /> <input
			value="保存" type="button" onclick="addremarks(${cusList.cusId })" /></span>
	</article>
</c:forEach>
