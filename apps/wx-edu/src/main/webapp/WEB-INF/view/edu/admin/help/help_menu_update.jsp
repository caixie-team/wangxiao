<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
<script type="text/javascript">
$(function(){
	$("#menuName").keyup(function(){
		$("#menuNameCount").text($.trim($(this).val()).length);
	});
	//初始化 Kindeditor
	KindEditor.create('#content',{
	   resizeType : 1,
	   allowPreviewEmoticons : false,
	   allowUpload : true,
	   syncType : 'auto',
	   urlType : 'absolute',
	   uploadJson : '<%=keImageUploadUrl%>&param=help',
		  afterBlur : function() {
			this.sync();
		  },
	   allowFileManager : false,
	   items : ['source','cut', 'copy', 'paste','plainpaste','|','fontname', 'fontsize', '|','forecolor', 'hilitecolor',
				'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright','justifyfull',
				'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
				'image', 'link']
	});
});
				

function updateSubmit(){
	if($("#menuName").val()==null||$("#menuName").val()==''){
		dialogFun("帮助菜单编辑","请填写菜单名称",0);
		return;
	}
	var parentId='${helpMenu.parentId}';
	if(parentId>0){
		if($("#menuOne").val()==0){
			dialogFun("帮助菜单编辑","请选择一级菜单",0);
			return;
		}
		if($("#content").val()==null||$("#content").val()==''){
			dialogFun("帮助菜单编辑","请填写帮助内容",0);
			return;
		}
	}
	if(isNaN($("#sort").val())){
		dialogFun("帮助菜单编辑","排序只能为数字",0);
		return;
	}
	$("#updateForm").submit();
}
function notE(){
	var _this = $("#sort");
	if(isEmpty(_this.val())){
		_this.val(0);
	}else {
		_this.val(_this.val().replace(/\D/g,""));
	}
}
</script>
</head>
<body>
<form action="${ctx}/admin/helpMenu/update" method="post" id="updateForm" class="am-form">
		<input type="hidden" value="${helpMenu.id}" name="helpMenu.id"/>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">帮助中心管理</strong> / <small>帮助菜单编辑</small></div>
</div>
<hr/>
<div class="mt20">
<div class="am-tab-panel am-fade am-active am-in">
	   <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;选择一级菜单： </div>
			<div class="am-u-sm-8 am-u-md-4">
			<select id="menuOne" name="helpMenu.parentId" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}">
				<option value="0">--请选择--</option>
				<c:forEach items="${helpMenus}" var="helpMenuOne">
					<option value="${helpMenuOne.id}" <c:if test="${helpMenuOne.id==helpMenu.parentId}">selected="selected"</c:if>>${helpMenuOne.name}</option>
				</c:forEach>
			</select>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top" <c:if test="${helpMenu.parentId<=0}">style="display: none"</c:if>>
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;菜单名称： </div>
			<div class="am-u-sm-8 am-u-md-4">
				<input id="menuName" type="text" name="helpMenu.name" maxlength="9" value="${helpMenu.name}" class="{required:true,number:true}"/>
				<em id="menuNameCount" style="color: #ff0000;">0</em>/9
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;帮助内容： </div>
			<div class="am-u-sm-8 am-u-md-4">
				<textarea id="content" name="helpMenu.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;">${helpMenu.content}</textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;排序： </div>
			<div class="am-u-sm-8 am-u-md-4">
				<input id="sort" type="text" name="helpMenu.sort" value="${helpMenu.sort}" onkeyup="notE()"/>
				<font color="red">倒序</font>
				</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;外链： </div>
			<div class="am-u-sm-8 am-u-md-4">
				<input id="linkBuilding" type="text" name="helpMenu.linkBuilding" value="${helpMenu.linkBuilding }" />
				<font color="red">(选填)</font>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp; </div>
			<div class="am-u-sm-8 am-u-md-9" style="text-align: center;">
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