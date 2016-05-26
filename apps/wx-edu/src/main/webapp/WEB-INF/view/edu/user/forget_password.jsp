<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
<div class="bg-fa of">
    <section class="container">
        <!-- login register box begin -->
        <div class="lr-box">
            <article class="lr-e-box lr-e-box-mb">
                <section class="lr-e-wrap">
                    <aside class="lr-bird">
                        <span class="lr-b-icon"></span>
                    </aside>
                    <header class="comm-title">
                        <h2 class="tac clearfix">
                            <span class="c-333">找回密码</span>
                        </h2>
                    </header>
                    <section class="fg-step-1">
                        <div>
                            <ul class="fg-fun-ul">
                                <li class="fg-phone">
                                    <a href="javascript:void(0)" title="手机找回">
                                        <big></big>
                                        <p class="hLh30 mt10"><span class="fsize16 c-666">手机找回</span></p>
                                    </a>
                                </li>
                                <li class="fg-email">
                                    <a href="javascript:void(0)" title="邮箱找回">
                                        <big></big>
                                        <p class="hLh30 mt10"><span class="fsize16 c-666">邮箱找回</span></p>
                                    </a>
                                </li>
                            </ul>
                            <div class="clear"></div>
                        </div>
                        <div class="mt50"><a href="javascript: void(0)" class="bm-lr-btn fg-step-btn-1" title="下一步">下一步</a></div>
                    </section><!-- /第一步 -->
                    <section class="fg-step-2 undis">
                        <section class="lr-ul-wrap">
                            <ul class="bm-lr-ul">
                                <li id="div_mobile" class="undis" id="div_mobile">
                                    <div class="bm-lr-jy-box">
                                        <section class="hLh30 ml10 mr10 pr">
                                            <big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
                                            <div class="DT-arrow"><em>◆</em><span>◆</span></div>
                                            <em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="error_mobile">请输入正确的手机号！</span>
                                        </section>
                                    </div>
                                    <label><em class="icon18 ml5 user-icon"></em><input type="text" placeholder="请输入手机号码" id="mobile" maxlength="11" name=""></label>
                                </li>
                                <li id="div_email" class="undis" id="div_email">
                                    <div class="bm-lr-jy-box">
                                        <section class="hLh30 ml10 mr10 pr">
                                            <big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
                                            <div class="DT-arrow"><em>◆</em><span>◆</span></div>
                                            <em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="error_email">请输入正确的邮箱！</span>
                                        </section>
                                    </div>
                                    <label><em class="icon18 ml5 user-icon"></em><input type="text" placeholder="请输入邮箱" id="email" name=""></label>
                                </li>
                                <li class="lr-yzm-li" id="div_checkCode">
                                    <div class="bm-lr-jy-box undis">
                                        <section class="hLh30 ml10 mr10 pr">
                                            <big class="bm-close" title="关闭" onclick="$(this).parent().parent('.bm-lr-jy-box').hide();">x</big>
                                            <div class="DT-arrow"><em>◆</em><span>◆</span></div>
                                            <em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="error_checkCode">请输入动态验证码！</span>
                                        </section>
                                    </div>
                                    <label>
                                        <aside class="lr-yzm-btn fr">
                                            <a title="获取验证码" href="javascript:void(0)" onclick="getCheckCode(this)">获取验证码</a>
                                        </aside>
                                        <em class="icon18 ml5 pwd-icon"></em><input type="text" id="checkCode" name="" placeholder="动态验证码">
                                    </label>
                                </li>
                            </ul>
                            <div class="mt30"><a title="下一步" class="bm-lr-btn fg-step-btn-2" href="javascript: void(0)">下一步</a></div>
                        </section>
                    </section><!-- /第二步 -->
                    <section class="fg-step-3 undis">
                        <section class="lr-ul-wrap">
                            <ul class="bm-lr-ul">
                                <li id="div_password">
                                    <div class="bm-lr-jy-box undis">
                                        <section class="hLh30 ml10 mr10 pr">
                                            <big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
                                            <div class="DT-arrow"><em>◆</em><span>◆</span></div>
                                            <em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="error_password">请输入正确的密码！</span>
                                        </section>
                                    </div>
                                    <label><em class="icon18 ml5 pwd-icon"></em><input type="password" id="password" maxlength="20" placeholder="输入新密码" name=""></label>
                                </li>
                                <li id="div_confirm_password">
                                    <div class="bm-lr-jy-box undis">
                                        <section class="hLh30 ml10 mr10 pr">
                                            <big onclick="$(this).parent().parent('.bm-lr-jy-box').hide();" title="关闭" class="bm-close">x</big>
                                            <div class="DT-arrow"><em>◆</em><span>◆</span></div>
                                            <em class="icon18 login-err-icon"></em><span class="fsize12 vam ml5" id="error_confirm_password">请输入正确的密码！</span>
                                        </section>
                                    </div>
                                    <label><em class="icon18 ml5 pwd-icon"></em><input type="password" id="confirm_password" maxlength="20" placeholder="再输一次" name=""></label>
                                </li>
                            </ul>
                        </section>
                        <div class="mt30"><a title="提 交" class="bm-lr-btn fg-step-btn-3" href="javascript: void(0)">提 交</a></div>
                    </section><!-- /第三步 -->
                </section>
            </article>
        </div>
        <!-- login register box end -->
    </section>
</div>
<script type="text/javascript">
    var isValidateMobile=false;
    var isValidateEmail=false;
    var isValidatePassword=false;
    var isValidateConfirmPassword=false;
    $(function() {
        $("input").val("");
        $(".fg-fun-ul li a").click(function(){
            var _this = $(this);
            if(!_this.parent().hasClass("current")){
                _this.parent().addClass("current");
                _this.parent().siblings().removeClass("current");
            }
        });

        // 验证手机号
        $("#mobile").on("blur",function(){
            isValidateMobile=false;
            $("#div_mobile").children().eq(0).show();
            var mobile=$(this).val();
            if(!isMobile(mobile)){//验证手机是否正确
                $("#error_mobile").html('请输入正确的手机号！');
                return;
            }
            $.ajax({
                url:'${ctx}/checkRegMoblie',
                data:{'mobile':mobile},
                dataType: 'json',
                success:function(result){
                    if(!result){
                        isValidateMobile=true;
                        $("#div_mobile").children().eq(0).hide();
                    }else{
                        $("#error_mobile").html('手机号码不存在！');
                    }
                },
                error:function(){
                    $("#error_mobile").html('系统异常,请稍后重试！');
                }
            });
        });

        // 验证邮箱
        $("#email").on("blur",function(){
            isValidateEmail=false;
            $("#div_email").children().eq(0).show();
            var email=$(this).val();
            if(!isEmail(email)){//验证邮箱是否正确
                $("#error_email").html('请输入正确的邮箱！');
                return;
            }
            $.ajax({
                url:'${ctx}/checkEmail',
                data:{"email":email},
                dataType: 'json',
                success:function(result){
                    if(!result){
                        isValidateEmail=true;
                        $("#div_email").children().eq(0).hide();
                    }else{
                        $("#error_email").html('邮箱不存在！');
                    }
                },
                error:function(){
                    $("#error_email").html('系统异常,请稍后重试！');
                }
            });
        });

        // 验证密码
        $("#password").on("blur",function(){
            isValidatePassword=false;
            $("#div_password").children().eq(0).show();
            var password=$(this).val();
            if(isEmpty(password)){
                $("#error_password").html('请输入密码！');
                return;
            }
            if(password.length<6||password.length>20){
                $("#error_password").html('请将密码长度限制在6-20之间！');
                return;
            }
            isValidatePassword=true;
            $("#div_password").children().eq(0).hide();
            $("#confirm_password").blur();
        });

        // 验证确认密码
        $("#confirm_password").on("blur",function(){
            isValidateConfirmPassword=false;
            $("#div_confirm_password").children().eq(0).show();
            var password=$("#password").val();
            var confirm_password=$(this).val();
            if(isEmpty(confirm_password)){
                $("#error_confirm_password").html('请输入确认密码！');
                return;
            }
            if(confirm_password.length<6||confirm_password.length>20){
                $("#error_confirm_password").html('请将密码长度限制在6-20之间！');
                return;
            }
            if(password!=confirm_password){
                $("#error_confirm_password").html('两次输入密码不同,请重新输入！');
                return;
            }
            isValidateConfirmPassword=true;
            $("#div_confirm_password").children().eq(0).hide();
        });

        fgStepFun(); //跳下一步
        birdFun(); //切换鸟
    })


    var timer;
    var time=60;
    function getCheckCode(obj){
        if($("#div_email").hasClass("undis")){
            // 获取手机验证码
            if(!isValidateMobile){
                $("#div_mobile").children().eq(0).show();
                $("#error_mobile").html('请输入正确的手机号！');
                return;
            }
            $.ajax({
                url:'${ctx}/getCheckCode',
                type:'post',
                data:{'mobile':$("#mobile").val()},
                dataType:'json',
                success:function(result){
                    if(result.success){
                        timer = setInterval(function(){
                            if(time<0){
                                clearInterval(timer);
                                $(obj).html("获取验证码");
                                $(obj).css("cursor","pointer");
                                $(obj).attr("onclick","getCheckCode(this)");
                                return;
                            }
                            $(obj).css("cursor","default");
                            $(obj).attr("onclick","");
                            $(obj).html(time+"秒");
                            time--;
                        },1000);
                    }
                }
            })
        }else if($("#div_mobile").hasClass("undis")){
            // 获取邮箱验证码
            if(!isValidateEmail){
                $("#div_email").children().eq(0).show();
                $("#error_email").html('请输入正确的邮箱！');
                return;
            }
            $.ajax({
                url:'${ctx}/sendEmailCode',
                type:'post',
                data:{'email':$("#email").val()},
                dataType:'json',
                success:function(result){
                    if(result.success){
                        timer = setInterval(function(){
                            if(time<0){
                                clearInterval(timer);
                                $(obj).html("获取验证码");
                                $(obj).css("cursor","pointer");
                                $(obj).attr("onclick","getCheckCode(this)");
                                return;
                            }
                            $(obj).css("cursor","default");
                            $(obj).attr("onclick","");
                            $(obj).html(time+"秒");
                            time--;
                        },1000);
                    }
                }
            })
        }
    }
    //跳下一步
    var fgStepFun = function() {
        $(".fg-step-btn-1").click(function() {
            if($('.fg-phone').hasClass('current')){
                $("#div_mobile").show();
            }else if($('.fg-email').hasClass('current')){
                $("#div_email").show();
            }
            $(".fg-step-1").slideUp("200", function() {
                $(".fg-step-2").show();
            });
        });
        $(".fg-step-btn-2").click(function() {
            var checkCode = $("#checkCode").val();
            if(isEmpty(checkCode)){
                $("#div_checkCode").children().eq(0).show();
                $("#error_checkCode").html('请输入验证码！');
                return;
            }
            $("#div_checkCode").children().eq(0).hide();
            if(isValidateMobile){
                if(!isValidateMobile){
                    return;
                }
                var mobile = $("#mobile").val();
                $.ajax({
                    url:'${ctx}/checkRandomCode',
                    type:'post',
                    data:{'mobile':mobile,'randomCode':checkCode},
                    dataType:'json',
                    success:function(result){
                        if(result.success){
                            $(".fg-step-2").slideUp("200", function() {
                                $(".fg-step-3").show();
                            });
                        }else{
                            $("#div_checkCode").children().eq(0).show();
                            $("#error_checkCode").html('验证码错误,请重新输入！');
                        }
                    }
                });
            }else if(isValidateEmail){
                var email = $("#email").val();
                $.ajax({
                    url:'${ctx}/checkRandomCode',
                    type:'post',
                    data:{'email':email,'randomCode':checkCode},
                    dataType:'json',
                    success:function(result){
                        if(result.success){
                            $(".fg-step-2").slideUp("200", function() {
                                $(".fg-step-3").show();
                            });
                        }else{
                            $("#div_checkCode").children().eq(0).show();
                            $("#error_checkCode").html('验证码错误,请重新输入！');
                        }
                    }
                });
            }
        })
        // 提交
        $(".fg-step-btn-3").click(function() {
            if(isValidatePassword&&isValidateConfirmPassword){
                var mobile = $("#mobile").val();
                var email = $("#email").val();
                var password = $("#password").val();
                var confirmPassword = $("#confirm_password").val();
                $.ajax({
                    url:'${ctx}/updatePassword',
                    type:'post',
                    data:{
                        'mobile':mobile,
                        'email':email,
                        'password':password,
                        'confirmPassword':confirmPassword
                    },
                    dataType:'json',
                    success:function(result){
                        if(result.success){
                            dialogFun("修改密码","修改成功",5,'${ctx}/login');
                        }else{
                            dialogFun("修改密码",result.message,0);
                        }
                    }
                })
            }
        })
    }
    //切换鸟
    var birdFun = function() {
        $(".bm-lr-ul input[type='password']").focusin(function() {
            $(".lr-b-icon").css("background-image","url(${ctx}/static/nxb/web/img/ce.png)");
        }).focusout(function() {
            $(".lr-b-icon").css("background-image","url(${ctx}/static/nxb/web/img/ce.png)");
        })
    }


</script>
</body>
</html>
