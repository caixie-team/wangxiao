<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="${ct}/static/themes/default/assets/examples/css/pages/login-v3.css">

</head>
<body class="page-login-v3">

<div class="page-login-v3 page animsition vertical-align text-center" data-animsition-in="fade-in"
     data-animsition-out="fade-out">>
    <div class="page-content vertical-align-middle">
        <div class="panel panel-bordered panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">用户登录</h3>
            </div>
            <div class="panel-body">
                <%--<div class="brand">--%>
                    <%--<img class="brand-img" src="${ctx}/static/themes/default/assets/images/logo-blue.png" alt="...">--%>
                    <%--<h2 class="brand-text font-size-18">登录</h2>--%>
                <%--</div>--%>
                <form method="post" action="#">
                    <div class="form-group form-material floating">
                        <input id="userEmail" type="email" class="form-control" name="email" placeholder="邮箱/手机号" onkeyup="enterSubmit(event,'pageLogin(2)')"/>
                        <%--<label class="floating-label">邮箱/手机号</label>--%>
                    </div>
                    <div class="form-group form-material floating">
                        <input id="userPassword" type="password" class="form-control" name="password"
                               placeholder="密码" onkeyup="enterSubmit(event,'pageLogin(2)')"/>
                        <%--<label class="floating-label">密码</label>--%>
                    </div>
                    <div class="form-group clearfix">
                        <div class="checkbox-custom checkbox-inline checkbox-primary checkbox-lg pull-left">

                            <input type="checkbox" id="autoThirty" name="autoThirty" checked="checked">
                            <label for="autoThirty">自动登录</label>
                        </div>
                        <a class="pull-right" href="${ctx}/front/forget_passwd">忘记密码?</a>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block btn-lg margin-top-40">登录</button>
                </form>
                <p>没有账号? <a href="${ctx}/register">去注册</a></p>
            </div>
            <%--<div class="alert alert-danger alert-dismissible" role="alert">--%>
                <%--<button type="button" class="close" data-dismiss="alert" aria-label="Close">--%>
                    <%--<span aria-hidden="true">×</span>--%>
                    <%--<span class="sr-only">Close</span>--%>
                <%--</button>--%>
                <%--Lorem ipsum dolor sit amet, <a class="alert-link" href="javascript:void(0)">consectetur adipisicing elit</a>.--%>
            <%--</div>--%>
            <ul class="nav nav-tabs nav-tabs-bottom nav-tabs-line dropup" data-plugin="nav-tabs" role="tablist">
                <%--<li class="qq-kj"><a href="javascript:oauthLogin('QQ')" title="QQ登录"></a></li>--%>
                <%--<li class="wx-kj"><a href="javascript:oauthLogin('WEIXIN')" title="微信登录"></a></li>--%>
                <%--<li class="wb-kj"><a href="javascript:oauthLogin('SINA')" title="微博登录"></a></li>--%>
                <li class="" role="presentation"><a data-toggle="tab" href="#exampleBottomHome" aria-controls="exampleBottomHome" role="tab" aria-expanded="false"><i class="icon wb-plugin" aria-hidden="true"></i>Home</a></li>
                <li role="presentation" class=""><a data-toggle="tab" href="#exampleBottomComponents" aria-controls="exampleBottomComponents" role="tab" aria-expanded="false"><i class="icon wb-user" aria-hidden="true"></i>Components</a></li>
                <li role="presentation" class="active"><a data-toggle="tab" href="#exampleBottomCss" aria-controls="exampleBottomCss" role="tab" aria-expanded="true"><i class="icon wb-tag" aria-hidden="true"></i>CSS</a></li>

            </ul>
            <%--<div class="panel-footer">--%>
                <%--<p>快捷登录</p>--%>
                <%--<div class="social">--%>
                    <%--<a class="btn btn-icon btn-pure" href="javascript:void(0)">--%>
                        <%--<i class="icon bd-twitter" aria-hidden="true"></i>--%>
                    <%--</a>--%>
                    <%--<a class="btn btn-icon btn-pure" href="javascript:void(0)">--%>
                        <%--<i class="icon bd-facebook" aria-hidden="true"></i>--%>
                    <%--</a>--%>
                    <%--<a class="btn btn-icon btn-pure" href="javascript:void(0)">--%>
                        <%--<i class="icon bd-google-plus" aria-hidden="true"></i>--%>
                    <%--</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>


    </div>


</div>
<%--<div class="page animsition vertical-align text-center" data-animsition-in="fade-in"--%>
<%--data-animsition-out="fade-out">>--%>
<%--</div>--%>

<script type="text/javascript" src="${ctximg}/static/edu/js/login/login.js"></script>
<script src="${ctx}/static/global/js/components/material.js"></script>

</body>
</html>
