<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>计划详情</title>
</head>
<body>
    <article class="uc-m-content mb50">
        <div class="i-box">
            <section>
                <h3><span class="c-333 fsize24">${plan.name}</span></h3>
                <div class="of mt20 u-p-c-box">
                    <span class=" vam disIb"><em class="icon16 mr5 vam crea-time"></em><tt class="vam fsize16 c-666 f-fM">创建于<fmt:formatDate value="${plan.releaseTime}" pattern="yyyy-MM-dd"/></tt></span>
                    <span class="c-666 vam disIb ml30 fsize16"><em class="icon16 mr5 compl-time"></em><tt class="vam fsize16 c-666 f-fM">计划完成时间：<fmt:formatDate value="${plan.endTime}" pattern="yyyy-MM-dd"/></tt></span>
                    <span class="c-666 vam disIb ml30 fsize16"><em class="icon16 mr5 remain-time"></em><tt class="vam fsize16 c-666 f-fM">剩余时间：${plan.overDate}天</tt></span>
                </div>
                <div class="learn-progress">
                    <section>
                        <span class=" vam disIb"><em class="icon16 mr5 vam learn-pro"></em><tt class="vam fsize16 c-666 f-fM">学习进度</tt></span>
                    </section>
                    <div class="u-lp-box">
                        <section class="u-lp-timebar pr">
                            <div class="time-bar-wrap">
                                <div class="lev-num-wrap" title="已学<fmt:formatNumber value='${planRecord.progressPercentage}' type='percent'/>">
                                    <aside class="lev-num-bar" style="width: <fmt:formatNumber value='${planRecord.progressPercentage}' type='percent'/>"></aside>
                                </div>
                            </div>
                            <div class="c-left-box"><span class="fsize12 c-999">0%</span></div>
                            <div class="c-right-box"><span class="fsize12 c-999">100%</span></div>
                            <div class="learn-pro-percent" style="left:<fmt:formatNumber value='${planRecord.progressPercentage}' type='percent'/>">
                                <section class="fn-sub-box pr" style="display: block;">
                                    <div class="DT-arrow"><em>◆</em><span>◆</span></div>
                                    <div class="fn-sub-wrap fn-sub tac">
                                        <span class="fsize12 c-fffs"><fmt:formatNumber value='${planRecord.progressPercentage}' type='percent'/></span>
                                    </div>
                                </section>
                            </div>
                        </section>
                        <div class="mt40 tac">
                            <c:if test="${planRecord.progressPercentage<1}">
                                <a id="playCourse" href="javascript:void(0)" target="_blank" class="bm-lr-btn bm-l-keep">继续学习</a>
                            </c:if>
                            <c:if test="${planRecord.progressPercentage>=1}">
                                <a href="javascript:void(0)" class="bm-lr-btn bm-l-keep">已完成</a>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div>
                    <section>
                        <span class=" vam disIb"><em class="icon16 mr5 vam parti-state"></em><tt class="vam fsize16 c-666 f-fM">参与情况</tt></span>
                    </section>
                    <section class="mt20 comp-state">
                        <span class="c-666 fsize14"><tt class="c-master fsize16">${plan.peopleNum}</tt>人参加</span>
                        <span class="c-666 fsize14 ml30">已完成：<tt class="c-master fsize16">${plan.completeNum}</tt>人</span>
                        <span class="c-666 fsize14 ml30">未完成：<tt class="c-master fsize16">${plan.peopleNum-plan.completeNum}</tt>人</span>
                    </section>
                    <section class="c-learn-body mt20 u-c-l-b">
                        <c:if test="${empty recordList}">

                        </c:if>
                        <c:if test="${not empty recordList}">
                            <c:forEach items="${recordList}" var="record">
                                <span <c:if test="${record.progressPercentage<1}">class="p-not-finish"</c:if>>
                                    <c:if test="${not empty record.avatar}">
                                        <c:if test="${fn:contains(record.avatar,'http' )}">
                                            <img src="${record.avatar}">
                                        </c:if>
                                        <c:if test="${!fn:contains(record.avatar,'http' )}">
                                            <img src="<%=staticImageServer%>${record.avatar}">
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty record.avatar}">
                                        <img src="${ctx}/static/common/images/user_default.png">
                                    </c:if>
                                    <tt class="c-999 txtOf">${record.userName}</tt>
                                    <c:if test="${record.progressPercentage<1}"><div class="not-fin-chapter"><img src="${ctx}/static/nxb/web/img/not-f-chapter.png"></div></c:if>
                                </span>
                            </c:forEach>
                        </c:if>
                    </section>
                </div>
            </section>
        </div>
        <div class="i-box mt20">
            <section class="c-learn-plan">

                <c:if test="${empty phaseList}">

                </c:if>
                <c:if test="${not empty phaseList}">
                    <c:forEach items="${phaseList }" var="phase" varStatus="index">
                        <div class="mt15">
                            <div class="cou-info-menu">
                                <div class="chapter-name txtOf">
                                    <p>阶段${index.index+1} <span class="ml10">${phase.phaseName}</span></p>
                                    <div class="p-l-a-box">
                                        <c:if test="${phase.progressPercentage<1}">
                                            <a id="course_href${phase.id}" target="_blank" href="javascript:void(0)" class="p-learn-again"><font>继续学习</font></a>
                                        </c:if>
                                        <c:if test="${phase.progressPercentage>=1}">
                                            <a href="javascript:void(0)" class="p-learn-again"><font>重新学习</font></a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="chap-seclist pr">
                                <ul>
                                    <c:if test="${empty phase.phaseDetailList}">

                                    </c:if>
                                    <c:if test="${not empty phase.phaseDetailList}">
                                        <c:forEach items="${phase.phaseDetailList }" var="phaseDetail" varStatus="status" >
                                            <%-- 试卷 --%>
                                            <c:if test="${phaseDetail.type==1}">

                                            </c:if>
                                            <%-- 课程 --%>
                                            <c:if test="${phaseDetail.type==2}">
                                                <li class="course-list course-${phase.id}" data="${phaseDetail.otherId}" lang="${phaseDetail.progressPercentage}">
                                                    <div class="c-p-wrap current">
                                                        <div class="c-blue-dot"><tt></tt></div>
                                                        <a href="${ctx}/front/couinfo/${phaseDetail.otherId}" target="_blank" class="c-p-title txtOf">第${status.index+1}节 <span class="ml10">${phaseDetail.detailName}</span></a>
                                                        <c:choose>
                                                            <c:when test="${phaseDetail.progressPercentage<1}">
                                                                <a href="${ctx}/front/couinfo/${phaseDetail.otherId}" target="_blank" title="视频播放" class="play-icon-box p-l-continue">去学习</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="javascript:void(0)" title="视频播放" class="play-icon-box p-l-continue p-c-finish">已完成</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <div class="c-cou-type"><em class="icon16 ml5"></em></div>
                                                    </div>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <script type="text/javascript">
                                            var flag = true;
                                            $(".course-${phase.id}").each(function(){
                                                var _this = $(this);
                                                var progress = _this.attr("lang");
                                                if(flag&&progress<1){
                                                    $("#course_href${phase.id}").attr("href","${ctx}/front/couinfo/"+_this.attr("data"));
                                                    flag = false;
                                                }
                                            });
                                        </script>
                                    </c:if>
                                </ul>
                                <div class="c-white-box"></div>
                            </div>
                        </div>
                    </c:forEach>
                    <script type="text/javascript">
                        var flag = true;
                        $(".course-list").each(function(){
                            var _this = $(this);
                            var progress = _this.attr("lang");
                            if(flag&&progress<1){
                                $("#playCourse").attr("href","${ctx}/front/couinfo/"+_this.attr("data"));
                                flag = false;
                            }
                        });
                    </script>
                </c:if>
            </section>
        </div>
    </article>
<script type="text/javascript">

</script>
</body>
</html>
