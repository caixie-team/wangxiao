<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" http-equiv="Content-Type"/>
	<title>考试系统</title>
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8"> 
	<meta name="application-name" content=""> 
	<meta name="description" content=""> 
	<meta name="application-url" content="">
	<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js"></script><![endif]-->
	<link rel="stylesheet" href="${ctximg}/static/exam/css/exam-style.css">
	<script src="${ctximg}/static/common/jquery-1.11.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/exam/js/util/web_util.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctximg}/static/exam/js/examJs.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$(function(){
			var myDate=new Date();
			var num = myDate.getDay();
			str = "/static/exam/images/bg/"+num+".jpg";
			$("#indexImages").attr("src",str);
		});
		//修改密码
		function changePassword(){
			var oldPwd = $("#oldPwd").val();
			var newPassword = $("#new-password").val();
			var newAgPassword = $("#new-ag-password").val();
			 if(oldPwd==null || oldPwd.trim()==''){
				 $(".regErrorMessage").html('请输入原密码');
				 $(".e-l-jy-regist").attr("style","visibility: visible;");
				 return;
			 }
			 if(newPassword==null || newPassword.trim()==''){
				 $(".regErrorMessage").html('请输入新密码');
				 $(".e-l-jy-regist").attr("style","visibility: visible;");
				 return;
			 }
			
			 if(newAgPassword==null || newAgPassword.trim()==''||newPassword!=newAgPassword){
				 $(".regErrorMessage").html('两次密码不一致');
				 $(".e-l-jy-regist").attr("style","visibility: visible;");
				 return;
			 }
			 if(oldPwd==newAgPassword){
				 $(".regErrorMessage").html('旧密码不能和新密码相同');
				 $(".e-l-jy-regist").attr("style","visibility: visible;");
				 return;
			 }
			 var pattern = /(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
				if(pattern.test(newAgPassword)==false){
					$(".regErrorMessage").html('请不要输入非法关键字');
					 $(".e-l-jy-regist").attr("style","visibility: visible;");
					return;
				}
			$.ajax({
				type: 'POST',
				url: '${ctx}/cus/updatePwd',
				dataType: "json",
				data:{
				'customer.cusPwd':oldPwd,
				'newPwd':newPassword
				},
				success: function (result) {
					if(result.success){
						dialog(2);
						$(".dialogText").html('<span class="bc-icon">&nbsp;</span>'+result.message);
						heightAdjust();
						timer = setTimeout(toQuestionitemList, 1000);
					}else{
						$(".regErrorMessage").html(result.message);
						$(".e-l-jy-regist").attr("style","visibility: visible;");
					}
					
					/* var obj = result.returnMessage;
					var objArray = obj.split(",");
					dialog(objArray[0],objArray[1]); */
					},
				error: function (msg) {
				dialog('系统繁忙，请稍后再操作',2);
				}
				}); 
		}
		function toQuestionitemList(){
			window.location.href="${ctx}/quest/toQuestionitemList";
		}
		function heightAdjust(){//谈框高度调整
			var dialogEle = $(".dialog-shadow");
			var dTop = (parseInt(document.documentElement.clientHeight, 10)/2) + (parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10)),
			dH = dialogEle.height(),
			dW = dialogEle.width();
		dialogEle.css({"top" : (dTop-(dH/2)) , "margin-left" : -(dW/2)});
		}
	</script>
</head>     
<body>
		<div class="e-bg"><img id="indexImages" src="${ctximg}/static/exam/images/bg/0.jpg"></div><!-- /bg -->
		<!-- e-main begin -->
		<section class="e-main">
			<div class="w1000">
				<!-- /exam login begin -->
				<section class="e-login-wrap">
					<article class="e-login-bg">
						<section class="e-login">
							<!-- /e-login -->
							<div class="e-register-ele">
								<h2 class="e-login-title"><span>修改密码</span></h2>
								<div class="mt10">
									<p class="e-l-jy e-l-jy-regist"><font class="fsize12 c-orange regErrorMessage"></font></p>
								</div>
								<div>
									<ol class="e-login-options">
										<li>
											<label for="oldPwd">原密码：</label>
											<input id="oldPwd" type="password" name="" value="">
										</li>
										<li>
											<label for="new-password">新密码：</label>
											<input id="new-password" type="password" name="" value="">
										</li>
										<li>
											<label for="new-ag-password">确认密码：</label>
											<input id="new-ag-password" type="password" name="" value="">
										</li>
									</ol>
									<section class="mt30 tac">
										<a href="javascript:changePassword()" title="修 改" class="e-register-btn">修 改</a>
									</section>
									<section class="mt30 tar">
									</section>	
								</div>
							</div>
							<!-- /e-register -->
						</section>
					</article>
				</section>
				<!-- /exam login end -->
			</div>
		</section>
		<!-- e-main end -->
</body>
</html>