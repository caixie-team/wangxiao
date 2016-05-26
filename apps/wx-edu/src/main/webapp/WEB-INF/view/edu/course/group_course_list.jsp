<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>岗位课程</title>
</head>
<body>
<article class="uc-m-content mb50">
    <header class="uc-com-title">
        <span>岗位课程</span>
    </header>
    <div class="i-box mt20">
        <section class="mt20">
            <c:if test="${empty courseList}">
                <section class="no-data-wrap">
                    <em class="no-data-ico cTipsIco">&nbsp;</em>
                    <span class="c-666 fsize14 ml10 vam">还没有岗位课程</span>
                </section>
            </c:if>
            <c:if test="${not empty courseList}">
                <ul class="of job-cou-list">
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
                                            <div title="已学53%" class="lev-num-wrap">
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
                </ul>
                <form id="searchForm" action="${ctx }/uc/groupCourseList" method="post">
                    <input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
                </form>
                <jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
            </c:if>
        </section>
    </div>
</article>
</body>
</html>
