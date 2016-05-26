<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>修改密码</title>
</head>
<body>
	<div class="">
		<article>
			<header class="uc-com-title">
				<span>密码设置</span>
			</header>
			<div class="u-title-box u-user-title">
				<ol class="js-tap clearfix">
					<li><a href="${ctx}/uc/uinfo" title="个人资料">个人资料</a></li>
					<li><a href="${ctx}/uc/avatar" title="修改头像">修改头像</a></li>
					<li class="current"><a href="javascript:void(0)" title="密码设置">密码设置</a></li>
					<li><a href="${ctx}/uc/updateMobile" title="手机号">手机号</a></li>
					<li><a href="${ctx}/uc/updateEmail" title="Email">Email</a></li>
				</ol>
			</div>
			<div class="i-box">
				<section>
					<div class="u-account-box">
						<form id="updateMobileForm" method="post" action="">
							<ol class="u-account-li">
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">旧密码</span>
									</label>
									<input type="password" name="customer.cusPwd" id="oldPwd" class="u-a-inpt">
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">新密码</span>
									</label>
									<input type="password" value="" id="newPwd" name="newPwd" class="u-a-inpt">
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">确认密码</span>
									</label>
									<input type="password" value="" id="newPwdConfirm" name="newPwdConfirm" class="u-a-inpt">
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">&nbsp;</span>
									</label>
									<a href="javascript:void(0)" onclick="subit()"  class="lh-reply-btn ml15" title="提交">提&nbsp;交</a>
								</li>
							</ol>
						</form>
					</div>
				</section>
			</div>
		</article>
	</div>
<script type="text/javascript" src="${ctximg}/static/edu/js/user/u_editpwd.js"></script>
<script type="text/javascript">
	var updateError = "${updateError}";
	var updateMessage = "${updateMessage}";
</script>
</body>
