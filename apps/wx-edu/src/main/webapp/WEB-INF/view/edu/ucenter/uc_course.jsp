<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我购买的课程</title>
</head>
<body>
<article class="uc-m-content mb50">
    <header class="uc-com-title">
        <span>我购买的课程</span>
    </header>
    <div class="i-box">
        <div>
            <ul class="u-f-c-list u-live-cou">
                <c:if test="${empty buycourses}">
                    <li>
                        <section class="mt30 mb30 tac">
                            <em class="no-data-ico cTipsIco">&nbsp;</em>
                            <span class="c-666 fsize14 ml10 vam">暂时没有购买任何课程~</span>
                        </section>
                    </li>
                </c:if>
                <c:if test="${not empty buycourses}">
                    <c:forEach items="${buycourses}" var="buyCourseList" varStatus="indexCourse">
                        <li>
                            <aside class="u-f-wrap">
                                <div class="">
                                    <a href="${ctx}/front/couinfo/${buyCourseList.id}" target="_blank" class="u-f-pic">
                                        <c:choose>
                                            <c:when test="${not empty buyCourseList.logo}">
                                                <img alt="" class="img-responsive" src="<%=staticImageServer%>/${buyCourseList.logo}" xsrc="">
                                            </c:when>
                                            <c:otherwise>
                                                <img alt="" class="img-responsive" src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" xsrc="">
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </div>
                            </aside>
                            <h3 class="hLh30 txtOf">
                                <a class="i-oc-title" title="${buyCourseList.name}" href="${ctx}/front/couinfo/${buyCourseList.id}">${buyCourseList.name}</a>
                            </h3>
                            <section class="i-q-txt mt5">
                                <p class="txtOf"> <span class="c-666 fsize14">${buyCourseList.title}</span> </p>
                            </section>
                            <section class="hLh30 txtOf mt5 pr10 u-l-validity">
                                <span class="c-666">
                                    <tt class="c-4e mr10"> 购买有效期至：
                                        <c:if test="${not empty buyCourseList.authTime }">
                                            <b class="unFw"><fmt:formatDate value="${buyCourseList.authTime }" pattern="yyyy-MM-dd"/></b>
                                        </c:if>
                                    </tt>
                                    (<tt class="c-red">剩</tt> <b>${buyCourseList.remainDays<=0?0:buyCourseList.remainDays}</b> <tt>天</tt>)
                                </span>
                            </section>
                            <aside class="u-f-c-more">
                                <a class="c-blue fsize12" href="${ctx}/front/couinfo/${buyCourseList.id}" target="_blank">查看</a>
                            </aside>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>
</article>
</body>
</html>
