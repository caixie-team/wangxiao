<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<section class="comm-title-2" style="margin-bottom: 20px;">
										<span class="c-t-2-l" style="font-weight: normal;"><tt class="c-333 fsize16 f-fM">评论列表</tt></span>
									</section>
									<c:if test="${fn:length(disArticleReplyList)==0 }">
										<div class="Prompt" id="shafa">
											<img src="${ctximg}/static/sns/images/tishi.png"
												class="vam disIb">
											<p class="vam c-555 fsize14 disIb">还没有评论，快去抢沙发~</p>
										</div>
									</c:if>
									
									<section class="blog-comment-list" id="disreply">
										<c:forEach items="${disArticleReplyList }" var="dalr">
											<dl class="blog-comment-cont rem${dalr.id}">
												<dd class="b-c-nr clearfix">
													<div class="fl BC-head">
														<c:if test="${empty dalr.userExpandDto||empty dalr.userExpandDto.avatar}">
														<a href="${ctx}/p/${dalr.recusId}/home"><img
																src="${ctximg}/static/sns/pics/user.jpg" height="50"
																width="50" /></a>
														</c:if>
														<c:if test="${not empty dalr.userExpandDto&&not empty dalr.userExpandDto.avatar}">
															<a href="${ctx}/p/${dalr.recusId}/home"><img
																src="<%=staticImageServer%>${dalr.userExpandDto.avatar}" height="50"
																width="50" /></a>
																</c:if>
													</div>
													<div class="fl BC-TxtAtt">
														<h4 class="of">
															<span class="fr">
															<c:if test="${distatus==0}">
													<a href="javascript:void(0)"
														title="回复" class="b-c-reply-btn fsize12"
														onclick="otherreply(${dalr.articleId},${dalr.groupId},'${dalr.showName }',${dalr.id })"><i
															class="icon12 fsize12">&nbsp;</i> 回复</a>
															</c:if>
															 <c:if
															test="${dalr.recusId==loginId }">
															<a class="b-c-delete-btn c-888 ml10 fsize12" title="删除"
																href="javascript:void(0)"
																onclick="deldisreply(${dalr.articleId},${dalr.id})">
																<i class="icon16 fsize12"> </i> 删除
															</a>
														</c:if> </span>
															<span class="c-888 fsize12"><tt class="c-blue"><a href="${ctx}/p/${dalr.recusId}/home" class="c-blue">${dalr.showName }</a></tt>评论于：<tt class="fsize12">
																	${dalr.modelStr }
																</tt></span>
														</h4>
														<div class="b-c-cont-txt mt5 huifucontent${dalr.id }">
															<p>${dalr.replyContent}</p>
														</div>
													</div>
												</dd>
												<dd class="lineBetween" style="left:-4px;width: 605px">&nbsp;</dd>
											</dl>
										</c:forEach>
									</section>
				<!-- 公共分页 开始 -->
				<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"></jsp:include>				