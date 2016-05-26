<%--<%@ page import="weibo4j.util.WeiboConfig" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<%--<html xmlns:wb="http://open.weibo.com/wb">--%>
<head>
	<title>${course.name }</title>
	<%--<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>--%>
</head>
<body>
<div class="bg-fa of">
	<section class="container">
		<section class="path-wrap txtOf hLh30">
			<a class="c-999 fsize14" title="" href="/">首页</a> \
			<c:if test="${course.sellType=='PACKAGE'}">
				<a class="c-999 fsize14" title="" href="${ctx}/front/showPackageList">套餐列表</a>
			</c:if>
			<c:if test="${course.sellType=='COURSE'}">
				<a class="c-999 fsize14" title="" href="${ctx}/front/showCourseList">课程列表</a>
			</c:if>
			 \ <span class="c-333 fsize14">${course.name}</span>
		</section>
		<div class="i-box clearfix pr">
			<article class="c-v-pic-wrap">
				<section id="videoPlay" class="p-h-video-box pr">
					<c:choose>
						<c:when test="${not empty course.logo}">
							<img class="dis c-v-pic" alt="${course.name}" src="<%=staticImageServer%>/${course.logo}">
						</c:when>
						<c:otherwise>
							<img class="dis c-v-pic" alt="${course.name}" src="<%=staticImageServer%>/${courseimagemap.courseimage.url}">
						</c:otherwise>
					</c:choose>
					<c:if test="${course.sellType=='COURSE'}">
						<section class="c-cou-mask"></section>
						<a class="v-play-btn" title="${course.name}" onclick="openListFree()" href="javascript:void(0)">
							<em></em>
						</a>
					</c:if>
				</section>
			</article>
			<c:if test="${course.sellType=='PACKAGE'}">
				<div class="c-v-icon"><img src="${ctx}/static/nxb/web/img/c-package-icon.png" width="120" height="120"></div>
			</c:if>
			<article class="fl c-a-wrap">
				<div class="c-attr-box">
					<h3 class="hLh30 of unFw mt5">
						<font class="c-333 fsize24 f-fM">${course.name}</font>
					</h3>
					<div class="i-q-txt pt10 mt5 i-q-txt2">
						<p class="c-999 fsize14">${course.title}</p>
					</div>
					<div class="clearfix mt20">
						<p><span class="c-orange fsize28 vam"><span class="fsize16 c-666">价格：</span><span class="c-orange fsize16">￥</span>${course.currentprice}</span>
							<span class="ml20 c-666 fsize16 vam">${course.buycount}</span><span class="fsize16 c-999 vam">人已购买</span></p>
					</div>
					<div class="mt20">
						<p class="fsize16 c-666">讲师：
							<c:forEach items="${course.teacherList }" var="tea" varStatus="index">
								<c:if test="${index.index==0}">
									<span class="c-blue1 hand" onclick="window.location.href='${ctx}/front/teacher/${tea.id}'">${tea.name }</span>
								</c:if>
								<c:if test="${index.index!=0}">
									<span class="c-blue1 hand ml10" onclick="window.location.href='${ctx}/front/teacher/${tea.id}'">${tea.name }</span>
								</c:if>
							</c:forEach>
						</p>
					</div>
					<div class="mt20">
						<p class="fsize16 c-666">
							<c:if test="${course.sellType=='COURSE'}">
								课时：<span class="c-blue1">${course.totalLessionnum}</span>
							</c:if>
							<c:if test="${course.sellType=='PACKAGE'}">
								包含课程：<span class="c-blue1">${course.totalLessionnum}</span>门
							</c:if>
						</p>
					</div>
					<div class="mt20 clearfix">
						<div class="fl learn-btn">
							<c:choose>
								<c:when test="${isPlay}">
									<c:if test="${course.sellType=='COURSE'}">
										<a href="javascript:void(0)" onclick="doPlay(${course.id })" title="立即观看" class="bm-lr-btn">立即观看</a>
									</c:if>
									<c:if test="${course.sellType=='PACKAGE'}">
										<a href="javascript:void(0)" onclick="doPlay(${coursePackage.id })" title="立即观看" class="bm-lr-btn">立即观看</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" title="立即购买" onclick="buySellType('${course.id}')" class="bm-lr-btn">立即购买</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="fl c-ml20">
							<c:if test="${course.losetype==0 }">
								<span class="fsize12 c-666">
									有效期：<fmt:formatDate value="${course.loseAbsTime }" />
									<tt class="am-text-warning">（在此之前可反复观看）</tt>
								</span>
							</c:if>
							<c:if test="${course.losetype==1 }">
								<span class="fsize12 c-666">
									从购买之日起${course.loseTime }天
									<tt class="am-text-warning">（在此之间可反复观看）</tt>
								</span>
							</c:if>
							<div class="mt10 pr">
								<div class="fl">
									<c:if test="${alreadyFavorite}">
										<a class="c-cou-btn sc-already" href="javascript:void(0)"><em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">已收藏</tt></a>
									</c:if>
									<c:if test="${!alreadyFavorite}">
										<a class="c-cou-btn" onclick="house('${course.id}')"><em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">收藏</tt></a>
									</c:if>
								</div>
								<div class="fl ml15" id="c-share">
									<a class="c-cou-btn c-share"><em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">分享</tt></a>
									<div class="share-box undis">
										<div class="bdsharebuttonbox bdshare-button-style0-16" id="bdshare" data-bd-bind="1455678358863" style="right: -160px;"><a data-cmd="more" class="bds_more" href="#"></a><a data-cmd="qzone" class="bds_qzone" href="#" title="分享到QQ空间"></a><a data-cmd="tsina" class="bds_tsina" href="#" title="分享到新浪微博"></a><a data-cmd="tqq" class="bds_tqq" href="#" title="分享到腾讯微博"></a><a data-cmd="renren" class="bds_renren" href="#" title="分享到人人网"></a><a data-cmd="weixin" class="bds_weixin" href="#" title="分享到微信"></a></div>
										<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
									</div>
								</div>
								<%--<div class="fl ml15">
									<wb:share-button appkey="<%=WeiboConfig.getValue("client_ID")%>" addition="simple" type="button"></wb:share-button>
								</div>--%>
								<%--<div class="fl ml15">
									<a class="c-cou-btn c-rec" id="c-i-tabTitle" name="c-r"><em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">推荐课程</tt></a>
								</div>--%>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
		<div class="mt20 mb20">
			<div class="clearfix">
				<div class="fl col-75">
					<div class="mr20">
						<div class="c-title-box">
							<ol class="js-tap clearfix">
								<c:if test="${course.sellType=='COURSE'}">
									<li class="current expandClass" lang="info"><a href="javascript:void(0)" title="课程介绍">课程介绍</a></li>
									<li class="expandClass" lang="catalog"><a href="javascript:void(0)" title="课程目录">课程目录</a></li>
								</c:if>
								<c:if test="${course.sellType=='PACKAGE'}">
									<li class="current expandClass" lang="info"><a href="javascript:void(0)" title="套餐介绍">套餐介绍</a></li>
									<li class="expandClass"><a href="javascript:void(0)" title="包含课程">包含课程(${coursePackageList==null?0:coursePackageList.size()})</a></li>
								</c:if>
								<li class="expandClass" lang="assess"><a href="javascript:void(0)" title="课程评论">课程评论(${course.commentcount})</a></li>
								<li class="expandClass" lang="answer"><a href="javascript:void(0)" title="课程咨询">课程咨询</a></li>
							</ol>
						</div>
						<div>
							<%-- 课程介绍 --%>
							<div class="i-box publicClass undis">
								<%--<div class="c-cou-pic">--%>
									<%--<img src="./img/pic/c-cou-banner.jpg" width="760" height="120">--%>
								<%--</div>--%>
								<div class="mt10 i-q-t3">
									<c:choose>
										<c:when test="${not empty course.context}">${course.context}</c:when>
										<c:otherwise>
											<section class="mt30 mb30 tac">
												<em class="no-data-ico cTipsIco">&nbsp;</em>
												<span class="c-666 fsize14 ml10 vam">课程简介小编正在积极整理中~</span>
											</section>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<%-- 课程目录 --%>
							<div class="i-box publicClass undis">
								<c:if test="${course.sellType=='PACKAGE'}">
									<c:if test="${empty coursePackageList}">
										<section class="mt30 mb30 tac">
											<em class="no-data-ico cTipsIco">&nbsp;</em>
											<span class="c-666 fsize14 ml10 vam">课程套餐小编正在积极整理中~</span>
										</section>
									</c:if>
									<c:if test="${not empty coursePackageList}">
										<ul class="of job-cou-list c-recomm-course">
											<c:forEach items="${coursePackageList }" var="course">
												<li>
													<div class="cc-l-wrap">
														<a class="course-img" href="${ctx}/front/couinfo/${course.id}">
															<c:choose>
																<c:when test="${not empty course.logo}">
																	<img class="img-responsive" alt="${course.name}" src="<%=staticImageServer%>/${course.logo}">
																</c:when>
																<c:otherwise>
																	<img class="img-responsive" alt="${course.name}" src="<%=staticImageServer%>/${courseimagemap.courseimage.url}">
																</c:otherwise>
															</c:choose>
														</a>
														<div class="j-c-desc-wrap">
															<h3 class="hLh30 txtOf pt10">
																<a class="j-course-title" href="${ctx}/front/couinfo/${course.id}" target="_blank">${course.name}</a>
															</h3>
															<div class="clearfix of mt15 cj-cou-ds">
																<span class="fl c-999 f-fM txtOf">讲师：
																	<c:forEach items="${course.teacherList}" var="teacher">
																		<a class="c-666 mr10" href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
																	</c:forEach>
																</span>
																<span class="fr c-999 f-fM ">课时：<span class="c-666">${course.totalLessionnum}</span></span>
															</div>
															<dl class="cj-cou-desc of">
																<dd>
																	<div class="c-c-sbox txtOf" title="播放量：${course.playcount}">
																		<em class="icon12 c-play-num"></em>
																		<tt class="fsize14">${course.playcount}</tt>
																	</div>
																</dd>
																<dd>
																	<div class="c-c-sbox txtOf" title="评论数：${course.commentcount}">
																		<em class="icon12 c-review-num"></em>
																		<tt class="fsize14">${course.commentcount}</tt>
																	</div>
																</dd>
																<dd>
																	<div style="border-right:none;" class="c-c-sbox txtOf" title="价格：${course.currentprice}元">
																		<em class="icon12 c-couse-v"></em>
																		<tt class="fsize14  vam">${course.currentprice}</tt>
																	</div>
																</dd>
															</dl>
														</div>
													</div>
												</li>
											</c:forEach>
										</ul>
									</c:if>
								</c:if>
							</div>
							<%-- 课程评论 --%>
							<div class="i-box publicClass undis">

							</div>
							<%-- 课程咨询 --%>
							<div class="i-box publicClass undis">

							</div>
						</div>
						<c:if test="${course.sellType=='COURSE'}">
							<%-- 推荐课程 --%>
							<div class="i-box mt20">
								<div class="mb20" id="c-r-content"><span class="fsize16 c-333">推荐课程</span></div>
								<c:if test="${empty courseList}">
									<div class="mt40">
										<!-- /无数据提示 开始-->
										<section class="no-data-wrap">
											<em class="no-data-ico cTipsIco">&nbsp;</em>
											<span class="c-666 fsize14 ml10 vam">没有推荐课程，小编正在努力准备中...</span>
										</section>
									</div>
								</c:if>
								<c:if test="${not empty courseList}">
									<ul class="of job-cou-list c-recomm-course">
										<c:forEach items="${courseList}" var="course">
											<li>
												<div class="cc-l-wrap">
													<a class="course-img" href="${ctx}/front/couinfo/${course.id }">
														<c:choose>
															<c:when test="${not empty course.logo }">
																<img src="<%=staticImageServer%>/${course.logo}" xsrc="" alt="${course.name}" class="img-responsive"></c:when>
															<c:otherwise>
																<img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" xsrc="" alt="${course.name}" class="img-responsive">
															</c:otherwise>
														</c:choose>
													</a>
													<div class="j-c-desc-wrap">
														<h3 class="hLh30 txtOf pt10">
															<a class="j-course-title" title="${course.name}" href="${ctx}/front/couinfo/${course.id }">${course.name}</a>
														</h3>
														<div class="clearfix of mt15 cj-cou-ds">
															<span class="fl c-999 f-fM txtOf">讲师：
																<c:forEach items="${course.teacherList}" var="teacher">
																	<a class="c-666 mr10" href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
																</c:forEach>
															</span>
															<span class="fr c-999 f-fM ">课时：<span class="c-666">${course.totalLessionnum}</span></span>
														</div>
														<dl class="cj-cou-desc of">
															<dd>
																<div class="c-c-sbox txtOf" title="播放量：${course.playcount}">
																	<em class="icon12 c-play-num"></em>
																	<tt class="fsize14">${course.playcount}</tt>
																</div>
															</dd>
															<dd>
																<div class="c-c-sbox txtOf" title="评论数：${course.commentcount}">
																	<em class="icon12 c-review-num"></em>
																	<tt class="fsize14">${course.commentcount}</tt>
																</div>
															</dd>
															<dd>
																<div style="border-right:none;" class="c-c-sbox txtOf" title="价格：${course.currentprice}元">
																	<em class="icon12 c-couse-v"></em>
																	<tt class="fsize14  vam">${course.currentprice}</tt>
																</div>
															</dd>
														</dl>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</c:if>
					</div>
				</div>
				<div class="fr col-25 mt-20">
					<div class="i-box">
						<c:forEach items="${course.teacherList}" var="teacher" begin="0" end="2">
							<div class="c-teacher tac speci-inv-t">
								<div class="b-l-pic">
									<a href="${ctx}/front/teacher/${teacher.id}">
										<c:if test="${not empty teacher.picPath}">
											<img src="<%=staticImageServer%>${teacher.picPath}">
										</c:if>
										<c:if test="${empty teacher.picPath}">
											<img src="${ctx}/static/common/images/user_default.png">
										</c:if>
									</a>
								</div>
								<div class="mt15"><p class="c-333 fsize16">讲师：<a href="${ctx}/front/teacher/${teacher.id}" class="c-333">${teacher.name}</a></p></div>
								<div class="c-tea-desc mt10"><p>${teacher.career}</p></div>
							</div>
						</c:forEach>
					</div>
					<div class="i-box mt20">
						<div class="mb10"><span class="fsize16 c-333">学习此课的人（<span id="studyNum">0</span>）</span></div>
						<section class="c-learn-body">
							<c:choose>
								<c:when test="${empty userExpandList}">
									<div class="mt40">
										<!-- /无数据提示 开始-->
										<section class="no-data-wrap">
											<em class="no-data-ico cTipsIco">&nbsp;</em>
											<span class="c-666 fsize14 ml10 vam">当前还没有人学过此课程...</span>
										</section>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach items="${userExpandList}" var="userExpand" varStatus="index">
										<span title="${userExpand.nickname!=null?userExpand.nickname:userExpand.email}">
											<c:if test="${not empty userExpand.avatar}">
												<c:if test="${fn:contains(userExpand.avatar,'http' )}">
													<img src="${userExpand.avatar}">
												</c:if>
												<c:if test="${!fn:contains(userExpand.avatar,'http' )}">
													<img src="<%=staticImageServer%>${userExpand.avatar}">
												</c:if>
											</c:if>
											<c:if test="${empty userExpand.avatar}">
												<img src="${ctx}/static/common/images/user_default.png">
											</c:if>
											<tt class="c-999 txtOf">${userExpand.nickname!=null?userExpand.nickname:userExpand.email}</tt>
										</span>
									</c:forEach>
									<script type="text/javascript">
										var studyNum = '${userExpandList.size()}';
										$("#studyNum").html(studyNum!=''?studyNum:0);
									</script>
								</c:otherwise>
							</c:choose>
						</section>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<form action="${ctx}/front/playkpoint/${course.id}" method="post" id="changeForm">
	<input type="hidden" id="kpointId" name="kpointId" value="${kpoint.id}"/>
</form>
<script type="text/javascript" src="${ctximg}/static/common/webutils.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/front/course/course.js"></script>
<script type="text/javascript">
	// 课程id
	var courseId='${course.id}';
	// 课程类型
	var sellType = '${course.sellType}';
	// 过期类型
	var loseType='${course.losetype }';
	// 过期时间
	var loseTimeTime = '${course.loseAbsTime }';

	var msg = getCookieFromServer("msg");

	// 接收消息
	if(isNotEmpty(msg)&&msg=='false'){
		DeleteCookie("msg");
		dialogFun("提示消息","您未购买课程或课程已过期",0);
	}
</script>
</body>
</html>