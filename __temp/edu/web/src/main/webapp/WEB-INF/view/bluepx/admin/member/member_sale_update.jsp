<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>会员商品修改</title>

<script type="text/javascript">
function updateSubmit(){
	if($("#name").val()==null||$("#name").val()==''){
		alert("请填写名称");
		return ;
	}
	if($("#type").val()==null||$("#type").val()==0){
		alert("请选择会员类型");
		return ;
	}
	if($("#price").val()==null||$("#price").val()==''){
		alert("请填写会员价格");
		return ;
	}
	if($("#days").val()==null||$("#days").val()==''){
		alert("请填写开通天数");
		return ;
	}
	$("#updateForm").submit();
}
</script>
</head>
<body >
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>会员商品管理</span> &gt; <span>会员商品修改</span> </h4>
	</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
			<form action="${ctx}/admin/membersale/update" method="post" id="updateForm">
			<input type="hidden" name="memberSale.id" value="${memberSale.id }"/>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th align="left" colspan="2"><span>会员商品基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;名称 </td>
						<td>
							<input type="text" name="memberSale.name" id="name" value="${memberSale.name }" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;类型 </td>
						<td>
							<select name="memberSale.type" id="type">
								<option value="0">--请选择--</option>
								<c:forEach items="${memberTypes }" var="memberType">
									<option value="${memberType.id }"  <c:if test="${memberType.id==memberSale.type }">selected="selected"</c:if>>${memberType.title }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;价格 </td>
						<td>
							<input type="text" name="memberSale.price" id="price" value="${memberSale.price }" class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;开通天数 </td>
						<td>
							<input type="text" name="memberSale.days" id="days" value="${memberSale.days }"  class="{required:true}"/>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;排序</td>
						<td>
							<input type="text" name="memberSale.sort" value="${memberSale.sort }" class="{required:true}"/><font color="red">倒序</font>
						</td>
					</tr>
					<tr>
						<td align="center">&nbsp;描述</td>
						<td>
							<textarea rows="5" cols="70" name="memberSale.description" id="description" class="{required:true}" >${memberSale.description }</textarea>
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
		</div>
		<!-- /tab4 end -->
		
</body>
</html>
