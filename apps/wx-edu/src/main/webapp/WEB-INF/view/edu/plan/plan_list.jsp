<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>学习计划</title>
</head>
<body>
<article class="uc-m-content mb50">
    <header class="uc-com-title">
        <span>学习计划</span>
    </header>
    <div class="i-box">
        <c:if test="${empty recordList}">
            <section class="no-data-wrap">
                <em class="no-data-ico">&nbsp;</em>
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
                                                <a class="bm-lr-btn check-out-btn" href="${ctx}/uc/planRecord/${record.planId}">继续学习</a>
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
</article>
<form id="searchForm" action="${ctx }/uc/planRecordList" method="post">
    <input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
</form>
<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
<script type="text/javascript" src="${ctximg }/static/common/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_home.js"></script>
</body>
</html>
