<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新小组列表</title>
	<script type="text/javascript">
	 $(function(){
	 	$("#groupListId").val(${disGroup.disclassifyId});
	 });
	function tijiao(){
		var disname=$("input[name='disGroup.name']").val().trim();
		var disintroduction=$("#disintroduction").val().trim();
		var sort =$("input[name='disGroup.sort']").val().trim();
		if(disname==''||disname==null){
			alert("小组名称不能为空");
			return;
		}else if(disname.length>24){
			alert("小组名称不得超过24个字");
			return;
		}else if(disintroduction==''||disintroduction==null){
			alert("小组简介不能为空");
			return;
		}else if(disintroduction.length>200){
			alert("小组简介不能超过200个字");
			return;
		}
		else if(!/^[0-9]*$/.test(sort)){
			alert("请输入数字");
			return;
		}else{
			$("#form1").submit();
		}
	}
	</script>
</head>

<body  >
		<form action="${ctx}/admin/disgroup/updateDisGroupById"
			method="post" id="form1">
			<input type="hidden" name="disGroup.disclassifyId" value="${disGroup.disclassifyId}">
			<table class="commonTab01" width="100%" cellspacing="0"
				cellpadding="0" border="0">
				<thead>
					<tr>
						<th align="left" colspan="2"><span> 修改小组信息<tt
									class="c_666 ml20 fsize12">
									（ <font color="red">*</font> 为必填项）
								</tt>
						</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="20%" align="center"><font color="red">*</font> 小组名称</td>
						<td width="80%">
							 <input type="text" value="${disGroup.name}"
							name="disGroup.name"> 
						</td>

					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>小组简介</td>
						<td width="80%">
							 <textarea name="disGroup.introduction" style="width: 538px; height: 230px;" id="disintroduction">${disGroup.introduction}</textarea>
						</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>分类</td>
						<td width="80%">
							<select id="groupListId" class="commSelect" disabled="disabled">
								<option value="0">--请选择分类--</option>
								<c:forEach items="${disGroupList}" var="dgl">
									<option value="${dgl.id}">${dgl.name}</option>
								</c:forEach>
							</select>						
						</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>小组成员数</td>
						<td width="80%">${disGroup.memberNum }</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>创建时间</td>
						<td width="80%"><fmt:formatDate type="both"
								value="${disGroup.createTime }" ></fmt:formatDate></td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>组长</td>
						<td width="80%">${disGroup.showName }</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>小组文章数</td>
						<td width="80%">${disGroup.articleCounts }</td>
					</tr>
					<tr>
						<td width="20%" align="center"><font color="red">*</font>可设置排序值</td>
						<td width="80%"><input type="text" value="${disGroup.sort}"
							name="disGroup.sort"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="hidden"
							name="disGroup.id" value="${disGroup.id }"> <input
							class="btn btn-danger" type="button" value="提交" onclick="tijiao()"> <input
							class="btn ml10" type="button" onclick="history.go(-1)"
							value="返回"></td>
					</tr>
				</tbody>
			</table>
		</form>

</body>

</html>