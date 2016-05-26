<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>直播</title>
	
</head>
<body>
	
											<c:forEach items="${courseList }" var="live">
												<!-- 已结束 -->
												<c:if test="${live.beginTimeNum==0&&live.endTimeNum==0}">
													<dl class="livend">
														<dt>
															<aside class="r-tip-bott">
																<span>已结束</span>
															</aside>
															<div class="oc-l-timer">
																<section class="oc-l-t-d">
																	<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																</section>
																<section class="oc-l-t-y-m">
																	<em>&nbsp;</em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																</section>
																<div class="clear"></div>
															</div>
														</dt>
														<dd>
															<h2 class="oc-live-title">
																<span>${live.name }</span>
															</h2>
															<section class="oc-live-body">
																<h5 class="oc-l-t-name">讲师：
																	<c:forEach items="${live.teacherList }" var="teacher">
																		${teacher.name}&nbsp;&nbsp;
																	</c:forEach>
																</h5>
																<div class="comm-oc-live-btn">
																	<a href="javascript:void(0)" class="e-live-btn">已结束</a>
																</div>
															</section>
														</dd>
													</dl>
												</c:if>
												
		                                   		<!-- 直播中 -->
				                                <c:if test="${live.beginTimeNum==0&&live.endTimeNum!=0}">
				                                	<dl class="liveing">
														<dt>
															<aside class="r-tip-bott">
																<span>直播中</span>
															</aside>
															<div class="oc-l-timer">
																<section class="oc-l-t-d">
																	<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																</section>
																<section class="oc-l-t-y-m">
																	<em>&nbsp;</em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																</section>
																<div class="clear"></div>
															</div>
														</dt>
														<dd>
															<h2 class="oc-live-title">
																<span>${live.name }</span>
															</h2>
															<section class="oc-live-body">
																<h5 class="oc-l-t-name">讲师：
																	<c:forEach items="${live.teacherList }" var="teacher">
																		${teacher.name}&nbsp;&nbsp;
																	</c:forEach>
																</h5>
																<h6 class="oc-l-time">距离直播结束：${live.endMin }分钟</h6>
																<div class="comm-oc-live-btn">
																	<a href="" title="" class="i-live-btn">进入直播</a>
																</div>
															</section>
														</dd>
													</dl>
				                                </c:if>
				                                <!-- 未开始-->
				                                <c:if test="${live.beginTimeNum!=0&&live.endTimeNum!=0}">
				                                	<!-- 免费-->
				                                	<c:if test="${live.isPay==0}">
				                                	 	<dl class="goLive">
															<dt>
																<aside class="r-tip-bott">
																	<span>未开始</span>
																</aside>
																<div class="oc-l-timer">
																	<section class="oc-l-t-d">
																		<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																	</section>
																	<section class="oc-l-t-y-m">
																		<em>&nbsp;</em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																	</section>
																	<div class="clear"></div>
																</div>
															</dt>
															<dd>
																<h2 class="oc-live-title">
																	<span>${live.name }</span>
																</h2>
																<section class="oc-live-body">
																	<h5 class="oc-l-t-name">讲师：
																		<c:forEach items="${live.teacherList }" var="teacher">
																			${teacher.name}&nbsp;&nbsp;
																		</c:forEach>
																	</h5>
																	<h6 class="oc-l-time">直播开始：${live.begin.day}天${live.begin.hour}时${live.begin.min}分</h6>
																	<div class="comm-oc-live-btn">
																		<a href="" title="" class="e-live-btn">未开始</a>
																	</div>
																</section>
															</dd>
														</dl>
				                                	</c:if>
				                                	<!-- 收费-->
				                                	<c:if test="${live.isPay>0}">
				                                	  	<dl class="goLive chare-live">
															<dt>
																<aside class="r-tip-bott">
																	<c:if test="${live.begin.day==0 }"><span>今天</span></c:if>
																	<c:if test="${live.begin.day>0 }"><span>${ live.begin.day}天后</span></c:if>
																</aside>
																<div class="oc-l-timer">
																	<section class="oc-l-t-d">
																		<span><fmt:formatDate value="${live.liveBeginTime }" pattern="HH:mm"/>-<fmt:formatDate value="${live.liveEndTime }" pattern="HH:mm"/></span>
																	</section>
																	<section class="oc-l-t-y-m">
																		<em>&nbsp;</em><span></em><span><fmt:formatDate value="${live.liveBeginTime }" pattern="yy-MM-dd"/>开课</span>
																	</section>
																	<div class="clear"></div>
																</div>
															</dt>
															<dd>
																<h2 class="oc-live-title">
																	<span>${live.name }</span>
																</h2>
																<section class="oc-live-body">
																	<h5 class="oc-l-t-name">讲师：
																		<c:forEach items="${live.teacherList }" var="teacher">
																			${teacher.name}&nbsp;&nbsp;
																		</c:forEach>
																	</h5>
																	<h6 class="oc-l-time">时间：${live.begin.day}天${live.begin.hour}时${live.begin.min}分</h6>
																	<div class="oc-l-ks-price">
																		<ol>
																			<li class="b-line"><tt>${live.lessionnum}课时</tt></li>
																			<li><tt>${live.subjectName }</tt></li>
																		</ol>
																		<aside class="ks-price-num">
																			<small>￥</small>
																			<big>${live.currentprice }</big>
																		</aside>
																	</div>
																	<div class="comm-oc-live-btn">
																		<a href="/mobile/live/order/add?courseId=${live.id }" title="" class="i-live-btn">立即购买</a>
																	</div>
																</section>
															</dd>
														</dl>
				                                	</c:if>
				                                </c:if>
											</c:forEach>
										
</body>
</html>