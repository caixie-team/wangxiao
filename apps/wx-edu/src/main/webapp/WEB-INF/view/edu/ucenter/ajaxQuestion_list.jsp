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
<div id="solveProblems">
<c:if test="${!empty SugSuggestList }">
	<!-- pc端 我的问题列表  开始-->
	<section class="g-zl-tab">
		<dl class="g-zl-table">
			<dt class="g-zl-thead">
				<ol class="g-zl-th th-col3-4 log-operation">
					<li style="width: 55%">
						<div class="th-td-pl10">
							<span>标题</span>
						</div>
					</li>
					<li class="th-col-3-4-und">
						<div class="th-td-pl10">
							<span>
							<c:choose>
								<c:when test="${flag=='mysug'}">已采纳</c:when>
								<c:otherwise>被采纳</c:otherwise>
							</c:choose>
							</span>
						</div>
					</li>
					<li><div class="th-td-pl10">
							<span>回答数</span>
						</div></li>
					<li style="width: 23%">
						<div class="th-td-pl10">
							<span>提问时间</span>
						</div>
					</li>
				</ol>
				<div class="clear"></div>
			</dt>
			<dd class="g-zl-tbody log-operation">
				<ol class="g-zl-td td-col3-4">
				<c:forEach items="${SugSuggestList}" var="sug">
					<li style="width: 55%">
						<div class="th-td-pl10">
							<a title="" class="txtOf t-q-pic" href="${ctx}/front/question/info/${sug.id}">
							<c:if test="${not empty sug.queryCustomer.avatar}">
								<img width="40" height="40" alt="${sug.queryCustomer.showname}" class="vam "
								src="<%=staticImageServer%> ${ sug.queryCustomer.avatar}"> 
							</c:if>
							<c:if test="${ empty sug.queryCustomer.avatar}">
			               		<img width="40" height="40" alt="${sug.queryCustomer.showname}" class="vam " src="${ctximg}/static/common/images/user_default.png">
			                </c:if>
								<span  title="${sug.title}" class="ml10">${sug.title}</span>
								
							</a>
						</div>
					</li>
					<li class="th-col-3-4-und">
						<div class="th-td-pl10 txtOf">
							<c:if test="${sug.status==1 }">
								<em title="满意答案" class="icon24 cn-Q">&nbsp;</em>
							</c:if>
						</div>
					</li>
					<li>
						<div class="th-td-pl10">
							<span>${sug.replycount}</span>
						</div>
					</li>
					<li style="width: 23%">
						<div class="th-td-pl10">
							<span><fmt:formatDate value="${sug.addtime}" type="both"/></span>
						</div>
					</li>
					</c:forEach>
				</ol>
				<div class="clear"></div>
			</dd>
		</dl>
	</section>
	<!-- pc端 我的问题列表  结束-->
	<!-- 移动端 我的问题列表  开始-->
	<section class="phone-u-question">
		<dl class="mt15">
		<c:forEach items="${SugSuggestList}" var="sug">
			<dt class="clearfix">
				<span class="fl c-666">提问时间：<fmt:formatDate value="${sug.addtime}" type="both"/></span> <span
					class="fr c-master">回答数：${sug.replycount}</span>
			</dt>
			<dd>
				<ol class="g-zl-td td-col3-4">
					<li style="width: 75%">
						<div>
							<a title="" class="txtOf t-q-pic" href="${ctx}/front/question/info/${sug.id}">
			                <c:if test="${not empty sug.queryCustomer.avatar}">
			                <img width="40" height="40" alt="${sug.queryCustomer.showname}" class="vam "
								src="<%=staticImageServer%> ${ sug.queryCustomer.avatar}"> 
			                </c:if>
			                <c:if test="${empty sug.queryCustomer.avatar}">
								<img width="40" height="40" alt="${sug.queryCustomer.showname}" class="vam "
								src="${ctximg}/static/nxb/web/img/avatar-boy.gif"> 
							</c:if>
								<span title="${sug.title}"
								class="ml10">${sug.title}</span>
							</a>
						</div>
					</li>
					<li style="width: 25%;">
						<div class="th-td-pl10 txtOf">
						<c:if test="${sug.status==1 }">
							<span class="c-333">被采纳</span>
						</c:if>
						</div>
					</li>
				</ol>
				<div class="clear"></div>
			</dd>
			</c:forEach>
		</dl>
	</section>
	</c:if>
	<!-- 移动端 我的问题列表  结束-->
</div>