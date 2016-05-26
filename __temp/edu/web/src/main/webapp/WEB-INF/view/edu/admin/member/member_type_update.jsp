<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>会员类型修改</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">
function updateSubmit(){
	if($("#title").val()==null||$("#title").val()==''){
		alert("请填写名称");
		return ;
	}
	$("#updateForm").submit();
}
</script>
</head>
<body >
	
	<div class="page_head">
		<h4><em class="icon14 i_01"></em>&nbsp;<span>会员类型管理</span> &gt; <span>会员类型修改</span> </h4>
	</div>
		<!-- /tab4 begin -->
		<div class="mt20">
			<div class="commonWrap">
			<form action="${ctx}/admin/membertype/update" method="post" id="updateForm">
			<input type="hidden" name="memberType.id" value="${ memberType.id}"/>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
				<thead>
					<tr>
						<th align="left" colspan="2"><span>会员类型基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;名称 </td>
						<td>
							<input type="text" name="memberType.title" id="title" value="${memberType.title }" class="{required:true}"/>
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
