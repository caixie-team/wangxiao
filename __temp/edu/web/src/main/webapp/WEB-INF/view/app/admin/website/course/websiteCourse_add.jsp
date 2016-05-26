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
		if($("#appWebsiteCourseName").val()==null||$("#appWebsiteCourseName").val()==""){
			alert("名称不能为空");
			return;
		}
		if($("#link").val()==null||$("#link").val()==""){
			alert("更多跳转不能为空");
			return;
		}
		if(isNaN($("#courseNum").val())){
			alert("课程数量只能为数字");
			return;
		}
		$("#addWebsiteCourseForm").submit();
	}
</script>
</head>
<body>
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>推荐课程分类管理</span> &gt; <span>推荐课程分类添加</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/appWebsite/addCourse" method="post" id="addWebsiteCourseForm">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>推荐课程分类基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;分类名称 </td>
					<td>
						<input type="text" name="appWebsiteCourse.name" id="appWebsiteCourseName" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;更多跳转</td>
					<td>
						<input type="text"  name="appWebsiteCourse.link" id="link" class="{required:true,number:true,min:0,max:1000}"/>
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;课程数量</td>
					<td>
						<input type="text" name="appWebsiteCourse.courseNum" id="courseNum" value="0" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td align="center">描述</td>
					<td>
						<textarea rows="3" cols="80" name="appWebsiteCourse.description" id="description"></textarea>
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
	<!-- /tab4 end -->
</body>
</html>