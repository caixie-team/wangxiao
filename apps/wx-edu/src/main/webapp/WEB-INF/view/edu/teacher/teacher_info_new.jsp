<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>教师详情</title>
<script type="text/javascript">
$(function() {
	//课程列表说明层
		$(".teach-Style-in>ul>li .t-s-in-infor").hover(function() {
			$(this).children(".t-s-in-infor-bg").animate({"bottom" : "0px"}, 200);
		}, function() {
			$(this).children(".t-s-in-infor-bg").animate({"bottom" : "-188px"}, 200);
		});
	});
	
	
</script>
</head>
<body>

</body>
</html>
