<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新小组分类</title>
	<script type="text/javascript">
	function artSub(){
		var classifyname=$("input[name='artclassify.name']").val().trim();
		var sort =$("input[name='artclassify.sort']").val().trim();
		if(classifyname==''||classifyname==null){
			alert("文章分类名称不能为空");
			return;
		}else if(sort==''||sort==null){
			alert("排序值不能为空");
			return;
		}else if(!/^[0-9]*$/.test(sort)){
			alert("请输入数字");
			return;
		}else{
			$("#form1").submit();
		}
	}
	</script>
</head>

<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>修改文章分类</span>
		</h4>
	</div>
	<div class="mt20">
	<form action="${ctx}/admin/blog/updateArticleClassify" method="post" id="form1">
		<table class="commonTab01" width="100%" cellspacing="0"
			cellpadding="0" border="0">
			<thead>
				<tr>
					<th align="left" colspan="2"><span> 修改文章分类 <tt
								class="c_666 ml20 fsize12">
								（ <font color="red">*</font> 为必填项）
							</tt>
					</span></th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td width="20%" align="center"><font color="red">*</font> 文章分类</td>
				<td width="80%"><input type="text" value="${artclassify.name}" name="artclassify.name"></td>
					</tr>
					<tr>
				<td width="20%" align="center"><font color="red">*</font> 分类排序值</td>
				<td width="80%"><input type="text" value="${artclassify.sort}" name="artclassify.sort"></td>
					</tr>
					<tr>
				<td width="20%" align="center"><font color="red">*</font> 分类添加时间</td>
				<td width="80%"><fmt:formatDate type="both" value="${artclassify.addTime}"></fmt:formatDate></td>
					</tr>
					<tr>
				<td align="center" colspan="2">
				<input type="hidden" name="artclassify.artId" value="${artclassify.artId}">
				<input class="btn btn-danger" type="button" value="提交" onclick="artSub()">
				<input class="btn ml10" type="button" onclick="history.go(-1)" value="返回">
				</td>
			</tr>
			</tbody>
		</table>
</form>
	</div>
</body>

</html>