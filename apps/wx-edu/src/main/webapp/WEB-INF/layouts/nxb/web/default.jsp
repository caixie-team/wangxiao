<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<%@ include file="/base.jsp" %>
<compress:html compressJavaScript="false">
    <!DOCTYPE HTML>

    <!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]> <html class="lt-ie9 lt-ie8"> <![endif]-->
    <!--[if IE 8]> <html class="lt-ie9"> <![endif]-->
    <!--[if gt IE 8]><!-->
    <html> <!--<![endif]-->

    <html class="no-js css-menubar" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><sitemesh:title></sitemesh:title>-${websitemap.web.company}-${websitemap.web.title}</title>
        <meta name="author" content="${websitemap.web.author}"/>
        <meta name="keywords" content="${websitemap.web.keywords}"/>
        <meta name="description" content="${websitemap.web.description}"/>
        <link rel="apple-touch-icon" href="${ctx}/static/themes/default/assets/images/apple-touch-icon.png">
        <link rel="shortcut icon" href="${ctx}/favicon.ico" type="image/x-icon">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="${ctx}/static/global/css/bootstrap.min.css">
        <link rel="stylesheet" href="${ctx}/static/global/css/bootstrap-extend.min.css">
        <link rel="stylesheet" href="${ctx}/static/themes/default/assets/css/site.min.css">


        <!-- Plugins -->
        <link rel="stylesheet" href="${ctx}/static/global/vendor/animsition/animsition.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/asscrollable/asScrollable.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/switchery/switchery.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/intro-js/introjs.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/slidepanel/slidePanel.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/flag-icon-css/flag-icon.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/chartist-js/chartist.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/chartist-plugin-tooltip/chartist-plugin-tooltip.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/aspieprogress/asPieProgress.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/jquery-selective/jquery-selective.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/bootstrap-datepicker/bootstrap-datepicker.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/owl-carousel/owl.carousel.css">
        <link rel="stylesheet" href="${ctx}/static/global/vendor/slick-carousel/slick.css">
        <link rel="stylesheet" href="${ctx}/static/themes/default/assets/examples/css/uikit/carousel.css">

        <link rel="stylesheet" href="${ctx}/static/themes/default/assets/examples/css/dashboard/team.css">
        <!-- Fonts -->
        <link rel="stylesheet" href="${ctx}/static/global/fonts/web-icons/web-icons.min.css">
        <link rel="stylesheet" href="${ctx}/static/global/fonts/brand-icons/brand-icons.min.css">
        <%--<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Roboto:300,400,500,300italic'>--%>

        <sitemesh:head></sitemesh:head>

        <!--[if lt IE 9]>
        <script src="${ctx}/static/global/vendor/html5shiv/html5shiv.min.js"></script>
        <![endif]-->
        <!--[if lt IE 10]>
        <script src="${ctx}/static/global/vendor/media-match/media.match.min.js"></script>
        <script src="${ctx}/static/global/vendor/respond/respond.min.js"></script>
        <![endif]-->
        <!-- Scripts -->
        <script src="${ctx}/static/global/vendor/modernizr/modernizr.js"></script>
        <script src="${ctx}/static/global/vendor/breakpoints/breakpoints.js"></script>
        <script>
            Breakpoints();
        </script>


            <%--<link rel="stylesheet" type="text/css" href="${ctximg}/static/nxb/web/css/reset.css">--%>
            <%--<link rel="stylesheet" type="text/css" href="${ctximg}/static/nxb/web/css/global.css">--%>
            <%--<link rel="stylesheet" type="text/css" href="${ctximg}/static/nxb/web/css/web-nxb.css">--%>
            <%--<link href="${ctximg}/static/nxb/web/css/mw_320_768.css" rel="stylesheet" type="text/css"--%>
            <%--media="screen and (min-width: 320px) and (max-width: 768px)">--%>
            <%--<script src="${ctximg}/static/common/jquery-1.11.1.min.js" type="text/javascript"></script>--%>
        <!--[if lt IE 9]><!---->
            <%--<script src="${ctximg}/static/nxb/web/js/html5.js"></script><![endif]-->--%>
    </head>
    <body class="site-navbar-small dashboard">
    <!--[if lt IE 8]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a
            href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <nav class="site-navbar navbar navbar-default navbar-fixed-top navbar-mega"
         role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle hamburger hamburger-close navbar-toggle-left hided"
                    data-toggle="menubar">
                <span class="sr-only">Toggle navigation</span>
                <span class="hamburger-bar"></span>
            </button>
            <button type="button" class="navbar-toggle collapsed" data-target="#site-navbar-collapse"
                    data-toggle="collapse">
                <i class="icon wb-more-horizontal" aria-hidden="true"></i>
            </button>
            <a class="navbar-brand navbar-brand-center" href="/index.html">
                <img class="navbar-brand-logo navbar-brand-logo-normal"
                     src="<%=staticImageServer%>/${logomap.logo.url}"
                     title="Remark">
                <img class="navbar-brand-logo navbar-brand-logo-special"
                    <%--src="${ctx}/static/themes/default/assets/images/logo-blue.png"--%>
                     src="<%=staticImageServer%>/${logomap.logo.url}"
                     title="Remark">
                <span class="navbar-brand-text hidden-xs"> Remark</span>
            </a>
            <button type="button" class="navbar-toggle collapsed" data-target="#site-navbar-search"
                    data-toggle="collapse">
                <span class="sr-only">Toggle Search</span>
                <i class="icon wb-search" aria-hidden="true"></i>
            </button>
        </div>
        <div class="navbar-container container-fluid">
            <!-- Navbar Collapse -->
            <div class="collapse navbar-collapse navbar-collapse-toolbar" id="site-navbar-collapse">
                <!-- Navbar Toolbar -->
                <ul class="nav navbar-toolbar">
                    <li class="hidden-float" id="toggleMenubar">
                        <a data-toggle="menubar" href="#" role="button">
                            <i class="icon hamburger hamburger-arrow-left">
                                <span class="sr-only">Toggle menubar</span>
                                <span class="hamburger-bar"></span>
                            </i>
                        </a>
                    </li>
                        <%--<li class="hidden-xs" id="toggleFullscreen">--%>
                        <%--<a class="icon icon-fullscreen" data-toggle="fullscreen" href="#" role="button">--%>
                        <%--<span class="sr-only">Toggle fullscreen</span>--%>
                        <%--</a>--%>
                        <%--</li>--%>
                    <li class="hidden-float">
                        <a class="icon wb-search" data-toggle="collapse" href="#" data-target="#site-navbar-search"
                           role="button">
                            <span class="sr-only">Toggle Search</span>
                        </a>
                    </li>

                    <c:set var="contextPath" value="<%=request.getRequestURI()%>"/>
                    <c:forEach items="${navigatemap.INDEX}" var="indexNavigate" begin="0" end="5">

                        <li
                                <c:if test="${indexNavigate.url==contextPath}">class="active"</c:if> >
                            <a href="${indexNavigate.url}" title="${indexNavigate.name}"
                               <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a>
                        </li>
                    </c:forEach>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false"
                           role="button">其它
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <c:forEach items="${navigatemap.INDEX}" var="indexNavigate" begin="6">
                                <li role="presentation">
                                    <a href="${indexNavigate.url}" title="${indexNavigate.name}" role="menuitem"
                                       <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>

                </ul>
                <!-- End Navbar Toolbar -->
                <!-- Navbar Toolbar Right -->

                <ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
                    <c:if test="${ empty loginUser }">
                        <li><a href="${ctx}/login" class="navbar-link">登录</a></li>
                        <li><a href="${ctx}/register" class="navbar-link">注册</a></li>
                    </c:if>

                    <c:if test="${not empty loginUser }">
                        <li class="dropdown">
                            <a class="navbar-avatar dropdown-toggle" data-toggle="dropdown" href="/uc/home"
                               aria-expanded="false"
                               data-animation="scale-up" role="button">
                            <span class="avatar avatar-online">
                                <c:if test="${empty loginUser.avatar}">
                                    <img src="${ctx}/static/global/portraits/5.jpg" alt="...">
                                </c:if>
                                <c:if test="${not empty loginUser.avatar}">
                                    <c:if test="${fn:contains(loginUser.avatar,'http:')}">
                                        <img src="${loginUser.avatar}" width="30" height="30">
                                    </c:if>
                                    <c:if test="${fn:contains(loginUser.avatar,'http:')==false}">
                                        <img src="${staticUrl}${loginUser.avatar}" width="30" height="30">
                                    </c:if>
                                </c:if>
                                <i></i>
                            </span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li role="presentation">
                                    <a href="${ctx}/uc/home" role="menuitem"><i class="icon wb-user"
                                                                                aria-hidden="true"></i> 个人主页</a>
                                </li>
                                <li role="presentation">
                                    <a href="${ctx}/uc/planRecordList" role="menuitem"><i class="icon wb-payment"
                                                                                          aria-hidden="true"></i>
                                        学习计划</a>
                                </li>
                                <li role="presentation">
                                    <a href="${ctx}/uc/course" role="menuitem"><i class="icon wb-settings"
                                                                                  aria-hidden="true"></i> 我的课程</a>
                                </li>
                                <li role="presentation">
                                    <a href="${ctx}/uc/letter" role="menuitem"><i class="icon wb-settings"
                                                                                  aria-hidden="true"></i> 我的消息</a>
                                </li>
                                <li class="divider" role="presentation"></li>
                                <li role="presentation">
                                    <a href="javascript:void(0)" onclick="exit()" role="menuitem"><i
                                            class="icon wb-power"
                                            aria-hidden="true"></i> 登出</a>
                                </li>
                            </ul>
                        </li>

                    </c:if>
                </ul>
                <!-- End Navbar Toolbar Right -->
            </div>
            <!-- End Navbar Collapse -->
            <!-- Site Navbar Seach -->
            <div class="collapse navbar-search-overlap" id="site-navbar-search">
                <form role="search">
                    <div class="form-group">
                        <div class="input-search">


                            <form action="${ctx}/front/showCourseList" method="post" id="searchCourse">
                                <i class="input-search-icon wb-search" aria-hidden="true"></i>
                                <button type="button" class="input-search-close icon wb-close"
                                        data-target="#site-navbar-search"
                                        data-toggle="collapse" aria-label="Close"></button>

                                <input id="searchCourseName" type="text" class="form-control" name="queryCourse.name"
                                       placeholder="输入想要学习的课程">

                                    <%--<label class="h-r-s-box"><input type="text" id="searchCourseName" placeholder="输入你想学的课程" name="queryCourse.name" value="">--%>
                                    <%--<button type="submit" class="s-btn">--%>
                                    <%--<em class="icon18">&nbsp;</em>--%>
                                    <%--</button></label>--%>
                            </form>
                        </div>
                    </div>
                </form>
            </div>
            <!-- End Site Navbar Seach -->
        </div>
    </nav>


    <div class="page animsition">


        <div class="page-content padding-30 container-fluid">

                <%--<jsp:include page="/WEB-INF/layouts/nxb/header.jsp"/>--%>

            <%--<div class="panel-body container-fluid">--%>
                <%--<div class="row">--%>

                    <%--<div class="col-sm-12">--%>
                        <%--<!-- Example Captions -->--%>
                        <%--<div class="example-wrap">--%>
                            <%--<div class="example">--%>
                                <%--<div class="carousel slide" id="exampleCarouselCaptions" data-ride="carousel">--%>
                                    <%--<ol class="carousel-indicators carousel-indicators-fillin">--%>
                                        <%--<li class="active" data-slide-to="0"--%>
                                            <%--data-target="#exampleCarouselCaptions"></li>--%>
                                        <%--<li class="" data-slide-to="1" data-target="#exampleCarouselCaptions"></li>--%>
                                        <%--<li class="" data-slide-to="2" data-target="#exampleCarouselCaptions"></li>--%>
                                    <%--</ol>--%>
                                    <%--<div class="carousel-inner" role="listbox">--%>
                                        <%--<div class="item active">--%>
                                            <%--<img class="width-full"--%>
                                                 <%--src="${ctx}/static/global/photos/object-4-960x640.jpg"--%>
                                                 <%--alt="...">--%>
                                            <%--<div class="carousel-caption">--%>
                                                <%--<h3>First Slide Label</h3>--%>
                                                <%--<p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="item">--%>
                                            <%--<img class="width-full"--%>
                                                 <%--src="${ctx}/static/global/photos/object-5-960x640.jpg"--%>
                                                 <%--alt="...">--%>
                                            <%--<div class="carousel-caption">--%>
                                                <%--<h3>Second Slide Label</h3>--%>
                                                <%--<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="item">--%>
                                            <%--<img class="width-full"--%>
                                                 <%--src="${ctx}/static/global/photos/object-6-960x640.jpg"--%>
                                                 <%--alt="...">--%>
                                            <%--<div class="carousel-caption">--%>
                                                <%--<h3>Third Slide Label</h3>--%>
                                                <%--<p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<a class="left carousel-control" href="#exampleCarouselCaptions" role="button"--%>
                                       <%--data-slide="prev">--%>
                                        <%--<span class="icon wb-chevron-left" aria-hidden="true"></span>--%>
                                        <%--<span class="sr-only">Previous</span>--%>
                                    <%--</a>--%>
                                    <%--<a class="right carousel-control" href="#exampleCarouselCaptions" role="button"--%>
                                       <%--data-slide="next">--%>
                                        <%--<span class="icon wb-chevron-right" aria-hidden="true"></span>--%>
                                        <%--<span class="sr-only">Next</span>--%>
                                    <%--</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<!-- End Example Captions -->--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
                <%--<div class="in-wrap">--%>
                <%--<!-- 公共头引入 -->--%>
                <%--<jsp:include page="/WEB-INF/layouts/nxb/header.jsp"/>--%>
            <!-- 公共头引入 -->
            <sitemesh:body></sitemesh:body>
            <!-- 公共底引入 -->
                <%--<jsp:include page="/WEB-INF/layouts/nxb/footer.jsp"/>--%>
            <!-- 公共底引入 -->
                <%--</div>--%>
        </div>
    </div>
    <%--<jsp:include page="/WEB-INF/layouts/nxb/right_help.jsp"/>--%>
        <%--<script src="${ctximg}/static/nxb/web/js/common.js" type="text/javascript"></script>--%>
        <%--<script src="${ctximg}/static/nxb/web/js/swiper-2.1.0.js" type="text/javascript"></script>--%>
        <%--<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>--%>
        <%--<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>--%>
    <script type="text/javascript">
        var baselocation = "${ctx}";
        var baselocationsns = "${ctxsns}";
        var baselocationexam = "${ctxexam}";
        var imagesPath = "${ctximg}";
        var usercookiekey = "<%=usercookiekey%>";
        var mydomain = "<%=mydomain%>";
        var keImageUploadUrl = "<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
        var uploadSimpleUrl = "<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
        var uploadSwfUrl = "<%=uploadSwfUrl%>";//uplopad的js上传使用的路径
        var staticImageServer = "<%=staticImageServer%>";//上传后返回路径
        var loginkeyword = '${keywordmap.keyword.verifyLogin}';
        var upUserId = "<%=CommonConstants.UP_USER_ID%>";
        var imageUrl = "${imageUrl}";
        var staticUrl = "${staticUrl}";
        var userId = ${userId};
        var loginUser = "${loginUser}";
        $(function () {
            //mbFun(); //移动端方法调取
            goTop();
            gnFun();//底部导航换色；
            //dialogFun("a","aadsf",1);
        })

    </script>
    <!-- 统计代码 -->
        <%--${tongjiemap.censusCode.censusCodeString}--%>


    <!-- Core  -->
    <script src="${ctx}/static/global/vendor/jquery/jquery.js"></script>
    <script src="${ctx}/static/global/vendor/bootstrap/bootstrap.js"></script>
    <script src="${ctx}/static/global/vendor/animsition/animsition.js"></script>
    <script src="${ctx}/static/global/vendor/asscroll/jquery-asScroll.js"></script>
    <script src="${ctx}/static/global/vendor/mousewheel/jquery.mousewheel.js"></script>
    <script src="${ctx}/static/global/vendor/asscrollable/jquery.asScrollable.all.js"></script>
    <script src="${ctx}/static/global/vendor/ashoverscroll/jquery-asHoverScroll.js"></script>
    <!-- Plugins -->
    <script src="${ctx}/static/global/vendor/switchery/switchery.min.js"></script>
    <script src="${ctx}/static/global/vendor/intro-js/intro.js"></script>
    <script src="${ctx}/static/global/vendor/screenfull/screenfull.js"></script>
    <script src="${ctx}/static/global/vendor/slidepanel/jquery-slidePanel.js"></script>
    <script src="${ctx}/static/global/vendor/chartist-js/chartist.js"></script>
    <script src="${ctx}/static/global/vendor/chartist-plugin-tooltip/chartist-plugin-tooltip.min.js"></script>
    <script src="${ctx}/static/global/vendor/aspieprogress/jquery-asPieProgress.js"></script>
    <script src="${ctx}/static/global/vendor/matchheight/jquery.matchHeight-min.js"></script>
    <script src="${ctx}/static/global/vendor/jquery-selective/jquery-selective.min.js"></script>
    <script src="${ctx}/static/global/vendor/bootstrap-datepicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/static/global/vendor/owl-carousel/owl.carousel.js"></script>
    <script src="${ctx}/static/global/vendor/slick-carousel/slick.js"></script>
    <!-- Scripts -->
    <script src="${ctx}/static/global/js/core.js"></script>
    <script src="${ctx}/static/themes/default/assets/js/site.js"></script>
    <script src="${ctx}/static/themes/default/assets/js/sections/menu.js"></script>
    <script src="${ctx}/static/themes/default/assets/js/sections/menubar.js"></script>
    <script src="${ctx}/static/themes/default/assets/js/sections/sidebar.js"></script>
    <script src="${ctx}/static/global/js/configs/config-colors.js"></script>
    <script src="${ctx}/static/themes/default/assets/js/configs/config-tour.js"></script>
    <script src="${ctx}/static/global/js/components/asscrollable.js"></script>
    <script src="${ctx}/static/global/js/components/animsition.js"></script>
    <script src="${ctx}/static/global/js/components/slidepanel.js"></script>
    <script src="${ctx}/static/global/js/components/switchery.js"></script>
    <script src="${ctx}/static/global/js/components/matchheight.js"></script>
    <script src="${ctx}/static/global/js/components/aspieprogress.js"></script>
    <script src="${ctx}/static/global/js/components/bootstrap-datepicker.js"></script>
    <script src="${ctx}/static/themes/default/assets/examples/js/dashboard/team.js"></script>
    <script src="${ctx}/static/global/js/components/owl-carousel.js"></script>
    <script src="${ctx}/static/themes/default/assets/examples/js/uikit/carousel.js"></script>
    <script type="text/javascript" src="${ctximg}/static/common/echarts/echarts.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=imagesPath%>/static/edu/js/web/index.js"></script>
    </body>
    </html>
</compress:html>