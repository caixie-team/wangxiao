<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改积分模板</title>

<script type="text/javascript">
function submit(){
	var score=$("#score").val();//获得分数
	var name=$("#name").val();//获得模板名称
	if(name==null||name.trim()==''){
		dialogFun("修改积分","请输入模板名称",0);
		return;
	}
	if(score=null||score.trim()==''){//验证为空
		dialogFun("修改积分","请输入分数",0);
		return;
	}
	if(!/^[0-9]*$/.test($("#score").val())){//验证数字
		dialogFun("修改积分","请输入数字",0);
		return;
	}
	$("#updateIntegralTemplateForm").submit();
}
</script> 
</head>
<body >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">积分模板管理</strong> / <small>积分模板</small>
		</div>
	</div>
	<hr/>
	<div class="am-tab-panel am-fade am-active am-in">
<form  action="${ctx}/admin/integral/template/update" method="post" id="updateIntegralTemplateForm" class="am-form">
<input name="userIntegralTemplate.id" type="hidden" value="${userIntegralTemplate.id}"/>
 <div class="am-g am-margin-top">
		<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;模板名称</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" name="userIntegralTemplate.name" value="${userIntegralTemplate.name}" id="name" class="am-input-sm" />
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
		<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;使用类型</div>
			<div class="am-u-sm-8 am-u-md-4">
			<select name="userIntegralTemplate.type" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"  style="display: none;">
				<option value="0">学员</option>
				</select>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;使用场景 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${userIntegralTemplate.keyword}" readonly="readonly" name="userIntegralTemplate.keyword"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;功能分数 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" value="${userIntegralTemplate.showScore}" name="userIntegralTemplate.score" id="score" class="am-input-sm" />
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
			<div class="am-u-sm-8 am-u-md-4">
			 <a class="am-btn am-btn-danger"  onclick="submit()">提交</a>
			 <a class="am-btn am-btn-success" title="返 回" href="javascript:history.go(-1);">返 回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
</form>
</div>
</body>
</html>
