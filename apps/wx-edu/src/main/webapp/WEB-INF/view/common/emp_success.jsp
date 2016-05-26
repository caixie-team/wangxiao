<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<head>
<title>操作成功</title>
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/admin/css/common_frame.css?v=<%=version%>" />
<link rel="stylesheet" type="text/css"
	href="${ctximg}/static/common/admin/css/common_frame_right.css?v=<%=version%>" />
<script type="text/javascript"
	src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
<script type="text/javascript">
	$().ready(function() {

	});
</script>
</head>
<body style="background: #F5F5F5;">
	<div class="mt20">
		<table width="100%" cellspacing="0" cellpadding="0" border="0"
			class="commonTab01">
			<c:if test="${msg!=null&&msg!='' }">
				<thead>
					<tr>
						<th colspan="6" align="left"><span>操作成功<tt
									class="c_666 ml20 fsize12"></tt></span></th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th colspan="6"><span>${record }<tt
									class="c_666 ml20 fsize12"></tt></span></th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th width="2%"><span>行号</span></th>
						<th width="5%"><span>手机号</span></th>
						<th width="8%"><span>邮箱</span></th>
						<th width="5%"><span>姓名</span></th>
						<th width="6%"><span>密码</span></th>
						<th width="25%"><span>备注</span></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${information }" var="information">
						<tr style="text-align: center;">
							<td>${information.rowsNumber}</td>
							<td>${information.mobile}</td>
							<td>${information.email }</td>
							<td>${information.nickname }</td>
							<td>${information.password }</td>
							<td><font color="red">${information.note }</font></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6" align="center"><input type="button"
							value="返回" onclick="history.go(-1)" class="btn ml10" /></td>
					</tr>
				</tbody>
			</c:if>
			<c:if test="${msg==null||msg=='' }">
				<thead>
					<tr>
						<th colspan="5" align="left"><span>操作成功<tt
									class="c_666 ml20 fsize12"></tt></span></th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th colspan="6"><span>${record }<tt
									class="c_666 ml20 fsize12"></tt></span></th>
					</tr>
				</thead>
				<thead>
					<tr style="text-align: center;">
						<td>${information.rowsNumber}</td>
						<td>${information.mobile}</td>
						<td>${information.email }</td>
						<td>${information.nickname }</td>
						<td>${information.password }</td>
						<td><font color="red">${information.note }</font></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${information }" var="information">
						<tr style="text-align: center;">
							<td>${information.rowsNumber}</td>
							<td>${information.mobile}</td>
							<td>${information.email }</td>
							<td>${information.nickname }</td>
							<td>${information.password }</td>
							<td><font color="red">${information.note }</font></td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="6" align="center"><input type="button"
							value="返回" onclick="history.go(-1)" class="btn ml10" /></td>
					</tr>
				</tbody>
			</c:if>
		</table>
	</div>
</body>
</html>
