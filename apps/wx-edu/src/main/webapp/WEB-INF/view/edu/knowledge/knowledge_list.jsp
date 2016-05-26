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
            <a class="c-999 fsize14" title="" href="${ctx}/">首页</a> \
            <span class="c-333 fsize14">学习流程</span>
        </section>
        <section class="">
            <c:if test="${empty knowledgeList}">
                <div class="mt40">
                    <!-- /无数据提示 开始-->
                    <section class="no-data-wrap">
                        <em class="no-data-ico">&nbsp;</em>
                        <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
                    </section>
                </div>
            </c:if>
            <c:if test="${not empty knowledgeList}">
                <ul class="procedure-list">
                    <c:forEach items="${knowledgeList}" var="knowledge">
                        <li>
                            <div class="pro-l-wrap">
                                <div class="pr pro-l-bg hand" onclick="newHref(${knowledge.id})">
                                    <h3 class="proced-title"><span class="c-fff txtOf">${knowledge.name}</span></h3>
                                    <div class="pro-l-pic tac">
                                        <c:if test="${empty knowledge.picture}">
                                            <img src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="${ctximg}/static/common/images/default_goods.png" class="img-responsive" alt="">
                                        </c:if>
                                        <c:if test="${not empty knowledge.picture}">
                                            <img src="${ctximg}/static/nxb/web/img/default/online-cou-default.gif" xsrc="<%=staticImageServer%>${knowledge.picture}" class="img-responsive" alt="">
                                        </c:if>
                                    </div>
                                </div>
                                <div class="mt30">
                                    <div class="proce-desc">
                                        <p class="c-666 fsize14 hand" onclick="newHref(${knowledge.id})">${knowledge.description}</p>
                                    </div>
                                    <div class="clearfix mt20">
                                        <div class="fl"><span class="fsize16 c-666">课程总数：<tt class="c-master">${knowledge.courseNum}</tt></span></div>
                                        <div class="fr pr">
                                            <a class="c-cou-btn c-check ml20" href="${ctx}/front/getKnowledge/${knowledge.id}" id="c-i-tabTitle" name="c-r"><em class="icon14 vam"></em><tt class="ml5 fsize14 c-666 vam f-fM">查看</tt></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </section>
    </section>
</div>
<form id="searchForm" action="${ctx}/knowledge/getKnowledgeList" method="post">
    <input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
</form>
<jsp:include page="/WEB-INF/view/common/web_page.jsp" />
<script type="text/javascript">
    $(function() {
        scrollLoad();
    })
    function newHref(id){
        window.location.href="${ctx}/front/getKnowledge/"+id;
    }
</script>
</body>
</html>
