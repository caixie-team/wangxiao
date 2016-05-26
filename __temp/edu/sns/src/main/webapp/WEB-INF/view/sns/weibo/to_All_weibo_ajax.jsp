<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
		<!-- 主体 开始-->
				<!-- 左侧目录区域 -->
				<c:if test="${empty queryWeiBoList}">
						<div class="Prompt">
							<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
							<p class="vam c-555 fsize14 disIb">您还没有发布观点呢。</p>
						</div>
					</c:if>
				<c:forEach items="${queryWeiBoList}" var="qwbl">
					<article class="DT-detail-wrap" id="del${qwbl.id}">
						<aside class="DT-face">
							<a title="${qwbl.showname}" href="${ctx}/p/${qwbl.cusId}/home">
							<c:if test="${qwbl.avatar!=null&&qwbl.avatar!='' }">
								<img width="50" height="50" alt="" src="<%=staticImageServer%>${qwbl.avatar }">
							</c:if>
							<c:if test="${qwbl.avatar==null||qwbl.avatar=='' }">
								<img width="50" height="50" alt="" src="${ctximg}/static/sns/pics/user.jpg">
							</c:if>
							</a>
							<c:if test="${empty qwbl.cusAttentionId&&qwbl.cusId!=cusId}">
								<span class="c-blue hand attention${qwbl.cusId}" onclick="attention(${qwbl.cusId},this)">关注</span>
							</c:if>
						</aside>
						<div class="DT-detail">
							<section class="DT-name">
								<a class="c-333 dt-xm" title="${qwbl.showname}" href="${ctx}/p/${qwbl.cusId}/home">${qwbl.showname}</a>
								<span class="c-888"></span>
								<c:if test="${empty qwbl.forwardId||qwbl.forwardId==0 }">
									<span class="c-555">发表了观点：</span>
								</c:if>
								<c:if test="${!empty qwbl.forwardId&&qwbl.forwardId!=0 }">
									<span class="c-555">转发了观点：</span>
								</c:if>
							</section>
							<section class="DT-text">
								${qwbl.content}
							</section>
							<c:if test="${!empty qwbl.forwardContent&&qwbl.forwardContent!=''}">
							<section class="DT-text">
								<div class="ZF-box">
									<div class="DT-arrow-2"><em>◆</em><span>◆</span></div>
									${qwbl.forwardContent}
								</div>
							</section>
							</c:if>
							<section class="mt10">
								<div class="clearfix">
									<section class="fr c-bbb">
										<%-- <c:if test="${empty qwbl.cusAttentionId&&qwbl.cusId!=cusId}">
										<a class="c-blue"href="javascript:void(0)" onclick="attention(${qwbl.cusId},this)">关注</a>&nbsp;
										</c:if> --%>
										<c:if test="${type==3}">
										<a class="c-blue"
										href="javascript:void(0)"
										onclick="delWeiBoConfirm(${qwbl.id})">删除
										</a>
										</c:if>
										<c:if test="${cusId!=qwbl.cusId&&qwbl.content!='原观点已删除'}">
										<a class="forward-btn c-blue" href="javascript:void(0)">转发(<span class="forwardnum${qwbl.id }">${qwbl.forwardNum}</span>)</a>&nbsp;&nbsp;|&nbsp;
										</c:if>
										<a class="DT-comment-btn c-blue" href="javascript:void(0)">评论(<span id="commentNum${qwbl.id }">${qwbl.commentNum}</span>)</a>
									</section>
									<section class="c-b-green" title="<fmt:formatDate value="${qwbl.addTime}" type="both" />">${qwbl.modelStr}
									</section>
								</div>
							</section>
							<div class="DT-comment-wrap pingl undis" weiboId="${qwbl.id}" ></div>
							<div class="DT-comment-wrap DT-comment-forward undis" weiboId="${qwbl.id}" ></div>
						</div>
					</article>
					</c:forEach>
			<!-- 公共分页 开始 -->
			<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"/>
			<!-- 公共分页 结束 -->
