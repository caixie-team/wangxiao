<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
%>
	<head>
		<title>操作成功</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=<%=version%>"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_right.css?v=<%=version%>"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script> 
<script type="text/javascript">
		$().ready(function() {
 			
 		});
	</script>
</head>
<body style="background: #F5F5F5;">
	<div class="am-cf">
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">操作成功</strong></div>
	</div>
<div class="mt20">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<c:if test="${msg!=null&&msg!='' }">
		
		<tbody>
			<tr>
				<td width="100%" align="center">
					${msg}
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="返回" onclick="history.go(-1)" class="am-btn am-btn-primary"/>
				</td>
			</tr>
		</tbody>
		</c:if>
		<c:if test="${msg==null||msg=='' }">

		<tbody>
			<tr>
				<td width="100%" align="center">
					提交成功！
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="返回" onclick="history.go(-1)" class="am-btn am-btn-primary"/>
				</td>
			</tr>
		</tbody>
		</c:if>
	</table>
</div>
	</body>
</html>
