<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<!-- / slide -->
<!-- /两屏宣传图 -->
<!-- /岗位课程开始 -->
<div class="row row-lg">
    <div class="page-header">
        <h1 class="page-title">推荐课程</h1>
    </div>
    <div class="col-lg-12">
        <!-- Example Sample Portfolio -->
        <%--<div class="example-wrap">--%>
        <%--<h4 class="example-title">推荐课程</h4>--%>
        <%--<p>最新、最有价值的课程.</p>--%>
        <%--<div class="example margin-bottom-0">--%>
        <div class="owl-carousel-portfolio owl-carousel-navOut" data-plugin="owlCarousel"
             data-margin="30">

            <c:forEach items="${mapCourseList.index_course_1}" var="subjectCourse"
                       varStatus="courseindex">
                <div class="item">
                    <div class="widget widget-shadow">
                        <div class="widget-header cover overlay" style="height: calc(100% - 100px);">
                            <a class="course-img" href="${ctx}/front/couinfo/${subjectCourse.id }">
                                <c:choose>
                                    <c:when test="${ not empty subjectCourse.logo}">
                                        <%--<img src="<%=staticImageServer%>/${subjectCourse.logo}"--%>
                                        <%--xSrc="" class="img-responsive">--%>
                                        <img class="cover-image"
                                             src="${ctx}/static/global/photos/placeholder.png" alt="..."
                                             style="height: 100%;">
                                    </c:when>
                                    <c:otherwise>
                                        <%--<img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}"--%>
                                        <%--xSrc="" class="img-responsive">--%>
                                        <img class="cover-image"
                                             src="${ctx}/static/global/photos/placeholder.png" alt="..."
                                             style="height: 100%;">
                                    </c:otherwise>
                                </c:choose>
                            </a>
                                <%--<img class="cover-image" src="${ctx}/static/global/photos/placeholder.png" alt="..."--%>
                                <%--style="height: 100%;">--%>
                            <div class="overlay-panel overlay-background overlay-top">
                                <div class="row">
                                    <div class="col-xs-6">

                                        <div class="font-size-14 white">
                                            <a title="${subjectCourse.name}"
                                               href="${ctx}/front/couinfo/${subjectCourse.id }">${subjectCourse.name}</a>
                                        </div>
                                        <div class="font-size-14 grey-400">
                                            课时:${subjectCourse.lessionnum}</div>
                                    </div>
                                    <div class="col-xs-6 text-right">
                                        <div class="font-size-14 white">
                                            <c:forEach items="${subjectCourse.teacherList}"
                                                       var="teacher" varStatus="teaindex">
                                                <c:if test="${teaindex.index==0 }">
                                                    <a class="c-666 mr10"
                                                       href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
                                                </c:if>
                                                <c:if test="${teaindex.index>0 }">
                                                    <a class="c-666"
                                                       href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget-footer text-center bg-white padding-horizontal-30 padding-vertical-20 height-100">
                            <div class="row no-space">
                                <div class="col-xs-4">
                                    <div class="counter">
                                        <span class="counter-number">${subjectCourse.playcount}</span>
                                        <div class="counter-label">播放量</div>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <div class="counter">
                                        <span class="counter-number">${subjectCourse.commentcount}</span>
                                        <div class="counter-label">评论数</div>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <div class="counter">
                                        <span class="counter-number">${subjectCourse.currentprice}</span>
                                        <div class="counter-label">课单价</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>


            <%--</div>--%>
            <%--</div>--%>
        </div>
        <!-- End Example Sample Portfolio -->
    </div>
</div>
<!-- /岗位课程结束-->

<div class="row row-lg">

    <c:if test="${not empty subjectShowIndex}">
        <c:forEach items="${subjectShowIndex}" var="subject">
            <div class="panel panel-transparent nav-tabs-horizontal">
                <div class="panel-heading">
                    <h3 class="panel-title">${subject.subjectName}</h3>
                    <div class="panel-actions">
                        <a class="fr net-c-more" target="_blank"
                           href="${ctx}/front/showCourseList?queryCourse.subjectId=${subject.subjectId}"><tt
                                class="vam mr10">更多课程</tt><em class="icon14"></em></a>
                            <%--<a class="panel-action icon wb-minus" aria-controls="exampleTransparentBody" aria-expanded="true" data-toggle="panel-collapse" href="#exampleTransparentBody" aria-hidden="true"></a>--%>
                            <%--<a class="panel-action icon wb-close" data-toggle="panel-close" aria-hidden="true"></a>--%>
                    </div>
                </div>
                <%--<h4 class="example-title">Nav Tabs Line</h4>--%>
                <ul class="nav nav-tabs nav-tabs-line net-list-head">
                    <c:forEach items="${subject.childSubjectList}" var="childSubject" varStatus="index">

                        <li role="presentation" class="c-tab-title">
                            <a data-toggle="tab"
                               href="javascript:void(0)"
                               title="${childSubject.subjectName}"
                               id="${childSubject.subjectId}">
                                ${childSubject.subjectName}</a>
                        </li>
                    </c:forEach>

                </ul>
                <div class="panel-body">
                    <ul class="of job-cou-list"></ul>
                        <%--<header class="comm-title net-list-head">--%>
                        <%----%>
                        <%--<section class="c-tab-title">--%>
                        <%--<c:forEach items="${subject.childSubjectList}" var="childSubject" varStatus="index">--%>
                        <%--<a href="javascript:void(0)" title="${childSubject.subjectName}"--%>
                        <%--id="${childSubject.subjectId}">${childSubject.subjectName}</a>--%>
                        <%--</c:forEach>--%>
                        <%--</section>--%>
                        <%--<div class="clear"></div>--%>
                        <%--</header>--%>
                    <%--<div class="net-cou-box">--%>
                        <%--<!-- 读取数据中... -->--%>
                        <%--<div class="tac mb10">--%>
                            <%--<img src="${ctximg}/static/nxb/web/img/loading.gif" width="100" height="9"--%>
                                 <%--alt="正在读取...">--%>
                            <%--<p class="hLh30"><span class="c-red fsize12 f-fA">正在读取...</span></p>--%>
                        <%--</div>--%>
                        <%--<!-- 读取数据中... -->--%>
                        <%--<ul class="of job-cou-list"></ul>--%>
                    <%--</div>--%>

                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
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
                                                <a class="fsize14 c-fff" title="${news.title}" target="_blank"
                                                   href="${ctx}/front/toArticle/${news.id}">${news.title}</a>
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
                                        <img src="${ctximg}/static/nxb/web/img/avatar-boy.gif"
                                             xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
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
												</span></p>
                                    </section>
                                </section>
                            </li>
                            <li>
                                <section class="sta-r-wrap">
                                    <div class="sta-r-face">
                                        <img src="${ctximg}/static/nxb/web/img/avatar-boy.gif"
                                             xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
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
												</span></p>
                                    </section>
                                </section>
                            </li>
                            <li>
                                <section class="sta-r-wrap">
                                    <div class="sta-r-face">
                                        <img src="${ctximg}/static/nxb/web/img/avatar-boy.gif"
                                             xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
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
												</span></p>
                                    </section>
                                </section>
                            </li>
                            <li>
                                <section class="sta-r-wrap">
                                    <div class="sta-r-face">
                                        <img src="${ctximg}/static/nxb/web/img/avatar-boy.gif"
                                             xsrc="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="">
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
												</span></p>
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
                    <div id="left_container" class="bor-dot-right pr50"
                         style="width: 85%;margin: auto;height: 260px;"></div>
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
                                        <div class="mt10 e-s-mod1 chartProgress" id="container_${plan.id}"
                                             peopleNum="${plan.peopleNum!=null?plan.peopleNum:0 }"
                                             completeNum="${plan.completeNum!=null?plan.completeNum:0 }">
                                                <%--<img src="${ctximg}/static/nxb/web/img/pic/c-circle.png">--%>
                                        </div>
                                        <div class="e-s-mod2">
                                            <section>
                                                <p class="hLh30 c-666 fsize16">创建于<fmt:formatDate
                                                        value="${plan.releaseTime}" type="both"
                                                        pattern="yyyy-MM-dd"/></p>
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
                                                    <p class="c-666 fsize14 txtOf"><span
                                                            class="c-orange">${plan.peopleNum}</span>人参加</p>
                                                    <p class="c-666 fsize14 txtOf">已完成：<span
                                                            class="c-orange">${plan.completeNum}</span>人</p>
                                                    <p class="c-666 fsize14 txtOf">未完成：<span
                                                            class="c-orange">${plan.peopleNum-plan.completeNum}</span>人
                                                    </p>
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
<%--<script type="text/javascript" src="${ctximg}/static/common/echarts/echarts.min.js" charset="UTF-8"></script>--%>
<%--<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/web/index.js"></script>--%>
</body>
</html>