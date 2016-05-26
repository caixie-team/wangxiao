<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${empty SugSuggestList}">
	<div class="Prompt">
		<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
		<p class="vam c-555 fsize14 disIb">还没有问题呢。</p>
	</div>
</c:if>
<c:forEach items="${SugSuggestList}" var="sslst">
	<div class="Q-article-list-2">
		<ul>
			<li>
				<aside class="DT-face DT-buu">
                    <c:if test="${empty sslst.queryCustomer.avatar}">
                        <a title="${sslst.queryCustomer.showname}" target="_blank" href="/p/${sslst.cusId }/home"><img width="50" height="50" alt="" class="dis" src="<%=imagesPath%>/static/common/images/user_default.jpg"></a>
                    </c:if>
                    <c:if test="${not empty sslst.queryCustomer.avatar}">
                        <a title="${sslst.queryCustomer.showname}" target="_blank" href="/p/${sslst.cusId }/home"><img width="50" height="50" alt="" class="dis" src="<%=staticImageServer%>${sslst.queryCustomer.avatar}"></a>
                    </c:if>
				</aside>
				<h5 class="of">
					<div class="fl">
						<tt class="icon22 wenIco"> </tt>
						<a
							href="javascript:window.location.href='${ctx}/sug/info/${sslst.id }'"
							class="c-blue article-q-l-link-txt" style="display: inline"
							title="${sslst.title}">${sslst.title}</a>
					</div>
				</h5>
				<div class="QA-desc-2">${sslst.summary }</div>
				<div class="mt10 clearfix">
					<span class="fr"><tt class="c-888">评论/查看：</tt><a
						href="javascript:window.location.href='${ctx}/sug/info/${sslst.id }'"
						title="评论/查看：${sslst.replycount}&nbsp;/&nbsp;${sslst.browseNum }"
						class="c-555">${sslst.replycount}&nbsp;/&nbsp;${sslst.browseNum
							}</a></span> <span class="fl ml10"><tt class="c-888">提问者：</tt><a
						href="${ctx}/p/${sslst.cusId }/home"
						title="${sslst.queryCustomer.showname}" class="c-blue">${sslst.queryCustomer.showname}</a></span>
				</div>
			</li>
		</ul>
	</div>
</c:forEach>
<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"/>