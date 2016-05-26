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
				
function showMenu(flag)
{
	if(flag==2)
	{
		$(".menuTwo").show();
	}else
	{
		$("#menuOne").val(0);
		KindEditor.html('#content', '');//清空文本编辑器内容
		$(".menuTwo").hide();
	}
}
function addSubmit(){
	if($("#menuName").val()==null||$("#menuName").val()==''){
		dialogFun("添加帮助菜单","请填写菜单名称",0);
		return;
	}
	var flag=$('input[name="menuChoose"]:eq(1)').prop("checked");
	if(flag){
		if($("#menuOne").val()==0){
			dialogFun("添加帮助菜单","请选择一级菜单",0);
			return;
		}
		if($("#content").val()==null||$("#content").val()==''){
			dialogFun("添加帮助菜单","请填写帮助内容",0);
			return;
		}
	}
	if(isNaN($("#sort").val())){
		dialogFun("添加帮助菜单","排序只能为数字",0);
		return;
	}
	$("#addForm").submit();
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
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">帮助中心管理</strong> / <small>新建帮助菜单</small>
		</div>
	</div>
	<hr/>
	<!-- /tab4 begin -->
	<div class="mt20">
		<form action="${ctx}/admin/helpMenu/add" method="post" id="addForm" class="am-form" >
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;级别选择：</div>
			<div class="am-u-sm-8 am-u-md-4">
			 	<label class=" am-radio-inline">
				<input onclick="showMenu(1)" type="radio" name="menuChoose" data-am-ucheck  checked="checked"/>一级菜单
				</label>
				<font style="font-family: '宋体', simsun;">&nbsp;&nbsp;</font>
				<label class=" am-radio-inline">
					<input onclick="showMenu(2)" type="radio" data-am-ucheck name="menuChoose"/>二级菜单
				</label>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top menuTwo" style="display: none">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"> <font color="red">*</font>&nbsp;选择一级菜单： </div>
			<div class="am-u-sm-8 am-u-md-4">
			<select id="menuOne" name="helpMenu.parentId" data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary'}"  style="display: none;" class="am-input-sm">
							<option value="0">--请选择--</option>
							<c:forEach items="${helpMenus}" var="helpMenuOne">
								<option value="${helpMenuOne.id}">${helpMenuOne.name}</option>
							</c:forEach>
						</select>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;菜单名称： </div>
			<div class="am-u-sm-8 am-u-md-4">
				<input id="menuName" type="text" name="helpMenu.name" maxlength="9" class="am-input-sm" />
						<em id="menuNameCount" style="color: #ff0000;">0</em>/9
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;帮助内容：</div>
			<div class="am-u-sm-8 am-u-md-4">
						<textarea id="content" name="helpMenu.content" cols="100" rows="8" style="width:560px;height:365px;visibility:hidden;" class="am-input-sm" ></textarea>
			<br/>
			（注：一级菜单时内容为非必填项）
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;排序：</div>
			<div class="am-u-sm-8 am-u-md-4">
				<input id="sort" type="text" name="helpMenu.sort" onkeyup="notE()" value="0" class="am-input-sm" />
						<font color="red">倒序</font>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;外链：</div>
			<div class="am-u-sm-8 am-u-md-4">
					<input id="linkBuilding" type="text" name="helpMenu.linkBuilding" value="" />
						<font color="red">(选填)</font>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
			<div class="am-u-sm-8 am-u-md-4">
			<a class="am-btn am-btn-danger" type="butten" onclick="addSubmit()" href="javascript:void(0);">提交</a>
			<a class="am-btn am-btn-success" title="返 回" href="javascript:history.go(-1);">返 回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		</form>
	</div>
</body>
</html>