<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>

<!-- SUB BANNER -->
<section class="sub-banner sub-banner-course">
    <div class="awe-static bg-sub-banner-course"></div>
    <div class="container">
        <div class="sub-banner-content">
            <h2 class="text-center">${course.name}</h2>
            <h4 class="text-center">${course.title}</h4>
        </div>
    </div>
</section>
<!-- END / SUB BANNER -->


<!-- COURSE -->
<section class="course-top">
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <div class="sidebar-course-intro">
                    <div class="breadcrumb">
                        <a href="/">首页</a> /
                        <a href="${ctx}/front/showcoulist">课程</a> /
                        ${course.name}
                    </div>
                    <div class="video-course-intro">
                        <div class="inner">
                            <div class="video-place">
                                <div class="img-thumb">
                                    <img src="/static/bluepx/images/thumb-intro.jpg" alt="">
                                </div>
                                <div class="awe-overlay"></div>
                                <a href="#" class="play-icon">
                                    <i class="fa fa-play"></i>
                                </a>
                            </div>
                            <div class="video embed-responsive embed-responsive-16by9">
                                <iframe src="//player.vimeo.com/video/100872038" class="embed-responsive-item">
                                </iframe>
                            </div>
                        </div>
                        <div class="price">
                            ￥${course.currentprice}
                        </div>
                        <a href="#" class="take-this-course mc-btn btn-style-1">购买</a>
                    </div>

                    <div class="new-course">
                        <div class="item course-code">
                            <i class="icon md-barcode"></i>
                            <h4><a href="#">课程码</a></h4>
                            <p class="detail-course"># A 15273</p>
                        </div>
                        <div class="item course-code">
                            <i class="icon md-time"></i>
                            <h4><a href="#">课时</a></h4>
                            <p class="detail-course">${course.buycount}</p>
                        </div>
                        <%--非直播课程显示有效期--%>
                        <c:if test="${course.sellType!='LIVE'}">
                            <div class="item course-code">
                                <i class="icon md-img-check"></i>
                                <h4><a href="#">有效期</a></h4>
                                <p class="detail-course">25 May 2014</p>
                                <c:if test="${course.losetype==0 }">
                                    <p class="detail-course">
                                        <fmt:formatDate value="${course.loseAbsTime }"/> （在此之前可反复观看)
                                    </p>
                                </c:if>
                                <c:if test="${course.losetype==1 }">
                                    <p class="detail-course">
                                        从购买之日起${course.loseTime }天（在此之间可反复观看)
                                    </p>
                                </c:if>
                            </div>
                        </c:if>
                        <%--直播课程显示开始直播时间--%>
                        <c:if test="${course.sellType=='LIVE'}">
                            <p class="detail-course">
                                直播开始时间:<fmt:formatDate value="${course.liveBeginTime}" type="both"/>
                            </p>
                        </c:if>

                    </div>
                    <hr class="line">
                    <div class="about-instructor">
                        <h4 class="xsm black bold">讲师介绍</h4>
                        <ul>
                            <c:forEach items="${course.teacherList }" var="tea">
                                <%--<span class="c-666" style="color: #FFFFFF;" title="${tea.name }"><a href="/front/teacher/${tea.id }" >${tea.name }</a> </span>--%>
                                <li>
                                    <div class="image-instructor text-center">
                                        <img src="/static/bluepx/images/team-13.jpg" alt="${tea.name}">
                                    </div>
                                    <div class="info-instructor">
                                        <cite class="sm black"><a href="/front/teacher/${tea.id}">${tea.name}</a></cite>
                                        <a href="#"><i class="fa fa-star"></i></a>
                                        <a href="#"><i class="fa fa-envelope"></i></a>
                                        <a href="#"><i class="fa fa-check-square"></i></a>
                                        <p>${tea.career}</p>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <hr class="line">
                    <div class="widget widget_equipment">
                        <i class="icon md-config"></i>
                        <h4 class="xsm black bold">学习工具</h4>
                        <div class="equipment-body">
                            <a href="#">Photoshop CC</a>,
                            <a href="#">Illustrator CC</a>
                        </div>
                    </div>
                    <div class="widget widget_tags">
                        <i class="icon md-download-2"></i>
                        <h4 class="xsm black bold">标签</h4>
                        <div class="tagCould">
                            <a href="#">Design</a>,
                            <a href="#">Photoshop</a>,
                            <a href="#">Illustrator</a>,
                            <a href="">Art</a>,
                            <a href="">Graphic Design</a>
                        </div>
                    </div>
                    <div class="widget widget_share">
                        <i class="icon md-forward"></i>
                        <h4 class="xsm black bold">课程分享</h4>
                        <div class="share-body">
                            <a href="#" class="twitter" title="twitter">
                                <i class="icon md-twitter"></i>
                            </a>
                            <a href="#" class="pinterest" title="pinterest">
                                <i class="icon md-pinterest-1"></i>
                            </a>
                            <a href="#" class="facebook" title="facebook">
                                <i class="icon md-facebook-1"></i>
                            </a>
                            <a href="#" class="google-plus" title="google plus">
                                <i class="icon md-google-plus"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
                <div class="tabs-page">
                    <ul class="nav-tabs" role="tablist">
                        <li class="active"><a href="#introduction" role="tab" data-toggle="tab">课程介绍</a></li>
                        <li><a href="#outline" role="tab" data-toggle="tab">课程目录</a></li>
                        <li><a href="#review" role="tab" data-toggle="tab">专家点评</a></li>
                        <li><a href="#student" role="tab" data-toggle="tab">学员</a></li>
                        <li><a href="#conment" role="tab" data-toggle="tab">课程评论</a></li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <!-- INTRODUCTION -->
                        <div class="tab-pane fade in active" id="introduction">
                            <c:choose>
                                <c:when test="${not empty course.context}">${course.context}</c:when>
                                <c:otherwise>
                                        <p>
                                            课程内容小编正在积极整理中 . . .
                                        </p>
                                    </section>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- END / INTRODUCTION -->

                        <!-- OUTLINE -->
                        <div class="tab-pane fade" id="outline">

                            <!-- SECTION OUTLINE -->
                            <div class="section-outline">
                                <h4 class="tit-section xsm">Section 1: Introduction</h4>
                                <ul class="section-list">
                                    <li>
                                        <div class="count"><span>1</span></div>
                                        <div class="list-body">
                                            <i class="icon md-camera"></i>
                                            <p><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elita</a>
                                            </p>
                                            <div class="data-lessons">
                                                <span>36:56</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>2</span></div>
                                        <div class="list-body">
                                            <i class="icon md-gallery-1"></i>
                                            <p><a href="#">Lorem ipsum dolor sit ameUt wisi enim ad minim veniam, quis
                                                nostrud Lorem ipsum dolor sit ame tation ullamcorper suscipit
                                                loborti</a></p>
                                            <div class="data-lessons">
                                                <span>15 images</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>3</span></div>
                                        <div class="list-body">
                                            <i class="icon md-volume-down"></i>
                                            <p><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elita</a>
                                            </p>
                                            <div class="data-lessons">
                                                <span>36:56</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>4</span></div>
                                        <div class="list-body">
                                            <i class="icon md-gallery-2"></i>
                                            <p><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elita</a>
                                            </p>
                                            <div class="data-lessons">
                                                <span>45 slides</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span><i class="icon md-search"></i></span></div>
                                        <div class="list-body">
                                            <i class="icon md-files"></i>
                                            <p><a href="#"><span>Quizz 1 :</span> Lorem ipsum dolor sit ameUt wisi enim
                                                ad minim veniam</a></p>
                                            <div class="data-lessons">
                                                <span>10 questions</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>a</span></div>
                                        <div class="list-body">
                                            <i class="icon md-files"></i>
                                            <p><a href="#"><span>Assignment :</span> Lorem ipsum dolor sit ameUt wisi
                                                enim ad minim veniam</a></p>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- END / SECTION OUTLINE -->

                            <!-- SECTION OUTLINE -->
                            <div class="section-outline">
                                <h4 class="tit-section xsm">Section 2 : Lorem ipsum dolor sit ame wisi enim ad minim
                                    veniam, quis nostrud exerc</h4>
                                <ul class="section-list">
                                    <li>
                                        <div class="count"><span>5</span></div>
                                        <div class="list-body">
                                            <i class="icon md-camera"></i>
                                            <p><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elita</a>
                                            </p>
                                            <div class="data-lessons">
                                                <span>36:56</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>6</span></div>
                                        <div class="list-body">
                                            <i class="icon md-gallery-1"></i>
                                            <p><a href="#">Lorem ipsum dolor sit ameUt wisi enim ad minim veniam, quis
                                                nostrud Lorem ipsum dolor sit ame tation ullamcorper suscipit
                                                loborti</a></p>
                                            <div class="data-lessons">
                                                <span>15 images</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>7</span></div>
                                        <div class="list-body">
                                            <i class="icon md-volume-down"></i>
                                            <p><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elita</a>
                                            </p>
                                            <div class="data-lessons">
                                                <span>36:56</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>8</span></div>
                                        <div class="list-body">
                                            <i class="icon md-gallery-2"></i>
                                            <p><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elita</a>
                                            </p>
                                            <div class="data-lessons">
                                                <span>45 slides</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span><i class="icon md-search"></i></span></div>
                                        <div class="list-body">
                                            <i class="icon md-files"></i>
                                            <p><a href="#"><span>Quizz 1 :</span> Lorem ipsum dolor sit ameUt wisi enim
                                                ad minim veniam</a></p>
                                            <div class="data-lessons">
                                                <span>10 questions</span>
                                            </div>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>

                                    <li>
                                        <div class="count"><span>a</span></div>
                                        <div class="list-body">
                                            <i class="icon md-files"></i>
                                            <p><a href="#"><span>Assignment :</span> Lorem ipsum dolor sit ameUt wisi
                                                enim ad minim veniam</a></p>
                                        </div>
                                        <a href="#" class="mc-btn-2 btn-style-2">Preview</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- END / SECTION OUTLINE -->
                        </div>
                        <!-- END / OUTLINE -->

                        <!-- REVIEW -->
                        <div class="tab-pane fade" id="review">
                            <div class="total-review">
                                <h3 class="md black">4 Reviews</h3>
                                <div class="rating">
                                    <a href="#" class="active"></a>
                                    <a href="#" class="active"></a>
                                    <a href="#" class="active"></a>
                                    <a href="#"></a>
                                    <a href="#"></a>
                                </div>
                            </div>
                            <ul class="list-review">
                                <li class="review">
                                    <div class="body-review">
                                        <div class="review-author">
                                            <a href="#">
                                                <img src="images/team-13.jpg" alt="">
                                                <i class="icon md-email"></i>
                                                <i class="icon md-user-plus"></i>
                                            </a>
                                        </div>
                                        <div class="content-review">
                                            <h4 class="sm black">
                                                <a href="#">John Doe</a>
                                            </h4>
                                            <div class="rating">
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>

                                            <em>5 days ago</em>
                                            <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et, interdum
                                                semper quam. Fusce in interdum tortor. Ut sollicitudin lectus dolor eget
                                                imperdiet libero pulvinar sit amet</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="review">
                                    <div class="body-review">
                                        <div class="review-author">
                                            <a href="#">
                                                <img src="images/team-13.jpg" alt="">
                                                <i class="icon md-email"></i>
                                                <i class="icon md-user-plus"></i>
                                            </a>
                                            <i class="icon"></i>
                                            <i class="icon"></i>
                                        </div>
                                        <div class="content-review">
                                            <h4 class="sm black">
                                                <a href="#">John Doe</a>
                                            </h4>
                                            <div class="rating">
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>
                                            <em>5 days ago</em>
                                            <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et, interdum
                                                semper quam. Fusce in interdum tortor. Ut sollicitudin lectus dolor eget
                                                imperdiet libero pulvinar sit amet</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="review">
                                    <div class="body-review">
                                        <div class="review-author">
                                            <a href="#">
                                                <img src="images/team-13.jpg" alt="">
                                                <i class="icon md-email"></i>
                                                <i class="icon md-user-plus"></i>
                                            </a>
                                            <i class="icon"></i>
                                            <i class="icon"></i>
                                        </div>
                                        <div class="content-review">
                                            <h4 class="sm black">
                                                <a href="#">John Doe</a>
                                            </h4>
                                            <div class="rating">
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#" class="active"></a>
                                                <a href="#"></a>
                                                <a href="#"></a>
                                            </div>
                                            <em>5 days ago</em>
                                            <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et, interdum
                                                semper quam. Fusce in interdum tortor. Ut sollicitudin lectus dolor eget
                                                imperdiet libero pulvinar sit amet</p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <div class="load-more">
                                <a href="">
                                    <i class="icon md-time"></i>
                                    Load more previous update</a>
                            </div>
                        </div>
                        <!-- END / REVIEW -->

                        <!-- STUDENT -->
                        <div class="tab-pane fade" id="student">
                            <c:choose>
                                <c:when test="${userExpandList==null}">
                                    <h3 class="md black">此课程还没有学员购买</h3>
                                </c:when>
                                <c:otherwise>
                                    <h3 class="md black">${userExpandList.size()}个学员正在学习</h3>
                                    <div class="tab-list-student">
                                        <ul class="list-student">

                                            <c:forEach items="${userExpandList}" var="userExpand" varStatus="index">
                                                <!-- LIST STUDENT -->
                                                <li>
                                                    <div class="image">
                                                        <c:choose>
                                                            <c:when test="${not empty userExpand.avatar}">
                                                                <%--<img src="images/team-13.jpg" alt="">--%>
                                                                <img src="<%=staticImageServer%>${userExpand.avatar}"
                                                                     width="30"
                                                                     height="30" alt="${userExpand.email}"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <img src="<%=imagesPath%>/static/common/images/user_default.jpg"
                                                                     width="30" height="30"
                                                                     alt="${userExpand.nickname}"/>
                                                            </c:otherwise>
                                                        </c:choose>

                                                    </div>
                                                    <div class="list-body">
                                                        <c:choose>
                                                            <c:when test="${not empty userExpand.nickname}">
                                                                <cite class="xsm"><a
                                                                        href="#">${userExpand.nickname}<</a></cite>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <cite class="xsm"><a
                                                                        href="#">${userExpand.email}</a></cite>
                                                            </c:otherwise>
                                                        </c:choose>

                                                            <%--<span class="address">Hanoi, Vietnam</span>--%>
                                                        <div class="icon-wrap">
                                                            <a href="#"><i class="icon md-email"></i></a>
                                                            <a href="#"><i class="icon md-user-plus"></i></a>
                                                        </div>
                                                    </div>
                                                </li>
                                                <!-- END / LIST STUDENT -->
                                            </c:forEach>


                                        </ul>
                                    </div>
                                    <%--<div class="load-more">--%>
                                        <%--<a href="">--%>
                                            <%--<i class="icon md-time"></i>--%>
                                            <%--Load more previous update</a>--%>
                                    <%--</div>--%>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- END / STUDENT -->

                        <!-- COMMENT -->
                        <div class="tab-pane fade" id="conment">
                            <div id="respond">
                                <h3 class="md black">100 Comments</h3>
                                <form>
                                    <div class="comment-form-comment">
                                        <textarea placeholder="You have comment or question, write it here"></textarea>
                                    </div>
                                    <div class="form-submit">
                                        <input type="submit" value="Post" class="mc-btn-2 btn-style-2">
                                    </div>
                                </form>
                            </div>
                            <ul class="commentlist">
                                <li class="comment">
                                    <div class="comment-body">
                                        <div class="comment-author">
                                            <a href="#">
                                                <img src="images/team-13.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="comment-content">
                                            <cite class="fn sm black bold"><a href="">John Doe</a></cite>
                                            <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et, interdum
                                                semper quam. Fusce in interdum tortor. Ut sollicitudin lectus dolor eget
                                                imperdiet libero pulvinar sit amet</p>

                                            <div class="comment-meta">
                                                <a href="#">5 days ago</a>
                                                <a href="#">Hide 2 replies</a>
                                                <a href="#"><i class="icon md-arrow-up"></i>13</a>
                                                <a href="#"><i class="icon md-arrow-down"></i>25</a>
                                            </div>

                                        </div>
                                    </div>
                                    <ul class="children">
                                        <li class="comment">
                                            <div class="comment-body">
                                                <div class="comment-author">
                                                    <a href="#">
                                                        <img src="images/team-13.jpg" alt="">
                                                    </a>
                                                </div>
                                                <div class="comment-content">
                                                    <cite class="fn sm black bold"><a href="">John Doe</a></cite>
                                                    <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et,
                                                        interdum semper quam. Fusce in interdum tortor. Ut sollicitudin
                                                        lectus dolor eget imperdiet libero pulvinar sit amet</p>

                                                    <div class="comment-meta">
                                                        <a href="#">5 days ago</a>
                                                        <a href="#"><i class="icon md-arrow-up"></i>13</a>
                                                        <a href="#"><i class="icon md-arrow-down"></i>25</a>
                                                    </div>

                                                </div>
                                            </div>
                                        </li>
                                        <li class="comment">
                                            <div class="comment-body">
                                                <div class="comment-author">
                                                    <a href="#">
                                                        <img src="images/team-13.jpg" alt="">
                                                    </a>
                                                </div>
                                                <div class="comment-content">
                                                    <cite class="fn sm black bold"><a href="">John Doe</a></cite>
                                                    <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et,
                                                        interdum semper quam. Fusce in interdum tortor. Ut sollicitudin
                                                        lectus dolor eget imperdiet libero pulvinar sit amet</p>

                                                    <div class="comment-meta">
                                                        <a href="#">5 days ago</a>
                                                        <a href="#"><i class="icon md-arrow-up"></i>13</a>
                                                        <a href="#"><i class="icon md-arrow-down"></i>25</a>
                                                    </div>

                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                                <li class="comment">
                                    <div class="comment-body">
                                        <div class="comment-author">
                                            <a href="#">
                                                <img src="images/team-13.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="comment-content">
                                            <cite class="fn sm black bold"><a href="">John Doe</a></cite>
                                            <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et, interdum
                                                semper quam. Fusce in interdum tortor. Ut sollicitudin lectus dolor eget
                                                imperdiet libero pulvinar sit amet</p>

                                            <div class="comment-meta">
                                                <a href="#">5 days ago</a>
                                                <a href="#">Hide 2 replies</a>
                                                <a href="#"><i class="icon md-arrow-up"></i>13</a>
                                                <a href="#"><i class="icon md-arrow-down"></i>25</a>
                                            </div>

                                        </div>
                                    </div>
                                </li>
                                <li class="comment">
                                    <div class="comment-body">
                                        <div class="comment-author">
                                            <a href="#">
                                                <img src="images/team-13.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="comment-content">
                                            <cite class="fn sm black bold"><a href="">John Doe</a></cite>
                                            <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et, interdum
                                                semper quam. Fusce in interdum tortor. Ut sollicitudin lectus dolor eget
                                                imperdiet libero pulvinar sit amet</p>

                                            <div class="comment-meta">
                                                <a href="#">5 days ago</a>
                                                <a href="#">Hide 2 replies</a>
                                                <a href="#"><i class="icon md-arrow-up"></i>13</a>
                                                <a href="#"><i class="icon md-arrow-down"></i>25</a>
                                            </div>

                                        </div>
                                    </div>
                                </li>
                                <li class="comment">
                                    <div class="comment-body">
                                        <div class="comment-author">
                                            <a href="#">
                                                <img src="images/team-13.jpg" alt="">
                                            </a>
                                        </div>
                                        <div class="comment-content">
                                            <cite class="fn sm black bold"><a href="">John Doe</a></cite>
                                            <p>Morbi nec nisi ante. Quisque lacus ligula, iaculis in elit et, interdum
                                                semper quam. Fusce in interdum tortor. Ut sollicitudin lectus dolor eget
                                                imperdiet libero pulvinar sit amet</p>

                                            <div class="comment-meta">
                                                <a href="#">5 days ago</a>
                                                <a href="#">Hide 2 replies</a>
                                                <a href="#"><i class="icon md-arrow-up"></i>13</a>
                                                <a href="#"><i class="icon md-arrow-down"></i>25</a>
                                            </div>

                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <div class="load-more">
                                <a href="">
                                    <i class="icon md-time"></i>
                                    Load more previous update</a>
                            </div>
                        </div>
                        <!-- END / COMMENT -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- END / COURSE TOP -->

<!-- FORM CHECKOUT -->
<div class="form-checkout">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <form>
                    <ul id="bar">
                        <li class="active"><span class="count">1</span>Register</li>
                        <li><span class="count">2</span>Order and payment</li>
                        <li><span class="count">3</span>Finish checkout</li>
                    </ul>
                    <span class="closeForm"><i class="icon md-close-1"></i></span>
                    <div class="form-body">
                        <!-- fieldsets -->
                        <fieldset>
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="form-1">
                                        <h3 class="fs-title text-capitalize">sign in</h3>
                                        <div class="form-email">
                                            <input type="text" placeholder="Email">
                                        </div>
                                        <div class="form-password">
                                            <input type="password" placeholder="Password">
                                        </div>
                                        <div class="form-check">
                                            <input type="checkbox" id="check">
                                            <label for="check">
                                                <i class="icon md-check-2"></i>
                                                Remember me</label>
                                            <a href="#">Forget password?</a>
                                        </div>
                                        <div class="form-submit-1">
                                            <input type="button" value="Sign In and Continue"
                                                   class="next mc-btn btn-style-1">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-2">
                                        <h3 class="fs-title text-capitalize">New Member</h3>
                                        <div class="form-firstname">
                                            <input type="text" placeholder="First name"/>
                                        </div>
                                        <div class="form-lastname">
                                            <input type="text" placeholder="Last name"/>
                                        </div>
                                        <div class="form-datebirth">
                                            <input type="text" placeholder="Date of Birth">
                                        </div>
                                        <div class="form-email">
                                            <input type="text" name="pass" placeholder="Annamolly@outlook.com"
                                                   class="error">
                                            <span class="text-error">this email has been already taken</span>
                                        </div>
                                        <div class="form-password">
                                            <input type="password" name="pass" placeholder="Password" class="valid">
                                        </div>
                                        <div class="form-submit-1">
                                            <input type="button" value="Sign Up and Continue"
                                                   class="next mc-btn btn-style-1">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>

                        <fieldset>
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="form-1">
                                        <div class="mc-item mc-item-2">
                                            <div class="image-heading">
                                                <img src="images/feature/img-1.jpg" alt="">
                                            </div>
                                            <div class="meta-categories"><a href="#">Web design</a></div>
                                            <div class="content-item">
                                                <div class="image-author">
                                                    <img src="images/avatar-1.jpg" alt="">
                                                </div>
                                                <h4><a href="course-intro.html">The Complete Digital Photography Course
                                                    Amazon Top Seller</a></h4>
                                                <div class="name-author">
                                                    By <a href="#">Name of Mr or Mrs</a>
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
                                                    2568
                                                </div>
                                                <div class="comment-info">
                                                    <i class="icon md-comment"></i>
                                                    25
                                                </div>
                                                <div class="price">
                                                    $190
                                                    <span class="price-old">$134</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-2">
                                        <h3 class="fs-title">Choose your payment method</h3>
                                        <ul class="pay">
                                            <li>
                                                <input type="radio" name="pay" id="visa">
                                                <label for="visa">
                                                    <i class="icon-radio"></i>
                                                    Visa / Master Card
                                                </label>
                                            </li>
                                            <li>
                                                <input type="radio" name="pay" id="paypal">
                                                <label for="paypal">
                                                    <i class="icon-radio"></i>
                                                    Paypal
                                                </label>
                                            </li>
                                            <li>
                                                <input type="radio" name="pay" id="cash">
                                                <label for="cash">
                                                    <i class="icon-radio"></i>
                                                    Cash
                                                </label>
                                            </li>
                                        </ul>

                                        <div class="form-cardnumber">
                                            <label for="cardnumber">Card number</label>
                                            <input type="text" id="cardnumber">
                                        </div>

                                        <div class="form-expirydate">
                                            <label for="expirydate">Expiry date</label>
                                            <input type="text" id="expirydate">
                                            <input type="text">
                                        </div>

                                        <div class="form-code">
                                            <label for="code">Code</label>
                                            <input type="text" id="code">
                                        </div>

                                        <div class="clearfix"></div>

                                        <div class="form-couponcode">
                                            <label for="couponcode">Coupon code</label>
                                            <input type="text" id="couponcode">
                                        </div>

                                        <div class="form-total">
                                            <h4>Total Payment</h4>
                                            <span class="price">$89</span>
                                        </div>

                                        <div class="clearfix"></div>

                                        <div class="form-submit-1">
                                            <input type="button" value="Confirm and Finish"
                                                   class="next mc-btn btn-style-1">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>

                        <fieldset>
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="form-1">
                                        <div class="mc-item mc-item-2">
                                            <div class="image-heading">
                                                <img src="images/feature/img-1.jpg" alt="">
                                            </div>
                                            <div class="meta-categories"><a href="#">Web design</a></div>
                                            <div class="content-item">
                                                <div class="image-author">
                                                    <img src="images/avatar-1.jpg" alt="">
                                                </div>
                                                <h4><a href="course-intro.html">The Complete Digital Photography Course
                                                    Amazon Top Seller</a></h4>
                                                <div class="name-author">
                                                    By <a href="#">Name of Mr or Mrs</a>
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
                                                    2568
                                                </div>
                                                <div class="comment-info">
                                                    <i class="icon md-comment"></i>
                                                    25
                                                </div>
                                                <div class="price">
                                                    $190
                                                    <span class="price-old">$134</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-2">
                                        <h3 class="fs-title">Thank You For Your Purchase</h3>
                                        <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy
                                            nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut
                                            wisi enim ad minim veniam, quis nostrud exerci.</p>

                                        <div class="widget widget_share">
                                            <h4>Share</h4>
                                            <div class="share-body">
                                                <a href="#" class="twitter" title="twitter">
                                                    <i class="icon md-twitter"></i>
                                                </a>
                                                <a href="#" class="pinterest" title="pinterest">
                                                    <i class="icon md-pinterest-1"></i>
                                                </a>
                                                <a href="#" class="facebook" title="facebook">
                                                    <i class="icon md-facebook-1"></i>
                                                </a>
                                                <a href="#" class="google-plus" title="google plus">
                                                    <i class="icon md-google-plus"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="form-submit-1">
                                            <input type="submit" value="Start Learning" class="next mc-btn btn-style-1">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- END / FORM CHECKOUT -->

<!-- COURSE CONCERN -->
<section id="course-concern" class="course-concern">
    <div class="container">
        <h3 class="md black">同类课程推荐</h3>
        <div class="row">



            <c:forEach items="${courseList}" var="course">
                <div class="col-xs-6 col-sm-4 col-md-3">
                    <!-- MC ITEM -->
                    <div class="mc-item mc-item-2">
                        <div class="image-heading">
                            <c:choose>
                                <c:when test="${!empty course.logo&&course.logo!=''}">
                                    <img src="<%=staticImageServer%>${course.logo}"  alt="${course.name}">
                                </c:when>
                                <c:otherwise>
                                    <img src="<%=imagesPath%>/static/edu/images/default/default_goods.jpg" alt="${course.name}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="meta-categories"><a href="${ctx}/front/couinfo/${course.id}">${course.name}</a></div>
                        <div class="content-item">
                            <div class="image-author">
                                <img src="images/avatar-1.jpg" alt="">
                            </div>
                            <h4><a href="${ctx}/front/couinfo/${course.id}">${course.title}</a>
                            </h4>
                            <div class="name-author">
                                <c:forEach items="${course.teacherList}" var="teacher">
                                    <a class="c-666" href="${ctx }/front/teacher/${teacher.id}">${teacher.name}</a>&nbsp;
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
        </div>
    </div>
</section>
<!-- END / COURSE CONCERN -->
