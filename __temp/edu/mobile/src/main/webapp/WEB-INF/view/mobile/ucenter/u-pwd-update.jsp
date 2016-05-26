<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的账号</title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/uc/home" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>修改密码</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="animated fadeIn">
				<div>
					<div class="v-card-title-box">
						<div class="v-card-title" style="position: absolute;">
							<ul class="col-2" id="item-l-ul">
								<li><a href="/mobile/uc/uinfo" title="">基本信息</a></li>
								<li class="current"><a href="javascript: void(0)" title="">修改密码</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div style="margin-bottom: -50px;">
					<div>
						<!-- 基本信息 -->
						<section class="u-account-box">
							<form id="updatePwdForm">
							<!-- 校验样式是给li加 class="amError"; -->
							<ol class="u-account-set">
								<li id="emailError">
									<span class="u-a-lab">原密码：</span>
									<label class="u-a-inp"><input type="password"  id="oldpwd" ></label>
								</li>
								<li id="nicknameError">
									<span class="u-a-lab">新密码：</span>
									<label class="u-a-inp"><input type="password"  id="newpwd" ></label>
								</li>
								<li id="nicknameError">
									<span class="u-a-lab">再一次：</span>
									<label class="u-a-inp"><input type="password"  id="confirmNewpwd"  ></label>
								</li>
							</ol>
							</form>
							<section class="lr-btn i-box">
								<a href="javascript:void(0)" onclick="updatePwd()" title="">保 存</a>
							</section>
						</section>
						<!-- 基本信息 -->
					</div>
				</div>
			
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
		})
		//修改信息
		function updatePwd(){
			if($("#oldpwd").val()==""){
				dialog('提示','请输入原密码','',0);
				return;
			}
			if($("#newpwd").val()==""){
				dialog('提示','请输入新密码','',0);
				return;
			}
			if($("#confirmNewpwd").val()==""){
				dialog('提示','请再次输入新密码','',0);
				return;
			}
			if($("#newpwd").val()!=$("#confirmNewpwd").val()){
				dialog('提示','两次输入的密码不相等','',0);
				return;
			}
			var pattern = /^(?!_)(?!_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
			if(pattern.test($("#newpwd").val())==false){
				dialog('提示','请不要输入非法关键字','',0);
				return;
			}
			
			if($("#newpwd").val().length<6){
				dialog('提示','密码长度必须大于6位','',0);
				return;
			}
			$.ajax({
				url:baselocation+'/mobile/uc/pwd',
				type:'post',
				dataType:'json',
				data:{"oldpwd":$("#oldpwd").val(),"newpwd":$("#newpwd").val()},
				success:function (result){
					if(result.success){
						dialog('提示','修改密码成功','',0);
						$("#updatePwdForm")[0].reset();
					}else{
						dialog('提示',"原密码不正确",'',0);
					}
				}
			});
		}

	</script>
</body>
</html>