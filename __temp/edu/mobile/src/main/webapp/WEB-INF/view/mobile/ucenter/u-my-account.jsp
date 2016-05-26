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
				<h2 class="commHeadTitle"><span>我的账号</span></h2>
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
								<li class="current"><a href="javascript: void(0)" title="">基本信息</a></li>
								<li><a href="/mobile/uc/dopwd" title="">修改密码</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div style="margin-bottom: -50px;">
					<div>
						<!-- 基本信息 -->
						<section class="u-account-box">
							<form id="updateUserForm">
							<!-- 校验样式是给li加 class="amError"; -->
							<ol class="u-account-set">
								<li id="emailError">
									<span class="u-a-lab">邮箱：</span>
									<label class="u-a-inp"><input type="text" readonly="readonly" onclick="clearError(this)" id="email" name="queryUser.email" value="${queryUser.email }"></label>
								</li>
								<li id="nicknameError">
									<span class="u-a-lab">昵称：</span>
									<label class="u-a-inp"><input type="text" onclick="clearError(this)" id="nickname" name="queryUser.nickname" value="${queryUser.nickname }"></label>
								</li>
								<li id="realnameError">
									<span class="u-a-lab">姓名：</span>
									<label class="u-a-inp"><input type="text" onclick="clearError(this)" id="realname" name="queryUser.realname" value="${queryUser.realname }"></label>
								</li>
								<li>
									<span class="u-a-lab">性别：</span>
									<select name="queryUser.gender" id="gender">
										<option value="0" selected="selected">男</option>
										<option value="1" <c:if test="${queryUser.gender==1 }">selected="selected"</c:if>>女</option>
										<option value="2" <c:if test="${queryUser.gender==2 }">selected="selected"</c:if>>保密</option>
									</select>
								</li>
								<li id="mobileError">
									<span class="u-a-lab">手机：</span>
									<label class="u-a-inp"><input type="text" onclick="clearError(this)" id="mobile" name="queryUser.mobile" value="${queryUser.mobile }"></label>
								</li>
								<li>
									<span class="u-a-lab">简介：</span>
									<textarea name="queryUser.userInfo" id="userInfo">${queryUser.userInfo}</textarea>
								</li>
							</ol>
							</form>
							<section class="lr-btn i-box">
								<a href="javascript:void(0)" onclick="formsubmit()" title="">保 存</a>
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
		function clearError(object){
			$(object).parent().parent().removeClass("amError");
		}
		//修改信息
		function formsubmit(){
			if($("#email").val()==""){
				$("#emailError").addClass("amError");
				return;
			}
			if($("#nickname").val()==""){
				$("#nicknameError").addClass("amError");
				return;
			}
			if($("#realname").val()==""){
				$("#realnameError").addClass("amError");
				return;
			}
			if($("#mobile").val()==""){
				$("#mobileError").addClass("amError");
				return;
			}
			var date = $("#updateUserForm").serialize();
			$.ajax({
				url:baselocation+'/mobile/uc/user/update',
				type:'post',
				dataType:'json',
				data:date,
				success:function (result){
					if(result.success){
						if(result.entity=='success'){
							dialog('修改信息','修改成功','',0);
						}
						if(result.entity=='mobileHave'){
							dialog('修改信息',"手机号已存在,请重新填写",'',0);
						}
						
					}else{
						dialog('修改信息',"失败请刷新重试",'',0);
					}
				}
			});
		}

	</script>
</body>
</html>