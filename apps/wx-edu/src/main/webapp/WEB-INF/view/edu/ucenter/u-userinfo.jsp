<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人信息</title>
</head>
<body>
	<div class="">
		<article>
			<header class="uc-com-title">
				<span>个人信息</span>
			</header>
			<div class="u-title-box u-user-title">
				<ol class="js-tap clearfix">
					<li class="current"><a href="javascript:void(0)" title="个人资料">个人资料</a></li>
					<li><a href="${ctx}/uc/avatar" title="修改头像">修改头像</a></li>
					<li><a href="${ctx}/uc/uppwd" title="密码设置">密码设置</a></li>
					<li><a href="${ctx}/uc/updateMobile" title="手机号">手机号</a></li>
					<li><a href="${ctx}/uc/updateEmail" title="Email">Email</a></li>
				</ol>
			</div>
			<div class="i-box">
				<section>
					<div class="u-account-box">
						<form id="updateUserForm">
							<ol class="u-account-li">
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">昵 称</span>
									</label>
									<input type="text" value="${queryUser.nickname}" id="nickname" name="queryUser.nickname" class="u-a-inpt">
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">姓 名</span>
									</label>
									<input type="text" id="realname" value="${queryUser.realname}" name="queryUser.realname" class="u-a-inpt">
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">性 别</span>
									</label>
									<input type="radio" value="0" id="gender1" checked="checked" name="queryUser.gender" <c:if test="${queryUser.gender == 0}">checked="checked"</c:if> >
									<span class="vam fsize14 c-666">男</span>
									<input type="radio" value="1" id="gender0" name="queryUser.gender" <c:if test="${queryUser.gender != 0}"> checked="checked"</c:if>>
									<span class="vam fsize14 c-666">女</span>
								</li>
							</ol>
						</form>
					</div>
				</section>
				<section></section>
				<!-- 头像修改/ -->
				<section>
					<div class="u-a-g-list c-u-sett-box">
						<section class="pd20-50 mt20 ">
							<div class="g-zl-wrap g-zl-dl">
								<section class="mt20">
									<a onclick="formsubmit()" href="javascript:void(0)" title="修改" class="bm-lr-btn u-user-btn">修&nbsp;改</a>
								</section>
							</div>
						</section>
					</div>
				</section>
			</div>
		</article>
	</div>
	<script type="text/javascript" src="${ctximg}/static/edu/js/ucenter/u_user_info.js?v=${v}"></script>
</body>
</html>