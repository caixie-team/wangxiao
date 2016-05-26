<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 新增 -->
<!DOCTYPE html>
<html>
<head>
<title>课程直播</title>
</head>
<body>
	<!-- 中间内容 -->
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li class="current">
						<a href="javascript:void(0)" title="在线直播">在线直播</a>
					</li>
				</ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				<div class="u-m-sub-head tar">
					<a class="u-common-btn ml5" title="历史直播课程" href="${ctx}/uc/live?vedioLive.dateFalg=history">历史直播课程</a>
					<a class="u-common-btn ml5" title="即将直播课程" href="${ctx}/uc/live?vedioLive.dateFalg=soon">即将直播课程</a>
				</div>

				<c:if test="${empty vedioLiveList}">
					<section class="comm-tips-1">
						<p>
							<em class="vam c-tips-1">&nbsp;</em>
							<font class="c-999 fsize12 vam">还没有直播课程！</font>
						</p>
					</section>
				</c:if>
				<div class="pl15 pr15">
					<section>
						<ul class="u-sys-message">
							<c:forEach items="${vedioLiveList}" var="videoLive">
								<li>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tbody>
											<tr>
												<td width="30%" valign="top" style="padding: 0 20px 0 0;">
													<div class="zb-img">
														<p class="hLh30 of">
															<tt class="c-0f58c5 fsize18 f-fM">讲师：${videoLive.teacher}</tt>
														</p>
														<p class="mt10">
															<tt class="c-0f58c5 fsize13 f-fM">
																播放时间：
																<fmt:formatDate value="${videoLive.liveTime}" pattern="yyyy-MM-dd HH:mm:ss" />
															</tt>
														</p>
														<p class="mt10">
															<tt class="c-0f58c5 fsize13 f-fM">
																结束时间：
																<fmt:formatDate value="${videoLive.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
															</tt>
														</p>
														<p class="mt10">
															<tt class="c-0f58c5 fsize13 f-fM">人数：${videoLive.joinNum}&nbsp;人</tt>
														</p>
													</div>
												</td>
												<td width="70%" valign="top" class="u-s-m-desc">
													<h5 class="of">
														<c:if test="${videoLive.endTime >= date}">
															<span class="fr">
																<a href="${ctx}/live/info/${videoLive.id}" target="_blank" title="加入直播" class="go-live-btn">参与</a>
															</span>
														</c:if>
														<c:if test="${videoLive.endTime < date}">
															<span class="fr">
																<img class="dis" width="70" height="70" src="${ctx}/static/edu/images/u-center/v-live-h.png">
															</span>
														</c:if>
														<tt class="fsize14 c-333 mt5 disIb">${videoLive.title}</tt>
													</h5>
													<div class="mt10 pr">
														<p class="live-desc">${videoLive.content}</p>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</li>
							</c:forEach>
						</ul>
					</section>
					<!-- /pageBar begin -->
					<c:if test="${!empty vedioLiveList}">
						<section>
							<div class="pagination pagination-large tac">
								<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
							</div>
						</section>
					</c:if>
					<!-- /pageBar end -->
				</div>
			</section>
		</section>
	</article>
	<!-- 中间内容结束 -->
</body>
</html>
