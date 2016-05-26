<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<title>个人信息</title>
</head>
<body>
	<div class="">
		<article>
			<header class="uc-com-title">
				<span>Email</span>
			</header>
			<div class="u-title-box u-user-title">
				<ol class="js-tap clearfix">
					<li><a href="${ctx}/uc/uinfo" title="个人资料">个人资料</a></li>
					<li><a href="${ctx}/uc/avatar" title="修改头像">修改头像</a></li>
					<li><a href="${ctx}/uc/uppwd" title="密码设置">密码设置</a></li>
					<li><a href="${ctx}/uc/updateMobile" title="手机号">手机号</a></li>
					<li class="current"><a href="${ctx}/uc/updateEmail" title="Email">Email</a></li>
				</ol>
			</div>
			<div class="i-box">
				<section>
					<div class="u-account-box">
						<form id="updateEmailForm">
							<ol class="u-account-li">
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">Email</span>
									</label>
									<input type="text" class="u-a-inpt" id="oldEmail" disabled value="${user.email}"/>
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">&nbsp;</span>
									</label>
									<a href="javascript:void(0)" class="lh-reply-btn ml15" onclick="nextStep(1)" title="修改Email">修改Email</a>
								</li>
								<li class="undis">
									<label class="u-a-title">
										<span class="fsize16 c-999">验证码</span>
									</label>
									<input type="text" class="u-a-inpt" id="randomCode_old"/>
									<label>
										<a href="javascript:void(0)" class="lh-reply-btn ml15" onclick="getEmailRandom(this,'oldEmail')" title="验证码">获取验证码</a>
									</label>
								</li>
								<li class="undis">
									<label class="u-a-title">
										<span class="fsize16 c-999">&nbsp;</span>
									</label>
									<a href="javascript:void(0)" class="lh-reply-btn ml15" title="确认" onclick="nextStep(2)">确&nbsp;认</a>
								</li>
								<li class="undis">
									<label class="u-a-title">
										<span class="fsize16 c-999">新Email</span>
									</label>
									<input type="text" class="u-a-inpt" id="newEmail"/>
									<label style="color:red;">
									</label>
								</li>
								<li class="undis">
									<label class="u-a-title">
										<span class="fsize16 c-999">验证码</span>
									</label>
									<input type="text" class="u-a-inpt" id="randomCode_new"/>
									<label>
										<a href="javascript:void(0)" class="lh-reply-btn ml15" onclick="getEmailRandom(this,'newEmail')" title="验证码">获取验证码</a>
									</label>
								</li>
								<li class="undis">
									<label class="u-a-title">
										<span class="fsize16 c-999">&nbsp;</span>
									</label>
									<a href="javascript:void(0)" class="lh-reply-btn ml15" title="确认" onclick="nextStep(3)">确&nbsp;认</a>
								</li>
							</ol>
						</form>
					</div>
				</section>
			</div>
		</article>
	</div>
	<script type="text/javascript">
		$(function(){
			var email = '${user.email}';
			if(isEmpty(email)){
				$(".u-account-li li").hide();
				$(".u-account-li li").eq(4).show();
				$(".u-account-li li").eq(5).show();
			}
		})
		var timer;
		var time = 60;
		function nextStep(index){
			if(index==1){
				$(".u-account-li li").show();
				$(".u-account-li li").eq(1).hide();
				$(".u-account-li li").eq(4).hide();
				$(".u-account-li li").eq(5).hide();
				$(".u-account-li li").eq(6).hide();
			}else if(index==2){
				var random = $("#randomCode_old").val();
				if(isEmpty(random)){
					dialogFun("提示","请输入验证码",0);
					return;
				}
				var email = $("#oldEmail").val();
				$.ajax({
					url:"${ctx}/checkRandomCode",
					type:"post",
					data:{"email":email,"randomCode":random},
					dataType:"json",
					success:function(result){
						if(result.success){
							$(".u-account-li li").show();
							$(".u-account-li li").eq(0).hide();
							$(".u-account-li li").eq(1).hide();
							$(".u-account-li li").eq(2).hide();
							$(".u-account-li li").eq(3).hide();
						}else{
							dialogFun("提示",result.message,0);
						}
					}
				});
			}else if(index==3){
				var random = $("#randomCode_new").val();
				if(isEmpty(random)){
					dialogFun("提示","请输入验证码",0);
					return;
				}
				var email = $("#newEmail").val();
				$.ajax({
					url: '${ctx}/updateMobileOrEmail',
					type: 'post',
					data: {'email': email, 'random': random},
					dataType: 'json',
					success: function (result) {
						if (result.success) {
							dialogFun("提示", "修改成功", 5,window.location.href);
						} else {
							dialogFun("提示", result.message, 0);
						}
					}
				})
			}
		}
		function getEmailRandom(obj,id){
			$.ajax({
				url: '${ctx}/sendEmailCode',
				type: 'post',
				data: {'email': $("#"+id).val()},
				dataType: 'json',
				success: function (result) {
					if (result.success) {
						timer = setInterval(function () {
							if (time < 0) {
								clearInterval(timer);
								$(obj).html("获取验证码");
								$(obj).css("cursor", "pointer");
								$(obj).attr("onclick", "getRandomCode()");
								return;
							}
							$(obj).css("cursor", "default");
							$(obj).attr("onclick", "");
							$(obj).html(time + "秒");
							time--;
						}, 1000);
					}
				}
			})
		}
	</script>
</body>
</html>