<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<div class="mt10 pl10 pr10">
	<div class="of">
		<section class="n-reply-wrap">
			<fieldset>
				<textarea id="assessInofContextId" onkeyup="checkWordAssess()" placeholder="欢迎评论，分享，鼓励原创，学习体验、生活经验，好书、好文、好资源，都可在此发表..." class="f-fM" name=""></textarea>
			</fieldset>
			<p class="of mt5 tar pl10">
				<span class="fl">
					<em class="icon12 msg-e-icon"></em>
					<tt class="c-red com-err-info vam ml5" id="assessError">还可以输入255个字</tt>
				</span>
				<a class="lh-reply-btn ml15" title="提 交" href="javascript:void(0)" onclick="addAssess()">提 交</a>
			</p>
		</section>
	</div>
	<div class="mt10 hLh30 n-ctcn-tit"><span class="fsize14 c-333">全部评论(${page.totalResultSize})</span></div>
</div>
<div>
	<div class="n-ct-of">
		<c:if test="${not empty courseAssessList}">
			<section class="n-ct-cont-notice-list">
				<section class="review-box">
					<ul>
						<c:forEach items="${courseAssessList }" var="assess">
							<li class="pr">
								<aside class="noter-pic">
									<c:if test="${not empty assess.avatar }">
										<c:if test="${fn:contains(assess.avatar,'http' )}">
											<img width="40" height="40" alt="" class="picImg" src="${assess.avatar }">
										</c:if>
										<c:if test="${!fn:contains(assess.avatar,'http' )}">
											<img width="40" height="40" alt="" class="picImg" src="<%=staticImageServer%>${assess.avatar }">
										</c:if>
									</c:if>
									<c:if test="${empty assess.avatar }">
										<img width="40" height="40" alt="" class="picImg" src="<%=imagesPath%>/static/common/images/user_default.png">
									</c:if>
								</aside>
								<div class="of">
									<span class="fl">
										<font class="fsize14 c-blue1">
											<c:if test="${empty assess.nickname }">
												${assess.email }
											</c:if>
											<c:if test="${not empty assess.nickname }">
												${assess.nickname }
											</c:if>
										</font>
									</span>
									<span class="fr c-999 fsize12"><fmt:formatDate value="${assess.createTime }" type="both" pattern="yyyy-MM-dd HH:mm"/></span>
								</div>
								<div class="noter-txt mt5">
									<p>${assess.content }</p>
								</div>
							</li>
						</c:forEach>
					</ul>
				</section>
			</section>
			<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />
		</c:if>
		<c:if test="${empty courseAssessList}">
			<section class="mt30 mb30 tac">
				<em class="no-data-ico cTipsIco">&nbsp;</em>
				<span class="c-666 fsize14 ml10 vam">沙发正在等你抢哦~</span>
			</section><!-- /无数据提示 -->
		</c:if>
	</div>
</div>
