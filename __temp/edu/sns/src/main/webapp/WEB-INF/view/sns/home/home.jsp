<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<head>
    <title>社区</title>
    <script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all-wb.js?v=<%=version%>"></script>
    <script src="${ctximg}/static/sns/js/weibo/weibo.js?v=<%=version%>" type="text/javascript"></script>
    <script src="${ctximg}/static/sns/js/home/home.js?v=<%=version%>" type="text/javascript"></script>
</head>
<body>
<input type="hidden" class="cusId" value="">
<!-- 主体 开始-->
<section class="W-main-c fl">
    <div class="W-main-cc">
        <section class="W-main-center">
            <div class="pl20">
                <section class="clearfix">
                    <div class="title-txt">与大家分享你的趣事儿！</div>
                    <div class="fr">&nbsp;</div>
                </section>
                <section class="mt10">
                    <div class="send-txt-input weiBoContent">
                        <textarea name="" id="weiBoContent" class="s-t-detail"></textarea>
                    </div>
                </section>
                <section class="mt5">
                    <div class="clearfix">

                        <div class="fr tar">
                            <span class="mr10 vam disIb"><span class="c-888 fsize12 error f-fM numStxt"></span></span>
                            <a href="javascript:void(0)" onclick="addWeiBo();" title="发布"
                               class="send-btn-wrap"><span>发布</span></a>
                        </div>
                        <div class="fl ml5"><label class="ml20 vam disIb c-888 fsize12" style="margin-top: -4px;"><input
                                type="checkbox" name="name" checked="checked" id="public"/>&nbsp;公开</label></div>
                    </div>
                </section>
                <!-- 页面提示  -->
                <section class="mt20 mb20">
                    <!-- <article class="comm-tips-1">
                        <span><i class="icon18 tipsIcon-1">&nbsp;</i><tt class="c-888 fsize12 ml10 vam">提示：近期有30个挑战，有4个挑战马上要开始，请不要错过</tt></span>
                    </article> -->
                </section>
                <!-- 页面提示  -->
                <!-- 动态 -->
                <section class="mt10 DT-wrap">
                    <header class="comm-title-1">
                        <ul class="clearfix">
                            <li class="current">
                                <a href="javascript:void(0)" title="动态">动态</a>

                                <div class="ct-tabarrow-bg">&nbsp;</div>
                            </li>
                        </ul>
                    </header>
                    <div class="tab-nosep mt10">
                        <ol class="clearfix">
                            <li class="current"><a href="javascript:void(0)" onclick="Dynamic(this,1)" title="全部">全部</a>
                            </li>
                            <li><a href="javascript:void(0)" onclick="Dynamic(this,2)" title="小组">小组</a></li>
                            <li><a href="javascript:void(0)" onclick="Dynamic(this,3)" title="好友">好友</a></li>
                            <li><a href="javascript:void(0)" onclick="Dynamic(this,4)" title="课程">课程</a></li>
                            <li><a href="javascript:void(0)" onclick="Dynamic(this,5)" title="考试">考试</a></li>
                        </ol>
                    </div>
                    <!-- 无数据时提示 -->

                    <!-- 无数据时提示 -->
                    <div id="dongtai">
                    </div>
                </section>
                <!-- 动态 -->

                <!-- 公共分页 开始 -->
                <div class="pagination pagination-large">
                    <ul id="loading" page="1" style="text-align: center;"><img
                            src="${ctximg}/static/sns/images/loading.gif" class="vam" width="15px" height="15px"/><tt
                            class="ml5 c-555 vam">正在加载，请稍后...</tt></ul>
                    <br>
                    <ul id="pageFlag"></ul>
                </div>
                <!-- 公共分页 结束 -->
            </div>
        </section>
        <section class="W-main-right">
            <div class="pl20">
                <div>
                    <section class="comm-title-2">
                        <span class="c-t-2-l">最近访客(<a href="${ctx}/friend?falg=visitor"><tt
                                class="fsize12 f-fM c-blue">${num }</tt></a>)</span>
                    </section>
                    <section class="visitor-list mt20">
                        <ul class="clearfix">
                            <c:forEach items="${visitorList }" var="vlt">
                                <li><a href="${ctx}/p/${vlt.visitorCusId }/home" target="_blank"
                                       title="${vlt.showname }">
                                    <c:if test="${vlt.avatar!=null&&vlt.avatar!='' }">
                                        <img src="<%=staticImageServer%>${vlt.avatar }" height="40" width="40" alt="">

                                        <p class="mb10"><span class="c-blue fsize14">
												<c:if test="${vlt.showname!=''}">
                                                    ${fn:substring(vlt.showname,0,6)}
                                                </c:if>
												
												<c:if test="${vlt.showname==''}">
                                                    &nbsp;
                                                </c:if>
												
												</span></p>
                                        <span class="c-888" title="${vlt.modelStr}"><fmt:formatDate pattern="MM月dd日"
                                                                                                    value="${vlt.addTime }"></fmt:formatDate></span>
                                    </c:if>
                                    <c:if test="${vlt.avatar==null||vlt.avatar=='' }">
                                        <img src="${ctximg}/static/sns/pics/user.jpg" height="40" width="40" alt="">

                                        <p class="mb10"><span class="c-blue fsize14">
													<c:if test="${vlt.showname!=''}">
                                                        ${fn:substring(vlt.showname,0,6)}
                                                    </c:if>
													
													<c:if test="${vlt.showname==''}">
                                                        &nbsp;
                                                    </c:if>
												</span></p>
                                        <span class="c-888"><fmt:formatDate pattern="MM月dd日"
                                                                            value="${vlt.addTime }"></fmt:formatDate></span>
                                    </c:if>
                                </a></li>
                            </c:forEach>
                        </ul>
                    </section>
                </div>
                <div id="weiboList">
                </div>
                <div id="courseList">
                </div>
            </div>
        </section>
    </div>
</section>
<!-- 主体区域 -->

</body>
