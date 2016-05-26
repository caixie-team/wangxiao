<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!-- HEADER -->
<header id="header" class="header">
    <div class="container">

        <!-- LOGO -->
        <!-- <div class="logo"><a href="index.html"><img src="images/logo.png" alt=""></a></div> -->
        <div class="logo"><a href="${ctx }" title="${websitemap.web.company}"><img
                src="<%=staticImageServer %>${logomap.logo.url}" alt=""/></a></div>

        <!-- END / LOGO -->

        <!-- NAVIGATION -->
        <nav class="navigation">

            <div class="open-menu">
                <span class="item item-1"></span>
                <span class="item item-2"></span>
                <span class="item item-3"></span>
            </div>

            <!-- MENU -->
            <ul id="menu" class="menu">
                <li v-for="nav in navs.INDEX">
                    <a href="{{nav.url}}" title="{{nav.name}}">{{nav.name}}</a>
                </li>
                <%--<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">--%>
                    <%--<li><a href="${indexNavigate.url}" title="${indexNavigate.name}"--%>
                           <%--<c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a>--%>
                    <%--</li>--%>
                <%--</c:forEach>--%>
            </ul>
            <!-- END / MENU -->

            <!-- SEARCH BOX -->
            <div class="search-box">
                <i class="icon md-search"></i>
                <div class="search-inner">
                    <form>
                        <input type="text" placeholder="key words">
                    </form>
                </div>
            </div>
            <!-- END / SEARCH BOX -->

            <!-- LIST ACCOUNT INFO -->
            <ul class="list-account-info">
                <li class="list-item">
                    <span class="itemnew">登录</span>
                </li>
                <!-- MESSAGE INFO -->
                <li class="list-item messages">
                    <div class="message-info item-click">
                        <i class="icon md-email"></i>
                        <span class="itemnew"></span>
                    </div>
                    <div class="toggle-message toggle-list">
                        <div class="list-profile-title">
                            <h4>Inbox message</h4>
                            <span class="count-value">3</span>
                            <a href="#" class="new-message"><i class="icon md-pencil"></i></a>
                        </div>
                        <ul class="list-message">

                            <!-- LIST ITEM -->
                            <li class="ac-new">
                                <a href="#">
                                    <div class="image">
                                        <img src="images/team-13.jpg" alt="">
                                    </div>
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Megacourse</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>Welcome message</p>
                                        <div class="time">
                                            <span>12:53</span>
                                        </div>
                                        <div class="indicator">
                                            <i class="icon md-paperclip"></i>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li class="ac-new">
                                <a href="#">
                                    <div class="image">
                                        <img src="images/team-13.jpg" alt="">
                                    </div>
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Name of sender</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>Message title</p>
                                        <div class="time">
                                            <span>5 days ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li class="ac-new">
                                <a href="#">
                                    <div class="image">
                                        <img src="images/team-13.jpg" alt="">
                                    </div>
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Sasha Grey</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>Maecenas sodales, nisl eget dign...</p>
                                        <div class="time">
                                            <span>5 days ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li>
                                <a href="#">
                                    <div class="image">
                                        <img src="images/team-13.jpg" alt="">
                                    </div>
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Amanda Gleam</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>Message title</p>
                                        <div class="time">
                                            <span>5 days ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li>
                                <a href="#">
                                    <div class="image">
                                        <img src="images/team-13.jpg" alt="">
                                    </div>
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Amanda Gleam</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>Message title</p>
                                        <div class="time">
                                            <span>5 days ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li>
                                <a href="#">
                                    <div class="image">
                                        <img src="images/team-13.jpg" alt="">
                                    </div>
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Amanda Gleam</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>Message title</p>
                                        <div class="time">
                                            <span>5 days ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                        </ul>
                        <div class="viewall">
                            <a href="#">view all 80 messages</a>
                        </div>
                    </div>
                </li>
                <!-- END / MESSAGE INFO -->

                <!-- NOTIFICATION -->
                <li class="list-item notification">
                    <div class="notification-info item-click">
                        <i class="icon md-bell"></i>
                        <span class="itemnew"></span>
                    </div>
                    <div class="toggle-notification toggle-list">
                        <div class="list-profile-title">
                            <h4>Notification</h4>
                            <span class="count-value">2</span>
                        </div>

                        <ul class="list-notification">

                            <!-- LIST ITEM -->
                            <li class="ac-new">
                                <a href="#">
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Megacourse</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>attend Salary for newbie course</p>
                                        <div class="image">
                                            <img src="images/feature/img-1.jpg" alt="">
                                        </div>
                                        <div class="time">
                                            <span>5 minutes ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li class="ac-new">
                                <a href="#">
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Megacourse</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>attend Salary for newbie course</p>
                                        <div class="image">
                                            <img src="images/feature/img-1.jpg" alt="">
                                        </div>
                                        <div class="time">
                                            <span>5 minutes ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li>
                                <a href="#">
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Megacourse</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>attend Salary for newbie course</p>
                                        <div class="image">
                                            <img src="images/feature/img-1.jpg" alt="">
                                        </div>
                                        <div class="time">
                                            <span>5 minutes ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li>
                                <a href="#">
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Megacourse</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>attend Salary for newbie course</p>
                                        <div class="image">
                                            <img src="images/feature/img-1.jpg" alt="">
                                        </div>
                                        <div class="time">
                                            <span>5 minutes ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li>
                                <a href="#">
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Megacourse</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>attend Salary for newbie course</p>
                                        <div class="image">
                                            <img src="images/feature/img-1.jpg" alt="">
                                        </div>
                                        <div class="time">
                                            <span>5 minutes ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->

                            <!-- LIST ITEM -->
                            <li>
                                <a href="#">
                                    <div class="list-body">
                                        <div class="author">
                                            <span>Megacourse</span>
                                            <div class="div-x"></div>
                                        </div>
                                        <p>attend Salary for newbie course</p>
                                        <div class="image">
                                            <img src="images/feature/img-1.jpg" alt="">
                                        </div>
                                        <div class="time">
                                            <span>5 minutes ago</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <!-- END / LIST ITEM -->


                        </ul>
                    </div>
                </li>
                <!-- END / NOTIFICATION -->

                <li class="list-item account">
                    <div class="account-info item-click">
                        <img src="images/team-13.jpg" alt="">
                    </div>
                    <div class="toggle-account toggle-list">
                        <ul class="list-account">
                            <li><a href="setting.html"><i class="icon md-config"></i>Setting</a></li>
                            <li><a href="login.html"><i class="icon md-arrow-right"></i>Sign Out</a></li>
                        </ul>
                    </div>
                </li>


            </ul>
            <!-- END / LIST ACCOUNT INFO -->

        </nav>
        <!-- END / NAVIGATION -->

    </div>
</header>
<!-- END / HEADER -->