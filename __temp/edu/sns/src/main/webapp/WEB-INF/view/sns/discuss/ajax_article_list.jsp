<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${fn:length(disArticleList)==0}">
									<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
									<p class="vam c-555 fsize14 disIb">还没有小组话题，快去创建第一篇小组话题吧...</p>
									</div>
									</c:if>
	<c:if test="${isTransfer==0&&isJoin!=0}">
								<ul>
									<c:forEach items="${disArticleList }" var="dal">
										<li>
											<h5 class="clearfix">
												<div class="fr c-bbb fsize12">
													<span class="ml10"><tt class="c-888">最后发表：</tt> <c:if
															test="${dal.lastReply!=null }">
															<tt class="c-888">
																<span class="c-green mr5">${dal.lastReply}</span>
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
													<%-- <c:if test="${dal.selType==0 }">
												<tt class="icon22 yuan">&nbsp;</tt>
												</c:if>
												<c:if test="${dal.selType==1 }">
												<tt class="icon22 zhuan">&nbsp;</tt>
												</c:if> --%>
													<a
														href="${ctx}/dis/artinfor/${dal.id}/${dal.groupId}"
														class="c-blue article-q-l-link-txt" title="${dal.title }"
														style="display: inline">${dal.title }</a>
												</div>
											</h5>
											<div class="QA-desc-2">${dal.summary}</div>
											<div class="mt5 clearfix">
												<span class="fr"> <c:if test="${dal.status==0 }">
														<a class="c-green" href="javascript:void(0)"
															onclick="prohibitChat(${dal.groupId},${dal.id},this)">禁止回应</a>
													</c:if> <c:if test="${dal.status==1 }">
														<a class="c-green" href="javascript:void(0)"
															onclick="permitChat(${dal.groupId},${dal.id},this)">允许回应</a>
													</c:if> <c:if test="${dal.top==0 }">
														<a class="c-green" href="javascript:void(0)"
															onclick="distop(${dal.id},this)">置顶</a>
													</c:if> <c:if test="${dal.top==1 }">
														<a class="c-green" href="javascript:void(0)"
															onclick="disCancel(${dal.id},this)">取消置顶</a>
													</c:if> <a class="c-green" href="javascript:void(0)"
													onclick="delarticle(${dal.id},${dal.groupId})">删除</a></span> <span
													class="fl ml10"><tt class="c-888">作者：</tt><a
													href="${ctx}/p/${dal.cusId}/home" title=""
													class="c-blue">${dal.showName}</a></span>
											</div>
										</li>
									</c:forEach>
								</ul>
							</c:if>
							<c:if test="${isJoin==0||(isJoin!=0&&isTransfer==1)}">
								<ul>
									<c:forEach items="${disArticleList }" var="dal">
										<li>
											<h5 class="clearfix">
												<div class="fr c-bbb fsize12">
													<span class="ml10"><tt class="c-888">最后发表：</tt> <c:if
															test="${dal.lastReply!=null }">
															<tt class="c-888">
																<span class="c-green mr5">${dal.lastReply
																			}</span>
																${dal.lastStr}
															</tt>
														</c:if> <c:if test="${dal.lastReply==null }">
															<tt class="c-888">
																<span class="c-green">${dal.showName
																			}</span>
																${dal.modelStr}
															</tt>
														</c:if></span>
												</div>
												<%-- <c:if test="${isJoin!=0 }"></c:if> --%>
												<div class="likes">
													<c:if test="${dal.selType==0}">
															${dal.favorCount }<br>
															喜欢
															</c:if>
													<c:if test="${dal.selType==1}">
																${dal.favorCount }<br>
																参加
																</c:if>
												</div>


												<div class="fl">
													<%-- 	<c:if test="${dal.selType==1 }">
												<tt class="icon22 yuan">&nbsp;</tt>
												</c:if>
												<c:if test="${dal.selType==2 }">
												<tt class="icon22 zhuan">&nbsp;</tt>
												</c:if> --%>
													<a
														href="${ctx}/dis/artinfor/${dal.id}/${dal.groupId}"
														class="c-blue article-q-l-link-txt" title="${dal.title }"
														style="display: block;height: 56px;line-height: 26px; text-indent: 0;white-space: normal;">${dal.title }</a>
												</div>

												<%-- <c:if test="${isJoin==0 }">
														<div class="fl">
															<a
																href="javascript:void(0)"
																class="c-blue article-q-l-link-txt"
																title="${dal.title }" style="display: block;height: 56px;line-height: 26px; text-indent: 0;white-space: normal;">${dal.title }</a>
														</div>
														</c:if> --%>
											</h5> <%-- <c:if test="${isJoin!=0 }"></c:if> --%>
												<div class="QA-desc-2">${dal.summary}</div>
												<div class="mt5 clearfix">
													<span class="fr"><tt class="c-888">评论/查看：</tt><a
														href="${ctx}/dis/artinfor/${dal.id}/${dal.groupId}"
														title="" class="c-555">${dal.reNum}/${dal.countView}</a></span> <span
														class="fl ml10"><tt class="c-888">作者：</tt><a
														href="${ctx}/p/${dal.cusId}/home" title=""
														class="c-blue">${dal.showName}</a></span>
												</div>
											 <%-- <c:if test="${isJoin==0 }">
												<div class="mt5 clearfix">
													<span class="fr c-888"><tt class="c-888">评论/查看：</tt>
														${dal.reNum}/${dal.countView}</span> <span class="fl ml10"><tt
															class="c-888">作者：</tt><a
														href="${ctx}/p/${dal.cusId}/home" title=""
														class="c-blue">${dal.showName}</a></span>
												</div>
											</c:if> --%>
										</li>
									</c:forEach>
								</ul>
							</c:if>
				<!-- 公共分页 开始 -->
				<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"></jsp:include>				