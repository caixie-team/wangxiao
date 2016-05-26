<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>教师详情</title>
</head>
<body>
	<!-- //讲师详情 -->
	<div class="mb50">
		<section class="w1000">
			<div class="mt30 pr">
				<div class="pathwray">
					<ol class="clearfix c-master f-fM fsize14">
						<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
						<li><a href="${ctx}/front/teacherlist" title="讲师" class="c-master">讲师</a> &gt;</li>
						<li><span>${teacher.name}介绍</span></li>
					</ol>
				</div>
			</div>
			<div class="mt30">
				<section class="comm-shadow-2">
					<div class="teacher-infor-wrap">
						<span class="fl t-i-w-bigPic">
						<c:if test="${not empty teacher.picPath}">
							<img src="<%=staticImageServer%>${teacher.picPath}" height="180" width="240" alt="">
						</c:if>
						<c:if test="${ empty teacher.picPath}">
							<img src="<%=staticImageServer %>/static/common/images/user_default.jpg" height="180" width="240" alt="">
						</c:if>
						</span>
						<section>
							<span class="fsize18 f-fM c-master">${teacher.name}</span>
							<span class="c-666 ml30">
							<c:if test="${teacher.isStar==0 }">高级讲师 </c:if>
							<c:if test="${teacher.isStar!=0 }">首席讲师 </c:if>
							</span>
						</section>
						<section class="t-i-w-txt mt10">
							<div>${teacher.education}</div>
							<div class="mt15">
								${teacher.career}
							</div>
						</section>
					</div>
				</section>
				<section class="mt30">
					<section class="clearfix">
						<aside class="fr w300">
							<section>
								<section>
									<h3 class="of a-title unFw"><font class="c-master f-fM fsize18">同类型讲师</font></h3>
								</section>
								<section class="c-p-list-1">
									<ul>
									<c:if test="${empty teacherList }">
									<section class="">
									<section class="comm-tips-1">
									<p>
									<em class="vam c-tips-1"> </em>
									<font class="c-999 fsize12 vam">暂时没有同类型老师. . .</font>
									</p>
									</section>
									</c:if>
									<c:if test="${not empty teacherList }" >
									<c:forEach items="${teacherList }" var="teachers">
										<li>
											<h5>
											<img src="<%=staticImageServer %>${teachers.picPath}" width="100" height="75" class="mr10 fl" />
											<a href="${ctx}/front/teacher/${teachers.id}" title="${teachers.name}" class="fsize14 c-4e">${teachers.name}</a><tt class="ml50 unFw fsize12 c-999">
											<c:if test="${teachers.isStar==0}">高级讲师 </c:if>
											<c:if test="${teachers.isStar!=0}">首席讲师 </c:if>
											</tt></h5>
											<p class="hLh30 of c-999 mt5">${teachers.career}</p>
											<div class="clear"></div>
										</li>
									</c:forEach>
									</c:if>
									</ul>
								</section>
							</section>
						</aside>
						<article class="fl w650">
							<section class="comm-shadow-1">
								<div class="comm-title-1">
									<section>
										<span class="fl"><font class="fsize16 f-fM c-brow">所讲课程</font></span>
										<tt class="ml20">
								<a href="${ctx }/front/showcoulist?teacherId=${teacher.id}" title="更多课程" class="c-999 fsize12 unFw">更多课程&gt;&gt;</a>
							</tt>
									</a>
									</section>
								</div>
							</section>
							<div class="mt40 t-my-courses">
								<ol class="s-c-list">
								<c:if test="${empty courseList}">
									<section class="">
									<section class="comm-tips-1">
									<p>
									<em class="vam c-tips-1"> </em>
									<font class="c-999 fsize12 vam">该讲师暂未录制课程. . .</font>
									</p>
									</section>
								</c:if>
								<c:if test="${not empty courseList}">
									<c:forEach items="${courseList}" var="course">
										<li>
											<div class="pr s-c-pics">
                                                <c:if test="${not empty course.logo }">
                                                    <img width="220" height="165"    src="<%=staticImageServer%>${course.logo}"/>
                                                </c:if>
                                                <c:if test="${ empty course.logo }">
                                                    <img width="220" height="165"   src="/static/edu/images/default/default_goods.jpg" />
                                                </c:if>
                                                <div class="pa s-c-name">
                                                    <a class="fsize14 c-fff" title="${course.name}" href="${ctx }/front/couinfo/${course.id}">${course.name}</a>
                                                </div>
											</div>
											<section class="pl10 pr10 of">
												<div class="s-c-desc pt10 pb10">
													<p class="c-666">${course.title}</p>
												</div>
											</section>
											<div class="of mt10 mb20">
												<section class="fl w50pre">
													<div class="tac">
														<p class="mt10">
															<a href="javascript:void(0)" onclick="window.location.href='${ctx}/front/couinfo/${course.id}'" title="开通课程" class="gray-btn"><tt class="vam c-333">开通课程</tt><em class="ml5 r-arrow icon16 vam">&nbsp;</em></a>
														</p>
													</div>
												</section>
												<section class="fl w50pre">
													<div class="tac">
														<p class="mt10">
															<a href="javascript:void(0)" onclick="house(${course.id})" title="收藏课程" class="c-orange">收藏课程</a>
														</p>
													</div>
												</section>
											</div>
										</li>
									</c:forEach>
								</c:if>
								</ol>
								<div class="clear"></div>
							</div>
						</article>
					</section>
				</section>
			</div>
		</section>
	</div>
	<!-- /讲师详情 结束 -->
</body>
</html>
