<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>考试结果</title>
</head>
<body>
<span><a href="/paper/toExamPaperRecord/${paperRecord.id}">查看解析</a></span>
<table border="2">
	<tr>
	<td width="100">用户试卷ID</td>
	<td>用户得分</td>
	<td>正确率</td>
	<td>用户ID</td>
	<td>试卷ID</td>
	<td>添加时间</td>
	<td>测试时间</td>
	<td>考试题数</td>
	<td>正确题数</td>
	<td>专业ＩＤ</td>
	<td>试卷类型</td>
	</tr>
	<tr>
	<td>${paperRecord.id}</td>
	<td>${paperRecord.userScore}</td>
	<td>${paperRecord.accuracy}</td>
	<td>${paperRecord.cusId}</td>
	
	<td>${paperRecord.epId}</td>
	<td><s:date name="paperRecord.addTime"  format="yyyy-MM-dd HH:mm"/></td>
	<td>${paperRecord.testTime}</td>
	<td>${paperRecord.qstCount}</td>
	<td>${paperRecord.correctNum}</td>
	<td>${paperRecord.subjectId}</td>
	<td>${paperRecord.type}</td>
	</tr>

</table>




















</body>
</html>
