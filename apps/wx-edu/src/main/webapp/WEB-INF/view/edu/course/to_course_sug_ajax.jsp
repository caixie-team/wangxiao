<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<div class="mt10 pl10 pr10">
	<div class="of">
		<section class="n-reply-wrap">
			<fieldset>
				<textarea id="problemContextTextareaId" onkeyup="checkWordroblem()" maxlength="255" placeholder="写下你的问题，平台讲师或热心学员们会帮你解答..." class="f-fM" name=""></textarea>
			</fieldset>
			<p class="of mt5 tar pl10">
				<span class="fl">
					<em class="icon12 msg-e-icon"></em>
					<tt class="c-red com-err-info vam ml5" id="problemError">还可以输入255个字</tt>
				</span>
				<a class="lh-reply-btn ml15" title="提 交" href="javascript:void(0)" onclick="addProblemFun()">提 交</a>
			</p>
		</section>
	</div>
	<div class="mt10 hLh30 n-ctcn-tit"><span class="fsize14 c-333">全部答疑(${page.totalResultSize})</span></div>
</div>
<div>
	<div class="n-ct-of">
		<c:if test="${empty sugSuggestList}">
			<section class="mt30 mb30 tac">
				<em class="no-data-ico cTipsIco">&nbsp;</em>
				<span class="c-666 fsize14 ml10 vam">沙发正在等你抢哦~</span>
			</section><!-- /无数据提示 -->
		</c:if>
		<c:if test="${not empty sugSuggestList}">
			<section class="n-ct-cont-notice-list">
				<section class="review-box question-list">
					<ul>
						<c:forEach items="${sugSuggestList }" var="sug">
							<li class="pr" lang="${sug.id}">
								<aside class="noter-pic">
									<c:if test="${empty sug.queryCustomer.avatar}">
										<img width="40" height="40" class="picImg" src="<%=imagesPath%>/static/common/images/user_default.png">
									</c:if>
									<c:if test="${not empty sug.queryCustomer.avatar}">
										<c:if test="${fn:contains(sug.queryCustomer.avatar, 'http')}">
											<img width="40" height="40" class="picImg" src="${sug.queryCustomer.avatar}">
										</c:if>
										<c:if test="${!fn:contains(sug.queryCustomer.avatar, 'http')}">
											<img width="40" height="40" class="picImg" src="<%=staticImageServer%>${sug.queryCustomer.avatar}">
										</c:if>
									</c:if>
								</aside>
								<div class="of">
									<span class="fl">
										<font class="fsize14 c-blue1"> ${sug.showName}</font>
									</span>
									<span class="fr c-999 fsize12"><fmt:formatDate type="both" value="${sug.addTime}" pattern="yyyy-MM-dd HH:mm"/></span>
								</div>
								<div class="noter-txt mt5">
									<p>${sug.content}</p>
								</div>
								<div class="tar">
									<a href="javascript:void(0)" class="c-666 fsize14" title="回复" onclick="$('#reply${sug.id}').slideToggle(150)"><em class="icon18 c-reply"></em></a>
								</div>
								<div class="n-reply mt5" id="reply${sug.id}">

								</div>
							</li>
						</c:forEach>
					</ul>
				</section>
			</section>
			<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />
		</c:if>
	</div>
</div>