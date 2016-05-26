<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
</head>
<body>
<article class="uc-m-content">
	<div class="i-box">
		<section>
			<div class="hLh20">
				<span class="c-999 f-fH"><big class="fsize14">${loginUser.showname }</big><big class="fsize14">，下午好</big><big class="fsize14">！欢迎回来。</big></span>
			</div>
			<c:if test="${empty groupList}">
				<section class="no-data-wrap">
					<em class="no-data-ico cTipsIco">&nbsp;</em>
					<span class="c-666 fsize14 ml10 vam">您还没有加入小组~</span>
				</section>
			</c:if>
			<c:if test="${not empty groupList}">
				<c:forEach items="${groupList}" var="group">
					<div class="fl col-35">
						<h4 class="mt30 tac"><span class="fsize18 c-333">${group.name}</span></h4>
						<section class="mb10 mt20 tac">
							<ol class="ut-tb-ol">
								<li class="fl col-50">
									<a href="" title="查看学习记录" class="ut-tb ut-tb-1"><em>&nbsp;</em></a>
									<p class="c-666 hLh30 mt10 txtOf">
										<small class="fsize12">已学 </small>
										<big class="fsize14">${group.courseNum}</big>
										<small class="fsize12"> 课时</small>
									</p>
								</li>
								<li class="fl col-50">
									<a href="" title="查看考试记录" class="ut-tb ut-tb-2"><em>&nbsp;</em></a>
									<p class="c-666 hLh30 mt10 txtOf">
										<small class="fsize12">已做 </small>
										<big class="fsize14">999</big>
										<small class="fsize12"> 试卷</small>
									</p>
								</li>
							</ol>
							<div class="clear"></div>
						</section>
					</div>
				</c:forEach>
			</c:if>
			<div class="clear"></div>
		</section>
	</div>
	<div class="i-box mt20">
		<section class="uc-c-title">
			<a href="${ctx}/uc/study" title="" class="fr fsize12 c-999">查看更多</a>
			<span class="fsize16">最近学习</span>
		</section>
		<c:if test="${empty studylist}">
			<section class="no-data-wrap">
				<em class="no-data-ico cTipsIco">&nbsp;</em>
				<span class="c-666 fsize14 ml10 vam">最近没有学习，快去<a href="" title="" class="c-blue">学习吧</a>...</span>
			</section>
		</c:if>
		<c:if test="${not empty studylist}">
			<section class="uc-c-table">
				<c:forEach items="${studylist}" var="study">
					<ol class="uc-c-table-col-2 clearfix">
						<li class="fl col-50">
							<div class="hLh30 txtOf pl10">
								<span class="fsize14 c-333">${study.courseName}-${study.kpointName}</span>
							</div>
						</li>
						<li class="fl col-25 tar">
							<div class="hLh30 txtOf">
								<%--<span class="fsize12 c-red">80%</span>--%>
								<span class="fsize12 c-666"><fmt:formatDate value="${study.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></span>
							</div>
						</li>
						<li class="fl col-25 tar">
							<a href="${ctx}/front/playkpoint/${study.courseId}/?kpointId=${study.kpointId}" title="" class="lh-reply-btn">继续学习</a>
						</li>
					</ol>
				</c:forEach>
				<%--<ol class="uc-c-table-col-2 clearfix">
					<li class="fl col-50">
						<div class="hLh30 txtOf pl10">
							<span class="fsize14 c-333">岗位课程之必备课程</span>
						</div>
					</li>
					<li class="fl col-25 tar">
						<div class="hLh30 txtOf">
							<span class="fsize12 c-green">100%</span>
							<span class="fsize12 c-666"> 完成 2016/01/20</span>
						</div>
					</li>
					<li class="fl col-25 tar">
						<a href="" title="" class="lh-reply-btn">再次学习</a>
					</li>
				</ol>--%>
			</section>
		</c:if>
	</div>
	<div class="i-box mt20">
		<section class="uc-c-title">
			<a href="${ctx}/uc/planRecordList" title="" class="fr fsize12 c-999">查看更多</a>
			<span class="fsize16">学习计划</span>
		</section>
		<c:if test="${empty recordList}">
			<section class="no-data-wrap">
				<em class="no-data-ico cTipsIco">&nbsp;</em>
				<span class="c-666 fsize14 ml10 vam">您还没有学习计划~</span>
			</section>
		</c:if>
		<c:if test="${not empty recordList}">
			<section class="uc-emp-box">
				<ol>
					<c:forEach items="${recordList}" var="record">
						<li class="fl col-50">
							<div class="emp-s-b-sec">
								<h3 class="hLh30 txtOf"><span class="c-333 fsize20">${record.planName}</span></h3>
								<div class="clearfix pt15 e-s-box">
									<div class="mt10 e-s-mod1 chartProgress" id="${record.id}" total="${record.totalTime }" complete="${record.completeTime }">
										<img src="${ctximg}/static/nxb/web/img/pic/c-circle.png">
									</div>
									<div class="e-s-mod2">
										<section>
											<p class="hLh30 c-666 fsize16">创建于<fmt:formatDate value="${record.releaseTime}" pattern="yyyy-MM-dd" type="both"/> </p>
											<p class="hLh30 c-666 fsize16">计划完成时间：${record.overDays }天</p>
										</section>
										<section class="clearfix mt15 pr">
											<div class=" e-m-stasec">
												<p class="c-666 fsize14 txtOf"><span class="c-orange">${record.peopleNum }</span>人参加</p>
												<p class="c-666 fsize14 txtOf">已完成：<span class="c-orange">${record.completeNum }</span>人</p>
												<p class="c-666 fsize14 txtOf">未完成：<span class="c-orange">${record.peopleNum-record.completeNum }</span>人</p>
											</div>
											<div class="e-m-more">
												<a class="bm-lr-btn check-out-btn" href="${ctx}/uc/planRecord/${record.planId}">查看详情</a>
											</div>
										</section>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ol>
				<div class="clear"></div>
			</section>
		</c:if>
	</div>
	<c:if test="${keywordmap.keyword.verifyExam=='ON' }">
		<div class="i-box mt20">
			<section class="uc-c-title">
				<a href="${ctxexam}/paper/toExamPaperRecordList" title="" class="fr fsize12 c-999">查看更多</a>
				<span class="fsize16">考试记录</span>
			</section>
			<c:if test="${empty examRecord}">
				<section class="no-data-wrap">
					<em class="no-data-ico cTipsIco">&nbsp;</em>
					<span class="c-666 fsize14 ml10 vam">考试系统未启动...</span>
				</section>
			</c:if>
			<c:if test="${not empty examRecord}">
				${examRecord}
			</c:if>
		</div>
		<div class="i-box mt20">
			<section class="uc-c-title">
				<a href="${ctx}/uc/myArrangeExam" title="" class="fr fsize12 c-999">查看更多</a>
				<span class="fsize16">评测</span>
			</section>
			<c:if test="${empty arrangeRecordList}">
				<section class="no-data-wrap">
					<em class="no-data-ico cTipsIco">&nbsp;</em>
					<span class="c-666 fsize14 ml10 vam">考试系统未开启...</span>
				</section>
			</c:if>
			<c:if test="${not empty arrangeRecordList}">
				<section class="uc-c-table u-homework-box">
					<ol class="uc-c-table-col-2 uc-cTab-th clearfix">
						<li class="fl col-50">
							<div class="hLh30 txtOf tal">
								<strong class="fsize14 ml10">测试名称</strong>
							</div>
						</li>
						<li class="fl col-25">
							<div class="hLh30 txtOf tac">
								<strong class="fsize14">结果</strong>
							</div>
						</li>
						<li class="fl col-25 tac">
							<strong class="fsize14">操作</strong>
						</li>
					</ol>
					<c:forEach items="${arrangeRecordList}" var="record">
						<ol class="uc-c-table-col-2 clearfix">
							<input type="hidden" id="beginTime${record.exampaperId}" value="<fmt:formatDate value="${record.beginTime}" type="both" pattern="yyyy/MM/dd/HH/mm/ss" />"/>
							<input type="hidden" id="endTime${record.exampaperId}" value="<fmt:formatDate value="${record.endTime}" type="both" pattern="yyyy/MM/dd/HH/mm/ss" />"/>
							<li class="fl col-50">
								<div class="hLh30 txtOf pl10">
									<span class="fsize14 c-333">${record.arrangeName}</span>
								</div>
							</li>
							<li class="fl col-25 tar">
								<div class="hLh30 txtOf">
									<c:if test="${record.isComplete==0}">
										<span class="fsize12 c-666 am-text-left">未完成</span>
									</c:if>
									<c:if test="${record.isComplete==1}">
										<span class="fsize12 c-red">${record.score}分</span>
										<span class="fsize12 c-666"> / <fmt:formatDate value="${record.submitTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></span>
									</c:if>
								</div>
							</li>
							<li class="fl col-25 tac">
								<c:if test="${record.isComplete==0}">
									<a href="javascript:void(0)" onclick="startArrangeExam(${record.exampaperId},${record.id},${record.isComplete },${record.isRepeat})" title="" class="c-blue fsize12 vam c-hk-learn">开始评测</a>
								</c:if>
								<c:if test="${record.isComplete==1}">
									<c:if test="${record.isRepeat==1}">
										<a href="javascript:void(0)" onclick="startArrangeExam(${record.exampaperId},${record.id},${record.isComplete },${record.isRepeat})" title="" class="c-blue fsize12 vam c-hk-learn">开始评测</a>
									</c:if>
									<a href="${ctxexam}/paper/getExamPaperReport/${record.examRecordId}" title="" class="c-green fsize12 vam c-hk-look">查看报告</a>
								</c:if>
							</li>
						</ol>
					</c:forEach>
					<div class="clear"></div>
				</section>
			</c:if>
		</div>
	</c:if>
	<div class="i-box mt20">
		<section class="uc-c-title">
			<a href="${ctx}/uc/groupCourseList" title="" class="fr fsize12 c-999">查看更多</a>
			<span class="fsize16">岗位课程</span>
		</section>
		<section class="mt20">
			<ul class="of job-cou-list">
				<c:if test="${empty courseList}">
					<section class="no-data-wrap">
						<em class="no-data-ico cTipsIco">&nbsp;</em>
						<span class="c-666 fsize14 ml10 vam">还没有岗位课程</span>
					</section>
				</c:if>
				<c:if test="${not empty courseList}">
					<c:forEach items="${courseList}" var="course">
						<li>
							<div class="cc-l-wrap">
								<a class="course-img" href="${ctx}/front/couinfo/${course.id}">
									<c:if test="${not empty course.logo}">
										<img src="<%=staticImageServer%>/${course.logo}" xsrc="" alt="" class="img-responsive">
									</c:if>
									<c:if test="${empty course.logo}">
										<img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" xsrc="" alt="" class="img-responsive">
									</c:if>
								</a>
								<div class="j-c-desc-wrap">
									<h3 class="hLh30 txtOf pt10">
										<a class="j-course-title" title="" href="${ctx}/front/couinfo/${course.id}">${course.name}</a>
									</h3>
									<div class="clearfix of mt15 cj-cou-ds">
										<span class="fl c-999 f-fM txtOf">讲师：
											<c:if test="${not course.teacherList}">
												<c:forEach items="${teacherList}" var="teacher">
													<a class="c-666 mr10" href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
												</c:forEach>
											</c:if>
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
									<section class="mt10 of">
										<div class="time-bar-wrap">
											<div title="" class="lev-num-wrap">
												<c:if test="${course.lessionnum>0}">
													<aside style="width: <fmt:formatNumber value="${course.studyhistoryNum/course.lessionnum}" type="percent"/>;" class="lev-num-bar"></aside>
													<c:choose>
														<c:when test="${course.studyhistoryNum/course.lessionnum<0.3}">
															<span class="lev-num" style="color:black;"><fmt:formatNumber value="${course.studyhistoryNum/course.lessionnum}" type="percent"/>/100%</span>
														</c:when>
														<c:otherwise>
															<span class="lev-num"><fmt:formatNumber value="${course.studyhistoryNum/course.lessionnum}" type="percent"/>/100%</span>
														</c:otherwise>
													</c:choose>
												</c:if>
											</div>
										</div>
									</section>
									<section class="mt10 of">
										<p class="fsize12 c-master2">目前已完成${course.studyhistoryNum}个课时！</p>
									</section>
								</div>
							</div>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</section>
	</div>
</article>
<!-- /右侧内容区域 结束-->
<script type="text/javascript" src="${ctximg }/static/common/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_home.js"></script>
</body>
</html>
