<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">

function addSubmit(){
	if($("#professionalName").val()==null||$("#professionalName").val()==''){
		alert("请填写专业名称");
		return;
	}
	if(isNaN($("#sort").val())){
		alert("排序只能为数字");
		return;
	}
	$("#addForm").submit();
}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>考试专业管理</span> &gt; <span>考试专业添加</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/professional/addProfessional" method="post" id="addForm">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>考试专业基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;专业名称：</td>
					<td>
						<input id="professionalName" type="text" name="professional.professionalName" maxlength="9" class="{required:true,number:true}"/>
						<em id="menuNameCount" style="color: #ff0000;">0</em>/9
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;排序：</td>
					<td>
						<input id="sort" type="text" name="professional.sort" value="0" class="{required:true,number:true}"/>
						<font color="red">倒序</font>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:addSubmit()">提 交</a>
						<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</body>
</html>