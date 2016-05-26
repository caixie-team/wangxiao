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
					<textarea id="commentContent" maxlength="225" onkeyup="checkWordNum(this)" placeholder="输入您要评论的文字" name=""></textarea>
					<p class="of mt5 tar pl10 pr10">
						<span class="fl" style="display: none;">
							<em class="icon12 msg-e-icon"></em>
							<tt class="c-red com-err-info vam ml5"></tt>
						</span>
						<span class="c-999 fsize14">还可以输入<tt class="c-red">225</tt>字</span>
						<a class="lh-reply-btn ml15" title="回复" href="javascript:void(0)" onclick="addAssess()">发表</a>
					</p>
				</section>
			</div>
		</li>
	</ul>
</section>
<div class="mt40"><span class="fsize18 c-333">课程评论（${page.totalResultSize!=null?page.totalResultSize:0}）</span></div>
<section class="review-box mt15">
	<ul>
		<c:if test="${not empty courseAssessList}">
			<c:forEach items="${courseAssessList }" var="assess">
				<li class="pr replyCount">
					<aside class="noter-pic">
						<c:if test="${empty assess.avatar}">
							<img width="50" height="50" src="${ctx}/static/common/images/user_default.png" class="picImg">
						</c:if>
						<c:if test="${not empty assess.avatar}">
							<c:if test="${fn:contains(assess.avatar,'http' )}">
								<img width="50" height="50" src="${assess.avatar }" class="picImg">
							</c:if>
							<c:if test="${!fn:contains(assess.avatar,'http' )}">
								<img width="50" height="50" src="<%=staticImageServer %>${assess.avatar }" class="picImg">
							</c:if>
						</c:if>
					</aside>
					<div class="of reply-p-info">
						<span class="fl">
							<font class="fsize16 c-blue1 txtOf"> ${assess.nickname!=null?assess.nickname:assess.email }</font>
						</span>
						<span class="fr c-666 fsize12"><font><fmt:formatDate value="${assess.createTime }" type="both" pattern="yyyy-MM-dd HH:mm"/></font></span>
					</div>
					<div class="noter-txt mt5">
						<p>${assess.content }</p>
					</div>
				</li>
			</c:forEach>
		</c:if>
		<c:if test="${empty courseAssessList}">
			<section class="mt30 mb30 tac">
				<em class="no-data-ico cTipsIco">&nbsp;</em>
				<span class="c-666 fsize14 ml10 vam">还没有相关评论，快来抢沙发~</span>
			</section>
		</c:if>
	</ul>
</section>
<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />

