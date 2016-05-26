<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>知识体系</title>
</head>
<body>
    <div class="bg-fa of" id="aCoursesList">
        <!-- /课程列表 开始 -->
        <section class="container">
            <section class="path-wrap txtOf hLh30">
                <a class="c-999 fsize14" title="" href="${ctx}">首页</a> \
                <a class="c-999 fsize14" title="" href="${ctx}/front/getKnowledgeList">学习流程</a> \
                <span class="c-333 fsize14">${knowledge.name}</span>
            </section>
            <div class="i-box clearfix mt20 mb30">
                <div class="t-info-box c-procedure-box">
                    <div class="t-i-pic">
                        <a>
                            <c:choose>
                                <c:when test="${empty knowledge.picture}">
                                    <img width="210" height="210" src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="${ctx}/static/common/images/default_goods.png" class="img-responsive">
                                </c:when>
                                <c:otherwise>
                                    <img width="210" height="210" src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="<%=staticImageServer%>${knowledge.picture}" alt="" class="img-responsive">
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </div>
                    <div class="t-i-desc">
                        <h3 class="hLh30"> <span class="fsize24 c-333">${knowledge.name}</span> </h3>
                        <section class="c-tea-desc">
                            <p class="mt20 c-666">${knowledge.description}</p>
                        </section>
                        <div class="clearfix mt30 ">
                            <div class="fl">
                                <span class="c-666 fsize18">课程总数：<tt class="c-master fsize20 f-fH">${knowledge.courseNum}</tt></span>
                            </div>
                            <div class="p-s-margin fl">
                                <div class="c-share pr of" style="width: 256px;">
                                    <div class="disIb">
                                        <em class="icon14 vam"></em>
                                        <tt class="ml5 fsize14 c-666 vam f-fM">分享</tt>
                                    </div>
                                    <div class="bdsharebuttonbox bdshare-button-style0-16" id="bdshare" data-bd-bind="1456112747602" style="right: -160px;"><a data-cmd="more" class="bds_more" href="#"></a><a data-cmd="qzone" class="bds_qzone" href="#" title="分享到QQ空间"></a><a data-cmd="tsina" class="bds_tsina" href="#" title="分享到新浪微博"></a><a data-cmd="tqq" class="bds_tqq" href="#" title="分享到腾讯微博"></a><a data-cmd="renren" class="bds_renren" href="#" title="分享到人人网"></a><a data-cmd="weixin" class="bds_weixin" href="#" title="分享到微信"></a></div>
                                    <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="c-left-line1">
                <c:if test="${empty knowledgeCatalogList}">

                </c:if>
                <c:if test="${not empty knowledgeCatalogList}">
                    <c:set var="courseCount" value="0"/>
                    <c:forEach items="${knowledgeCatalogList}" varStatus="index" var="catalog">
                        <section class="mt20 mb20">
                            <header class="clearfix p-title-box">
                                <div class="fl pr">
                                    <span class="tac c-fff c-proc-title">${catalog.name}</span>
                                    <tt class="p-c-circle f-fH">${index.index+1}</tt>
                                </div>
                                <span class="fsize14 c-p-yellow fl ml30 mt10">${catalog.description}</span>
                            </header>
                            <div class="net-cou-box mt30 c-p-clist">
                                <c:if test="${empty catalog.courseList}">
                                    <div class="mt40">
                                        <!-- /无数据提示 开始-->
                                        <section class="no-data-wrap">
                                            <em class="no-data-ico">&nbsp;</em>
                                            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
                                        </section>
                                    </div>
                                </c:if>
                                <c:if test="${not empty catalog.courseList}">
                                    <ul class="of job-cou-list">
                                        <c:forEach items="${catalog.courseList}" var="course">
                                            <li>
                                                <div class="cc-l-wrap">
                                                    <a class="course-img" href="${ctx}/front/couinfo/${course.id}">
                                                        <c:choose>
                                                            <c:when test="${empty course.logo}">
                                                                <img src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="${ctx}/static/common/images/default_goods.png" alt="" class="img-responsive">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="<%=staticImageServer%>${course.logo}" alt="" class="img-responsive">
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </a>
                                                    <div class="j-c-desc-wrap">
                                                        <h3 class="hLh30 txtOf pt10">
                                                            <a class="j-course-title" title="${course.name}" href="${ctx}/front/couinfo/${course.id}">${course.name}</a>
                                                        </h3>
                                                        <div class="clearfix of mt15 cj-cou-ds">
                                                            <span class="fl c-999 f-fM txtOf">讲师：
                                                                <c:forEach items="${course.teacherList}" var="teacher">
                                                                    <a class="c-666 mr10" href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
                                                                </c:forEach>
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
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </div>
                        </section>
                    </c:forEach>
                </c:if>
            </div>
        </section>
    </div>
<script type="text/javascript">
    $(function() {
        scrollLoad();
    })
</script>
</body>
</html>
