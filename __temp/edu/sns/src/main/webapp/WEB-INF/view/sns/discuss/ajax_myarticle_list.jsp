<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${fn:length(myDisArticle)==0}">
	<div class="Prompt">
		<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
		<p class="vam c-555 fsize14 disIb">还没有小组话题，快去创建自己的小组话题吧...</p>
	</div>
</c:if>
<div class="Q-article-list-2">
	<ul>
		<c:forEach items="${myDisArticle }" var="dal">
			<li id="rem${dal.id }">
				<h5 class="clearfix">
					<div class="fr c-bbb fsize12">
						<span class="ml10"><tt class="c-888">最后发表：</tt> <c:if
								test="${dal.lastReply!=null }">
								<tt class="c-888">
									<span class="c-green">${dal.lastReply}</span>
									&nbsp;${dal.lastStr}
								</tt>
							</c:if> <c:if test="${dal.lastReply==null }">
								<tt class="c-888">
									<span class="c-green">${dal.showName}</span>
								&nbsp;${dal.modelStr}
								</tt>
							</c:if> </span>
					</div>
					<div class="likes">
						<c:if test="${dal.selType==0 }">
											${dal.favorCount }<br>
											喜欢
											</c:if>
						<c:if test="${dal.selType==1}">
											${dal.favorCount }<br>
											参加
											</c:if>
					</div>
					<div class="fl">
						<%-- 	<c:if test="${dal.selType==0 }">
												<tt class="icon22 yuan">&nbsp;</tt>
												</c:if>
												<c:if test="${dal.selType==1 }">
												<tt class="icon22 zhuan">&nbsp;</tt>
												</c:if> --%>
						<a href="${ctx}/dis/artinfor/${dal.id}/${dal.groupId}"
							class="c-blue article-q-l-link-txt" title="${dal.title }"
							style="display: inline">${dal.title }</a>
					</div>
				</h5>
				<div class="QA-desc-2">${dal.summary}</div>
				<div class="mt5 clearfix">
					<span class="fr"><a class="c-green"
						href="${ctx}/dis/artail/${dal.id}/${dal.groupId}">编辑</a>&nbsp;&nbsp;<a
						class="c-green" href="javascript:void(0)"
						onclick="delarticle(${dal.id},${dal.groupId})">删除</a></span> <span
						class="fl ml10"><tt class="c-888">作者：</tt><a
						href="${ctx}/p/${dal.cusId}/home" title=""
						class="c-blue">${dal.showName}</a></span>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>
<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"></jsp:include>			