<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户权限限制</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_right.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}"></script> 
<script type="text/javascript">
		$().ready(function() {
 			
 		});
	</script>
</head>
<body  >
<div class="container">
	<div class="no-limit-box">
		<span><em class="face-icon"></em><tt class=" vam">对不起，您没有访问此功能的权限……</tt></span>
		<div class="ret-btn"><a href="${ctx}/"><front>返回首页</front></a></div>
	</div>
</div>
</body>
</html>
