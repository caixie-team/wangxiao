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
		dialogFun("会员修改 ","请填写名称",0);
		return ;
	}
	$("#updateForm").submit();
}
</script>
</head>
<body >
<div class="am-cf">
	      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">会员类型管理</strong> / <small>会员类型修改</small></div>
</div>	
<hr/>
		 <!-- /tab4 begin -->
<form action="${ctx}/admin/membertype/update" method="post" id="updateForm" class="am-form">
<input type="hidden" name="memberType.id" id="id" value="${memberType.id }"/>
<div class="mt20">
<div class="am-tab-panel am-fade am-active am-in">
		<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;名称 </div>
					<div class="am-u-sm-8 am-u-md-4">
					<input type="text" name="memberType.title" id="title" value="${memberType.title }"class="am-input-sm" />
					</div>
					<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
					<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp; </div>
					<div class="am-u-sm-8 am-u-md-4" align="center">
					<a class="am-btn am-btn-danger" title="提 交" href="javascript:updateSubmit()">提 交</a>
					<a class="am-btn am-btn-success" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</div>
					<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
</div>
</div>
</form>
</body>
</html>
	