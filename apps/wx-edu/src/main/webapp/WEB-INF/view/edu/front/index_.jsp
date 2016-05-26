<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
</head>
<body>
	<!-- / slide -->
	<div class="i-slide">
		<section>
			<!-- 如果需要导航按钮 -->
			<a class="arrow-left s-arrow" href="#"></a>
			<a class="arrow-right s-arrow" href="#"></a>
			<!-- 图片位置 -->
			<div class="swiper-container">
				<c:if test="${empty websiteImages.indexCenterBanner}">
					<div class="swiper-wrapper">
						<div class="swiper-slide" style="background: #3C5849;"><img class="imgload" src="${ctximg}/static/nxb/web/img/pic/banner-1.jpg" alt=""></div>
						 <div class="swiper-slide" style="background: #E5C0A3;display:none"><img class="imgload" src="${ctximg}/static/nxb/web/img/pic/banner-2.jpg" alt=""></div>
						<div class="swiper-slide" style="background: #3C5849;display:none"><img class="imgload" src="${ctximg}/static/nxb/web/img/pic/banner-3.jpg" alt=""></div>
					</div>
				</c:if>
				<c:if test="${not empty websiteImages.indexCenterBanner}">
					<div class="swiper-wrapper">
						<c:forEach items="${websiteImages.indexCenterBanner}" var="image" varStatus="index">
							<c:if test="${empty image.imagesUrl}">
							<c:if test="${index.index==0 }">
								<div class="swiper-slide" style="background: ${image.color};"><img class="imgload" src="${ctximg}/static/nxb/web/img/pic/banner-1.jpg" alt=""></div>
							</c:if>
							<c:if test="${index.index>0 }">
								<div class="swiper-slide" style="background: ${image.color};"><img class="imgload" src="${ctximg}/static/nxb/web/img/pic/banner-1.jpg" alt=""></div>
							</c:if>
							</c:if>
							<c:if test="${not empty image.imagesUrl}">
								<c:if test="${index.index==0 }">
								<div class="swiper-slide" style="background: ${image.color};"><img class="imgload" src="<%=staticImageServer%>${image.imagesUrl}" alt=""></div>
								</c:if>
								<c:if test="${index.index>0 }">
								<div class="swiper-slide" style="background: ${image.color};"><img class="imgload" src="<%=staticImageServer%>${image.imagesUrl}" alt=""></div>
								</c:if>
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</div>
			<!-- 如果需要分页器 -->
			<div class="pagination"></div>
			<!-- 浮动快捷登录注册层 开始-->
			<div class="board-box">
				<section class="container">
					<aside class="board-wrap pa login-already-wrap"><!-- 登录之后加class="login-already-wrap" -->
						<div class="board-mod">
							<!-- 登录前状态开始 -->
							<c:if test="${ empty loginUser }">
								<section class="bm-lr-wrap" id="index_unlogin">
									<h6 class="hLh30">
											<span class="fr">
												<a href="${ctx}/register" title="" class="c-999 fsize12">注册</a>
												<small class="c-666 fsize16 f-fA ml5">&gt;</small>
											</span>
										<span class="fsize16 c-999">用户登录</span>
									</h6>
									<ul class="bm-lr-ul">
										<li>
											<div class="bm-lr-jy-box undis" id="login_account_div">
												<section class="hLh30 ml10 mr10 pr">
													<big class="bm-close" title="关闭" onclick="$(this).parent().parent('.bm-lr-jy-box').hide();">x</big>
													<div class="DT-arrow"><em>◆</em><span>◆</span></div>
													<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="login_account_error">请输入正确的邮箱/手机号！</span>
												</section>
											</div>
											<label><em class="icon18 ml5 user-icon"></em><input type="text" placeholder="邮箱/手机号" id="userEmail" onkeyup="enterSubmit(event,'pageLogin(2)')"></label>
										</li>
										<li>
											<div class="bm-lr-jy-box undis" id="login_password_div">
												<section class="hLh30 ml10 mr10 pr">
													<big class="bm-close" title="关闭" onclick="$(this).parent().parent('.bm-lr-jy-box').hide();">x</big>
													<div class="DT-arrow"><em>◆</em><span>◆</span></div>
													<em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="login_password_error">请输入正确的邮箱/手机号！</span>
												</section>
											</div>
											<label><em class="icon18 ml5 pwd-icon"></em><input onkeyup="enterSubmit(event,'pageLogin(2)')"  type="password" id="userPassword" name="" value="" placeholder="密码"></label>
										</li>
									</ul>
									<div class="mt20"><a href="javascript:;" onclick="pageLogin(2)" class="bm-lr-btn" title="立即登录">立即登录</a></div>
									<div class="hLh20 mt10">
										<span class="fr"><a href="${ctx}/front/forget_passwd" title="" class="c-999 fsize12">忘记密码？</a></span>
											<span class="fl">
												<label>
													<input type="checkbox" name="autoThirty" checked="checked" class="vam" id="autoThirty">
													<small class="vam c-999 fsize12">自动登录</small>
												</label>
											</span>
									</div>
								</section>
							</c:if>
							<!-- 登录前状态结束 -->
							<!-- 登录后状态开始 -->
							<c:if test="${ not empty loginUser }">
							<section class="bm-login-wrap tac" id="index_login">
								<div class="b-l-pic">
								<c:if test="${not empty loginUser.avatar}">
									<c:if test="${fn:contains(loginUser.avatar,'http:')}">
										<img src="${loginUser.avatar}">
									</c:if>
									<c:if test="${fn:contains(loginUser.avatar,'http:')==false}">
										<img src="${staticUrl}${loginUser.avatar}">
									</c:if>
								</c:if>
								<c:if test="${empty loginUser.avatar}">
								<img src="${ctximg}/static/nxb/web/img/avatar-boy.gif">
								</c:if>
								</div>
								<div><a href="${ctx}/uc/home" class="b-l-name">${loginUser.showname}</a></div>
								<div class="my-learning-wrap clearfix of mt10">
								<a href="${ctx}/uc/course" class="bm-lr-btn fl">我的学习</a>
								<a href="${ctx}/uc/myArrangeExam" class="bm-lr-btn fr">我的测评</a>
								</div>
							</section>
							</c:if>
							<!-- 登录后状态结束 -->
							<section class="bm-gg-wrap of">
								<ul class="comm-new-list newsNotice">
									<c:if test="${empty noticeList }">
										<li>
											<p><a href="javascript:void(0)" title="" class="hLh30 txtOf">暂无公告</a></p>
										</li>
									</c:if>
									<c:if test="${not empty noticeList }">
										<c:forEach items="${noticeList}" var="notice" varStatus="index">
											<c:if test="${index.index % 2 == 0}">
												<li><p><a href="${ctx}/front/toArticle/${notice.id}" title="${notice.title}" class="hLh30 txtOf">${notice.title}</a></p>
											</c:if>
											<c:if test="${index.index % 2 == 1}">
												<p><a href="${ctx}/front/toArticle/${notice.id}" title="${notice.title}" class="hLh30 txtOf">${notice.title}</a></p></li>
											</c:if>
										</c:forEach>
									</c:if>
								</ul>
							</section>
							<!-- 公告 消息 -->
						</div>
					</aside>
				</section>
			</div>
			<!-- 浮动快捷登录注册层 结束-->
		</section>
	</div>
	<!-- / slide -->
	<div class="bg-f8">
		<article class="container">
			<section class="nxb-ipic-box nxb-i-pic-2"><img src="${ctximg}/static/nxb/web/img/pic/nxb-i-pic-2.jpg" width="100%" alt=""></section>
		</article>
	</div>
	<!-- /两屏宣传图 -->
	<!-- /岗位课程开始 -->
	<div class="">
		<article class="container">
			<section>
				<header class="comm-title">
					<h2 class="tac clearfix">
						<span class="c-333">岗位课程</span>
					</h2>
				</header>
				<div class="course-slidewrap ">
					<div class="course-slide of">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<article class="comm-course-list swiper-slide">
									<ul class="of job-cou-list">
										<c:forEach items="${mapCourseList.index_course_1}" var="subjectCourse" varStatus="courseindex">
											<li>
												<div class="cc-l-wrap">
													<a class="course-img" href="${ctx}/front/couinfo/${subjectCourse.id }">
														<c:choose>
															<c:when test="${ not empty subjectCourse.logo}">
																<img src="<%=staticImageServer%>/${subjectCourse.logo}" xSrc="" class="img-responsive">
															</c:when>
															<c:otherwise>
																<img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" xSrc=""  class="img-responsive">
															</c:otherwise>
														</c:choose>
													</a>
													<div class="j-c-desc-wrap">
														<h3 class="hLh30 txtOf pt10">
															<a class="j-course-title" title="${subjectCourse.name}" href="${ctx}/front/couinfo/${subjectCourse.id }">${subjectCourse.name}</a>
														</h3>
														<div class="clearfix of mt15 cj-cou-ds">
															<span class="fl fsize16 c-999 f-fM txtOf">
															讲师：
															<c:forEach items="${subjectCourse.teacherList}" var="teacher" varStatus="teaindex">
																<c:if test="${teaindex.index==0 }">
																	<a class="c-666 mr10" href="${ctx}/front/teacher/${teacher.id}" >${teacher.name}</a>
																</c:if>
																<c:if test="${teaindex.index>0 }">
																	<a class="c-666" href="${ctx}/front/teacher/${teacher.id}" >${teacher.name}</a>
																</c:if>
															</c:forEach>
															</span>
															<span class="fr fsize16 c-999 f-fM ">课时：<span class="c-666">${subjectCourse.lessionnum}</span></span>
														</div>
														<dl class="cj-cou-desc of">
															<dd>
																<div class="c-c-sbox txtOf" title="播放量：${subjectCourse.playcount}">
																	<em class="icon12 c-play-num"></em>
																	<tt class="fsize14">${subjectCourse.playcount}</tt>
																</div>
															</dd>
															<dd>
																<div class="c-c-sbox txtOf" title="评论数：${subjectCourse.commentcount}">
																	<em class="icon12 c-review-num"></em>
																	<tt class="fsize14">${subjectCourse.commentcount}</tt>
																</div>
															</dd>
															<dd>
																<div style="border-right:none;" class="c-c-sbox txtOf" title="价格：${subjectCourse.currentprice}元">
																	<em class="icon12 c-couse-v"></em>
																	<tt class="fsize14  vam">${subjectCourse.currentprice}</tt>
																</div>
															</dd>
														</dl>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</article>
							</div>
						</div>
						<div class="pagination"></div>
					</div>
				</div>
			</section>
		</article>
	</div>
	<!-- /岗位课程结束-->
	<c:if test="${not empty subjectShowIndex}">
		<c:forEach items="${subjectShowIndex}" var="subject">
			<div class="bg-f8 of">
				<article class="container">
					<header class="comm-title net-list-head">
						<h2 class="fl tac mt5"> <span class="c-blue1">${subject.subjectName}</span> </h2>
						<a class="fr net-c-more" target="_blank" href="${ctx}/front/showCourseList?queryCourse.subjectId=${subject.subjectId}"><tt class="vam mr10">更多课程</tt><em class="icon14"></em></a>
						<section class="c-tab-title">
							<c:forEach items="${subject.childSubjectList}" var="childSubject" varStatus="index">
								<a href="javascript:void(0)" title="${childSubject.subjectName}" id="${childSubject.subjectId}">${childSubject.subjectName}</a>
							</c:forEach>
						</section>
						<div class="clear"></div>
					</header>
					<div class="net-cou-box">
						<!-- 读取数据中... -->
						<div class="tac mb10">
							<img src="${ctximg}/static/nxb/web/img/loading.gif" width="100" height="9" alt="正在读取...">
							<p class="hLh30"><span class="c-red fsize12 f-fA">正在读取...</span></p>
						</div>
						<!-- 读取数据中... -->
						<ul class="of job-cou-list"></ul>
					</div>
				</article>
			</div>
		</c:forEach>
	</c:if>

	<!-- 企业动态和员工评价开始 -->
	<div class="bg-f8 of pb40">
		<article class="container">
			<div class="clearfix">
				<section class="fl col-65">
					<header class="comm-title of">
						<h2 class="fl">
							<span class="c-333">企业动态</span>
						</h2>
						<div class="fr mr5">
							<a class="c-666 c-more" title="" href="${ctx}/front/articlelist/news">
								<em></em>
							</a>
						</div>
					</header>
					<c:if test="${empty newsList}">

					</c:if>
					<c:if test="${not empty newsList}">
						<div>
							<ul class="clearfix of com-dynamic-list">
								<c:forEach items="${newsList}" var="news">
									<li>
										<div class="c-d-l-wrap pr">
											<div class="c-d-l-desc">
												<div class="c-d-l-dbox">
													<section class="hLh30 of">
														<a class="fsize14 c-fff" title="${news.title}" target="_blank" href="${ctx}/front/toArticle/${news.id}">${news.title}</a>
													</section>
													<section class="c-d-desc">
														<p>${news.description}</p>
													</section>
												</div>
											</div>
											<a href="${ctx}/front/toArticle/${news.id}" target="_blank">
												<c:if test="${empty news.picture}">
													<img src="${ctximg}/static/common/images/default_goods.png" xsrc="" alt="">
												</c:if>
												<c:if test="${not empty news.picture}">
													<img src="<%=staticImageServer%>${news.picture}" xsrc="" alt="">
												</c:if>
											</a>
										</div>
									</li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</section>
				<section class="fr col-35">
					<div class="ml40">
						<header class="comm-title clearix of">
							<h2 class="fl">
								<span class="c-333">员工评价</span>
							</h2>
							<div class="fr mr5">
								<a class="c-666 c-more" title="" href="">
									<em></em>
								</a>
							</div>
						</header>
						<div class="staff-review">
							<ul id="staReview">
								<li>
									<section class="sta-r-wrap">
										<div class="sta-r-face">
											<img src="${ctximg}/static/nxb/web/img/avatar-boy.gif" xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
										</div>
										<section class="hLh20 of mt5">
												<span class="fr">
													<tt class="c-master2 f-fM fsize12">2015/11/04 12:02</tt>
												</span>
											<span class="fsize16 c-333"> 李田田</span>
										</section>
										<section class="i-q-txt mt10">
											<p><span class="c-666 f-fM fsize14">
													李甜同学学习了“<a class="c-orange" href="">移动应用的界面设计</a>”
												</span> </p>
										</section>
									</section>
								</li>
								<li>
									<section class="sta-r-wrap">
										<div class="sta-r-face">
											<img src="${ctximg}/static/nxb/web/img/avatar-boy.gif" xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
										</div>
										<section class="hLh20 of mt5">
												<span class="fr">
													<tt class="c-master2 f-fM fsize12">2015/11/04 12:02</tt>
												</span>
											<span class="fsize16 c-333"> 李田田</span>
										</section>
										<section class="i-q-txt mt10">
											<p><span class="c-666 f-fM fsize14">
													李甜同学学习了“<a class="c-orange" href="">移动应用的界面设计</a>”
												</span> </p>
										</section>
									</section>
								</li>
								<li>
									<section class="sta-r-wrap">
										<div class="sta-r-face">
											<img src="${ctximg}/static/nxb/web/img/avatar-boy.gif" xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
										</div>
										<section class="hLh20 of mt5">
												<span class="fr">
													<tt class="c-master2 f-fM fsize12">2015/11/04 12:02</tt>
												</span>
											<span class="fsize16 c-333"> 李田田</span>
										</section>
										<section class="i-q-txt mt10">
											<p><span class="c-666 f-fM fsize14">
													李甜同学学习了“<a class="c-orange" href="">移动应用的界面设计</a>”
												</span> </p>
										</section>
									</section>
								</li>
								<li>
									<section class="sta-r-wrap">
										<div class="sta-r-face">
											<img src="${ctximg}/static/nxb/web/img/avatar-boy.gif" xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
										</div>
										<section class="hLh20 of mt5">
												<span class="fr">
													<tt class="c-master2 f-fM fsize12">2015/11/04 12:02</tt>
												</span>
											<span class="fsize16 c-333"> 李田田</span>
										</section>
										<section class="i-q-txt mt10">
											<p><span class="c-666 f-fM fsize14">
													李甜同学学习了“<a class="c-orange" href="">移动应用的界面设计</a>”
												</span> </p>
										</section>
									</section>
								</li>
							</ul>
						</div>
					</div>
				</section>
			</div>
		</article>
	</div>
	<!-- 企业动态和员工评价结束 -->
	<!-- 员工统计开始 -->
	<div class="of pb50">
		<article class="container">
			<section>
				<header class="comm-title">
					<h2 class="tac clearfix">
						<span class="c-333">员工统计</span>
					</h2>
				</header>
				<div class="clearfix emp-sta-box">
					<div class="fl w50pre">
						<div id="left_container" class="bor-dot-right pr50" style="width: 85%;margin: auto;height: 260px;"></div>
					</div>
					<div class="fr w50pre">
						<div id="right_container" class="pr50" style="width: 85%;margin: auto;height: 260px;"></div>
					</div>
				</div>
			</section>
		</article>
	</div>
	<!-- 员工统计结束 -->
	<!-- 部门学习计划开始 -->
	<div class="of gwcp-box bg-f8">
		<article class="container">
			<section>
				<header class="comm-title">
					<h2 class="tac clearfix">
						<span class="c-333">部门学习计划</span>
					</h2>
				</header>
				<div class="clearfix emp-sta-box e-s-box1">
					<c:if test="${empty planList}">
						<section class="no-data-wrap">
							<em class="no-data-ico">&nbsp;</em>
							<span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
						</section>
					</c:if>
					<c:if test="${not empty planList}">
						<c:forEach items="${planList}" var="plan">
							<div class="fl w50pre">
								<div class="mr20 bg-fff">
									<div class="emp-s-b-sec">
										<h3 class="hLh30 txtOf"><span class="c-333 fsize20">${plan.name}</span></h3>
										<div class="clearfix pt15 e-s-box">
											<div class="mt10 e-s-mod1 chartProgress" id="container_${plan.id}" peopleNum="${plan.peopleNum!=null?plan.peopleNum:0 }" completeNum="${plan.completeNum!=null?plan.completeNum:0 }" >
												<%--<img src="${ctximg}/static/nxb/web/img/pic/c-circle.png">--%>
											</div>
											<div class="e-s-mod2">
												<section>
													<p class="hLh30 c-666 fsize16">创建于<fmt:formatDate value="${plan.releaseTime}" type="both" pattern="yyyy-MM-dd"/></p>
													<p class="hLh30 c-666 fsize16">计划完成时间：
														<c:choose>
															<c:when test="${plan.overDate>0}">
																${plan.overDate}天
															</c:when>
															<c:otherwise>已结束</c:otherwise>
														</c:choose>
													</p>
												</section>
												<section class="clearfix mt15 pr">
													<div class=" e-m-stasec">
														<p class="c-666 fsize14 txtOf"><span class="c-orange">${plan.peopleNum}</span>人参加</p>
														<p class="c-666 fsize14 txtOf">已完成：<span class="c-orange">${plan.completeNum}</span>人</p>
														<p class="c-666 fsize14 txtOf">未完成：<span class="c-orange">${plan.peopleNum-plan.completeNum}</span>人</p>
													</div>
												</section>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</section>
		</article>
	</div>
	<!-- 部门学习计划结束 -->
	<!-- 岗位测评开始 -->
	<div class="of gwcp-box">
		<article class="container">
			<section>
				<header class="comm-title">
					<h2 class="tac clearfix">
						<span class="c-333">岗位测评</span>
					</h2>
				</header>
				<div class="c-job-test">
					<ul class="clearfix of">
						<li>
							<div class="c-j-t-wrap">
								<a href=""><img src="${ctximg}/static/nxb/web/img/pic/c-j-pic1.jpg" class="img-responsive"></a>
							</div>
						</li>
						<li>
							<div class="c-j-t-wrap">
								<a href=""><img src="${ctximg}/static/nxb/web/img/pic/c-j-pic2.jpg" class="img-responsive"></a>
							</div>
						</li>
						<li>
							<div class="c-j-t-wrap">
								<a href=""><img src="${ctximg}/static/nxb/web/img/pic/c-j-pic3.jpg" class="img-responsive"></a>
							</div>
						</li>
						<li>
							<div class="c-j-t-wrap">
								<a href=""><img src="${ctximg}/static/nxb/web/img/pic/c-j-pic4.jpg" class="img-responsive"></a>
							</div>
						</li>
					</ul>
				</div>
			</section>
		</article>
	</div>
	<!-- 岗位测评结束 -->
	<script type="text/javascript" src="${ctximg}/static/common/echarts/echarts.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/web/index.js"></script>
</body>
</html>