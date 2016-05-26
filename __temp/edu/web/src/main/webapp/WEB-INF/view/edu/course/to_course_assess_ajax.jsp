<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<div class="mt20">
	<ul class="c-comment-list">
		<c:forEach items="${courseAssessList }" var="assess">
			<section class="comment-question">
				<dl id="dayiListId">
					<dt>
						<span class="c-q-img-1">
								<c:if test="${empty assess.avatar}">
											<img id="userImgId" src="/static/common/images/user_default.jpg" height="60" width="60" >
										</c:if>
										<c:if test="${not empty assess.avatar}">
											<img id="userImgId" src=" <%=staticImageServer %>${assess.avatar }" height="60" width="60" >
								</c:if>
						</span>
						<div class="clearfix">
							<span class="fr"> <font class="fsize12 c-666"><fmt:formatDate value="${assess.createTime }" type="both" /></font></span>
							<span>
								<strong class="fsize14 c-master vam">
									<c:if test="${empty assess.nickname }"> ${assess.email }</c:if> 
									<c:if test="${!empty assess.nickname }"> ${assess.nickname }</c:if>
								</strong>
								<tt class="c-666 vam ml10">评论：</tt>
							</span>
						</div>
						<div class="mt5"><p class="c-999">${assess.content } </p></div>
						<div class="mt10 clearfix">
							<span class="fr c-ccc">
								<tt class="f-fM c-666 vam mr5"></tt>
							</span>
							<span></span>
						</div>
					</dt>
				</dl>
			</section>
		
		</c:forEach>
		<c:if test="${empty courseAssessList }">
			<section class="comment-question">
				<dl id="dayiListId">
					<section class="comm-tips-1">
						<p>
							<em class="vam c-tips-1">&nbsp;</em><font class="c-999 fsize12 vam">还没有相关评论，快来抢沙发！</font>
						</p>
					</section>
				</dl>
			</section>
		</c:if>
	</ul>
</div>
<section class="mt20">
	<div class="pagination pagination-large tac">
		<!-- 公共分页 开始 -->
		<jsp:include page="/WEB-INF/view/common/ajaxpage.jsp" />
		<!-- 公共分页 结束 -->
	</div>
</section>

