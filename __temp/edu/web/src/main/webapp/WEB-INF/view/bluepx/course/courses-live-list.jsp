<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>直播中心</title>
    <script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course.js"></script>
    <script type="text/javascript">
        var subId = '${queryCourse.subjectId}';
        //专业选中样式
        $(function () {
        	var ik = 0;
            setInterval(function () {
                $(".live-bg").css("background-position", --ik + "px");
            }, 10);

            $("#clickSubjcet" + subId).addClass("current");
        });
    </script>
</head>
<body>
<!-- /直播列表 开始-->
<div>
<div class="live-wrap">
    <section class="live-bg">
        <div class="live-banner">&nbsp;</div>
    </section>
</div>
<div class="live-main of">
<section class="w1000">
<div>
    <div class="pathwray unBr">
        <ol class="clearfix c-master f-fM fsize14">
            <li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
            <li><span>直播中心</span></li>
        </ol>
    </div>
</div>
<form id="searchForm" action="${ctx}/front/showlivelist" method="post">
    <input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
    <input type="hidden" id="hiddenSubjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
</form>
<div class="mt30 mb50">
<header class="scommTitle">
    <aside class="live-i-ico">&nbsp;</aside>
    <div class="sct-txt">
        <section class="mt15 fl"><span
                class="fsize24 f-fM c-333">在线互动直播  / Online Interactive Live</span></section>
         <div class="clear"></div>
        <div class="v-i-tab-a">
            <a href="javascript:clickSearch('subject','')" id="clickSubjcet" class="">全部直播</a>&nbsp;/&nbsp;
            <c:forEach items="${subjectList }" var="subject">
                <a href="javascript:clickSearch('subject','${subject.subjectId}')" id="clickSubjcet${subject.subjectId}">${subject.subjectName}</a>&nbsp;/&nbsp;
                <%--<li><a onclick="clickSearch('subject',${subject.subjectId})" title="${subject.subjectName }" href="javascript:void(0);">${subject.subjectName }</a></li>--%>
            </c:forEach>
        </div>
        <div class="sub-v-i-tab-a">
            <c:forEach items="${sonSubjectList}" var="subject">
                <a href="javascript:clickSearch('subject','${subject.subjectId}')" id="clickSubjcet${subject.subjectId}">${subject.subjectName}</a>&nbsp;/&nbsp;
            </c:forEach>
        </div>
    </div>
</header>
<!-- live list begin -->
<div class="mt50 pt50">
<!-- 一个直播 开始 -->
<c:if test="${empty courseList.size()}">
    <section class="comm-tips-1">
        <p>
            <em class="vam c-tips-1">&nbsp;</em>
            <font class="c-999 fsize12 vam">对不起，此条件下还没有相关直播课程！</font>
        </p>
    </section>
</c:if>
<c:if test="${!empty courseList.size()}">
    <c:forEach items="${courseList}" var="live">
        <article class="live-i-box">
            <aside class="live-i-data">
                <section class="live-i-data-box">
                    <div class="live-i-data-yd f-fM">
                                        <span class="c-fff fsize18"><fmt:formatDate value="${live.liveBeginTime}"
                                                                                    type="both" pattern="MM月"/></span>

                        <p class="mt30">
                                            <span class="c-master mt5"><fmt:formatDate value="${live.liveBeginTime}"
                                                                                       type="both" pattern="dd"/></span>
                        </p>
                    </div>
                    <div class="live-i-data-dd tac">
                        <strong class="c-master fsize16 f-fM"><fmt:formatDate
                                value="${live.liveBeginTime}" type="both" pattern="HH:mm"/>~
                            <fmt:formatDate
                                    value="${live.liveEndTime}" type="both" pattern="HH:mm"/></strong>
                    </div>
                </section>
            </aside>
            <section class="live-i-list">
                <div class="live-i-list-box">
                    <section class="live-il-txt">
                        <header class="live-il-txt-head">
                            <h3>${live.name}</h3>
                        </header>
                        <div class="pl20 pt20 pr20 of">
                            <section class="countdown">
                                <c:if test="${live.beginTimeNum==0&&live.endTimeNum==0}">
                                    <div class="tac mt30 mb20">
                                    	<span class="c-999" style="font-size: 36px;">直播结束！</span>
                                    </div>
                                </c:if>
                                <c:if test="${live.beginTimeNum==0&&live.endTimeNum!=0}">
                                    <div class="tac mt30 mb20">
                                    	<span class="c-999" style="font-size: 36px;">直播进行中！</span>
                                    </div>
                                </c:if>
                                <c:if test="${live.beginTimeNum!=0&&live.endTimeNum!=0}">
                                    <ul class="clearfix updatetime">
                                        <li class="tBg"><span id="day_show${live.id}"></span></li>
                                        <li><span class="c-999">天</span></li>
                                        <li class="tBg"><span id="hour_show${live.id}"></span></li>
                                        <li><span class="c-999">时</span></li>
                                        <li class="tBg"><span id="minute_show${live.id}"></span></li>
                                        <li><span class="c-999">分</span></li>
                                        <li class="tBg"><span id="second_show${live.id}"></span></li>
                                        <li><span class="c-999">秒</span></li>
                                        <script>
                                            timer(${live.beginTimeNum}, ${live.id})
                                        </script>
                                    </ul>
                                </c:if>

                            </section>
                            <div class="hLh30 of line3">
                                <span class="fr">
                                <tt class="c-orange fsize18 f-fM">￥${live.currentprice}</tt></span>
                                <span class="c-orange fsize18 f-fM">主讲：
                                <c:forEach items="${live.teacherList }" var="teacher">
                                    ${ teacher.name}
                                </c:forEach>
                                </span>
                            </div>
                            <div class="live-il-desc">
                                <p class="c-666 tD24">
                                        ${live.title}
                                </p>
                            </div>
                        </div>
                    </section>
                    <aside class="live-il-btn-box">
                        <section class="ml10">
                            <div class="live-il-btn">
                            	 <c:if test="${live.beginTimeNum==0&&live.endTimeNum==0}">
                                    <a class="mt10" href="${ctx}/front/couinfo/${live.id}" target="_blank">
                                    <span class="fsize20 f-fM">查看回放</span></a>
                                </c:if>
                                <c:if test="${live.beginTimeNum!=0}">
                                    <a class="mt10" href="${ctx}/front/couinfo/${live.id}" target="_blank">
                                    <span class="fsize20 f-fM">预约报名</span></a>
                                </c:if>
                                <c:if test="${live.beginTimeNum==0&&live.endTimeNum!=0}">
                                <a class="mt10" href="${ctx}/front/couinfo/${live.id}" target="_blank">
                                    <span class="fsize20 f-fM">进入直播</span></a>
                                </c:if>
                                <p class="tac pt10">
                                    <span class="fsize16 f-fM c-orange">预约人数：${live.buycount}人</span>
                                </p>
                            </div>
                            <div class="live-il-sort mt10">
									<span>
                                        <c:choose>
                                            <c:when test="${not empty live.logo}">
                                                <img src="<%=staticImageServer%>${live.logo}" height="116" width="207"
                                                     alt="${live.name}">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg"
                                                     height="116" width="207" alt="${live.name}">
                                            </c:otherwise>
                                        </c:choose>
                                     </span>
                                    <p class="hLh30 of tac">
                                        <span class="fsize16 f-fM c-fff" title="职业资格">${live.subjectName}</span>
                                    </p>
                            </div>
                        </section>
                    </aside>
                    <div class="clear">
                    </div>
                </div>
            </section>
            <div class="clear"></div>
        </article>
    </c:forEach>
    </c:if>
    <!-- 一个直播 结束 -->
    </div>
    <!-- live list end -->
    <section class="">
    <div class="pagination pagination-large tac mt50">
    <div class="pagination pagination-large tac ">
    <jsp:include page="/WEB-INF/view/common/page.jsp"/>
    </div>
    </div>
    </section>
    </div>
    </section>
    </div>
    </div>
    <!-- /直播列表 结束 -->
    </body>
    </html>
