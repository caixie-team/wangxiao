<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>修改密码</title>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/user/u_editpwd.js"></script>
<script type="text/javascript">
	var updateError = "${updateError}";
	var updateMessage = "${updateMessage}";
</script>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li class="current">
						<a href="" title="">账号管理</a>
					</li>
				</ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				<!-- /u-m-sub-head -->
				<div class="u-m-sub-head">
					<ol class="clearfix pl50">
						<li>
							<a href="${ctx}/uc/uinfo" title="基本信息">基本信息</a>
						</li>
						<li class="current">
							<a href="javascript:void(0)" title="">修改密码</a>
						</li>
						<li>
							<a href="${ctx}/uc/avatar" title="">修改头像</a>
						</li>
					</ol>
				</div>
				<!-- /u-m-sub-head -->
				<%-- <form action="${ctx}/cus/uc!updatePwd.action" method="post" name="pwdForm" id="pwdForm"> --%>
				<div class="mt50">
					<ol class="u-account-set">
						<li>
							<span class="vam u-a-lab">旧密码：</span>
							<label class="u-a-txt">
								<input type="password" name="customer.cusPwd" id="oldPwd" value="">
								<font id="oldPwd_1" color="red"></font>
							</label>
						</li>
						<li>
							<span class="vam u-a-lab">新密码：</span>
							<label class="u-a-txt">
								<input type="password" name="newPwd" id="newPwd" value="">
								<font id="newpwd_1" color="red"></font>
							</label>
						</li>
						<li>
							<span class="vam u-a-lab">再输一次：</span>
							<label class="u-a-txt">
								<input type="password" name="newPwdConfirm" id="newPwdConfirm" value="">
							</label>
						</li>
						<li class="mt40">
							<span class="vam u-a-lab">&nbsp;</span>
							<span class="vam ml50">
								<label class="u-a-set-btn">
									<input type="button" onclick="subit();" value="保 存">
								</label>
							</span>
						</li>
					</ol>
				</div>
				<!-- </form> -->
			</section>
		</section>
	</article>
</body>
