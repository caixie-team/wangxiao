<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />

<script type="text/javascript">
	function updateSubmit(){
		
		if(isNaN($("#orderNum").val())){
			alert("课程数量只能为数字");
			return;
		}
		$("#updateWebsiteCourseDetailForm").submit();
	}
</script>
</head>
<body>
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>推荐课程管理</span> &gt; <span>推荐课程修改</span> </h4>
	</div>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/appWebsite/updateWebsiteCourseDetail" method="post" id="updateWebsiteCourseDetailForm">
		<input type="hidden" name="appWebsiteCourseDetail.id" value="${appWebsiteCourseDetailDTO.id}"/>
		<table width="100%" cellspacing="0" cellpupdateing="0" border="0" class="commonTab01">
			<thead>
				<tr>
					<th align="left" colspan="2"><span>推荐课程基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;课程名称 </td>
					<td>
						${appWebsiteCourseDetailDTO.courseName }
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;分类</td>
					<td>
						${appWebsiteCourseDetailDTO.recommendName }
					</td>
				</tr>
				<tr>
					<td align="center"><font color="red">*</font>&nbsp;排序值</td>
					<td>
						<input type="text" name="appWebsiteCourseDetail.orderNum" value="${appWebsiteCourseDetailDTO.orderNum }" id="orderNum" value="0" class="{required:true}"/>
						<font color="red">倒序</font>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<a class="btn btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
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