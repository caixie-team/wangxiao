<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>查看试卷</title>
<script type="text/javascript">
$(document).ready(function() {
$("#type").val("${paper.type}");
$("#level").val("${paper.level}");
});
//组成专业字段显示
function loadSubject(){
	var str ="";
	var subject= getSubjectById('${paper.subjectId}');
	str += subject.subjectName;
		subject= getSubjectById(subject.subjectId);
		str = subject.subjectName+">>"+str;
	$("#subject").html(str);
}
//通过subjectId获得subject
function getProfessionalById(obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/subj/querySubjectById",
		data:{"subject.subjectId":obj},
		async:false,
		success:function(result){
			if(result.success){
				obj=result.entity;
			}
		}
});
	return obj;
}

</script>
</head>
<body >
<form action="${ctx}/admin/paper/updateExamPaper" method="post" id="addPaperForm">
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>试卷管理</span> &gt; <span>查看试卷</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>查看试卷<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;试卷名称</td>
				<td>
					<input type="text" name="paper.name" class="{required:true}" value="${paper.name}" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;所属考试专业分类</td>
				<td width="80%"  id="subject">
					${examProfessionalDto.professionalName }>>${examProfessionalDto.subjectName }
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>试卷类型</td>
				<td width="80%">
					<select name="paper.type" id="type" disabled="disabled">
                        <c:forEach items="${paperTypeList}" var="paperType">
                            <option value="${paperType.id}">${paperType.title}</option>
                        </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;试卷描述</td>
				<td>
					<textarea style="width: 38%;height: 48px;" name="paper.info" class="{required:true}" disabled="disabled">${paper.info }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;限制答题时间</td>
				<td>
					<input type="text"  name="paper.replyTime" class="{required:true,number:true,min:0,max:1000}" value="${paper.replyTime }" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;试卷总分</td>
				<td>
					<input type="text"  name="paper.score" class="{required:true,number:true}" value="${paper.score }"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;试卷难度</td>
				<td>
					<select name="paper.level" id="level" disabled="disabled">
						<option value="1">简单</option>
						<option value="2">中等</option>
						<option value="3">困难</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>
<!-- /tab4 end -->
</form>
</body>
</html>
