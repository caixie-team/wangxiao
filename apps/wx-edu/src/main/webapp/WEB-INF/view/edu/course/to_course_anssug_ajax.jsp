<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<section class="lh-bj-list pr mt20 replyhtml">
	<ul>
		<li class="unBr">
			<aside class="noter-pic">
				<c:if test="${not empty userImg}">
					<c:if test="${fn:contains(userImg,'http' )}">
						<img width="50" height="50" src="${userImg}" class="picImg">
					</c:if>
					<c:if test="${!fn:contains(userImg,'http' )}">
						<img width="50" height="50" src="<%=staticImageServer%>${userImg}" class="picImg">
					</c:if>
				</c:if>
				<c:if test="${empty userImg}">
					<img width="50" height="50" src="${ctx}/static/common/images/user_default.png" class="picImg">
				</c:if>
			</aside>
			<div class="of">
				<section class="n-reply-wrap">
					<textarea id="dyId" maxlength="225" onkeyup="checkWordNum(this)" placeholder="输入您的咨询" name=""></textarea>
					<p class="of mt5 tar pl10 pr10">
						<span class="fl" style="display: none;">
							<em class="icon12 msg-e-icon"></em>
							<tt class="c-red com-err-info vam ml5"></tt>
						</span>
						<span class="c-999 fsize14">还可以输入<tt class="c-red">225</tt>字</span>
						<a class="lh-reply-btn ml15" title="咨询" href="javascript:void(0)" onclick="addReview()">咨询</a>
					</p>
				</section>
			</div>
		</li>
	</ul>
</section>
<div class="mt40"><span class="fsize18 c-333">课程咨询</span></div>
<section class="review-box mt15">
	<ul>
		<c:if test="${not empty sugSuggestList}">
			<c:forEach items="${sugSuggestList }" var="sug">
				<li class="pr replyCount">
					<aside class="noter-pic">
						<c:if test="${empty sug.queryCustomer.avatar}">
							<img width="50" height="50" src="${ctx}/static/common/images/user_default.png" class="picImg">
						</c:if>
						<c:if test="${not empty sug.queryCustomer.avatar}">
							<c:if test="${fn:contains(sug.queryCustomer.avatar,'http' )}">
								<img width="50" height="50" src="${sug.queryCustomer.avatar }" class="picImg">
							</c:if>
							<c:if test="${!fn:contains(sug.queryCustomer.avatar,'http' )}">
								<img width="50" height="50" src="<%=staticImageServer %>${sug.queryCustomer.avatar }" class="picImg">
							</c:if>
						</c:if>
					</aside>
					<div class="of reply-p-info">
						<span class="fl">
							<font class="fsize16 c-blue1 txtOf"> ${sug.showName}</font>
						</span>
						<span class="fr c-666 fsize12"><font><fmt:formatDate value="${sug.addTime }" type="both" pattern="yyyy-MM-dd HH:mm"/></font></span>
					</div>
					<div class="noter-txt mt5">
						<p>${sug.content }</p>
					</div>
					<div class="tar">
						<a href="javascript:void(0)" class="c-666 fsize14" title="回复" onclick="showReplyDis(${sug.id})"><em class="icon18 c-reply"></em></a>
					</div>
					<div class="n-reply mt5" id="rePrlblemId${sug.id}" style="display: none;">
						<div id="reply${sug.id}"></div>
					</div>
				</li>
			</c:forEach>
		</c:if>
		<c:if test="${empty sugSuggestList}">
			<section class="mt30 mb30 tac">
				<em class="no-data-ico cTipsIco">&nbsp;</em>
				<span class="c-666 fsize14 ml10 vam">还没有相关咨询，快来抢沙发~</span>
			</section>
		</c:if>
	</ul>
</section>
<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />