<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>

<!-- SUB BANNER -->
<section class="sub-banner section">
    <div class="awe-parallax bg-profile-feature"></div>
    <div class="awe-overlay overlay-color-3"></div>
    <div class="container">
        <div class="sub-banner-content">
            <h2 class="big">这是最好的时代,也是最坏的时代</h2>
            <p>互联网时代如何学习,互联网时代如何进步!</p>
            <a href="#" class="mc-btn btn-style-3">进入直播课堂</a>
        </div>
    </div>
</section>
<!-- END / SUB BANNER -->


<!-- PAGE CONTROL -->
<section class="page-control">
    <div class="container">
        <div class="page-info">
            <a href="index.html"><i class="icon md-arrow-left"></i>返回首页</a>
        </div>
        <div class="page-view">
            展示形式
            <span class="page-view-info view-grid active" title="View grid"><i class="icon md-ico-2"></i></span>
            <span class="page-view-info view-list" title="View list"><i class="icon md-ico-1"></i></span>
            <div class="mc-select">
                <select class="select" name="" id="all-categories">
                    <option value="">所有课程</option>
                    <option value="">2</option>
                    <option value="${memberType}">${memberType.title}</option>
                </select>
            </div>
        </div>
    </div>
</section>
<!-- END / PAGE CONTROL -->

<!-- CATEGORIES CONTENT -->
<section id="categories-content" class="categories-content">
    <div class="container">
        <div class="row">
            <div class="col-md-9 col-md-push-3">
                <div class="content grid">
                    <div class="row">
                        <c:if test="${courseList.size()==0}">
                            <p>对不起，此条件下还没有相关课程！</p>
                        </c:if>
                        <c:if test="${courseList.size()>0}">
                            <c:forEach items="${courseList}" var="course" varStatus="index">

                                <!-- MC ITEM -->
                                <div class="col-sm-6 col-md-4">
                                    <div class="mc-item mc-item-2">
                                        <div class="image-heading">
                                            <c:choose>
                                                <c:when test="${!empty course.logo&&course.logo!=''}">
                                                    <img src="<%=staticImageServer%>${course.logo}"
                                                         style="width:277.125px;height:166.0px;"
                                                         alt="${course.name}">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg"
                                                         style="width:277.125px;height:166.0px;"
                                                         alt="${course.name}">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="meta-categories"><a
                                                href="${ctx}/front/couinfo/${course.id}">${course.name}</a></div>
                                        <div class="content-item">
                                            <div class="image-author">
                                                <img src="/static/bluepx/images/avatar-1.jpg" alt="">
                                            </div>
                                            <h4>
                                                <a href="${ctx}/front/couinfo/${course.id}">${course.title}</a>
                                            </h4>
                                            <div class="name-author">
                                                <c:forEach items="${course.teacherList}" var="teacher">
                                                    <a class="c-666"
                                                       href="${ctx }/front/teacher/${teacher.id}">${teacher.name}</a>&nbsp;
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="ft-item">
                                            <div class="rating">
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>
                                            <div class="view-info">
                                                <i class="icon md-users"></i>
                                                    ${course.viewcount}
                                            </div>
                                            <div class="comment-info">
                                                <i class="icon md-comment"></i>
                                                    ${course.pageViewcount}"
                                            </div>
                                            <div class="price">
                                                $190
                                                <span class="price-old">￥${course.currentprice}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END / MC ITEM -->
                                </div>
                            </c:forEach>

                        </c:if>

                    </div>
                </div>
            </div>

            <!-- SIDEBAR CATEGORIES -->
            <div class="col-md-3 col-md-pull-9">
                <aside class="sidebar-categories">
                    <div class="inner">

                        <!-- WIDGET TOP -->
                        <div class="widget">
                            <ul class="list-style-block">
                                <%--<li class="current"><a href="#">推荐课程</a></li>--%>
                                <%--<li><a href="#">免费课程</a></li>--%>
                                <%--<li><a href="#">热门课程</a></li>--%>
                                <c:forEach items="${memberTypes }" var="memberType">
                                    <li><a onclick="clickSearch('memberType',${memberType.id})"
                                           title="${memberType.title }"
                                           href="javascript:void(0);"> ${ memberType.title} </a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <!-- END / WIDGET TOP -->

                        <!-- WIDGET CATEGORIES -->
                        <div class="widget widget_categories">
                            <ul class="list-style-block">
                                <c:forEach items="${subjectList }" var="subject">
                                    <li><a onclick="clickSearch('subject',${subject.subjectId})"
                                           title="${subject.subjectName }"
                                           href="javascript:void(0);">${subject.subjectName }</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                        <!-- END / WIDGET CATEGORIES -->

                        <!-- BANNER ADS -->
                        <div class="mc-banner">
                            <a href="#"><img src="/static/bluepx/images/banner-ads-1.jpg" alt=""></a>
                        </div>
                        <!-- END / BANNER ADS -->

                        <!-- BANNER ADS -->
                        <div class="mc-banner">
                            <a href="#"><img src="/static/bluepx/images/banner-ads-2.jpg" alt=""></a>
                        </div>
                        <!-- END / BANNER ADS -->
                    </div>
                </aside>
            </div>
            <!-- END / SIDEBAR CATEGORIES -->

        </div>
    </div>
</section>
<!-- END / CATEGORIES CONTENT -->