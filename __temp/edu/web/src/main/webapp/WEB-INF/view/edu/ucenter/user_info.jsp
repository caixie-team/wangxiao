<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!-- 新增 -->
<!DOCTYPE html>
<html>
<head>
<title>基本信息</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/ucenter/u_user_info.js?v=${v}"></script>
<script type="text/javascript">
$(function (){
	$("#updateUserForm").validate();
});
</script>
</head>

<body>
<!-- 中间内容 -->
<article class="u-m-c-w837 u-m-center">
	<section class="u-m-c-wrap">
		<!-- /u-m-c-head -->
		<section class="u-m-c-head">
			<ul class="fl u-m-c-h-txt">
				<li class="current"><a href="" title="">账号管理</a></li>
			</ul>
			<div class="clear"></div>
		</section>
		<!-- /u-m-c-head -->
		<section class="line1">
			<!-- /u-m-sub-head -->
			<div class="u-m-sub-head">
				<ol class="clearfix pl50">
					<li class="current"><strong><a href="javascript:void(0)"  class="whiteCol">基本信息</a></strong></li>
					<li ><strong><a href="${ctx}/uc/uppwd" class="grayCol">修改密码</a></strong></li>
					<li><strong><a href="${ctx}/uc/avatar" class="grayCol">修改头像</a></strong></li>
				</ol>
			</div>
			<!-- /u-m-sub-head -->
			<form action="${ctx}/cus/uc!updatequeryUser.action" method="post"  id="updateUserForm">
			<div class="mt50">
				<ol class="u-account-set">
                    <li>
                        <span class="vam u-a-lab">登录邮箱：</span>
                        <label class="u-a-txt">
                        	<c:choose>
                        		<c:when test="${profile!=null && profile.userid==queryUser.cusId}">
                            	<input type="text" maxlength="50"  name="queryUser.email" id="email" onkeydown="$('#email-1').text('');" value="${queryUser.email}">
                        		</c:when>
                        		<c:otherwise>
	                            <input type="text" readonly="readonly" maxlength="50"  name="queryUser.email" id="email" onkeydown="$('#email-1').text('');" value="${queryUser.email}">
                        		</c:otherwise>
                        	</c:choose>
                            <font id="email-1" color="red"></font>
                        </label>
                    </li>
					<li>
						<span class="vam u-a-lab">昵称：</span>
						<label class="u-a-txt"><input type="text" maxlength="50" name="queryUser.nickname" id="nickname" value="${queryUser.nickname}"></label>
					</li>
					<li>
						<span class="vam u-a-lab">性别：</span>
						<input type="radio" name="queryUser.gender" id="gender1" value="0"
						 <c:if test="${queryUser.gender == 0}">
						 checked="checked"
						 </c:if>
						 placeholder="" style="margin: 0 0 0 10px;">
						<label for="boy" class="c-666">男</label>
						<input type="radio" name="queryUser.gender" id="gender0" value="1"
						 <c:if test="${queryUser.gender != 0}">
						 checked="checked"
						 </c:if>
						 placeholder="" style="margin: 0 0 0 10px;">
						<label for="girl" class="c-666">女</label>
					</li>

					<li>
						<span class="vam u-a-lab">手机号码：</span>
						<label class="u-a-txt">
							<input type="text" name="queryUser.mobile" maxlength="11" class="mobile"  id="mobile" onkeydown="$('#mobile-1').text('');" onkeyup="this.value=this.value.replace(/\D/g,'')" value="${queryUser.mobile}">
							<font id="mobile-1" color="red"></font>
						</label>
					</li>
					<li>
						<span class="vam u-a-lab">真实姓名：</span>
						<label class="u-a-txt"><input type="text"  name="queryUser.realname" maxlength="50" id="realname" value="${queryUser.realname}"></label>
					</li>
					<li>
						<span class="vam u-a-lab">个人简介：</span>
						<textarea style="height:60px;" class="vam" name="queryUser.userInfo" maxlength="30" id="userInfo">${queryUser.userInfo}</textarea>
						
					</li>

					<li class="mt40">
						<span class="vam u-a-lab">&nbsp;</span>
						<span class="vam ml50">
							<label class="u-a-set-btn"><input type="button" onclick="formsubmit()" value="保 存"></label>
						</span>
					</li>
				</ol>
			</div>
		</form>
		</section>
	</section>
</article>
<!-- 中间内容结束 -->
</body>
</html>
