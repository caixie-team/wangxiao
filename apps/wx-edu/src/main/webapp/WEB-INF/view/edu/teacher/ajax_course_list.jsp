<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/base.jsp"%>
<form id="searchForm" name="searchForm" action="${ctx}/front/teacher/${teacher.id}" method="post">
    <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
</form>
<ul class="of job-cou-list" id="aCoursesList">
    <c:if test="${empty courseList}">
        <div class="mt40">
            <!-- /无数据提示 开始-->
            <section class="no-data-wrap">
                <em class="no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">教师还未授课</span>
            </section>
            <!-- /无数据提示 结束-->
        </div>
    </c:if>
    <c:if test="${not empty courseList}">
        <c:forEach items="${courseList}" var="course">
            <li>
                <div class="cc-l-wrap">
                    <a class="course-img" href="${ctx}/front/couinfo/${course.id}">
                        <c:choose>
                            <c:when test="${not empty course.logo}">
                                <img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${course.logo}" alt="" class="img-responsive">
                            </c:when>
                            <c:otherwise>
                                <img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${courseimagemap.courseimage.url}" alt="" class="img-responsive">
                            </c:otherwise>
                        </c:choose>
                    </a>
                    <div class="j-c-desc-wrap">
                        <h3 class="hLh30 txtOf pt10">
                            <a class="j-course-title" title="" href="${ctx}/front/couinfo/${course.id}">${course.name}</a>
                        </h3>
                        <div class="clearfix of mt15 cj-cou-ds">
                            <span class="fl c-999 f-fM txtOf">讲师： <c:forEach items="${course.teacherList }" var="tea">
                                <a class="c-666 mr10" href="">${tea.name }</a>
                            </c:forEach>
                            </span> <span class="fr c-999 f-fM ">课时：<span class="c-666">${course.totalLessionnum }</span></span>
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
                                <div style="border-right: none;" class="c-c-sbox txtOf" title="价格：${course.currentprice }元">
                                    <em class="icon12 c-couse-v"></em>
                                    <tt class="fsize14  vam">${course.currentprice }</tt>
                                </div>
                            </dd>
                        </dl>
                    </div>
                </div>
            </li>
        </c:forEach>
    </c:if>
</ul>
<jsp:include page="/WEB-INF/view/common/ajax_web_page.jsp" />
<script type="text/javascript">
    $(function(){
        scrollLoad(); //响应滚动加载课程图片
    })
</script>