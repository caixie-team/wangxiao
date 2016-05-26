<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
 <script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_home.js"></script>
<script>
	$(function() {
		//stepFun();//新手引导四步骤方法
	});
	selectRondomTeacher();
</script>
</head>
<body>
			
			<article class="u-m-center">
				<!-- /u-m-c-head -->
				<section class="u-m-c-wrap">
					<section class="u-m-c-head">
						<span class="fr"><em class="right-go icon-2-16">&nbsp;</em><a href="${ctx}/front/showcoulist" title="" class="vam c-666 ml5">去选课</a></span>
						<ul class="fl u-m-c-h-txt of" id="uTabTitle">
							<li class="current uHover"><a href="javascript:void(0)" onclick="getNewFreeSell(this,1)" title="免费课程">免费课程</a></li>
							<li><a href="javascript:void(0)" onclick="getNewFreeSell(this,2)" title="我的课程">我的课程</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<div class="mt20" id="uTabCont" class="of">
						<!-- 免费课程tab start-->
						<article id="newFreeSellWayListUlId" style="display: block;">
							<section class="ml20 mr20">
								<div class="pl10 pr10">
									<ul class="u-buying-list u-collect-list">
										<c:forEach items="${freecourses }" var="courseList" varStatus="index1" >
											<li>
												<section class="fl u-c-img pr">
													<span class="play-1 pa" onclick="watchCourse(${courseList.id})">&nbsp;</span>
													<c:if test="${not empty courseList.logo }">
														<img src="<%=staticImageServer %>${courseList.logo}" width="220" alt="" class="dis" />
													</c:if>
													<c:if test="${empty courseList.logo }">
														<img src="${ctximg}/static/edu/images/default/default_goods.jpg" width="220" alt="" class="dis" />
													</c:if>
												</section>
												<h6 class="hLh20 of unFw">
		                         					<a class="c-4e fsize16 f-fM" title="${courseList.name}" href="${ctx}/front/playkpoint/${courseList.id}">${courseList.name}</a>
		                                             </h6>
													<div class="hLh20 of mt5">
														<span class="vam c-999">讲师：</span>
		                                                <c:forEach items="${courseList.teacherList}"  var="teacher">
		                                                    <span class="vam u-m-c-teacher c-999">${teacher.name} </span>
		                                                </c:forEach>
													</div>
													<div class="hLh20 of mt5"><span class="vam c-999">课时：</span>
													<span class="vam"><font class="fsize12 c-4e">${courseList.lessionnum}课时</font></span></div>
													<div class="hLh20 of mt5"><span class="vam c-999">有效期：</span>
														<span class="vam u-m-c-teacher"><font class="fsize12 c-999 ml10">免费观看</font></span>
														<%-- <span class="vam u-m-c-teacher"><font class="fsize12 c-orange"><fmt:formatDate value="${courseList.authTime}" pattern="yyyy-MM-dd" /></font><font class="fsize12 c-999 ml10">(在此之前可反复观看)</font></span> --%>
													</div>
													<div class="hLh20 of mt5 pr">
														<span class="c-brow mr10" >
															<a class="c-brow mr10" title="" target="_blank" href="${ctx }/front/playkpoint/${courseList.id}" >直接观看</a> |
														</span>
														<span class="c-brow mr10" >
															<a class="c-brow ml10" title="" target="_blank" href="${ctx}/front/couinfo/${courseList.id}">查看课程</a>
														</span>
														<span class="syD uSyd c-999">已学<tt class="c-red">${courseList.studyhistoryNum}</tt>/共<tt class="c-666">${courseList.kpointNum}</tt>个视频</span>
													</div>
												</li>
										</c:forEach>
									</ul>
								</div>
							</section>
						</article>
						<!-- 免费课程tab end-->
						<!-- 我已经购买课程tab start-->
                 		<article id="newWelcomeSellWayListUlId" style="display: none">
                            <c:if test="${buycourses.size()>0 }">
                                <section class="ml20 mr20">
                                    <div class="pl10 pr10">
                                        <ul class="u-buying-list u-collect-list">
                                            <c:forEach items="${buycourses}" var="buyCourseList" varStatus="indexCourse">
                                                <li>
                                                    <section class="fl u-c-img pr">
                                                        <span class="play-1 pa" onclick="watchCourse(${buyCourseList.id})">&nbsp;</span>
                                                        <c:if test="${not empty buyCourseList.logo}">
                                                            <img src="<%=staticImageServer %>${buyCourseList.logo}" width="220" alt="" class="dis" />
                                                        </c:if>
                                                        <c:if test="${ empty buyCourseList.logo}">
                                                            <img src="${ctximg}/static/edu/images/default/default_goods.jpg" width="220" alt="" class="dis" />
                                                        </c:if>
                                                    </section>
                                                    <h4 class="hLh20 of unFw"><a class="c-666 fsize16 f-fM" title="${buyCourseList.name}" href="${ctx}/front/playkpoint/${buyCourseList.id}">${buyCourseList.name}</a></h4>
                                                        <%--<div class="u-c-list-desc mt10">
                                                            <p class="c-999">${buyCourseList.title}</p>
                                                        </div>--%>
                                                    <div class="hLh20 of mt5">
                                                        <span class="vam c-999">讲师：</span>
                                                        <c:forEach items="${buyCourseList.teacherList}"  var="teacher">
                                                            <span class="vam u-m-c-teacher c-999">${teacher.name} </span>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="hLh20 of mt5">
                                                        <span class="vam c-999">课时：</span>
                                                        <span class="vam"><font class="fsize12 c-4e">${buyCourseList.lessionnum}课时</font></span>
                                                    </div>

                                                    <div class="hLh20 of mt5">
														<span>
															<span class="c-4e">
																购买有效期：
																<c:if test="${not empty buyCourseList.authTime }"><b><fmt:formatDate value="${buyCourseList.authTime }" pattern="yyyy-MM-dd"/></b></c:if>
															</span>
															<span class="c-red">
																(<tt>剩</tt> <b>${buyCourseList.remainDays<=0?0:buyCourseList.remainDays}</b> <tt>天</tt>)
															</span>
														</span>
                                                    </div>
                                                    <div class="hLh20 of mt5 pr">
														<span class="vam c-999">
															<c:choose>
				                                                <c:when test="${buyCourseList.remainDays>=0}">
				                                                    <a class="c-blue mr10" title="${buyCourseList.name}" target="_blank" href="${ctx}/front/playkpoint/${buyCourseList.id}"  class="commBtn002 ml20 disIb grayCol">直接观看</a>
				                                                </c:when>
				                                                <c:otherwise>
				                                                    <a class="c-blue mr10" title="" href="javascript:void(0);" onclick="expire()" class="commBtn002 ml20 disIb grayCol">已经过期</a>
				                                                </c:otherwise>
				                                            </c:choose>
														|</span>
                                                        <span class="vam"><a class="c-blue ml10" title="查看课程" href="${ctx}/front/couinfo/${buyCourseList.id}">查看课程</a></span>
                                                        <span class="syD uSyd c-999">已学<tt class="c-red">${buyCourseList.studyhistoryNum}</tt>/共<tt class="c-666">${buyCourseList.kpointNum}</tt>个视频</span>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </section>
                            </c:if>
                                    
                            <c:if test="${empty buycourses }">
                               <section class="comm-tips-1">
                                   <p>
                                       <em class="vam c-tips-1">&nbsp;</em>
                                       <font class="c-999 fsize12 vam">对不起，你还没购买课程！建议你<a href="${ctx}/front/showcoulist" title="" class="c-orange">去选课</a></font>
                                   </p>
                                 </section>
                            </c:if>
                                
                        </article>
                        <!-- 我已经购买课程tab end-->
					</div>
				</section>
				
				<!-- 推荐开始 -->
				<section class="u-m-c-head">
                     <ul class="fl u-m-c-h-txt">
                         <li class="current"><a href="javascript:void(0)" title="" class="vam">为您推荐</a></li>
                     </ul>
                      <div class="clear"></div>
                </section>
                <section class="u-tj-cour">
                    <ol class="clearfix">
                        <c:forEach items="${mapCourseList.index_course_6}" var="tjCourse">
                            <li>
                                <div class="u-tj-cour-img">
                                    <a href="${ctx}/front/couinfo/${tjCourse.id}" title="${tjCourse.name }">
                                        <c:choose>
                                            <c:when test="${not empty tjCourse.logo}">
                                                <img src="<%=staticImageServer %>${tjCourse.logo}" width="150" height="113" class="dis" />
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${ctximg }/static/edu/images/default/default_goods.jpg" width="150" height="113" class="dis" />
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                    <div class="pa u-tjc-name">
                                        <a class="fsize14 c-fff" title="${tjCourse.name }" href="${ctx}/front/couinfo/${tjCourse.id}">${tjCourse.name }</a>
                                    </div>
                                </div>
                                <div class="hLh20 of mt5">
                                    <span class="c-999 fr"><tt class="vam">浏览量：</tt><b title="浏览量:${tjCourse.viewcount}" class="vam">${tjCourse.viewcount}</b></span>
                                    <span class="fl"><a class="c-brow" title=""  href="${ctx}/front/couinfo/${tjCourse.id}">查看课程</a></span>
                                </div>
                            </li>
                        </c:forEach>
                    </ol>
                </section>
                <!-- 推荐结束 -->
			</article>
			<!-- /u-m-c-head -->
			
			<aside class="u-m-right">
				<div class="u-attr line2">
					<section class="u-a-name">
						<strong class="vam fsize16 c-4e f-fM" id="unameright"></strong>
							<span class="ml5 icon-2-14 girl" id="gril" style="display: none;">&nbsp;</span>
							<span class="ml5 icon-2-14 boy" id="boy" style="display: none;">&nbsp;</span>
					</section>
					<section class="hLh20 u-a-setting of">
						<span class="fl w50pre"><em class="icon-2-14 u-set-1">&nbsp;</em><a href="${ctx}/uc/uinfo" class="ml5 fsize12 c-666">个人设置</a></span>
						<span class="fl w50pre"><em class="icon-2-14 u-set-2">&nbsp;</em><a href="${ctx}/uc/avatar" class="ml5 fsize12 c-666">修改头像</a></span>
					</section>
					<section class="pl15 pr15 mb15">
						<div class="hLh20 mt10 pr">
							<span class="vam c-666">我的等级：</span>
							<a href="${ctx}/uc/level"  title="查看经验规则" class="myIntegralS vam c-666" id="levelTitle">${userIntegralMap.levelTitle}</a>
							<span class="levelSpanWrap vam">
								<span class="vam c-666"><b class="u-grade" id="levelSpan"></b></span>
								<script type="text/javascript">var level='${userIntegralMap.level}';$("#levelSpan").css({"background-position" : "-"+(level*36)+"px center"})</script>
							</span>
							<section class="levelTips">
								<div class="pr nextScore">
								<div class="DT-arrow"><em>◆</em><span>◆</span></div>
								当前经验${userIntegralMap.currentExp },距离下一级还需${userIntegralMap.nextExp-userIntegralMap.currentExp>0?userIntegralMap.nextExp-userIntegralMap.currentExp:0 }
								</div>
							</section>
						</div>
						<div class="hLh20 mt5">
							<span class="vam c-666">&nbsp;我的积分：&nbsp;<a href="${ctx}/uc/integift" id="lookIntegralScore" title="查看积分" class="myIntegralS"><b class="c-red fsize14" id="integralScore">${userIntegralMap.currentIntegral}</b></a> 分</span>
							<span class="vam ml30" ><a href="${ctx}/uc/integift" title="积分兑换" class="c-666">积分兑换&gt;&gt;</a></span>
						</div>
					</section>
				</div>
				<div class="u-notice line1">
					<section class="pl15 pr15">
						<h5 class="hLh30 unFw of line2"><span class="c-666 fsize16 f-fM u-c-title">最新公告</span></h5>
						<ul class="mt10">
							<c:forEach items="${noticeList}" var="atricle">
							<li><span class="u-notice-time"><tt class="fsize12 c-666"><fmt:formatDate value="${atricle.createTime}" pattern="MM月dd日"/></tt></span><a href="${ctx}/front/toArticle/${atricle.id}" target="_blank" title="${atricle.title}">${atricle.title}</a></li>
							</c:forEach>
						</ul>
					</section>
				</div>
					<div class="couses-tj">
					<section class="pl15 pr15">
						<h5 class="hLh30 unFw of line2"><span class="c-666 fsize16 f-fM u-c-title">可能您想学的</span></h5>
						<div class="line1">
							<ul class="u-c-list-1 mt20">
								<c:forEach items="${mapCourseList.index_course_7}" var="courseList" varStatus="index1">
								<li>
									<a  href="${ctx}/front/couinfo/${courseList.id}"  target="_blank" class="dis">
										<c:if test="${ not empty courseList.logo  }">
											<img src="<%=staticImageServer %>${courseList.logo}" width="220" alt="" class="dis" />
										</c:if>
										<c:if test="${  empty courseList.logo  }">
											<img src="${ctximg}/static/edu/images/default/default_goods.jpg" width="220" alt="" class="dis" />
										</c:if>
									</a>
										<h6 class="unFw hLh20 of"><a href="${ctx}/front/couinfo/${courseList.id}" target="_blank" title="${courseList.name}" class="c-4e">${courseList.name}</a></h6>
										<div class="mt5 u-c-desc-1">
										<p class="c-999">${courseList.title }</p>
										</div>
								</li>
							</c:forEach>
							</ul>
						</div>
					</section>
				</div>
				<div class="couses-tj">
					<section class="pl15 pr15">
						<h5 class="hLh30 unFw of line2"><a href="javascript:void(0)" onclick="selectRondomTeacher();" class="fr fresh icon-2-18 mt5" title="换一换"></a><span class="c-666 fsize16 f-fM u-c-title">明星老师</span></h5>
						<div class="line1 pt15">
							<ol class="clearfix u-teacher-list" id="randomteacher">
							</ol>
						</div>
					</section>
				</div>
			</aside>
	<!-- /u-main end -->
</body>
</html>
