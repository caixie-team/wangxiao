<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改试卷</title>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script> 
<script type="text/javascript">
$(document).ready(function() {
	$("#addPaperForm").validate();
	$("#type").val("${paper.type}");
	$("#level").val("${paper.level}");
	$("#professionalSelect").val("${examProfessionalDto.professionalId}");
	getSubjectByProId("${examProfessionalDto.professionalId}");
	$("#subjectSelect").val("${paper.subjectId}");
});

//根据专业id查询科目.
function getSubjectByProId(obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:"${ctx}/admin/subj/querySubjectByProId",
		data:{"querySubject.professionalId":obj},
		async:false,
		success:function(result){
			if(result.success==true){
				var subjectList = result.entity;
				var str = "";
				for(var i =0;i<subjectList.length;i++){
					str +='<option value="'+subjectList[i].subjectId+'"">'+subjectList[i].subjectName+'</option>';
				}
				$("#subjectSelect").html(str);
			}
		}
});
}
function addPaperFormSubmit(){
	$("#addPaperForm").submit();
}
</script>
</head>
<body >
<form action="${ctx}/admin/paper/updateExamPaper" method="post" id="addPaperForm">
<input type="hidden" name="paper.id" value="${paper.id }"/>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>试卷管理</span> &gt; <span>修改试卷</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>修改试卷<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;试卷名称</td>
				<td>
					<input type="text" name="paper.name" class="{required:true}" value="${paper.name }"/>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;所属考试专业分类</td>
				<td width="80%">
					<select onchange="getSubjectByProId(this.value)"  id="professionalSelect">
					 <c:forEach items="${professionalList}" var="professional">
					 	<option value="${professional.professionalId}" >${professional.professionalName}</option>
					 </c:forEach>
					 </select>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;所属考试科目</td>
				<td width="80%">
					<select id="subjectSelect" name="paper.subjectId">
					</select>
				</td>
			</tr>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>试卷类型</td>
				<td width="80%">
					<select name="paper.type" id="type">
						<c:forEach items="${paperTypeList }" var="ptl">
						<option value="${ptl.id }">${ptl.title }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;试卷描述</td>
				<td>
					<textarea style="width: 38%;height: 48px;" name="paper.info" class="{required:true}">${paper.info }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;限制答题时间</td>
				<td>
					<input type="text"  name="paper.replyTime" class="{required:true,number:true,min:0,max:1000}" value="${paper.replyTime }"/>
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
					<select name="paper.level" id="level">
						<option value="1">简单</option>
						<option value="2">中等</option>
						<option value="3">困难</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:addPaperFormSubmit();">提 交</a>
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
