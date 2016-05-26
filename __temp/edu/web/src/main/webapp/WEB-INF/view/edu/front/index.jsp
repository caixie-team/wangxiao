<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/web/index.js"></script>
</head>
<body class="W-body">
	<!-- /幻灯片 -->
	<div class="oSlide">
		<section class="oSlide-P" id="oSlideFun">
			<ul>
				<c:forEach var="image" items="${websiteImages.indexCenterBanner}" varStatus="status">
					<li <c:if test="${status.index==0}">class='oShow'</c:if>>
						<a target="_blank" href="<c:if test='${image.linkAddress!=null&&image.linkAddress!=""}'>${image.linkAddress}</c:if>" name="${image.color}"><img src="<%=staticImageServer%>${image.imagesUrl}" /></a>
					</li>
				</c:forEach>
				</ul>
			<span id="oSbtn" class="oSbtn">
				<c:forEach var="image" items="${websiteImages.indexCenterBanner}" varStatus="status">
					<a href="javascript:void(0)" <c:if test="${status.index==0}">class="on"</c:if>>&nbsp;</a>
				</c:forEach>
			</span>
		</section>
		<section class="slideColorBg" style="background: #333;"></section>
	</div>
	<!-- /幻灯片结束 -->
	<!-- /搜索栏 -->
	<div class="search-wrap">
		<article class="w1000">
			<div class="clearfix pr">
				<span class="nnIcon"><em class="icon14">&nbsp;</em><tt class="c-666 f-fM fsize14 ml5">公告：</tt></span>
				<ul class="newsNotice">
					<li>
						<c:forEach items="${noticeList }" var="Atricle" varStatus="index">
							<c:if test="${index.count<4 }">
							<section class="nnLink"><a href="${ctx}/front/toArticle/${Atricle.id}" target="_blank">${Atricle.title }</a></section>
							</c:if>
						</c:forEach>
					</li>
					<c:if test="${noticeList.size()>3}">
                    <li>
                        <c:forEach items="${noticeList}" var="Atricle" varStatus="index">
                            <c:if test="${index.count>3}">
                            <section class="nnLink"><a href="${ctx}/front/toArticle/${Atricle.id}">${Atricle.title }</a></section>
                            </c:if>
                        </c:forEach>
                    </li>
                    </c:if>
				</ul>
			</div>
		</article>
	</div>
	<!-- /搜索栏 结束-->
	<div id="SL-container">
	<!-- /最受欢迎课程推荐 -->
	<div class="mt30">
		<section class="w1000">
			<header class="clearfix">
				<section class="fr">
					<div class="mt10">
						<span class="vam disIb"><a href="/front/showcoulist" title="更多课程" class="show-all-btn"><em class="icon18 s-a-icon">&nbsp;</em>&nbsp;更多课程</a></span>
					</div>
				</section>
				<section class="fl">
					<ol class="of h-title-1">
						<li class="current"><a href="javascript:void(0)" onclick="getNewFreeSell(this,1)" title="受欢迎的课程">受欢迎的课程</a></li>
						<li><a href="javascript:void(0)" onclick="getNewFreeSell(this,2)" title="最新免费课程">最新免费课程</a></li>
					</ol>
				</section>
			</header>
			<article class="mt30">
				<!-- 最受欢迎课程，开始  -->
				<ul id="newWelcomeSellWayListUlId" class="courses-list-1 of clearfix">
					<c:forEach items="${mapCourseList.index_course_1}" var="courseHot">
						<li>
							<section class="c-l-wrap pr">
								<div class="c-l-desc">
									<section class="hLh20 of">
										<a href="${ctx}/front/couinfo/${courseHot.id}"  target="_blank" title="${courseHot.name}" class="fsize14 c-fff">${courseHot.name}</a>
									</section>
									<section class="c-attr-3 mt10 of">
										<span title="价格：${courseHot.currentprice}￥"><em class="cost icon16 vam">&nbsp;</em>
										<tt class="vam ml5">${courseHot.currentprice}￥</tt></span>
										 <span title="评论：${courseHot.commentcount}">
										 	<em class="comm-num icon16 vam">&nbsp;</em>
											<tt class="vam ml5">
												评论：${courseHot.commentcount}
											</tt>
										</span>
										<span title="浏览：${courseHot.viewcount}">
											<em class="s-listen icon16 vam">&nbsp;</em>
											<tt class="vam ml5">浏览：${courseHot.viewcount}</tt>
										</span>
									</section>
									<section class="mt10">
										<div class="of hLh20">
											<span title="讲师：" class="vam">
												<em class="teachers icon16 vam">&nbsp;</em><tt class="vam ml5">讲师：</tt>
											</span> 
											<span class="vam teac-wrap">
												<c:forEach items="${courseHot.teacherList}" var="teacher">
													<a  target="_blank" class="c-666" href="${ctx }/front/teacher/${teacher.id}" title="${teacher.name}">${teacher.name}</a>&nbsp;
												</c:forEach>
											</span>
										</div>
									</section>
									<section class="c-attr-3 mt15">
										<span>
											<a  target="_blank" href="${ctx}/front/couinfo/${courseHot.id}" title="开通课程" class="yellow-btn">
												<tt class="vam c-666">开通课程</tt><em class="ml5 r-arrow icon16 vam">&nbsp;</em>
											</a>
										</span> 
										<span>
											<b class="star-1-5 ml10 vam" title="星级：5星">&nbsp;</b> 
										</span> 
										<span class="tac">
											<a href="javascript:void(0)" onclick="house(${courseHot.id})" title="收藏课程" class="c-yellow">收藏课程</a>
										</span>
									</section>
								</div>
                                <a  target="_blank" href='${ctx}/front/couinfo/${courseHot.id}' title="${courseHot.name}" >
								<c:choose>
									<c:when test="${not empty courseHot.logo}">
										<img xSrc="<%=staticImageServer %>${courseHot.logo}" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" style="cursor: pointer;" height="225" width="300" alt="${courseHot.name}" >
									</c:when>
									<c:otherwise>
										<img xSrc="${ctximg}/static/edu/images/default/default_goods.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" style="cursor: pointer;"	height="225" width="300" alt="${courseHot.name}">
									</c:otherwise>
								</c:choose>
                                </a>
							</section>
						</li>
					</c:forEach>
				</ul>
				<!-- 最受欢迎课程，结束  -->
				
				<!-- 最新免费课程，开始 -->
				<ul style="display: none;" id="newFreeSellWayListUlId" class="courses-list-1 of clearfix">
					<c:forEach items="${mapCourseList.index_course_2}" var="courseHot">
						<li>
							<section class="c-l-wrap pr">
								<div class="c-l-desc">
									<section class="hLh20 of">
										<a   target="_blank" href="${ctx}/front/couinfo/${courseHot.id}" title="${courseHot.name}" class="fsize14 c-fff">${courseHot.name}</a>
									</section>
									<section class="c-attr-3 mt10 of">
										<span title="价格：${courseHot.currentprice}￥"><em class="cost icon16 vam">&nbsp;</em>
										<tt class="vam ml5">${courseHot.currentprice}￥</tt></span>
										 <span title="评论：${courseHot.commentcount}">
										 	<em class="comm-num icon16 vam">&nbsp;</em>
											<tt class="vam ml5"> 评论： ${courseHot.commentcount} </tt>
										</span>
										<span title="浏览：${courseHot.viewcount}">
											<em class="s-listen icon16 vam">&nbsp;</em>
											<tt class="vam ml5">浏览：${courseHot.viewcount}</tt>
										</span>
									</section>
									<section class="mt10">
										<div class="of hLh20">
											<span title="讲师：" class="vam">
												<em class="teachers icon16 vam">&nbsp;</em><tt class="vam ml5">讲师：</tt>
											</span> 
											<span class="vam teac-wrap">
												<c:forEach items="${courseHot.teacherList}" var="teacher">
													<a  target="_blank" class="c-666" href="${ctx}/front/teacher/${teacher.id}" title="${teacher.name}">${teacher.name}</a>&nbsp;
												</c:forEach>
											</span>
										</div>
									</section>
									<section class="c-attr-3 mt15">
										<span>
											<a target="_blank"  href="${ctx}/front/couinfo/${courseHot.id}" title="开通课程" class="yellow-btn">
												<tt class="vam c-666">开通课程</tt><em class="ml5 r-arrow icon16 vam">&nbsp;</em>
											</a>
										</span> 
										<span>
											<b class="star-1-5 ml10 vam" title="星级：5星">&nbsp;</b> 
										</span> 
										<span class="tac">
											<a href="javascript:void(0)" onclick="house(${courseHot.id})" title="收藏课程" class="c-yellow">收藏课程</a>
										</span>
									</section>
								</div>
                                <a href='${ctx}/front/couinfo/${courseHot.id}' title="${courseHot.name}" target="_blank">
								<c:choose>
									<c:when test="${not empty courseHot.logo}">
										<img xSrc="<%=staticImageServer %>${courseHot.logo}" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" style="cursor: pointer;" height="225" width="300" alt="${courseHot.name}" >
									</c:when>
									<c:otherwise>
										<img xSrc="${ctximg}/static/edu/images/default/default_goods.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" style="cursor: pointer;" height="225" width="300" alt="${courseHot.name}">
									</c:otherwise>
								</c:choose>
                                </a>
							</section>
						</li>
					</c:forEach>
				</ul>
				<!-- 最新免费课程，结束 -->
			</article>
		</section>
	</div>
	<!-- /最受欢迎课程推荐 结束 -->
	
	<!-- /ads-1000x220   开始 首页广告图1-->
	<div style="+margin-top: 20px;">
		<div class="w1000">
			
			<c:forEach var="image" items="${websiteImages.indexAdvertImages1}" >
				<a href="${image.linkAddress}" target="_blank">
				<img src="<%=staticImageServer %>${image.imagesUrl}" class="dis" height="220" width="1000">
				</a>
			</c:forEach>
		</div>
	</div>
	<!-- /ads-1000x220 结束 首页广告图1-->
	
	<!-- /分类课程 -->
	<div class="mt30">
		<header class="sort-c-head">
			<section class="w1000">
				<ol class="s-c-title">
					<li class="subjectlibar">
						<a href="javascript:void(0)" id="allsubjectcourse" onclick="querycourseBySubjectId(this,0)" title="所有专业">所有专业</a>
						<em class="icon30">&nbsp;</em>
					</li>
					<c:forEach items="${subjectList}" var="subject">
						<li class="subjectlibar"><a href="javascript:void(0)" onclick="querycourseBySubjectId(this,${subject.subjectId})"
							title="${subject.subjectName}">${subject.subjectName}</a><em
							class="icon30">&nbsp;</em></li>
					</c:forEach>
					</ol>
				<div class="clear"></div>
			</section>
		</header>
		<article class="w1000">
			<section class="mt30">
				<div>
					<ol id="s_c_list_ol_ID" class="s-c-list">
						 
					</ol>
					<div class="clear"></div>
				</div>
			</section>
		</article>
	</div>
	<!-- /自分类课程 结束-->
	<!-- /自定义课程 -->
	<div class="c-c-wrap">
		<article class="w1000">
			<aside class="fr w300">
				<h4 class="fl">
					<span class="show-all-btn">自定义课程排行榜/Top10</span>
				</h4>
				<div class="clear"></div>
				<section class="c-c-list">
					<ul>
					<c:forEach items="${cusCourseRankList}" var="customerCourse" varStatus="index">
						<li>
							<span class="c-c-time c-999"><fmt:formatDate type="both" value="${customerCourse.createTime}"/></span> 
							<tt class="order b-master c-fff">
								<font class="f-fM fsize16">${index.count}</font>
							</tt> 
							<a target="_blank" href="${ctx}/front/customerCourse?queryCustomerCourse.id=${customerCourse.id}#intro" title="${customerCourse.title }">${customerCourse.title }</a>
					   </li>
					</c:forEach>
					</ul>
				</section>
			</aside>
			<section class="fl w650">
				<header>
					<h2 class="hLh30">
						<em class="icon30 c-c-c-icon mr5">&nbsp;</em><span class="f-fM unFw c-master fsize20">自定义课程<tt class="ml20">
								<a target="_blank" href="${ctx }/front/customerCourse#intro" title="查看自定义课程" class="c-999 fsize12 unFw">查看自定义课程&gt;&gt;</a>
							</tt></span>
					</h2>
				</header>
				<article class="mt20">
					<ul class="clearfix c-c-l-infor">
					<c:if test="${not empty QueryCustomerCourseList}">
					<c:forEach items="${QueryCustomerCourseList}" var="queryCustomerCourse">
						<li>
								<section class="fl">
									<div class="c-c-i-wrap">
										<em class="icon24 c-sj">&nbsp;</em>
										<section class="ml10 mr10">
											<h5 class="hLh20 of mt10">
												<a  target="_blank" href="${ctx}/front/customerCourse?queryCustomerCourse.id=${queryCustomerCourse.id}#intro" title="${queryCustomerCourse.title}" class="c-4e f-fM fsize16">${queryCustomerCourse.title}</a>
											</h5>
											<div class="s-c-desc pt10 pb10">
												<p class="c-999">${queryCustomerCourse.content}</p>
											</div>
											<div class="clearfix mt5">
												<span class="fr"><em class="icon16 c-p-num">&nbsp;</em><font class="c-master fsize12">${queryCustomerCourse.joinNum}人想学</font></span>
												<span class="fl"><font class="fsize12 c-999"><fmt:formatDate value="${queryCustomerCourse.createTime}"/></font></span>
											</div>
										</section>
									</div>
								</section>
								<aside class="fl">
									<div class="c-c-member">
									<c:if test="${not empty queryCustomerCourse.userExpandDto.avatar}">
										<img src="<%=staticImageServer %>${queryCustomerCourse.userExpandDto.avatar}" height="40" width="53" alt="">
									</c:if>
									<c:if test="${empty queryCustomerCourse.userExpandDto.avatar}">
										<img src="<%=imagesPath%>/static/common/images/user_default.jpg" height="40" width="53" alt="">
									</c:if>
									<p class="mt5 c-999">${fn:substring(queryCustomerCourse.userExpandDto.showname,0,16)}</p>
									</div>
								</aside>
							</li>
							</c:forEach>
						</c:if>
						<c:if test="${QueryCustomerCourseList==null||QueryCustomerCourseList.size()<0}">
						<li><section class="fl">
									<div class="c-c-i-wrap">
										<em class="icon24 c-sj">&nbsp;</em>暂无自定义课程
									</div>
						     </section></li>
						</c:if>
						</ul>
				</article>
				<div class="pr dis pt20">
					<span class="c-c-in-btn"><a href="${ctx}/front/customerCourse" title="我要自定义课程" class="c-333">我要自定义课程</a></span>
					<h2 class="c-c-in">
						<span class="fl"><font class="fsize16 f-fM c-fff unFw">想学什么课程，让我们来帮你实现！已有
								<tt class="c-yellow fsize20 f-fG">${cusCourseJoinNum}</tt> 人参加
						</font></span>
					</h2>
				</div>
			</section>
			<div class="clear"></div>
		</article>
	</div>
	<!-- /自定义课程 结束 -->
	<!-- /ads-1000x120 -->
	<div class="mt30">
		<div class="w1000">
			
			<c:forEach var="image2" items="${websiteImages.indexAdvertImages2}" >
			<a href="${image2.linkAddress}"  target="_blank">
				<img src="<%=staticImageServer %>${image2.imagesUrl}" class="dis" class="dis ads-1" height="120" width="1000">
			</a>
			</c:forEach>
			
		</div>
	</div>
	<!-- /ads-1000x120 结束 -->
	
	<!-- / 课程列表 -->
	<div class="mt30">
		<section class="w1000">
			<aside class="fr w300">
				<div class="of">
					<span class="fr mt10"><a href="${ctx }/front/showcoulist" title="更多..." class="c-666"><u>更多&gt;&gt;</u></a></span>
					<h4 class="fl">
						<span class="c-l-top5"><font class="c-yellow">课程排行榜/Top5</font></span>
					</h4>
					<div class="clear"></div>
				</div>
				<div class="c-list-1">
					<ol>
					<c:forEach items="${mapCourseList.index_course_5}" var="sellNumCourse" varStatus="index">
							<li>
								<section class="fl pr mr15" style="cursor: pointer;">
									<tt class="order b-yellow pa"> <font class="fsize16 f-fM c-666">${index.index+1}</font></tt>
                                    <a target="_blank" href='${ctx}/front/couinfo/${sellNumCourse.id}' title="${sellNumCourse.name}">
                                    <c:choose>
										<c:when test="${not empty sellNumCourse.logo }">
											<img width="80" height="60" alt="${sellNumCourse.name}" class="dis" xSrc="<%=staticImageServer %>${sellNumCourse.logo}" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" />
										</c:when>
										<c:otherwise>
											<img width="80" height="60" alt="${sellNumCourse.name}" class="dis" xSrc="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" />
										</c:otherwise>
									</c:choose>
                                    </a>
								</section>
								<h3 class="hLh30 of unFw">
									<a target="_blank" class="fsize14 c-666" title="${sellNumCourse.name}" href="${ctx}/front/couinfo/${sellNumCourse.id}" target="_blank">${sellNumCourse.name}</a>
								</h3>
								<div class="hLh20 of mt5"> <p class="c-999">${sellNumCourse.title}</p> </div>
							</li>
						</c:forEach>
						
					</ol>
				</div>
				<div class="mt40">
					<section>
						<h3 class="of a-title unFw">
							<tt class="fr">
								<a href="${ctx}/front/articlelist/0" title="更多..." class="c-666 fsize12"><u>更多&gt;&gt;</u></a>
							</tt>
							<font class="c-333 f-fM fsize18">文章列表排行</font>
						</h3>
					</section>
					<section class="article-list-1">
						<ol>
							<c:forEach var="articleList" items="${articleList}" varStatus="index">
								<li>
								<tt class="order"> <font class="f-fM fsize16">${index.index+1}</font></tt>
								 <a target="_blank" href="${ctx}/front/toArticle/${articleList.id}" title="${articleList.title}">${articleList.title}</a>
								</li>
							</c:forEach>
						</ol>
					</section>
				</div>
			</aside>
			<article class="fl w650">
				<section class="c-l-title-wrap">
					<ul class="c-l-t">
						<li class="current"><a onclick="queryPageIndex(this,4)" href="javascript:void(0)" title="精品课程列表">精品课程列表</a></li>
						<li><a onclick="queryPageIndex(this,1)" href="javascript:void(0)" title="推荐课程列表">推荐课程列表</a></li>
					</ul>
				</section>
				<section>
					<!-- 精品课程列表，开始 -->
					<ul class="c-c-l" id="oneRightUlId">
						<c:forEach items="${mapCourseList.index_course_3}" var="courseOne">
							<li>
								<section class="fl c-c-img" style="cursor: pointer;" >
                                    <a href='${ctx}/front/couinfo/${courseOne.id}' target="_blank" title="${courseOne.name}">
									<c:choose>
										<c:when test="${not empty courseOne.logo}">
											<img xSrc="<%=staticImageServer%>${courseOne.logo}" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" height="116" width="154" alt="${courseOne.name}">
										</c:when>
										<c:otherwise>
											<img xSrc="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" height="116" width="154" alt="${courseOne.name}">
										</c:otherwise>
									</c:choose>
                                    </a>
								</section>
								<h4 class="hLh20 of unFw">
									<a target="_blank" href="${ctx}/front/couinfo/${courseOne.id}" title="${courseOne.name}" class="c-666 fsize18 f-fM">${courseOne.name}</a>
								</h4>
								<div class="s-c-desc pt10 pb10">
									<p class="c-999">${courseOne.title}</p>
								</div>
								<div class="of mt15">
									<section class="fr c-999">
										<span>价格：${courseOne.currentprice}￥</span> 
										<span class="ml5"><tt class="vam">浏览量：</tt><b class="star-1-3 vam" title="浏览量:${courseOne.pageViewcount}">${courseOne.pageViewcount}</b></span>
									</section>
									<section class="fl c-c-teacher">
										<dl class="of">
											<dt> <font class="c-666">讲师：</font> </dt>
											<dd>
												<c:forEach items="${courseOne.teacherList}" var="teacher">
													<a class="c-666" target="_blank"  href="${ctx}/front/teacher/${teacher.id}" title="${teacher.name}">${teacher.name}</a>&nbsp;
												</c:forEach>
											</dd>
										</dl>
									</section>
								</div>
							</li>
						</c:forEach>
					</ul>
					<!-- 精品课程列表，结束 -->
					<!-- 推荐课程列表，开始 -->
					<ul style="display: none;" class="c-c-l" id="groomUlId">
						<c:forEach items="${mapCourseList.index_course_4}" var="courseOne">
							<li>
								<section class="fl c-c-img" style="cursor: pointer;">
                                    <a href='${ctx}/front/couinfo/${courseOne.id}' target="_blank" title="${courseOne.name}">
									<c:choose>
										<c:when test="${not empty courseOne.logo}">
											<img xSrc="<%=staticImageServer%>${courseOne.logo}" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" height="116" width="154" alt="${courseOne.name}">
										</c:when>
										<c:otherwise>
											<img xSrc="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" height="116" width="154" alt="${courseOne.name}">
										</c:otherwise>
									</c:choose>
                                    </a>
								</section>
								<h4 class="hLh20 of unFw">
									<a target="_blank" href="${ctx}/front/couinfo/${courseOne.id}" title="${courseOne.name}" class="c-666 fsize18 f-fM">${courseOne.name}</a>
								</h4>
								<div class="s-c-desc pt10 pb10">
									<p class="c-999">${courseOne.title}</p>
								</div>
								<div class="of mt15">
									<section class="fr c-999">
										<span>价格：${courseOne.currentprice}￥</span> 
										<span class="ml5"><tt class="vam">浏览量：</tt><b class="star-1-3 vam" title="浏览量:${courseOne.pageViewcount}">${courseOne.pageViewcount}</b></span>
									</section>
									<section class="fl c-c-teacher">
										<dl class="of">
											<dt> <font class="c-666">讲师：</font> </dt>
											<dd>
												<c:forEach items="${courseOne.teacherList}" var="teacher">
													<a class="c-666" target="_blank" href="${ctx}/front/teacher/${teacher.id}" title="${teacher.name}">${teacher.name}</a>&nbsp;
												</c:forEach>
											</dd>
										</dl>
									</section>
								</div>
							</li>
						</c:forEach>
					</ul>
					<!-- 推荐课程列表，结束 -->
				</section>
			</article>
			<div class="clear"></div>
		</section>
	</div>
	<!-- / 课程列表 结束 -->
	<!-- / 讲师 -->
	<div class="mt30">
		<section class="teacher">
			<article class="w1000">
				<div class="t-title clearfix">
					<h4 class="fl unFw">
						<span class="c-fff"> <em class="t-t-icon">&nbsp;</em> <font class="fsize24 f-fM vam">讲师团队/</font> <small class="fsize14 vam">LecturerTeam</small>
						</span>
					</h4>
					<p class="fl mt40 c-999 ml20">
						领域内的专家，行业精英，讲课达人，只要你愿意分享你的知识，就来加入我们的讲师团队吧！
					</p>
				</div>
				<section class="pr mt30 mb30">
					<div class="pa t-more">
						<a href="/front/teacherlist" title="更多..." class="c-master">更多<br>.<br>.<br>.
						</a>
					</div>
					<div class="t-list">
						<ol class="clearfix">
							<c:forEach items="${teacherList}" var="teachers">
								<li style="cursor: pointer;" onclick="location.href='${ctx}/front/teacher/${teachers.id }'">
									<div class="t-infor-wrap">
										<section class="pl10 pr10">
											<h6 class="hLh30 of"> ${teachers.name} </h6>
											<div class="s-c-desc pt10 pb10"> <p class="c-333">${teachers.career}</p> </div>
										</section>
									</div>
									<span>
									<c:choose>
										<c:when test="${not empty teachers.picPath}">
											<img xSrc="<%=staticImageServer %>${teachers.picPath}" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" xSrc="<%=staticImageServer %>${teachers.picPath}" height="105" width="140" alt="">
										</c:when>
										<c:otherwise>
											<img xSrc="<%=imagesPath%>/static/edu/images/default/default_head.jpg" src="<%=imagesPath%>/static/edu/images/default/c-logo.png" height="105" width="140" alt="">
										</c:otherwise>
									</c:choose>
								</span>
								</li>
							</c:forEach>
						</ol>
					</div>
				</section>
			</article>
		</section>
	</div>
	<!-- / 讲师 结束 -->
	</div>
<!-- 公共底引入 -->
	<script type="text/javascript" src="<%=imagesPath%>/static/common/scrollLoad.js"></script>
</body>
</html>