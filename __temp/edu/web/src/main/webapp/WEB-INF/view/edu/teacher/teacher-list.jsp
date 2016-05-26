<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>教师</title>
<script type="text/javascript"
	src="<%=imagesPath%>/static/edu/js/front/course/course.js"></script>
<script type="text/javascript">
	var teacherName = '${queryTeacherCondition.teacherName}';
	var subjectId = '${queryTeacherCondition.subjectId}';
</script>
</head>
<body>
	<!-- /讲师列表 -->
	<div class="mb50">
		<section class="w1000">
			<div class="mt30">
				<div class="w1000">
					<c:forEach var="eximage" items="${websiteImages.expertAdvertImage}" >
						<a href="${eximage.linkAddress}" >
						<img src="<%=staticImageServer %>${eximage.imagesUrl}" class="dis" class="dis ads-1" height="120" width="1000">
						</a>
					</c:forEach>
				</div>
			</div>
			<div class="mt30 pr">
				<div class="pathwray">
					<div class="fr clearfix teacher-search">
						<form id="searchForm" name="searchForm" action="${ctx}/front/teacherlist" method="post">
							<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
							<!-- 教室隐藏域 -->
							<span class="c-666 fl" style="line-height: 34px;">输入讲师名称搜索：</span>
							<input type="text" name="teacher.name" id="teacherName"
								value="${teacher.name}" class="fl t-search-txt" /> <label
								class="t-search-sub fl" style="line-height: 28px;"> <input type="submit"
								value="搜索讲师"></label>
						</form>
					</div>
					<ol class="clearfix c-master f-fM fsize14">
						<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
						<li><span>讲师列表</span></li>
					</ol>
					<div class="clear"></div>
				</div>
			</div>
			<div class="mt30">
				<section class="clearfix">
					<article>
						<div class="teacher-list-title-wrap hLh30 of pl20 pr20">
							<span class="fr"> <tt class="vam c-666 fsize14">
									<font class="c-red">${page.currentPage}</font>/${page.totalPageSize}
								</tt> <tt class="vam u-d-page ml5">
								<c:choose>
								<c:when test="${page.first}">
								<a title="上一页" class="shang" href="javascript:void(0)">上一页</a>
								</c:when>
								<c:otherwise>
								<a title="上一页" class="shang" href="javascript:goPage(${page.currentPage-1 });">上一页</a>
								</c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${page.last}">
								<a title="下一页" class="xia" href="javascript:void(0)">下一页</a>
								</c:when>
								<c:otherwise>
								<a title="下一页" class="xia" href="javascript:goPage(${page.currentPage+1 })">下一页</a>
								</c:otherwise>
								</c:choose>
								</tt>
							</span> <span class="fl"><font class="f-fM fsize18 c-666 vam">讲师列表</font>
								<tt class="vam ml50 c-666">
									检索 ：<c:if test="${not empty teacher.name}"> <q class="c-orange" id="">${teacher.name}</q></c:if> 共搜索出<q
										class="c-orange">${page.totalResultSize}</q>条结果
								</tt></span>
						</div>
						<!-- /老师列表 开始 -->
						<section class="mb50">
							<ul class="teacher-list-wrap">
								<c:if test="${teacherList.size()==0}">
									<section class="comm-tips-1">
										<p>
											<em class="vam c-tips-1"> </em> <font
												class="c-999 fsize12 vam">没有找到相关的老师！</font>
										</p>
									</section>
								</c:if>

								<c:if test="${teacherList.size()>0}">
									<c:forEach items="${teacherList}" var="teacher">
										<li>
											<article class="fl w50pre" style="width: 60%">
												<section class="pr10">
													<a href="${ctx}/front/teacher/${teacher.id}" title=""> 
														<c:if test="${teacher.picPath!=''}">
															<img src="<%=staticImageServer%>${teacher.picPath}" height="116" width="154" class="teacher-img" alt="" />
														</c:if> 
														<c:if test="${teacher.picPath==''}">
															<img src="<%=imagesPath%>/static/images/user_default.jpg" height="116" width="154" class="teacher-img" alt="" />
														</c:if>
													</a>
													<div>
														<span class="vam">
															<a href="${ctx}/front/teacher/${teacher.id}" title="${teacher.name}">
															<font class="fsize14 c-master">${teacher.name}</font>
															</a>
														</span> 
														<span class="vam ml10"> 
															<font class="fsize12 c-666">
																<c:if test="${teacher.isStar==0}">
																高级讲师
																</c:if> 
																<c:if test="${teacher.isStar==1}">
																首席讲师
																</c:if>
															</font>
														</span>
													</div>
													<div class="mt10 hLh20 of">
														<span class="vam"><font class="fsize12 c-666">${teacher.education}</font></span>
													</div>
													<div class="mt10 teacher-desc-txt of">
														<p class="c-999">${teacher.career}</p>
													</div>
												</section>
											</article>
											<article class="fl w50pre" style="width: 40%">
												<div class="pl20 mb10">
													<font class="c-master fsize14 f-fM ml5">最近所讲课程</font>
												</div>
												<div class="pl10 teacher-courses-tj clearfix">
													<c:if test="${teacher.courseList[0].id!=0}">
														<c:forEach var="course" items="${teacher.courseList}">
															<section class="t-c-tj">
																<a title="${course.name}" href="${ctx}/front/couinfo/${course.id}">
																	<c:if test="${course.logo!=''}">
																		<img width="90" height="68" alt="" src="<%=staticImageServer%>${course.logo}">
																	</c:if>
																	<c:if test="${course.logo==''}">
																		<img width="90" height="68" alt="" src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg">
																	</c:if>
																	<p class="hLh20 of">${course.name}</p>
																</a>
															</section>
														</c:forEach>
													</c:if>
													<c:if test="${teacher.courseList[0]==null}">
														<section class="t-c-tj">
															<p style="width: 200%">
																<em class="vam c-tips-1"> </em> <font
																	class="c-999 fsize12 vam">暂无所讲课程！</font>
															</p>
														</section>
													</c:if> 
												</div>
											</article>
											<div class="clear"></div>
										</li>
									</c:forEach>
								</c:if>
							</ul>
						</section>
						<!-- /老师列表 结束 -->
							<section>
								<div class="pagination pagination-large tac">
									<jsp:include page="/WEB-INF/view/common/page.jsp" />
								</div>
							</section>
					</article>
				</section>
			</div>
		</section>
	</div>
	<!-- /讲师列表 结束 -->
</body>
</html>
