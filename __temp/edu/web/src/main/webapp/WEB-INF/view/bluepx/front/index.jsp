<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>

<!-- HOME SLIDER -->
<section class="slide" style="background-image: url(/static/bluepx/images/homeslider/bg.jpg)">
    <div class="container">
        <div class="slide-cn" id="slide-home">
            <!-- SLIDE ITEM -->
            <div class="slide-item">
                <div class="item-inner">
                    <div class="text">
                        <h2>更多的课程、更新的课程</h2>
                        <p>观注我们，跟随我们！
                        </p>
                        <div class="group">
                            <a href="#" class="mc-btn btn-style-1">最完整的课程体系</a>
                        </div>
                    </div>

                    <div class="img">
                        <img src="/static/bluepx/images/homeslider/img-thumb.png" alt="">
                    </div>
                </div>

            </div>
            <!-- SLIDE ITEM -->

            <!-- SLIDE ITEM -->
            <div class="slide-item">
                <div class="item-inner">
                    <div class="text">
                        <h2>更多的课程、更新的课程</h2>
                        <p>观注我们，跟随我们！
                        </p>
                        <div class="group">
                            <a href="#" class="mc-btn btn-style-1">最特色的课程！</a>
                        </div>
                    </div>

                    <div class="img">
                        <img src="/static/bluepx/images/homeslider/img-thumb.png" alt="">
                    </div>

                </div>
            </div>
            <!-- SLIDE ITEM -->

        </div>
    </div>
</section>
<!-- END / HOME SLIDER -->


<!-- AFTER SLIDER -->
<section id="after-slider" class="after-slider section">
    <div class="awe-color bg-color-1"></div>
    <div class="after-slider-bg-2"></div>
    <div class="container">

        <div class="after-slider-content tb">
            <div class="inner tb-cell">
                <h4>查找你想学的课程</h4>
                <div class="course-keyword">
                    <input type="text" placeholder="课程名">
                </div>
                <div class="mc-select-wrap">
                    <div class="mc-select">
                        <select class="select" name="" id="all-categories">
                            <option value="" selected>所有课程</option>
                            <option value="">2</option>
                        </select>
                    </div>
                </div>
                <div class="mc-select-wrap">
                    <div class="mc-select">
                        <select class="select" name="" id="beginner-level">
                            <option value="" selected>级别</option>
                            <option value="">Beginner level 2</option>
                            <option value="">Beginner level 3</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="tb-cell text-right">
                <div class="form-actions">
                    <input type="submit" value="搜索课程" class="mc-btn btn-style-1">
                </div>
            </div>
        </div>

    </div>
</section>
<!-- END / AFTER SLIDER -->

<!-- SECTION 1 -->
<section id="mc-section-1" class="mc-section-1 section">
    <div class="container">
        <div class="row">

            <div class="col-md-5">
                <div class="mc-section-1-content-1">
                    <h2 class="big">在线学习课程</h2>
                    <p class="mc-text">在线学习是一种潮流，是属于我们这个时代的学习方法.</p>
                    <a href="#" class="mc-btn btn-style-1">关于我们</a>
                </div>
            </div>

            <div class="col-md-6 col-lg-offset-1">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="featured-item">
                            <i class="icon icon-featured-1"></i>
                            <h4 class="title-box text-uppercase">简单易学</h4>
                            <p>随时随地，最简单的操作，最理想的学习方式。</p>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="featured-item">
                            <i class="icon icon-featured-2"></i>
                            <h4 class="title-box text-uppercase">学以致用</h4>
                            <p>边学边练，提供给您更好的练习平台，学以致用。</p>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="featured-item">
                            <i class="icon icon-featured-3"></i>
                            <h4 class="title-box text-uppercase">学习社区</h4>
                            <p>交流、学习、沟通，提供全网最优秀的学习交流、资源分享社区</p>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="featured-item">
                            <i class="icon icon-featured-4"></i>
                            <h4 class="title-box text-uppercase">教学跟踪</h4>
                            <p>智能学业跟踪，社区互联教学，丰富的协教平台。</p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- END / SECTION 1 -->


<!-- SECTION 2 -->
<section id="mc-section-2" class="mc-section-2 section">
    <div class="awe-parallax bg-section1-demo"></div>
    <div class="overlay-color-1"></div>
    <div class="container">
        <div class="section-2-content">
            <div class="row">

                <div class="col-md-5">
                    <div class="ct">
                        <h2 class="big">随时随地轻松的在线学习</h2>
                        <p class="mc-text">边学边练，提供给您更好的练习平台，学以致用。交流、学习、沟通，提供全网最优秀的学习交流、资源分享社区</p>
                        <a href="#" class="mc-btn btn-style-3">学习从这里开始</a>
                    </div>
                </div>

                <div class="col-md-7">
                    <div class="image">
                        <img src="/static/bluepx/images/image.png" alt="">
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- END / SECTION 2 -->


<!-- SECTION 3 -->
<section id="mc-section-3" class="mc-section-3 section">
    <div class="container">
        <!-- FEATURE -->
        <div class="feature-course">
            <h4 class="title-box text-uppercase">课程推荐</h4>
            <a href="categories.html" class="all-course mc-btn btn-style-1">全部课程</a>
            <div class="row">
                <div class="feature-slider">
                    <c:forEach items="${mapCourseList.index_course_1}" var="courseHot">

                        <div class="mc-item mc-item-1">
                            <div class="image-heading">
                                    <%--<img src="images/feature/img-1.jpg" alt="">--%>
                                <c:choose>
                                    <c:when test="${not empty courseHot.logo}">
                                        <img xSrc="<%=staticImageServer %>${courseHot.logo}"
                                             src="<%=imagesPath%>/static/edu/images/default/c-logo.png"
                                             style="cursor: pointer;" height="225" width="300" alt="${courseHot.name}">
                                    </c:when>
                                    <c:otherwise>
                                        <img xSrc="${ctximg}/static/edu/images/default/default_goods.jpg"
                                             src="<%=imagesPath%>/static/edu/images/default/c-logo.png"
                                             style="cursor: pointer;" height="225" width="300" alt="${courseHot.name}">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="meta-categories">
                                <a href="${ctx}/front/couinfo/${courseHot.id}" target="_blank"
                                   title="${courseHot.name}">
                                        ${courseHot.name}
                                </a>

                            </div>
                            <div class="content-item">
                                <div class="image-author">
                                    <img src="/static/bluepx/images/avatar-1.jpg" alt="">
                                </div>
                                <h4>
                                    <a href="course-intro.html">${courseHot.title}</a>
                                </h4>
                                <div class="name-author">
                                    <%--By <a href="#">王美老师</a>--%>
                                    <c:forEach items="${courseHot.teacherList}" var="teacher">
                                        <a target="_blank" href="${ctx }/front/teacher/${teacher.id}"
                                           title="${teacher.name}">${teacher.name}</a>&nbsp;
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
                                    ${courseHot.viewcount}
                                </div>
                                <div class="comment-info">
                                    <i class="icon md-comment"></i>
                                    ${courseHot.commentcount}
                                </div>
                                <div class="price">
                                    ￥ ${courseHot.currentprice}
                                    <span class="price-old">￥ ${courseHot.currentprice}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- END / FEATURE -->
    </div>
</section>
<!-- END / SECTION 3 -->
    